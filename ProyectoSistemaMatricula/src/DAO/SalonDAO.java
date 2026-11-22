package DAO;

import DTO.SalonDTO;
import Formatos.*;
import java.sql.*;
import java.util.ArrayList;

public class SalonDAO extends ConectarBD {
    
    public ArrayList<SalonDTO> ListaSalones() {
        ArrayList<SalonDTO> lista = new ArrayList();
        try {
            rs = st.executeQuery("SELECT * FROM salones WHERE estado='A' ORDER BY nombre"); 
            while(rs.next()) {
                SalonDTO sal = new SalonDTO();
                sal.setId(rs.getInt("id"));
                sal.setNumero(rs.getString("nombre")); 
                sal.setPiso(rs.getInt("piso"));
                sal.setCapacidad(rs.getInt("capacidad"));
                sal.setTieneProyector(rs.getString("tiene_proyector").equals("S"));
                sal.setTieneAire(rs.getString("tiene_aire").equals("S"));
                lista.add(sal);
            }
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede listar salones: " + e);
        }
        return lista;
    }
    
    public void InsertarSalon(SalonDTO sal) {
        try {
            ps = conexion.prepareStatement("INSERT INTO salones(nombre, piso, capacidad, "
                    + "tiene_proyector, tiene_aire, estado) VALUES (?,?,?,?,?, 'A')");
            ps.setString(1, sal.getNumero());
            ps.setInt(2, sal.getPiso());
            ps.setInt(3, sal.getCapacidad());
            ps.setString(4, sal.isTieneProyector() ? "S" : "N"); 
            ps.setString(5, sal.isTieneAire() ? "S" : "N"); 
            ps.executeUpdate();
            Mensajes.M1("Salón registrado correctamente");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede insertar salón: " + e);
        }
    }
    
    public SalonDTO ConsultarRegistro(String numero) {
        SalonDTO sal = null;
        try {
            ps = conexion.prepareStatement("SELECT * FROM salones WHERE estado='A' AND nombre=?");
            ps.setString(1, numero);
            rs = ps.executeQuery();
            if(rs.next()) {
                sal = new SalonDTO();
                sal.setId(rs.getInt("id"));
                sal.setNumero(rs.getString("nombre")); 
                sal.setPiso(rs.getInt("piso"));
                sal.setCapacidad(rs.getInt("capacidad"));
                sal.setTieneProyector(rs.getString("tiene_proyector").equals("S"));
                sal.setTieneAire(rs.getString("tiene_aire").equals("S"));
            }
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error no se puede consultar salón: " + e);
        }
        return sal;
    }
    
    public void ActualizarRegistro(SalonDTO sal) {
        try {
            ps = conexion.prepareStatement("UPDATE salones SET piso=?, capacidad=?, tiene_proyector=?, "
                    + "tiene_aire=? WHERE nombre=?");
            ps.setInt(1, sal.getPiso());
            ps.setInt(2, sal.getCapacidad());
            ps.setString(3, sal.isTieneProyector() ? "S" : "N");
            ps.setString(4, sal.isTieneAire() ? "S" : "N");
            ps.setString(5, sal.getNumero());
            ps.executeUpdate();
            Mensajes.M1("Registro de salón actualizado");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error al actualizar salón: " + e);
        }
    }
    
    public void EliminarRegistro(String numero) {
        try {
            ps = conexion.prepareStatement("UPDATE salones SET estado='I' WHERE nombre=?");
            ps.setString(1, numero);
            ps.executeUpdate();
            Mensajes.M1("Salón eliminado del sistema");
            conexion.close();
        } catch(Exception e) {
            Mensajes.M1("Error al eliminar salón: " + e);
        }
    }
}