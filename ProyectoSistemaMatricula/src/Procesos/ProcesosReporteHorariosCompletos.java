package Procesos;

import DAO.ReporteHorariosCompletosDAO;
import DTO.ReporteHorariosCompletosDTO;
import Vista.FrmReportes;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class ProcesosReporteHorariosCompletos {

    public static void PresentacionHorarios() {
        FrmReportes frm = new FrmReportes();
        frm.setTitle("Reporte de Horarios Completos");
        new Controlador.ControladorReportes(frm, "horarios");
    }
    
    public static void mostrarEnTabla(JTable tabla, String gradoSeleccionado) {
        ReporteHorariosCompletosDAO dao = new ReporteHorariosCompletosDAO();
        List<ReporteHorariosCompletosDTO> datos = dao.listarPorGrado(gradoSeleccionado);

        Map<String, Object[]> mapa = new LinkedHashMap<>();

        for (ReporteHorariosCompletosDTO dto : datos) {
            String clave = dto.getCurso() + " - " + dto.getDocente();

            Object[] fila = mapa.getOrDefault(clave, new Object[]{"", "", "", "", "", "", "", ""});
            fila[0] = dto.getCurso();
            fila[1] = dto.getDocente();

            switch (dto.getDia().toLowerCase()) {
                case "lunes" -> fila[2] = dto.getHorario();
                case "martes" -> fila[3] = dto.getHorario();
                case "miércoles" -> fila[4] = dto.getHorario();
                case "jueves" -> fila[5] = dto.getHorario();
                case "viernes" -> fila[6] = dto.getHorario();
            }

            fila[7] = dto.getAula();

            mapa.put(clave, fila);
        }

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{
            "CURSO", "DOCENTE", "LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "AULA"
        });

        for (Object[] fila : mapa.values()) {
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }
}


