package Formatos;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class ManejadorTablas {
    
    public static void FormatoTablaEstudiantes(JTable tabla) {
        AnchoColumnas(tabla, 0, 50);   // Nro
        JustificarCelda(tabla, 0);
        AnchoColumnas(tabla, 1, 150);  // Nombres
        AnchoColumnas(tabla, 2, 150);  // Apellidos
        AnchoColumnas(tabla, 3, 80);   // DNI
        JustificarCelda(tabla, 3);
        AnchoColumnas(tabla, 4, 100);  // Fecha Nacimiento
        JustificarCelda(tabla, 4);
        AnchoColumnas(tabla, 5, 200);  // Dirección
    }
    
    public static void FormatoTablaDocentes(JTable tabla) {
        AnchoColumnas(tabla, 0, 50);   // Nro
        JustificarCelda(tabla, 0);
        AnchoColumnas(tabla, 1, 150);  // Nombres
        AnchoColumnas(tabla, 2, 150);  // Apellidos
        AnchoColumnas(tabla, 3, 80);   // DNI
        JustificarCelda(tabla, 3);
        AnchoColumnas(tabla, 4, 80);   // Años Exp.
        JustificarCelda(tabla, 4);
        AnchoColumnas(tabla, 5, 150);  // Especialidad
    }
    
    public static void FormatoTablaSalones(JTable tabla) {
        AnchoColumnas(tabla, 0, 50);   // Nro
        JustificarCelda(tabla, 0);
        AnchoColumnas(tabla, 1, 100);  // Número
        JustificarCelda(tabla, 1);
        AnchoColumnas(tabla, 2, 50);   // Piso
        JustificarCelda(tabla, 2);
        AnchoColumnas(tabla, 3, 70);   // Capacidad
        JustificarCelda(tabla, 3);
        AnchoColumnas(tabla, 4, 80);   // Proyector
        JustificarCelda(tabla, 4);
        AnchoColumnas(tabla, 5, 80);   // Aire Acond.
        JustificarCelda(tabla, 5);
    }
    
    public static void FormatoTablaCursos(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(30);  // Nro
        tabla.getColumnModel().getColumn(1).setPreferredWidth(120); // Nombre
        tabla.getColumnModel().getColumn(2).setPreferredWidth(80);  // Grado
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100); // Área
        tabla.getColumnModel().getColumn(4).setPreferredWidth(200); // Descripción
    }
    
    public static void FormatoTablaHorarios(JTable tabla) {
        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);  // Nro
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150); // Curso
        tabla.getColumnModel().getColumn(2).setPreferredWidth(150); // Docente
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100); // Salón
        tabla.getColumnModel().getColumn(4).setPreferredWidth(70);  // Día
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100); // Hora inicio
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100); // Hora fin
    }

    
    private static void AnchoColumnas(JTable t, int numcolumna, int ancho) {
        TableColumn column = t.getColumnModel().getColumn(numcolumna);
        column.setPreferredWidth(ancho);       
    }
    
    private static void JustificarCelda(JTable t, int numcolumna) {
        DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
        modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
        t.getColumnModel().getColumn(numcolumna).setCellRenderer(modelocentrar);       
    }
}