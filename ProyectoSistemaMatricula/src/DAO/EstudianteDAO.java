package DAO;

import DTO.EstudianteDTO;
import Formatos.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class EstudianteDAO extends ConectarBD {
    
    public ArrayList<EstudianteDTO> ListaEstudiantes() {
        ArrayList<EstudianteDTO> lista = new ArrayList();
        try {
            rs = st.executeQuery("SELECT * FROM estudiantes WHERE estado='A' ORDER BY apellidos, nombres");
        while(rs.next()) {
            EstudianteDTO est = new EstudianteDTO();
            est.setId(rs.getInt("id"));
            est.setNombres(rs.getString("nombres"));
            est.setApellidos(rs.getString("apellidos"));
            est.setDni(rs.getString("dni"));
            est.setFechaNacimiento(rs.getDate("fecha_nacimiento")); 
            est.setDireccion(rs.getString("direccion"));
            est.setFoto(rs.getBytes("foto"));
            lista.add(est);
        }
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede listar estudiantes: " + e);
        }
        return lista;
    }

    public void InsertarEstudiante(EstudianteDTO est) {
        try {
            ps = conexion.prepareStatement(
                "INSERT INTO estudiantes(nombres, apellidos, dni, fecha_nacimiento, direccion, foto, estado) " +
                "VALUES (?,?,?,?,?,?,'A')");
            ps.setString(1, est.getNombres());
            ps.setString(2, est.getApellidos());
            ps.setString(3, est.getDni());
            ps.setDate(4, new java.sql.Date(est.getFechaNacimiento().getTime()));
            ps.setString(5, est.getDireccion());
            ps.setBytes(6, est.getFoto());
            ps.executeUpdate();
            Mensajes.M1("Estudiante registrado correctamente");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede insertar estudiante: " + e);
        }
    }

    public EstudianteDTO ConsultarRegistro(String dni) {
        EstudianteDTO est = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM estudiantes WHERE estado='A' AND dni=?");
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if(rs.next()) {
                est = new EstudianteDTO();
                est.setId(rs.getInt("id"));
                est.setNombres(rs.getString("nombres"));
                est.setApellidos(rs.getString("apellidos"));
                est.setDni(rs.getString("dni"));
                est.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                est.setDireccion(rs.getString("direccion"));
                est.setFoto(rs.getBytes("foto"));
            }
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede consultar estudiante: " + e);
        }
        return est;
    }

    public void ActualizarRegistro(EstudianteDTO est) {
        try {
            ps = conexion.prepareStatement(
                "UPDATE estudiantes SET nombres=?, apellidos=?, fecha_nacimiento=?, direccion=?, foto=? WHERE dni=?");
            ps.setString(1, est.getNombres());
            ps.setString(2, est.getApellidos());
            ps.setDate(3, new java.sql.Date(est.getFechaNacimiento().getTime()));
            ps.setString(4, est.getDireccion());
            ps.setBytes(5, est.getFoto());
            ps.setString(6, est.getDni());
            ps.executeUpdate();
            Mensajes.M1("Registro de estudiante actualizado");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error al actualizar estudiante: " + e);
        }
    }

    public void EliminarRegistro(String dni) {
        try {
            ps = conexion.prepareStatement("UPDATE estudiantes SET estado='I' WHERE dni=?");
            ps.setString(1, dni);
            ps.executeUpdate();
            Mensajes.M1("Estudiante eliminado del sistema");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error al eliminar estudiante: " + e);
        }
    }
}