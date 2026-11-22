package Controlador;

import DAO.*;
import DTO.*;
import Formatos.Mensajes;
import Procesos.*;
import Util.*;
import Vista.FrmReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorReportes implements ActionListener {
    FrmReportes vista;
    private String tipoReporteSeleccionado;

    public ControladorReportes(FrmReportes frm, String tipoReporte) {
        vista = frm;
        this.tipoReporteSeleccionado = tipoReporte;

        vista.pnlAlumnos.setVisible(true);
        vista.pnlDocente.setVisible(true);
        vista.pnlHorarios.setVisible(true);

        vista.chkAlumnosMatriculados.setSelected(false);
        vista.chkCursosDocente.setSelected(false);
        vista.chkHorariosCompletos.setSelected(false);


        switch (tipoReporte) {
            case "alumnos":
                vista.pnlAlumnos.setVisible(true);
                vista.chkAlumnosMatriculados.setSelected(true);
                vista.tblReportes.setModel(new MatriculaDAO().ListarTodos());
                break;
            case "docente":
                vista.pnlDocente.setVisible(true);
                vista.chkCursosDocente.setSelected(true);
                ProcesosReporteCursosDocente.mostrarEnTabla(vista.tblReportes,
                vista.txtDocente.getText().toString()
                );              
                break;
            case "horarios":
                vista.pnlHorarios.setVisible(true);
                vista.chkHorariosCompletos.setSelected(true);
                ProcesosReporteHorariosCompletos.mostrarEnTabla(vista.tblReportes,
                vista.cbxGradoHorarios.getSelectedItem().toString()
                );
                break;
        }
        
        vista.btnGenerar.addActionListener(this);
        vista.btnExportarPDF.addActionListener(this);
        vista.btnExportarExcel.addActionListener(this);  

        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGenerar) {
            generarReporte();
        } else if (e.getSource() == vista.btnExportarPDF) {
            exportarPDF();
        } else if (e.getSource() == vista.btnExportarExcel) {
            exportarExcel();
        }
    }

    private void generarReporte() {
    try {
        switch (tipoReporteSeleccionado) {
            case "alumnos":
                generarReporteAlumnos();
                break;
            case "docente":
                generarReporteDocente();
                break;
            case "horarios":
                generarReporteHorarios();
                break;
            default:
                Mensajes.M1("Tipo de reporte no válido");
                break;
        }
    } catch (Exception ex) {
        Mensajes.M1("Error al generar reporte: " + ex.getMessage());
        ex.printStackTrace();
    }
}

    private void generarReporteAlumnos() {
        String grado = vista.cbxGradoAlumnos.getSelectedItem().toString();
        String seccion = vista.cbxSeccionAlumnos.getSelectedItem().toString();
        String anio = vista.cbxAnio.getSelectedItem().toString();

        ReporteAlumnosMatriculadosDAO dao = new ReporteAlumnosMatriculadosDAO();
        List<ReporteAlumnosMatriculadosDTO> lista = dao.obtenerAlumnosParaReporte(grado, seccion, anio);

        int nuevos = 0;
        for (ReporteAlumnosMatriculadosDTO dto : lista) {
            if (!dao.existeEnReporte(dto.getCodigoMatricula(), anio)) {
                boolean exito = dao.insertarReporte(dto);
                if (exito) nuevos++;
            }
        }

        if (nuevos > 0) {
            Mensajes.M1("Se agregaron " + nuevos + " nuevos alumnos al reporte.");
        } else {
            Mensajes.M1("No hay nuevos alumnos para agregar.");
        }

        ProcesosReporteAlumnosMatriculados.mostrarEnTabla(vista.tblReportes, grado, seccion, anio);
    }

    private void generarReporteDocente() {
        String nombreDocente = vista.txtDocente.getText().trim();

        if (nombreDocente.equalsIgnoreCase("Seleccione al Docente") || nombreDocente.isEmpty()) {
            Mensajes.M1("Seleccione o ingrese un docente válido.");
            return;
        }

        DocenteDAO docenteDAO = new DocenteDAO();
        if (!docenteDAO.existeDocente(nombreDocente)) {
            Mensajes.M1("No se encontró ningún docente activo con ese nombre.");
            return;
       }
 
        ReporteCursosDocenteDAO dao = new ReporteCursosDocenteDAO();
        List<ReporteCursosDocenteDTO> lista = dao.listarPorNombreDocente(nombreDocente);

        if (lista.isEmpty()) {
            Mensajes.M1("No hay cursos registrados para el docente seleccionado.");
           return;
        }

        Mensajes.M1("Se encontraron " + lista.size() + " cursos del docente.");
        ProcesosReporteCursosDocente.mostrarEnTabla(vista.tblReportes, nombreDocente);
    }

    private void generarReporteHorarios() {
    String grado = vista.cbxGradoHorarios.getSelectedItem().toString();

    HorarioDAO horarioDAO = new HorarioDAO();
    if (!horarioDAO.existenHorariosParaGrado(grado)) {
        Mensajes.M1("No se encontraron horarios para el grado seleccionado.");
        return;
    }

    // Obtener los horarios desde el DAO
    ReporteHorariosCompletosDAO dao = new ReporteHorariosCompletosDAO();
    List<ReporteHorariosCompletosDTO> lista = dao.listarPorGrado(grado);

    if (lista.isEmpty()) {
        Mensajes.M1("No se encontraron datos para el reporte de horarios.");
        return;
    }

    int nuevos = 0;
    for (ReporteHorariosCompletosDTO dto : lista) {
        if (!dao.existeEnReporte(dto)) {
            boolean exito = dao.insertarReporte(dto);
            if (exito) nuevos++;
        }
    }

    if (nuevos > 0) {
        Mensajes.M1("Se insertaron " + nuevos + " horarios al reporte.");
    } else {
        Mensajes.M1("No hay nuevos horarios para agregar.");
    }

    ProcesosReporteHorariosCompletos.mostrarEnTabla(vista.tblReportes, grado);
}

    private void exportarPDF() {
        String titulo = "";
        String nombreArchivo = "";

        if (vista.chkAlumnosMatriculados.isSelected()) {
            titulo = "REPORTE DE ALUMNOS MATRICULADOS";
            nombreArchivo = "Reporte_AlumnosMatriculados.pdf";
        } else if (vista.chkCursosDocente.isSelected()) {
            titulo = "REPORTE DE CURSOS POR DOCENTE";
            nombreArchivo = "Reporte_CursosPorDocente.pdf";
        } else if (vista.chkHorariosCompletos.isSelected()) {
            titulo = "REPORTE DE HORARIOS COMPLETOS";
            nombreArchivo = "Reporte_HorariosCompletos.pdf";
        } else {
            Mensajes.M1("Seleccione un tipo de reporte para exportar");
            return;
        }

        boolean exportado = ExportadorTablaPDF.exportarTablaComoPDF(vista.tblReportes, titulo, nombreArchivo);
        if (!exportado) {
        }
    }

    private void exportarExcel() {
        String nombreArchivo = "";

        if (vista.chkAlumnosMatriculados.isSelected()) {
            nombreArchivo = "Reporte_AlumnosMatriculados";
        } else if (vista.chkCursosDocente.isSelected()) {
            nombreArchivo = "Reporte_CursosPorDocente";
        } else if (vista.chkHorariosCompletos.isSelected()) {
            nombreArchivo = "Reporte_HorariosCompletos";
        } else {
            Mensajes.M1("Seleccione un tipo de reporte para exportar");
            return;
        }

        boolean exportado = ExportadorTablaExcel.exportarTablaComoExcelConDialogo(vista.tblReportes, nombreArchivo);
        if (!exportado) {
        }
    }

}