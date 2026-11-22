package Controlador;

import Procesos.ProcesosHorarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Formatos.*;
import DTO.HorarioDTO;
import Vista.FrmGestionHorarios;
import DAO.*;
import Util.ImpresorTabla;

public class ControladorHorarios implements ActionListener {
    FrmGestionHorarios vista;
    HorarioDAO crud;
    HorarioDTO horario;
    
    public ControladorHorarios(FrmGestionHorarios f1) { 
        vista = f1;
        vista.btnBuscar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnEditar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        vista.btnImprimir.addActionListener(this);
        ProcesosHorarios.Presentacion(f1);
        ActualizarForma();
    }
    
    void ActualizarForma() {
        crud = new HorarioDAO();
        ProcesosHorarios.Estado1(vista);
        ProcesosHorarios.LimpiarEntradas(vista);
        ProcesosHorarios.MostrarHorarios(vista, crud.ListaHorarios());
    }     
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnBuscar) {
            String curso = vista.txtBuscar.getText();
            if(curso.isEmpty()) {
                Mensajes.M1("Ingrese curso a buscar");
                return;
            }
            
            crud = new HorarioDAO();
            horario = crud.ConsultarRegistro(curso);
            if(horario == null) {
                Mensajes.M1("El horario para el curso "+curso+" no existe");
            } else {
                vista.cbxCurso.setSelectedItem(horario.getCurso());
                vista.txtDocente.setText(horario.getDocente());
                vista.cbxSalon.setSelectedItem(horario.getSalon());
                vista.cbxDia.setSelectedItem(horario.getDia());
                vista.cbxHoraInicio.setSelectedItem(String.valueOf(horario.getHoraInicio()));
                vista.cbxHoraFin.setSelectedItem(String.valueOf(horario.getHoraFin()));
                ProcesosHorarios.Estado2(vista);
            }
        }
        if(e.getSource() == vista.btnGuardar) {
            horario = ProcesosHorarios.LeerDatos(vista);
            crud = new HorarioDAO();
            crud.InsertarHorario(horario);
            ActualizarForma();      
        }
        if(e.getSource() == vista.btnEditar) {
            horario = ProcesosHorarios.LeerDatos(vista);
            horario.setCurso(vista.cbxCurso.getSelectedItem().toString());
            crud = new HorarioDAO();
            crud.ActualizarRegistro(horario);
            ActualizarForma(); 
        }
        if(e.getSource() == vista.btnEliminar) {
            int respuesta = Mensajes.M3("Confirmar", "¿Desea eliminar el registro?");
            if(respuesta == 0) {
                String curso = vista.cbxCurso.getSelectedItem().toString();
                crud = new HorarioDAO();
                crud.EliminarRegistro(curso);
                ActualizarForma();
            }
        }
        if(e.getSource() == vista.btnLimpiar) {
            ActualizarForma();
        }
        if(e.getSource() == vista.btnImprimir) {
            new ImpresorTabla(vista.tblHorarios, "Lista de Horarios").imprimirTabla();
        }
    }    
}