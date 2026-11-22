package DAO;

import DTO.DetalleMatriculaDTO;
import Formatos.Mensajes;
import java.sql.*;
import java.util.ArrayList;

public class DetalleMatriculaDAO extends ConectarBD {
    
    public void InsertarDetalles(String codigoMatricula, ArrayList<Integer> idHorarios) {
        try {
            for (Integer idHorario : idHorarios) {
                ps = conexion.prepareStatement(
                    "INSERT INTO detalle_matricula(codigo_matricula, id_curso) VALUES (?, ?)");
                ps.setString(1, codigoMatricula);
                ps.setInt(2, idHorario);
                ps.executeUpdate();
            }
            conexion.close();
        } catch (Exception e) {
            Mensajes.M1("Error al registrar detalles de matrícula: " + e);
        }
    }
        

    public ArrayList<DetalleMatriculaDTO> ListaDetalles(String codigoMatricula) {
        ArrayList<DetalleMatriculaDTO> lista = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("SELECT * FROM detalle_matricula WHERE codigo_matricula=?");
            ps.setString(1, codigoMatricula);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleMatriculaDTO d = new DetalleMatriculaDTO();
                d.setId(rs.getInt("id"));
                d.setCodigoMatricula(rs.getString("codigo_matricula"));
                d.setIdCurso(rs.getInt("id_curso"));
                lista.add(d);
            }
            conexion.close();
        } catch (Exception e) {
            Mensajes.M1("Error al listar detalle de matrícula: " + e);
        }
        return lista;
    }
}

