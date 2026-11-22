package Util;

import Formatos.Mensajes;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import Vista.FrmProcesoMatricula;
import javax.swing.JTable;
import java.awt.Color;
import java.io.FileOutputStream;

public class ExportadorPDF {

    public static void exportarMatriculaComoPDF(FrmProcesoMatricula f, String nombreArchivo) {
        Document documento = new Document();
        
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();
            
            Paragraph titulo = new Paragraph("COLEGIO SAGRADOS CORAZONES", 
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            
            Paragraph subtitulo = new Paragraph("COMPROBANTE DE MATRÍCULA", 
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            subtitulo.setSpacingAfter(20);
            documento.add(subtitulo);
            
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            
            infoTable.addCell(crearCelda("Código:", true));
            infoTable.addCell(crearCelda(f.txtCodigo.getText(), false));
            
            infoTable.addCell(crearCelda("Fecha:", true));
            infoTable.addCell(crearCelda(f.jcFecha.getDate().toString(), false));
            
            infoTable.addCell(crearCelda("Grado:", true));
            infoTable.addCell(crearCelda(f.cbxGrado.getSelectedItem().toString(), false));
            
            infoTable.addCell(crearCelda("Total a Pagar:", true));
            infoTable.addCell(crearCelda(f.txtTotalPagar.getText(), false));
            
            documento.add(infoTable);
            
            Paragraph cursosTitulo = new Paragraph("CURSOS MATRICULADOS", 
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            cursosTitulo.setSpacingBefore(20);
            documento.add(cursosTitulo);
            
            PdfPTable cursosTable = new PdfPTable(3);
            cursosTable.setWidthPercentage(100);
            
            cursosTable.addCell(crearCelda("Curso", true));
            cursosTable.addCell(crearCelda("Grado", true));
            cursosTable.addCell(crearCelda("Área", true));
            
            JTable tabla = f.tblCursosDisponibles;
            for (int i = 0; i < tabla.getRowCount(); i++) {
                if ((Boolean) tabla.getValueAt(i, 0)) {
                    cursosTable.addCell(crearCelda(tabla.getValueAt(i, 1).toString(), false));
                    cursosTable.addCell(crearCelda(tabla.getValueAt(i, 2).toString(), false));
                    cursosTable.addCell(crearCelda(tabla.getValueAt(i, 3).toString(), false));
                }
            }
            
            documento.add(cursosTable);
            documento.close();
            
            Mensajes.M1("PDF generado: " + nombreArchivo);
        } catch (Exception e) {
            Mensajes.M1("Error al generar PDF: " + e.getMessage());
        }
        
    }
    
    private static PdfPCell crearCelda(String texto, boolean isHeader) {
        PdfPCell celda = new PdfPCell(new Phrase(texto));
        celda.setBackgroundColor(isHeader ? new Color(200, 200, 200) : Color.WHITE);
        celda.setPadding(5);
        return celda;
    }
}