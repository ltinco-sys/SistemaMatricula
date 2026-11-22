package DAO;

import DTO.DocenteDTO;
import Formatos.*;
import java.sql.*;
import java.util.ArrayList;

public class DocenteDAO extends ConectarBD {
    
    public ArrayList<DocenteDTO> ListaDocentes() {
        ArrayList<DocenteDTO> lista = new ArrayList();
        try {
            rs = st.executeQuery("SELECT * FROM docentes WHERE estado='A' ORDER BY apellidos, nombres");
            while(rs.next()) {
                DocenteDTO doc = new DocenteDTO();
                doc.setId(rs.getInt("id"));
                doc.setNombres(rs.getString("nombres"));
                doc.setApellidos(rs.getString("apellidos"));
                doc.setDni(rs.getString("dni"));
                doc.setAniosExperiencia(rs.getInt("anios_experiencia"));
                doc.setEspecialidad(rs.getString("especialidad"));
                doc.setFoto(rs.getBytes("foto"));
                lista.add(doc);
            }
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede listar docentes: " + e);
        }
        return lista;
    }
    
    public void InsertarDocente(DocenteDTO doc) {
        try {
            ps = conexion.prepareStatement("INSERT INTO docentes(nombres, apellidos, dni, anios_experiencia, "
                    + "especialidad, foto, estado) VALUES (?,?,?,?,?,?,'A')");
            ps.setString(1, doc.getNombres());
            ps.setString(2, doc.getApellidos());
            ps.setString(3, doc.getDni());
            ps.setInt(4, doc.getAniosExperiencia());
            ps.setString(5, doc.getEspecialidad());
            ps.setBytes(6, doc.getFoto());
            ps.executeUpdate();
            Mensajes.M1("Docente registrado correctamente");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede insertar docente: " + e);
        } 
    }
    
    public DocenteDTO ConsultarRegistro(String dni) {
        DocenteDTO doc = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM docentes WHERE estado='A' AND dni=?");
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if(rs.next()) {
                doc = new DocenteDTO();
                doc.setId(rs.getInt("id"));
                doc.setNombres(rs.getString("nombres"));
                doc.setApellidos(rs.getString("apellidos"));
                doc.setDni(rs.getString("dni"));
                doc.setAniosExperiencia(rs.getInt("anios_experiencia"));
                doc.setEspecialidad(rs.getString("especialidad"));
                doc.setFoto(rs.getBytes("foto"));
            }
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede consultar docente: " + e);
        }
        return doc;
    }
    
    public void ActualizarRegistro(DocenteDTO doc) {
        try {
            ps = conexion.prepareStatement("UPDATE docentes SET nombres=?, apellidos=?, anios_experiencia=?, "
                    + "especialidad=?, foto=? WHERE dni=?");
            ps.setString(1, doc.getNombres());
            ps.setString(2, doc.getApellidos());
            ps.setInt(3, doc.getAniosExperiencia());
            ps.setString(4, doc.getEspecialidad());
            ps.setBytes(5, doc.getFoto());
            ps.setString(6, doc.getDni());
            ps.executeUpdate();
            Mensajes.M1("Registro de docente actualizado");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error al actualizar docente: " + e);
        }
    }
    
    public void EliminarRegistro(String dni) {
        try {
            ps = conexion.prepareStatement("UPDATE docentes SET estado='I' WHERE dni=?");
            ps.setString(1, dni);
            ps.executeUpdate();
            Mensajes.M1("Docente eliminado del sistema");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error al eliminar docente: " + e);
        }
    }
    
    public boolean existeDocente(String nombre) {
        String sql = "SELECT COUNT(*) FROM docentes WHERE estado='A' AND " +
                 "(nombres LIKE ? OR apellidos LIKE ? OR CONCAT(nombres, ' ', apellidos) LIKE ?)";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            ps.setString(2, "%" + nombre + "%");
            ps.setString(3, "%" + nombre + "%");
            rs = ps.executeQuery();
        
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception ex) {
            System.out.println("Error al verificar docente: " + ex.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
        }
        return false;
    }
}