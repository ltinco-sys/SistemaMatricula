package Procesos;

import Vista.Login;
import javax.swing.JFrame;

public class ProcesosLogin {
    
    public static void Presentacion(Login login) {
        login.setTitle("Sistema de Matrícula - Login");
        login.setLocationRelativeTo(null);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setVisible(true);
    }
    
    public static void LimpiarEntradas(Login login) {
        login.txtUsuario.setText("");
        login.txtPassword.setText("");
        login.txtUsuario.requestFocus();
    }
}