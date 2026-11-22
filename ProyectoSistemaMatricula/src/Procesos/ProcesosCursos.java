package Procesos;

import DTO.CursoDTO;
import Formatos.ManejadorTablas;
import Formatos.Mensajes;
import Vista.FrmGestionCursos;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ProcesosCursos {
    
    public static void Presentacion(FrmGestionCursos f1) {
        f1.setTitle("Gestión de Cursos");    
        f1.setVisible(true);
    }    

    public static void Estado1(FrmGestionCursos f1) {
        f1.btnGuardar.setEnabled(true);
        f1.btnBuscar.setEnabled(true);
        f1.btnEditar.setEnabled(false);
        f1.btnEliminar.setEnabled(false);
    }

    public static void Estado2(FrmGestionCursos f1) {
        f1.btnGuardar.setEnabled(false);
        f1.btnBuscar.setEnabled(true);
        f1.btnEditar.setEnabled(true);
        f1.btnEliminar.setEnabled(true);
    }

    public static void LimpiarEntradas(FrmGestionCursos f1) {
        f1.txtBuscarCurso.setText("");
        f1.txtNombre.setText("");
        f1.cbxGrado.setSelectedIndex(0);
        f1.cbxArea.setSelectedIndex(0);
        f1.txtDescripcion.setText("");
        f1.txtBuscarCurso.requestFocus();
    }

    public static CursoDTO LeerDatos(FrmGestionCursos f1) {
        CursoDTO curso = new CursoDTO();
        curso.setNombre(f1.txtNombre.getText());
        curso.setGrado(f1.cbxGrado.getSelectedItem().toString());
        curso.setArea(f1.cbxArea.getSelectedItem().toString());
        curso.setDescripcion(f1.txtDescripcion.getText());
        return curso;
    } 

    public static void MostrarCursos(FrmGestionCursos f1, ArrayList<CursoDTO> lista) {
        String[] titulos = {"ID", "NOMBRE", "GRADO", "ÁREA", "DESCRIPCIÓN"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        for(int i = 0; i < lista.size(); i++) {
            Object[] fila = {
                i + 1,
                lista.get(i).getNombre(),
                lista.get(i).getGrado(),
                lista.get(i).getArea(),
                lista.get(i).getDescripcion()
            };
            modelo.addRow(fila);
        }
        
        f1.tblCurso.setModel(modelo);
        ManejadorTablas.FormatoTablaCursos(f1.tblCurso);
    }
}