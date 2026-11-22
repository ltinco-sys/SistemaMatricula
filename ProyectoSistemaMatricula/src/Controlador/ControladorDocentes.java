package Controlador;

import Procesos.ProcesosDocentes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Formatos.*;
import DTO.DocenteDTO;
import Vista.FrmGestionDocentes;
import DAO.*;
import Util.ImpresorTabla;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControladorDocentes implements ActionListener {
    FrmGestionDocentes vista;
    DocenteDAO crud;
    DocenteDTO docente;
    
    public ControladorDocentes(FrmGestionDocentes f1) { 
        vista = f1;
        vista.btnBuscar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnEditar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        vista.btnImprimir.addActionListener(this);
        vista.btnImportarFoto.addActionListener(this);
        ProcesosDocentes.Presentacion(f1);
        ActualizarForma();
    }
    
    void ActualizarForma() {
        crud = new DocenteDAO();
        ProcesosDocentes.Estado1(vista);
        ProcesosDocentes.LimpiarEntradas(vista);
        ProcesosDocentes.MostrarDocentes(vista, crud.ListaDocentes());
    }     
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnBuscar) {
            String dni = vista.txtBuscarDocente.getText();
            if(dni.isEmpty()) {
                Mensajes.M1("Ingrese DNI a buscar");
                return;
            }
            
            crud = new DocenteDAO();
            docente = crud.ConsultarRegistro(dni);
            if(docente == null) {
                Mensajes.M1("El docente con DNI "+dni+" no existe");
            } else {
                vista.txtNombres.setText(docente.getNombres());
                vista.txtApellidos.setText(docente.getApellidos());
                vista.txtDni.setText(docente.getDni());
                vista.spnAñosExp.setValue(docente.getAniosExperiencia());
                vista.txtEspecialidad.setText(docente.getEspecialidad());
                if (docente.getFoto() != null) {
                try {
                    ImageIcon icon = new ImageIcon(new ImageIcon(docente.getFoto()).getImage().
                            getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                    vista.lblFoto.setIcon(icon);
                    vista.lblFoto.setText("");
                } catch (Exception ex) {
                    vista.lblFoto.setText("Sin foto");
                }
            } else {
                vista.lblFoto.setIcon(null);
                vista.lblFoto.setText("Sin foto");
            }
                ProcesosDocentes.Estado2(vista);
            }
        }
        if(e.getSource() == vista.btnGuardar) {
            docente = ProcesosDocentes.LeerDatos(vista);
            crud = new DocenteDAO();
            crud.InsertarDocente(docente);
            ActualizarForma();      
        }
        if(e.getSource() == vista.btnEditar) {
            docente = ProcesosDocentes.LeerDatos(vista);
            docente.setDni(vista.txtDni.getText());
            crud = new DocenteDAO();
            crud.ActualizarRegistro(docente);
            ActualizarForma(); 
        }
        if(e.getSource() == vista.btnEliminar) {
            int respuesta = Mensajes.M3("Confirmar", "¿Desea eliminar el registro?");
            if(respuesta == 0) {
                String dni = vista.txtDni.getText();
                crud = new DocenteDAO();
                crud.EliminarRegistro(dni);
                ActualizarForma();
            }
        }
        if(e.getSource() == vista.btnLimpiar) {
            ActualizarForma();
        }
        if(e.getSource() == vista.btnImprimir) {
            new ImpresorTabla(vista.tblDocentes, "Lista de Docentes").imprimirTabla();
        }
        if (e.getSource() == vista.btnImportarFoto) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(filter);

            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedImage img = ImageIO.read(file);
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                    vista.lblFoto.setIcon(icon);
                    vista.lblFoto.setText("");
                    vista.fotoSeleccionada = file; 
                } catch (Exception ex) {
                    Mensajes.M1("Error al cargar imagen: " + ex.getMessage());
                }
            }
        }
    }    
}