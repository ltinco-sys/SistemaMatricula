package Controlador;

import Procesos.ProcesosSalones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Formatos.*;
import DTO.SalonDTO;
import Vista.FrmGestionSalones;
import DAO.*;
import Util.ImpresorTabla;

public class ControladorSalones implements ActionListener {
    FrmGestionSalones vista;
    SalonDAO crud;
    SalonDTO salon;
    
    public ControladorSalones(FrmGestionSalones f1) { 
        vista = f1;
        vista.btnBuscar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnEditar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);        
        vista.btnImprimir.addActionListener(this);
        ProcesosSalones.Presentacion(f1);
        ActualizarForma();
    }
    
    void ActualizarForma() {
        crud = new SalonDAO();
        ProcesosSalones.Estado1(vista);
        ProcesosSalones.LimpiarEntradas(vista);
        ProcesosSalones.MostrarSalones(vista, crud.ListaSalones());
    }     
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnBuscar) {
            String numero = vista.txtBuscarSalon.getText();
            if(numero.isEmpty()) {
                Mensajes.M1("Ingrese nombre de salón a buscar");
                return;
            }
            
            crud = new SalonDAO();
            salon = crud.ConsultarRegistro(numero);
            if(salon == null) {
                Mensajes.M1("El salón "+numero+" no existe");
            } else {
                vista.txtNombre.setText(salon.getNumero());
                vista.txtCapacidad.setText(String.valueOf(salon.getCapacidad()));
                vista.spnPiso.setValue(salon.getPiso());
                vista.chkProyector.setSelected(salon.isTieneProyector());
                vista.chkAire.setSelected(salon.isTieneAire());
                ProcesosSalones.Estado2(vista);
            }
        }
        if(e.getSource() == vista.btnGuardar) {
            salon = ProcesosSalones.LeerDatos(vista);
            crud = new SalonDAO();
            crud.InsertarSalon(salon);
            ActualizarForma();      
        }
        if(e.getSource() == vista.btnEditar) {
            salon = ProcesosSalones.LeerDatos(vista);
            salon.setNumero(vista.txtNombre.getText());
            crud = new SalonDAO();
            crud.ActualizarRegistro(salon);
            ActualizarForma(); 
        }
        if(e.getSource() == vista.btnEliminar) {
            int respuesta = Mensajes.M3("Confirmar", "¿Desea eliminar el registro?");
            if(respuesta == 0) {
                String numero = vista.txtNombre.getText();
                crud = new SalonDAO();
                crud.EliminarRegistro(numero);
                ActualizarForma();
            }
        }
        if(e.getSource() == vista.btnLimpiar) {
            ActualizarForma();
        }
        if(e.getSource() == vista.btnImprimir) {
            new ImpresorTabla(vista.tblSalones, "Lista de Salones").imprimirTabla();
        }
    }    
}