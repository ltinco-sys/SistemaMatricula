package DAO;

import DTO.UsuarioDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO extends ConectarBD {
    
    public UsuarioDTO validarUsuario(String usuario, String password) {
        UsuarioDTO dto = null;
        try {
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                dto = new UsuarioDTO();
                dto.setId(rs.getInt("id"));
                dto.setUsuario(rs.getString("usuario"));
                dto.setNombres(rs.getString("nombres"));
                dto.setRol(rs.getString("rol"));
            }
            conexion.close();
        } catch(Exception e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
        }
        return dto;
    }
}