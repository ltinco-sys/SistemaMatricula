package DAO;

import DTO.HorarioDTO;
import Formatos.*;
import java.sql.*;
import java.util.ArrayList;

public class HorarioDAO extends ConectarBD {
    
    public ArrayList<HorarioDTO> ListaHorarios() {
        ArrayList<HorarioDTO> lista = new ArrayList();
        try {
            rs = st.executeQuery("SELECT * FROM horarios WHERE estado='A' ORDER BY dia, hora_inicio");
            while(rs.next()) {
                HorarioDTO hor = new HorarioDTO();
                hor.setId(rs.getInt("id"));
                hor.setCurso(rs.getString("curso"));
                hor.setDocente(rs.getString("docente"));
                hor.setSalon(rs.getString("salon"));
                hor.setDia(rs.getString("dia"));
                hor.setHoraInicio(rs.getString("hora_inicio"));
                hor.setHoraFin(rs.getString("hora_fin"));
                lista.add(hor);
            }
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede listar horarios: " + e);
        }
        return lista;
    }
    
    public void InsertarHorario(HorarioDTO hor) {
        try {
            ps = conexion.prepareStatement("INSERT INTO horarios (curso, docente, salon, dia, hora_inicio, hora_fin, estado) " +
                "VALUES (?,?,?,?,?,?,'A')");
            ps.setString(1, hor.getCurso());
            ps.setString(2, hor.getDocente());
            ps.setString(3, hor.getSalon());
            ps.setString(4, hor.getDia());
            ps.setString(5, hor.getHoraInicio());
            ps.setString(6, hor.getHoraFin());
            ps.executeUpdate();
            Mensajes.M1("Horario registrado correctamente");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede insertar horario: " + e);
        }
    }
    
    public HorarioDTO ConsultarRegistro(String curso) {
        HorarioDTO hor = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM horarios WHERE estado='A' AND curso=?");
            ps.setString(1, curso);
            rs = ps.executeQuery();
            if(rs.next()) {
                hor = new HorarioDTO();
                hor.setId(rs.getInt("id"));
                hor.setCurso(rs.getString("curso"));
                hor.setDocente(rs.getString("docente"));
                hor.setSalon(rs.getString("salon"));
                hor.setDia(rs.getString("dia"));
                hor.setHoraInicio(rs.getString("hora_inicio"));
                hor.setHoraFin(rs.getString("hora_fin"));
            }
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede consultar horario: " + e);
        }
        return hor;
    }
    
    public void ActualizarRegistro(HorarioDTO hor) {
        try {
            ps = conexion.prepareStatement("UPDATE horarios SET docente=?, salon=?, dia=?, hora_inicio=?, hora_fin=? WHERE curso=?");
            ps.setString(1, hor.getDocente());
            ps.setString(2, hor.getSalon());
            ps.setString(3, hor.getDia());
            ps.setString(4, hor.getHoraInicio());
            ps.setString(5, hor.getHoraFin());;
            ps.setString(6, hor.getCurso());
            ps.executeUpdate();
            Mensajes.M1("Registro de horario actualizado");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error al actualizar horario: " + e);
        }
    }
    
    public void EliminarRegistro(String curso) {
        try {
            ps = conexion.prepareStatement("UPDATE horarios SET estado='I' WHERE curso=?");
            ps.setString(1, curso);
            ps.executeUpdate();
            Mensajes.M1("Horario eliminado del sistema");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error al eliminar horario: " + e);
        }
    }
    
    public boolean existenHorariosParaGrado(String grado) {
    String sql = "SELECT COUNT(*) FROM horarios h " +
                 "JOIN cursos c ON h.curso = c.nombre " +
                 "WHERE h.estado='A' AND c.grado = ?";
    try {
        ps = conexion.prepareStatement(sql);
        ps.setString(1, grado);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (Exception ex) {
        System.out.println("Error al verificar horarios: " + ex.getMessage());
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception e) {}
        try { if (ps != null) ps.close(); } catch (Exception e) {}
    }
    return false;
}
}