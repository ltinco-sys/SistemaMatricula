package DAO;

import DTO.ReporteAlumnosMatriculadosDTO;
import java.sql.*;
import java.util.*;

public class ReporteAlumnosMatriculadosDAO {
    ConectarBD c = new ConectarBD();

    public List<ReporteAlumnosMatriculadosDTO> listarPorGradoSeccionYAnio(String grado, String seccion, String anio) {
        List<ReporteAlumnosMatriculadosDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM reporte_alumnos_matriculados WHERE grado = ? AND seccion = ? AND anio_academico = ?";

        try {
            c.ps = c.conexion.prepareStatement(sql);
            c.ps.setString(1, grado);
            c.ps.setString(2, seccion);
            c.ps.setString(3, anio);
            c.rs = c.ps.executeQuery();

            while (c.rs.next()) {
                ReporteAlumnosMatriculadosDTO dto = new ReporteAlumnosMatriculadosDTO();
                dto.setId(c.rs.getInt("id"));
                dto.setCodigoMatricula(c.rs.getString("codigo_matricula"));
                dto.setNombreCompleto(c.rs.getString("nombre_completo"));
                dto.setDni(c.rs.getString("dni"));
                dto.setGrado(c.rs.getString("grado"));
                dto.setSeccion(c.rs.getString("seccion"));
                dto.setAnioAcademico(c.rs.getString("anio_academico"));
                dto.setFechaGeneracion(c.rs.getTimestamp("fecha_generacion"));
                lista.add(dto);
            }

        } catch (Exception ex) {
            System.out.println("Error al listar alumnos matriculados: " + ex.getMessage());
        }

        return lista;
    }

    public boolean insertarReporte(ReporteAlumnosMatriculadosDTO dto) {
        String sql = "INSERT INTO reporte_alumnos_matriculados " +
                     "(codigo_matricula, nombre_completo, dni, grado, seccion, anio_academico) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            c.ps = c.conexion.prepareStatement(sql);
            c.ps.setString(1, dto.getCodigoMatricula());
            c.ps.setString(2, dto.getNombreCompleto());
            c.ps.setString(3, dto.getDni());
            c.ps.setString(4, dto.getGrado());
            c.ps.setString(5, dto.getSeccion());
            c.ps.setString(6, dto.getAnioAcademico());
            return c.ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<ReporteAlumnosMatriculadosDTO> obtenerAlumnosParaReporte(String grado, String seccion, String anio) {
        List<ReporteAlumnosMatriculadosDTO> lista = new ArrayList<>();
        String sql = """
            SELECT m.codigo AS codigo_matricula, e.nombres, e.apellidos, e.dni, m.grado, m.seccion
            FROM matricula m
            INNER JOIN estudiantes e ON m.dni_estudiante = e.dni
            WHERE m.grado = ? AND m.seccion = ? AND YEAR(m.fecha) = ?
        """;

        try {
            c.ps = c.conexion.prepareStatement(sql);
            c.ps.setString(1, grado);
            c.ps.setString(2, seccion);
            c.ps.setString(3, anio);
            c.rs = c.ps.executeQuery();

            while (c.rs.next()) {
                ReporteAlumnosMatriculadosDTO dto = new ReporteAlumnosMatriculadosDTO();
                dto.setCodigoMatricula(c.rs.getString("codigo_matricula"));
                dto.setNombreCompleto(c.rs.getString("nombres") + " " + c.rs.getString("apellidos"));
                dto.setDni(c.rs.getString("dni"));
                dto.setGrado(grado);
                dto.setSeccion(seccion);
                dto.setAnioAcademico(anio);
                lista.add(dto);
            }

        } catch (Exception ex) {
            System.out.println("Error al obtener alumnos para reporte: " + ex.getMessage());
        }
    return lista;
}

    
    public boolean existeEnReporte(String codigoMatricula, String anio) {
        String sql = "SELECT COUNT(*) FROM reporte_alumnos_matriculados WHERE codigo_matricula = ? AND anio_academico = ?";
        try {
            c.ps = c.conexion.prepareStatement(sql);
            c.ps.setString(1, codigoMatricula);
            c.ps.setString(2, anio);
            c.rs = c.ps.executeQuery();
            if (c.rs.next()) {
                return c.rs.getInt(1) > 0;
            }
        } catch (Exception ex) {
            System.out.println("Error al verificar existencia en reporte: " + ex.getMessage());
        }
        return false;
    }
}
