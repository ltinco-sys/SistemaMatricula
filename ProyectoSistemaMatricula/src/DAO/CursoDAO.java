package DAO;

import DTO.CursoDTO;
import Formatos.*;
import java.sql.*;
import java.util.ArrayList;

public class CursoDAO extends ConectarBD {
    
    public ArrayList<CursoDTO> ListaCursos() {
        ArrayList<CursoDTO> lista = new ArrayList();
        try {
            rs = st.executeQuery("SELECT * FROM cursos WHERE estado='A' ORDER BY nombre");
            while(rs.next()) {
                CursoDTO cur = new CursoDTO();
                cur.setId(rs.getInt("id"));
                cur.setNombre(rs.getString("nombre"));
                cur.setGrado(rs.getString("grado"));
                cur.setArea(rs.getString("area"));
                cur.setDescripcion(rs.getString("descripcion"));
                cur.setCupos(rs.getInt("cupos"));
                lista.add(cur);
            }
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede listar cursos: " + e);
        }
        return lista;
    }
    
    public void InsertarCurso(CursoDTO cur) {
        try {
            ps = conexion.prepareStatement("INSERT INTO cursos(nombre, grado, area, descripcion, cupos, estado) VALUES (?,?,?,?,?,?)");
            ps.setString(1, cur.getNombre());
            ps.setString(2, cur.getGrado());
            ps.setString(3, cur.getArea());
            ps.setString(4, cur.getDescripcion());
            ps.setInt(5, 100);
            ps.setString(6, "A");
            ps.executeUpdate();
            Mensajes.M1("Curso registrado correctamente");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede insertar curso: " + e);
        }
    }
    
    public CursoDTO ConsultarRegistro(String nombre) {
        CursoDTO cur = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM cursos WHERE estado='A' AND nombre=?");
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            if(rs.next()) {
                cur = new CursoDTO();
                cur.setId(rs.getInt("id"));
                cur.setNombre(rs.getString("nombre"));
                cur.setGrado(rs.getString("grado"));
                cur.setArea(rs.getString("area"));
                cur.setDescripcion(rs.getString("descripcion"));
            }
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede consultar curso: " + e);
        }
        return cur;
    }
    
    public void ActualizarRegistro(CursoDTO cur) {
        try {
            ps = conexion.prepareStatement("UPDATE cursos SET grado=?, area=?, descripcion=? WHERE nombre=?");
            ps.setString(1, cur.getGrado());
            ps.setString(2, cur.getArea());
            ps.setString(3, cur.getDescripcion());
            ps.setString(4, cur.getNombre());
            ps.executeUpdate();
            Mensajes.M1("Registro de curso actualizado");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error al actualizar curso: " + e);
        }
    }
    
    public void EliminarRegistro(String nombre) {
        try {
            ps = conexion.prepareStatement("UPDATE cursos SET estado='I' WHERE nombre=?");
            ps.setString(1, nombre);
            ps.executeUpdate();
            Mensajes.M1("Curso eliminado del sistema");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error al eliminar curso: " + e);
        }
    }
    
    public void DisminuirCupo(int idCurso) {
        try {
            ps = conexion.prepareStatement("UPDATE cursos SET cupos = cupos - 1 WHERE id = ?");
            ps.setInt(1, idCurso);
            ps.executeUpdate();
        } catch (Exception e) {
            Mensajes.M1("Error al disminuir cupo: " + e);
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
        }
    }
    public int ObtenerCupo(int idCurso) {
        int cupo = 0;
        try {
            ps = conexion.prepareStatement("SELECT cupos FROM curso WHERE id = ?");
            ps.setInt(1, idCurso);
            rs = ps.executeQuery();
            if (rs.next()) {
                cupo = rs.getInt("cupos");
            }
        } catch (Exception e) {
            Mensajes.M1("Error al obtener cupo del curso: " + e);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
        }
        return cupo;
    }
}