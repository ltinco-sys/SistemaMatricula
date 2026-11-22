package Procesos;

import DAO.ReporteAlumnosMatriculadosDAO;
import DTO.ReporteAlumnosMatriculadosDTO;
import Vista.FrmReportes;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcesosReporteAlumnosMatriculados {

    public static void PresentacionAlumnos() {
        FrmReportes frm = new FrmReportes();
        frm.setTitle("Reporte de Alumnos Matriculados");
        new Controlador.ControladorReportes(frm, "alumnos");
    }
    
    public static void mostrarEnTabla(JTable tabla, String grado, String seccion, String anio) {
        ReporteAlumnosMatriculadosDAO dao = new ReporteAlumnosMatriculadosDAO();
        List<ReporteAlumnosMatriculadosDTO> lista = dao.listarPorGradoSeccionYAnio(grado, seccion, anio);

       DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{
            "NRO", "CÓDIGO MATRÍCULA", "NOMBRE COMPLETO", "DNI", "GRADO", "SECCIÓN", "AÑO"
        });

        int nro = 1;
        for (ReporteAlumnosMatriculadosDTO dto : lista) {
            modelo.addRow(new Object[]{
                nro++,
                dto.getCodigoMatricula(),
                dto.getNombreCompleto(),
                dto.getDni(),
                dto.getGrado(),
                dto.getSeccion(),
                dto.getAnioAcademico()
            });
        }

        tabla.setModel(modelo);
    }

}


