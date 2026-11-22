package Controlador;

import DAO.CursoDAO;
import DAO.MatriculaDAO;
import DAO.DetalleMatriculaDAO;
import DAO.EstudianteDAO;
import DTO.EstudianteDTO;
import DTO.MatriculaDTO;
import Procesos.ProcesosMatricula;
import Vista.FrmProcesoMatricula;
import Formatos.Mensajes;
import Util.ExportadorPDF;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class ControladorMatricula implements ActionListener {

    private FrmProcesoMatricula vista;
    private MatriculaDAO daoMatricula;
    private DetalleMatriculaDAO daoDetalle;
    private EstudianteDTO estudianteActual;

    public ControladorMatricula(FrmProcesoMatricula f) {
        vista = f;
        vista.btnBuscar.addActionListener(this);
        vista.btnGenerarMatricula.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnCancelar.addActionListener(this);
        vista.btnVistaPreviaPDF.addActionListener(this);
        vista.btnExportarPDF.addActionListener(this);
        
        ProcesosMatricula.Presentacion(vista);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGenerarMatricula) {
            if (estudianteActual == null) {
                Mensajes.M1("Primero busque un estudiante");
                return;
            }
            ProcesosMatricula.GenerarCodigoMatricula(vista);
            ProcesosMatricula.CalcularTotalPagar(vista);
            vista.btnGuardar.setEnabled(true);
        }
        
        if (e.getSource() == vista.btnCancelar) {
            ProcesosMatricula.LimpiarFormulario(vista);
        }
        

        if (e.getSource() == vista.btnExportarPDF) {
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File("Matricula_" + vista.txtCodigo.getText() + ".pdf"));
            int seleccion = chooser.showSaveDialog(vista);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    String ruta = chooser.getSelectedFile().getAbsolutePath();
                    ExportadorPDF.exportarMatriculaComoPDF(vista, ruta);
                } else {
                    Mensajes.M1("Exportación cancelada.");
            }
        }

        if (e.getSource() == vista.btnVistaPreviaPDF) {
            String nombreArchivo = "VistaPrevia.pdf";
            ExportadorPDF.exportarMatriculaComoPDF(vista, nombreArchivo);
            try {
                File archivo = new File(nombreArchivo);
                if (archivo.exists()) {
                    if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(archivo);
                } else {
                    Mensajes.M1("Tu sistema no soporta apertura automática de archivos.");
                    }
                } else {
                    Mensajes.M1("No se encontró el archivo de vista previa.");
                }
            } catch (Exception ex) {
                Mensajes.M1("Error al abrir la vista previa del PDF: " + ex.getMessage());
        }
    }
        
        if (e.getSource() == vista.btnBuscar) {
            String dni = vista.txtBuscar.getText().trim(); 
            if (dni.isEmpty()) {
                Mensajes.M1("Ingrese DNI del estudiante");
                return;
            }
            if (!dni.matches("\\d{8}")) {
                Mensajes.M1("El DNI debe tener 8 dígitos numéricos");
                return;
            }
            EstudianteDAO daoEstudiante = new EstudianteDAO();
            estudianteActual = daoEstudiante.ConsultarRegistro(dni);
            if (estudianteActual == null) {
                Mensajes.M1("Estudiante no encontrado");
                vista.btnGenerarMatricula.setEnabled(false);
                vista.btnGuardar.setEnabled(false);
                return;
            }
            Mensajes.M1("Estudiante encontrado: " + estudianteActual.getNombres() + " " + estudianteActual.getApellidos());
            vista.btnGenerarMatricula.setEnabled(true);
            ProcesosMatricula.MostrarCursosDisponibles(vista);
        }
        
        if (e.getSource() == vista.btnGuardar) {
            MatriculaDTO matricula = ProcesosMatricula.LeerDatos(vista);
            MatriculaDAO daoMatricula = new MatriculaDAO();
            CursoDAO daoCurso = new CursoDAO();
            ArrayList<Integer> cursosSeleccionados = ProcesosMatricula.ObtenerCursosSeleccionados(vista);
            for (int idCurso : cursosSeleccionados) {
                int cupo = daoCurso.ObtenerCupo(idCurso);
                if (cupo <= 0) {
                    Mensajes.M1("El curso con ID " + idCurso + " ya no tiene cupos disponibles.");
                    return; 
                }
            }
            if (daoMatricula.InsertarMatricula(matricula)) {
                DetalleMatriculaDAO daoDetalle = new DetalleMatriculaDAO();
                daoDetalle.InsertarDetalles(matricula.getCodigo(), cursosSeleccionados);
                for (int idCurso : cursosSeleccionados) {
                    daoCurso.DisminuirCupo(idCurso);
                }
            Mensajes.M1("Matrícula registrada con éxito.");
            ProcesosMatricula.LimpiarFormulario(vista);
            ProcesosMatricula.MostrarCursosDisponibles(vista);
            } else {
                Mensajes.M1("No se pudo registrar la matrícula.");
            }
        }
    }
}
