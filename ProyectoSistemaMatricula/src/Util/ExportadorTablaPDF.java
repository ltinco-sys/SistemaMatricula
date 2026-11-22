package Util;

import Formatos.Mensajes;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import javax.swing.*;
import java.awt.Color;
import java.io.FileOutputStream;

public class ExportadorTablaPDF {

    public static boolean exportarTablaComoPDF(JTable tabla, String tituloReporte, String nombreSugerido) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new java.io.File(nombreSugerido));
        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();

            Document documento = new Document();

            try {
                PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
                documento.open();

                Paragraph titulo = new Paragraph(tituloReporte,
                        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
                titulo.setAlignment(Element.ALIGN_CENTER);
                titulo.setSpacingAfter(20);
                documento.add(titulo);

                PdfPTable tablaPDF = new PdfPTable(tabla.getColumnCount());
                tablaPDF.setWidthPercentage(100);

                for (int i = 0; i < tabla.getColumnCount(); i++) {
                    PdfPCell celdaEncabezado = new PdfPCell(new Phrase(tabla.getColumnName(i)));
                    celdaEncabezado.setBackgroundColor(new Color(184, 218, 255));
                    celdaEncabezado.setPadding(5);
                    tablaPDF.addCell(celdaEncabezado);
                }

                for (int i = 0; i < tabla.getRowCount(); i++) {
                    for (int j = 0; j < tabla.getColumnCount(); j++) {
                        Object valor = tabla.getValueAt(i, j);
                        PdfPCell celda = new PdfPCell(new Phrase(valor != null ? valor.toString() : ""));
                        celda.setPadding(5);
                        tablaPDF.addCell(celda);
                    }
                }

                documento.add(tablaPDF);
                documento.close();

                Mensajes.M1("PDF generado exitosamente.");
                return true;
            } catch (Exception e) {
                Mensajes.M1("Error al exportar tabla a PDF: " + e.getMessage());
                return false;
            }
        } else {
            Mensajes.M1("Exportación cancelada.");
            return false;
        }
    }
}



