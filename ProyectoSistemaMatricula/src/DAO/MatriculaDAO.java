package DAO;

import DTO.MatriculaDTO;
import Formatos.Mensajes;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class MatriculaDAO extends ConectarBD {
    
    public boolean InsertarMatricula(MatriculaDTO matricula) {
        try {
            String sql = "INSERT INTO matricula(codigo, dni_estudiante, fecha, grado, seccion, total_pagar) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, matricula.getCodigo());
            ps.setString(2, matricula.getEstudianteDni());
            ps.setDate(3, new java.sql.Date(matricula.getFecha().getTime()));
            ps.setString(4, matricula.getGrado());
            ps.setString(5, matricula.getSeccion());
            ps.setDouble(6, matricula.getTotalPagar());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            Mensajes.M1("Error al registrar matrícula: " + e);
            return false;
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
        }
    }
    
    public String GenerarCodigoMatricula() {
        String codigo = "MAT001";
        try {
            rs = st.executeQuery("SELECT MAX(codigo) AS ultimo FROM matricula");
            if (rs.next()) {
                String ultimo = rs.getString("ultimo");
                if (ultimo != null) {
                    int num = Integer.parseInt(ultimo.substring(3)) + 1;
                    codigo = String.format("MAT%03d", num);
                }
            }
        } catch (Exception e) {
            Mensajes.M1("Error al generar código: " + e);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
        }
        return codigo;
    }
    
    public ArrayList<MatriculaDTO> ListarMatriculas() {
        ArrayList<MatriculaDTO> lista = new ArrayList<>();
        try {
            rs = st.executeQuery("SELECT * FROM matricula WHERE estado = 'A'");
            while (rs.next()) {
                MatriculaDTO m = new MatriculaDTO();
                m.setId(rs.getInt("id"));
                m.setCodigo(rs.getString("codigo"));
                m.setEstudianteDni(rs.getString("dni_estudiante"));
                m.setFecha(rs.getDate("fecha"));
                m.setGrado(rs.getString("grado"));
                m.setSeccion(rs.getString("seccion"));
                m.setTotalPagar(rs.getDouble("total_pagar"));
                lista.add(m);
            }
        } catch (Exception e) {
            Mensajes.M1("Error al listar matrículas: " + e);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
        }
        return lista;
    }
    
    public DefaultTableModel ListarTodos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CÓDIGO");
        modelo.addColumn("FECHA");
        modelo.addColumn("DNI ESTUDIANTE");
        modelo.addColumn("GRADO");
        modelo.addColumn("SECCIÓN");
        modelo.addColumn("TOTAL A PAGAR");

        try {
            String sql = "SELECT codigo, fecha, dni_estudiante, grado, seccion, total_pagar FROM matricula WHERE estado = 'A'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("codigo"),
                    rs.getDate("fecha"),
                    rs.getString("dni_estudiante"),
                    rs.getString("grado"),
                    rs.getString("seccion"),
                    rs.getDouble("total_pagar")
                });
            }
        } catch (Exception e) {
            Mensajes.M1("Error al listar todas las matrículas: " + e);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
        }
         return modelo;
    }  
}
