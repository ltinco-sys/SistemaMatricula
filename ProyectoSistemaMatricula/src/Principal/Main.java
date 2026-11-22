package Principal;

import Controlador.ControladorLogin;
import Vista.Login;

public class Main {
    public static void main(String[] args) {
        Login vista = new Login();
        ControladorLogin controlador = new ControladorLogin(vista);
    }
}