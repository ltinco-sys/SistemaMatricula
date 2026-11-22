package Util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JTable;
import java.io.FileOutputStream;
import Formatos.Mensajes;

public class ExportadorTablaExcel {

    public static boolean exportarTablaComoExcelConDialogo(JTable tabla, String nombrePorDefecto) {
        String ruta = UtilArchivos.seleccionarGuardarArchivo(nombrePorDefecto, "xlsx");

        if (ruta == null) {
            // No mostramos mensaje aquí, el controlador manejará esto
            return false; // Exportación cancelada
        }

        try (Workbook libro = new XSSFWorkbook()) {
            Sheet hoja = libro.createSheet("Reporte");

            // Encabezados
            Row filaHeader = hoja.createRow(0);
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                Cell celda = filaHeader.createCell(i);
                celda.setCellValue(tabla.getColumnName(i));
            }

            // Datos
            for (int i = 0; i < tabla.getRowCount(); i++) {
                Row fila = hoja.createRow(i + 1);
                for (int j = 0; j < tabla.getColumnCount(); j++) {
                    Object val = tabla.getValueAt(i, j);
                    fila.createCell(j).setCellValue(val != null ? val.toString() : "");
                }
            }

            // Ajustar ancho de columnas
            for (int i = 0; i < tabla.getColumnCount(); i++) {
                hoja.autoSizeColumn(i);
            }

            try (FileOutputStream fos = new FileOutputStream(ruta)) {
                libro.write(fos);
            }

            Mensajes.M1("Archivo Excel generado correctamente.");
            return true; // Exportación exitosa
        } catch (Exception ex) {
            Mensajes.M1("Error al exportar Excel: " + ex.getMessage());
            return false; // Error en exportación
        }
    }
}


