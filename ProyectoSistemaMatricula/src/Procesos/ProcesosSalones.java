package Procesos;

import DTO.SalonDTO;
import Formatos.ManejadorTablas;
import Formatos.Mensajes;
import Vista.FrmGestionSalones;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ProcesosSalones {
    
    public static void Presentacion(FrmGestionSalones f1) {
        f1.setTitle("Gestión de Salones");    
        f1.setVisible(true);
    }    

    public static void Estado1(FrmGestionSalones f1) {
        f1.btnGuardar.setEnabled(true);
        f1.btnBuscar.setEnabled(true);
        f1.btnEditar.setEnabled(false);
        f1.btnEliminar.setEnabled(false);
    }

    public static void Estado2(FrmGestionSalones f1) {
        f1.btnGuardar.setEnabled(false);
        f1.btnBuscar.setEnabled(true);
        f1.btnEditar.setEnabled(true);
        f1.btnEliminar.setEnabled(true);
    }

    public static void LimpiarEntradas(FrmGestionSalones f1) {
        f1.txtBuscarSalon.setText("");
        f1.txtNombre.setText("");
        f1.txtCapacidad.setText("");
        f1.spnPiso.setValue(1);
        f1.chkProyector.setSelected(false);
        f1.chkAire.setSelected(false);
        f1.txtBuscarSalon.requestFocus();
    }

    public static SalonDTO LeerDatos(FrmGestionSalones f1) {
        SalonDTO salon = new SalonDTO();
        salon.setNumero(f1.txtNombre.getText());
        salon.setCapacidad(Integer.parseInt(f1.txtCapacidad.getText()));
        salon.setPiso((Integer)f1.spnPiso.getValue());
        salon.setTieneProyector(f1.chkProyector.isSelected());
        salon.setTieneAire(f1.chkAire.isSelected());
        return salon;
    } 

    public static void MostrarSalones(FrmGestionSalones f1, ArrayList<SalonDTO> lista) {
        String[] titulos = {"ID", "NOMBRE", "PISO", "CAPACIDAD", "PROYECTOR", "AIRE ACOND."};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        
        for(int i = 0; i < lista.size(); i++) {
            Object[] fila = {
                i + 1,
                lista.get(i).getNumero(),
                lista.get(i).getPiso(),
                lista.get(i).getCapacidad(),
                lista.get(i).isTieneProyector() ? "Sí" : "No",
                lista.get(i).isTieneAire() ? "Sí" : "No"
            };
            modelo.addRow(fila);
        }
        
        f1.tblSalones.setModel(modelo);
        ManejadorTablas.FormatoTablaSalones(f1.tblSalones);
    }
}