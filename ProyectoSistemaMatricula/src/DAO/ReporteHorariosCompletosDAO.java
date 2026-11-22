package DAO;

import DTO.ReporteHorariosCompletosDTO;
import java.sql.*;
import java.util.*;

public class ReporteHorariosCompletosDAO {
    ConectarBD c = new ConectarBD();
    PreparedStatement ps;
    ResultSet rs;

    public List<ReporteHorariosCompletosDTO> listarPorGrado(String grado) {
        List<ReporteHorariosCompletosDTO> lista = new ArrayList<>();

        String sql = "SELECT " +
                "c.nombre AS curso, " +
                "CONCAT(d.nombres, ' ', d.apellidos) AS docente, " +
                "di.nombre AS dia, " +
                "CONCAT(h.hora_inicio, ' - ', h.hora_fin) AS horario, " +
                "sal.nombre AS aula " +
                "FROM docente_curso dc " +
                "JOIN docentes d ON dc.id_docente = d.id " +
                "JOIN cursos c ON dc.id_curso = c.id " +
                "JOIN grados g ON dc.id_grado = g.id " +
                "JOIN dias di ON dc.id_dia = di.id " +
                "JOIN horas h ON dc.id_hora = h.id " +
                "JOIN salones sal ON dc.id_salon = sal.id " +
                "WHERE g.nombre = ? AND d.estado = 'A' " +
                "ORDER BY c.nombre, di.id";

        try {
            ps = c.conexion.prepareStatement(sql);
            ps.setString(1, grado);
            rs = ps.executeQuery();

            while (rs.next()) {
                ReporteHorariosCompletosDTO dto = new ReporteHorariosCompletosDTO();
                dto.setCurso(rs.getString("curso"));
                dto.setDocente(rs.getString("docente"));
                dto.setDia(rs.getString("dia"));
                dto.setHorario(rs.getString("horario"));
                dto.setAula(rs.getString("aula"));
                lista.add(dto);
            }

        } catch (Exception ex) {
            System.out.println("Error al obtener horarios: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
        }

        return lista;
    }

    public boolean existeEnReporte(ReporteHorariosCompletosDTO dto) {
        String sql = "SELECT COUNT(*) FROM reporte_horarios_completos WHERE curso = ? AND docente = ? AND aula = ?";
        try {
            ps = c.conexion.prepareStatement(sql);
            ps.setString(1, dto.getCurso());
            ps.setString(2, dto.getDocente());
            ps.setString(3, dto.getAula());
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception ex) {
            System.out.println("Error al verificar existencia en reporte: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
        }
        return false;
    }

    public boolean insertarReporte(ReporteHorariosCompletosDTO dto) {
        String sql = "INSERT INTO reporte_horarios_completos " +
                "(grado, seccion, curso, docente, lunes, martes, miercoles, jueves, viernes, aula) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = c.conexion.prepareStatement(sql);
            ps.setString(1, dto.getGrado());
            ps.setString(2, dto.getSeccion());
            ps.setString(3, dto.getCurso());
            ps.setString(4, dto.getDocente());
            ps.setString(5, dto.getLunes());
            ps.setString(6, dto.getMartes());
            ps.setString(7, dto.getMiercoles());
            ps.setString(8, dto.getJueves());
            ps.setString(9, dto.getViernes());
            ps.setString(10, dto.getAula());

            return ps.executeUpdate() > 0;

        } catch (Exception ex) {
            System.out.println("Error al insertar en reporte horarios: " + ex.getMessage());
            return false;
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
        }
    }
}




