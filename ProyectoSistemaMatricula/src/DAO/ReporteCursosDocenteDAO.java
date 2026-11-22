package DAO;

import DTO.ReporteCursosDocenteDTO;
import java.util.*;
import java.sql.*;

public class ReporteCursosDocenteDAO {
    ConectarBD c = new ConectarBD();

    public List<ReporteCursosDocenteDTO> listarPorNombreDocente(String nombre) {
    List<ReporteCursosDocenteDTO> lista = new ArrayList<>();

    String sql = "SELECT " +
            "dc.id AS id_relacion, " +
            "d.id AS id_docente, " +
            "CONCAT(d.nombres, ' ', d.apellidos) AS nombre_docente, " +
            "c.nombre AS nombre_curso, " +
            "g.nombre AS grado, " +
            "s.nombre AS seccion, " +
            "CONCAT(h.hora_inicio, ' - ', h.hora_fin) AS horario, " +
            "sal.nombre AS aula, " +
            "c.cupos AS cupos_totales, " +
            "(SELECT COUNT(*) FROM detalle_matricula dm " +
            " JOIN matricula m ON m.codigo = dm.codigo_matricula " +
            " WHERE dm.id_curso = c.id AND m.grado = g.nombre AND m.seccion = s.nombre) AS cupos_ocupados " +
            "FROM docente_curso dc " +
            "JOIN docentes d ON dc.id_docente = d.id " +
            "JOIN cursos c ON dc.id_curso = c.id " +
            "JOIN grados g ON dc.id_grado = g.id " +
            "JOIN secciones s ON dc.id_seccion = s.id " +
            "JOIN salones sal ON dc.id_salon = sal.id " +
            "JOIN dias di ON dc.id_dia = di.id " +
            "JOIN horas h ON dc.id_hora = h.id " +
            "WHERE CONCAT(d.nombres, ' ', d.apellidos) LIKE ? AND d.estado = 'A'";

    try {
        c.ps = c.conexion.prepareStatement(sql);
        c.ps.setString(1, "%" + nombre + "%");
        c.rs = c.ps.executeQuery();

        while (c.rs.next()) {
            ReporteCursosDocenteDTO dto = new ReporteCursosDocenteDTO();
            dto.setId(c.rs.getInt("id_relacion"));
            dto.setIdDocente(c.rs.getInt("id_docente"));
            dto.setNombreDocente(c.rs.getString("nombre_docente"));
            dto.setNombreCurso(c.rs.getString("nombre_curso"));
            dto.setGrado(c.rs.getString("grado"));
            dto.setSeccion(c.rs.getString("seccion"));
            dto.setHorario(c.rs.getString("horario"));
            dto.setAula(c.rs.getString("aula"));
            dto.setCuposOcupados(c.rs.getInt("cupos_ocupados"));
            dto.setCuposTotales(c.rs.getInt("cupos_totales"));
            dto.setFechaGeneracion(new java.sql.Timestamp(System.currentTimeMillis())); // Asignamos ahora
            lista.add(dto);
        }

    } catch (Exception ex) {
        System.out.println("Error al listar cursos por docente desde docente_curso: " + ex.getMessage());
    }

    return lista;
}
}



