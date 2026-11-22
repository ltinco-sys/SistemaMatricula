package Procesos;

import DTO.DocenteDTO;
import Formatos.ManejadorTablas;
import Formatos.Mensajes;
import Vista.FrmGestionDocentes;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ProcesosDocentes {
    
    public static void Presentacion(FrmGestionDocentes f1) {
        f1.setTitle("Gestión de Docentes");    
        f1.setVisible(true);
    }    

    public static void Estado1(FrmGestionDocentes f1) {
        f1.btnGuardar.setEnabled(true);
        f1.btnBuscar.setEnabled(true);
        f1.btnEditar.setEnabled(false);
        f1.btnEliminar.setEnabled(false);
    }

    public static void Estado2(FrmGestionDocentes f1) {
        f1.btnGuardar.setEnabled(false);
        f1.btnBuscar.setEnabled(true);
        f1.btnEditar.setEnabled(true);
        f1.btnEliminar.setEnabled(true);
    }

    public static void LimpiarEntradas(FrmGestionDocentes f1) {
        f1.txtBuscarDocente.setText("");
        f1.txtNombres.setText("");
        f1.txtApellidos.setText("");
        f1.txtDni.setText("");
        f1.spnAñosExp.setValue(0);
        f1.txtEspecialidad.setText("");
        f1.txtBuscarDocente.requestFocus();
    }

    public static DocenteDTO LeerDatos(FrmGestionDocentes f1) {
        DocenteDTO docente = new DocenteDTO();
        docente.setNombres(f1.txtNombres.getText());
        docente.setApellidos(f1.txtApellidos.getText());
        docente.setDni(f1.txtDni.getText());
        docente.setAniosExperiencia((Integer)f1.spnAñosExp.getValue());
        docente.setEspecialidad(f1.txtEspecialidad.getText());
        if (f1.fotoSeleccionada != null) {
        try {
            byte[] bytesFoto = java.nio.file.Files.readAllBytes(f1.fotoSeleccionada.toPath());
            docente.setFoto(bytesFoto);
        } catch (IOException e) {
            Mensajes.M1("No se pudo leer la foto");
        }
    }
        return docente;
        
    } 

    public static void MostrarDocentes(FrmGestionDocentes f1, ArrayList<DocenteDTO> lista) {
        String[] titulos = {"ID", "NOMBRES", "APELLIDOS", "DNI", "AÑOS EXP.", "ESPECIALIDAD"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        for(int i = 0; i < lista.size(); i++) {
            Object[] fila = {
                i + 1,
                lista.get(i).getNombres(),
                lista.get(i).getApellidos(),
                lista.get(i).getDni(),
                lista.get(i).getAniosExperiencia(),
                lista.get(i).getEspecialidad()
            };
            modelo.addRow(fila);
        }
        
        f1.tblDocentes.setModel(modelo);
        ManejadorTablas.FormatoTablaDocentes(f1.tblDocentes);
    }
}