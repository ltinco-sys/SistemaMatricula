package DAO;

import java.sql.*;

public class ConectarBD implements Parametros {
    public Connection conexion;
    public Statement st;
    public ResultSet rs;
    public PreparedStatement ps;
    
    public ConectarBD() {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(RUTA, USUARIO, CLAVE);
            st = conexion.createStatement();
        } catch (Exception ex) {
            System.out.println("Error al conectar a la BD: " + ex.getMessage());
        }
    }
}