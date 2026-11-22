package Procesos;

import DAO.*;
import DTO.*;
import Formatos.Mensajes;
import Vista.FrmReportes;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProcesosReporteCursosDocente {

    public static void PresentacionDocente() {
        FrmReportes frm = new FrmReportes();
        frm.setTitle("Reporte de Cursos por Docente");
        new Controlador.ControladorReportes(frm, "docente");
    }
    
    public static void mostrarEnTabla(JTable tabla, String nombreDocente) {
        try {
            ReporteCursosDocenteDAO dao = new ReporteCursosDocenteDAO();
            List<ReporteCursosDocenteDTO> lista = dao.listarPorNombreDocente(nombreDocente);

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.setColumnIdentifiers(new Object[]{
                "NRO", "DOCENTE", "CURSO", "GRADO", "SECCIÓN", "HORARIO", "AULA", "CUPOS"
            });

            int nro = 1;
            for (ReporteCursosDocenteDTO dto : lista) {
                modelo.addRow(new Object[]{
                    nro++,
                    dto.getNombreDocente(),
                    dto.getNombreCurso(),
                    dto.getGrado(),
                    dto.getSeccion(),
                    dto.getHorario(),
                    dto.getAula(),
                    dto.getCuposOcupados()
                });
            }

            tabla.setModel(modelo);
        } catch (Exception ex) {
            Mensajes.M1("Error al cargar datos en la tabla: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

