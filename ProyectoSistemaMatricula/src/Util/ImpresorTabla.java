package Util;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;
import javax.swing.table.*;

public class ImpresorTabla implements Printable {
    private JTable tabla;
    private String titulo;
    private float scaleFactor = 1.0f;

    public ImpresorTabla(JTable tabla, String titulo) {
        this.tabla = tabla;
        this.titulo = titulo;
    }

    public void imprimirTabla() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error al imprimir: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, 
                "La impresión fue cancelada", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) 
            throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        double pageWidth = pageFormat.getImageableWidth();
        int tableWidth = tabla.getWidth();
        
        if (tableWidth > pageWidth) {
            scaleFactor = (float) (pageWidth / tableWidth);
            g2d.scale(scaleFactor, scaleFactor);
        }

        Font headerFont = new Font("SansSerif", Font.BOLD, 12);
        Font dataFont = new Font("SansSerif", Font.PLAIN, 10);
        
        g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
        String title = this.titulo != null ? this.titulo : "";
        int titleWidth = g2d.getFontMetrics().stringWidth(title);
        int centerX = (int) (tableWidth / 2 - titleWidth / 2);
        g2d.drawString(title, centerX, 20);
        g2d.translate(0, 30);

        int rowHeight = tabla.getRowHeight();
        int headerHeight = rowHeight + 10;
        
        g2d.setFont(headerFont);
        TableColumnModel columnModel = tabla.getColumnModel();
        
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRect(0, 0, tableWidth, headerHeight);
        
        g2d.setColor(Color.BLACK);
        int x = 0;
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            int colWidth = columnModel.getColumn(i).getWidth();
            
            g2d.drawRect(x, 0, colWidth, headerHeight);
            
            String header = tabla.getColumnName(i);
            int textWidth = g2d.getFontMetrics().stringWidth(header);
            g2d.drawString(header, x + (colWidth - textWidth) / 2, 15);
            
            x += colWidth;
        }

        g2d.setFont(dataFont);
        int y = headerHeight;
        
        for (int row = 0; row < tabla.getRowCount(); row++) {
            if (row % 2 == 0) {
                g2d.setColor(Color.WHITE);
            } else {
                g2d.setColor(new Color(240, 240, 240));
            }
            g2d.fillRect(0, y, tableWidth, rowHeight);
            g2d.setColor(Color.BLACK);
            x = 0;
            for (int col = 0; col < tabla.getColumnCount(); col++) {
                int colWidth = columnModel.getColumn(col).getWidth();
                
                g2d.drawRect(x, y, colWidth, rowHeight);
                
                Object value = tabla.getValueAt(row, col);
                if (value != null) {
                    String text = value.toString();
                    int textWidth = g2d.getFontMetrics().stringWidth(text);
                    
                    if (textWidth > colWidth - 4) {
                        text = text.substring(0, Math.min(10, text.length())) + "...";
                        textWidth = g2d.getFontMetrics().stringWidth(text);
                    }
                    
                    g2d.drawString(text, x + (colWidth - textWidth) / 2, y + 15);
                }
                
                x += colWidth;
            }
            y += rowHeight;
        }

        return PAGE_EXISTS;
    }
}