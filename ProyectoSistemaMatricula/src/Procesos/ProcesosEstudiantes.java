package Procesos;

import DTO.EstudianteDTO;
import Formatos.ManejadorTablas;
import Formatos.Mensajes;
import Vista.FrmGestionEstudiantes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProcesosEstudiantes {
    
    public static void Presentacion(FrmGestionEstudiantes f1) {
        f1.setTitle("Gestión de Estudiantes");    
        f1.setVisible(true);
    }    

    public static void Estado1(FrmGestionEstudiantes f1) {
        f1.btnGuardar.setEnabled(true);
        f1.btnBuscar.setEnabled(true);
        f1.btnEditar.setEnabled(false);
        f1.btnEliminar.setEnabled(false);
    }

    public static void Estado2(FrmGestionEstudiantes f1) {
        f1.btnGuardar.setEnabled(false);
        f1.btnBuscar.setEnabled(true);
        f1.btnEditar.setEnabled(true);
        f1.btnEliminar.setEnabled(true);
    }

    public static void LimpiarEntradas(FrmGestionEstudiantes f1) {
        f1.txtBuscarEstudiante.setText("");
        f1.txtNombres.setText("");
        f1.txtApellidos.setText("");
        f1.txtDni.setText("");
        f1.dcFechaNac.setDate(null);
        f1.txtDireccion.setText("");
        f1.txtBuscarEstudiante.requestFocus();
    }

    public static EstudianteDTO LeerDatos(FrmGestionEstudiantes f1) {
        EstudianteDTO estudiante = new EstudianteDTO();
        estudiante.setNombres(f1.txtNombres.getText());
        estudiante.setApellidos(f1.txtApellidos.getText());
        estudiante.setDni(f1.txtDni.getText());
        estudiante.setFechaNacimiento(f1.dcFechaNac.getDate());
        estudiante.setDireccion(f1.txtDireccion.getText());
        if (f1.fotoSeleccionada != null) {
            try {
                byte[] bytesFoto = java.nio.file.Files.readAllBytes(f1.fotoSeleccionada.toPath());
                estudiante.setFoto(bytesFoto);
            } catch (IOException e) {
                Mensajes.M1("No se pudo leer la foto");
            }
        }
        return estudiante;
   }

    public static void MostrarEstudiantes(FrmGestionEstudiantes f1, ArrayList<EstudianteDTO> lista) {
        String[] titulos = {"ID", "NOMBRES", "APELLIDOS", "DNI", "FECHA NAC.", "DIRECCIÓN"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
    
        for(int i = 0; i < lista.size(); i++) {
            Object[] fila = {
                i + 1,
                lista.get(i).getNombres(),
                lista.get(i).getApellidos(),
                lista.get(i).getDni(),
                lista.get(i).getFechaNacimiento(),
                lista.get(i).getDireccion()
            };
            modelo.addRow(fila);
        }
    
        f1.tblEstudiantes.setModel(modelo);
        ManejadorTablas.FormatoTablaEstudiantes(f1.tblEstudiantes);
    }
}