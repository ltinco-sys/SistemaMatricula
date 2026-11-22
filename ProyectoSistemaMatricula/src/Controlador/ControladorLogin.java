package Controlador;

import Vista.Login;
import Vista.FrmMenu;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import Formatos.Mensajes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorLogin implements ActionListener {
    Login vista;
    
    public ControladorLogin(Login login) {
        vista = login;
        vista.btnAcceder.addActionListener(this);
        vista.btnSalir.addActionListener(this);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnAcceder) {
            String usuario = vista.txtUsuario.getText();
            String password = new String(vista.txtPassword.getPassword());
            
            if(usuario.isEmpty() || password.isEmpty()) {
                Mensajes.M1("Debe ingresar usuario y contraseña");
                return;
            }
            
            UsuarioDAO dao = new UsuarioDAO();
            UsuarioDTO dto = dao.validarUsuario(usuario, password);
            
            if(dto != null) {
                Mensajes.M1("Bienvenido " + dto.getNombres());
                FrmMenu menu = new FrmMenu();
                new ControladorMenu(menu);
                vista.dispose();
            } else {
                Mensajes.M1("Usuario o contraseña incorrectos");
            }
        }
        if(e.getSource() == vista.btnSalir) {
            System.exit(0);
        }
    }
}