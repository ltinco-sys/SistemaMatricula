package Procesos;

import DTO.HorarioDTO;
import Formatos.ManejadorTablas;
import Formatos.Mensajes;
import Vista.FrmGestionHorarios;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ProcesosHorarios {
    
    public static void Presentacion(FrmGestionHorarios f1) {
        f1.setTitle("Gestión de Horarios");    
        f1.setVisible(true);
    }    

    public static void Estado1(FrmGestionHorarios f1) {
        f1.btnGuardar.setEnabled(true);
        f1.btnBuscar.setEnabled(true);
        f1.btnEditar.setEnabled(false);
        f1.btnEliminar.setEnabled(false);
    }

    public static void Estado2(FrmGestionHorarios f1) {
        f1.btnGuardar.setEnabled(false);
        f1.btnBuscar.setEnabled(true);
        f1.btnEditar.setEnabled(true);
        f1.btnEliminar.setEnabled(true);
    }

    public static void LimpiarEntradas(FrmGestionHorarios f1) {
        f1.txtBuscar.setText("");
        f1.cbxCurso.setSelectedIndex(0);
        f1.txtDocente.setText("");
        f1.cbxSalon.setSelectedIndex(0);
        f1.cbxDia.setSelectedIndex(0);
        f1.cbxHoraInicio.setSelectedIndex(0);
        f1.cbxHoraFin.setSelectedIndex(0);
        f1.txtBuscar.requestFocus();
    }

    public static HorarioDTO LeerDatos(FrmGestionHorarios f1) {
        HorarioDTO horario = new HorarioDTO();
        horario.setCurso(f1.cbxCurso.getSelectedItem().toString());
        horario.setDocente(f1.txtDocente.getText());
        horario.setSalon(f1.cbxSalon.getSelectedItem().toString());
        horario.setDia(f1.cbxDia.getSelectedItem().toString());
        horario.setHoraInicio(f1.cbxHoraInicio.getSelectedItem().toString());
        horario.setHoraFin(f1.cbxHoraFin.getSelectedItem().toString());
        return horario;
    } 

    public static void MostrarHorarios(FrmGestionHorarios f1, ArrayList<HorarioDTO> lista) {
        String[] titulos = {"ID", "CURSO", "DOCENTE", "SALÓN", "DÍA", "H. INICIO", "H. FIN"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        for(int i = 0; i < lista.size(); i++) {
            Object[] fila = {
                i + 1,
                lista.get(i).getCurso(),
                lista.get(i).getDocente(),
                lista.get(i).getSalon(),
                lista.get(i).getDia(),
                lista.get(i).getHoraInicio(),
                lista.get(i).getHoraFin()
            };
            modelo.addRow(fila);
        }
        
        f1.tblHorarios.setModel(modelo);
        ManejadorTablas.FormatoTablaHorarios(f1.tblHorarios);
    }
}