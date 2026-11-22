package Procesos;

import DAO.CursoDAO;
import DAO.MatriculaDAO;
import DTO.CursoDTO;
import DTO.MatriculaDTO;
import Vista.FrmProcesoMatricula;
import Formatos.Mensajes;
import Formatos.ManejadorTablas;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;

public class ProcesosMatricula {

    public static void Presentacion(FrmProcesoMatricula f) {
        f.setTitle("Proceso de Matrícula");
        f.setVisible(true);
        MostrarCursosDisponibles(f);  
    }
    
    public static void MostrarCursosDisponibles(FrmProcesoMatricula f) {
        CursoDAO cursoDAO = new CursoDAO();
        ArrayList<CursoDTO> cursos = cursoDAO.ListaCursos();

        String[] titulos = {"SEL.", "CURSO", "GRADO", "ÁREA", "CUPOS", "ID"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 ? Boolean.class : String.class;  
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0; 
            }
        };

        for (CursoDTO curso : cursos) {
            Object[] fila = {
                false,                   
                curso.getNombre(),         
                curso.getGrado(),          
                curso.getArea(),           
                curso.getCupos(),                  
                curso.getId()              
            };
            modelo.addRow(fila);
        }

        f.tblCursosDisponibles.setModel(modelo);
        f.tblCursosDisponibles.getColumnModel().getColumn(5).setMinWidth(0);  
        f.tblCursosDisponibles.getColumnModel().getColumn(5).setMaxWidth(0);
        
        ManejadorTablas.FormatoTablaCursos(f.tblCursosDisponibles);
    }

    public static void GenerarCodigoMatricula(FrmProcesoMatricula f) {
        MatriculaDAO dao = new MatriculaDAO();
        f.txtCodigo.setText(dao.GenerarCodigoMatricula());
    }

    public static void CalcularTotalPagar(FrmProcesoMatricula f) {
        double total = 0.0;
        DefaultTableModel modelo = (DefaultTableModel) f.tblCursosDisponibles.getModel();
        
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if ((Boolean) modelo.getValueAt(i, 0)) {  
                total += 150.0;  
            }
        }
        
        f.txtTotalPagar.setText(String.format("S/ %.2f", total));
    }

    public static ArrayList<Integer> ObtenerCursosSeleccionados(FrmProcesoMatricula f) {
        ArrayList<Integer> ids = new ArrayList<>();
        DefaultTableModel modelo = (DefaultTableModel) f.tblCursosDisponibles.getModel();
        
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if ((Boolean) modelo.getValueAt(i, 0)) { 
                ids.add(Integer.parseInt(modelo.getValueAt(i, 5).toString())); 
            }
        }
        
        return ids;
    }
    
    public static MatriculaDTO LeerDatos(FrmProcesoMatricula f) {
        MatriculaDTO m = new MatriculaDTO();
        m.setCodigo(f.txtCodigo.getText());
        m.setEstudianteDni(f.txtBuscar.getText());
        m.setFecha(f.jcFecha.getDate());
        m.setGrado(f.cbxGrado.getSelectedItem().toString());
        m.setSeccion(f.cbxSeccion.getSelectedItem().toString());
        m.setTotalPagar(Double.parseDouble(f.txtTotalPagar.getText().replace("S/ ", "")));
        return m;
    }
    
    public static void LimpiarFormulario(FrmProcesoMatricula f) {
        f.txtCodigo.setText("");
        f.txtBuscar.setText("");
        f.txtTotalPagar.setText("");
        f.cbxGrado.setSelectedIndex(0);
        f.cbxSeccion.setSelectedIndex(0);
        f.jcFecha.setDate(null);
    
        DefaultTableModel modelo = (DefaultTableModel) f.tblCursosDisponibles.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.setValueAt(false, i, 0); 
        }
        f.btnGuardar.setEnabled(false);
    }   
}