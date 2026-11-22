package Controlador;

import Procesos.ProcesosEstudiantes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Formatos.*;
import DTO.EstudianteDTO;
import Vista.FrmGestionEstudiantes;
import DAO.*;
import Util.ImpresorTabla;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControladorEstudiantes implements ActionListener {
    FrmGestionEstudiantes vista;
    EstudianteDAO crud;
    EstudianteDTO estudiante;
    
    public ControladorEstudiantes(FrmGestionEstudiantes f1) { 
        vista = f1;
        vista.btnBuscar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnEditar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        vista.btnImprimir.addActionListener(this);
        vista.btnImportarFoto.addActionListener(this);
        ProcesosEstudiantes.Presentacion(f1);
        ActualizarForma();
    }
    
    void ActualizarForma() {
        crud = new EstudianteDAO();
        ProcesosEstudiantes.Estado1(vista);
        ProcesosEstudiantes.LimpiarEntradas(vista);
        ProcesosEstudiantes.MostrarEstudiantes(vista, crud.ListaEstudiantes());
    }     
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnBuscar) {
            String dni = vista.txtBuscarEstudiante.getText();
            if(dni.isEmpty()) {
                Mensajes.M1("Ingrese DNI a buscar");
                return;
            }
            
            crud = new EstudianteDAO();
            estudiante = crud.ConsultarRegistro(dni);
            if(estudiante == null) {
                Mensajes.M1("El estudiante con DNI "+dni+" no existe");
            } else {
                vista.txtNombres.setText(estudiante.getNombres());
                vista.txtApellidos.setText(estudiante.getApellidos());
                vista.txtDni.setText(estudiante.getDni());
                vista.dcFechaNac.setDate(estudiante.getFechaNacimiento());
                vista.txtDireccion.setText(estudiante.getDireccion());
                if (estudiante.getFoto() != null) {
                    try {
                        ImageIcon icon = new ImageIcon(new ImageIcon(estudiante.getFoto()).getImage().
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
                ProcesosEstudiantes.Estado2(vista);
            }
        }
        if(e.getSource() == vista.btnGuardar) {
            estudiante = ProcesosEstudiantes.LeerDatos(vista);
            crud = new EstudianteDAO();
            crud.InsertarEstudiante(estudiante);
            ActualizarForma();      
        }
        if(e.getSource() == vista.btnEditar) {
            estudiante = ProcesosEstudiantes.LeerDatos(vista);
            estudiante.setDni(vista.txtDni.getText());
            crud = new EstudianteDAO();
            crud.ActualizarRegistro(estudiante);
            ActualizarForma(); 
        }
        if(e.getSource() == vista.btnEliminar) {
            int respuesta = Mensajes.M3("Confirmar", "¿Desea eliminar el registro?");
            if(respuesta == 0) {
                String dni = vista.txtDni.getText();
                crud = new EstudianteDAO();
                crud.EliminarRegistro(dni);
                ActualizarForma();
            }
        }
        if(e.getSource() == vista.btnLimpiar) {
            ActualizarForma();
        }
        if(e.getSource() == vista.btnImprimir) {
            new ImpresorTabla(vista.tblEstudiantes, "Lista de Estudiantes").imprimirTabla();
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