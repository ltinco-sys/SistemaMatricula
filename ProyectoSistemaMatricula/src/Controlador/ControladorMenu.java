package Controlador;

import Vista.FrmMenu;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class ControladorMenu implements ActionListener {
    FrmMenu vista;
    
    public ControladorMenu(FrmMenu menu) {
        vista = menu;
        vista.itemEstudiante.addActionListener(this);
        vista.itemDocente.addActionListener(this);
        vista.itemSalon.addActionListener(this);
        vista.itemCurso.addActionListener(this);
        vista.itemHorario.addActionListener(this);
        vista.itemMatricula.addActionListener(this);
        vista.itemListaAlumnos.addActionListener(this);
        vista.itemCursosDocente.addActionListener(this);
        vista.itemHorariosCompletos.addActionListener(this);
        vista.setExtendedState(JFrame.MAXIMIZED_BOTH);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.itemEstudiante) {
            FrmGestionEstudiantes frm = new FrmGestionEstudiantes();
            new ControladorEstudiantes(frm);
            vista.dspEscritorio.add(frm);
            frm.setVisible(true);
        }
        
        if(e.getSource() == vista.itemDocente) {
            FrmGestionDocentes frm = new FrmGestionDocentes();
            new ControladorDocentes(frm);
            vista.dspEscritorio.add(frm);
            frm.setVisible(true);
        }
        
        if(e.getSource() == vista.itemSalon) {
            FrmGestionSalones frm = new FrmGestionSalones();
            new ControladorSalones(frm);
            vista.dspEscritorio.add(frm);
            frm.setVisible(true);
        }
        
        if(e.getSource() == vista.itemCurso) {
            FrmGestionCursos frm = new FrmGestionCursos();
            new ControladorCursos(frm);
            vista.dspEscritorio.add(frm);
            frm.setVisible(true);
        }
        
        if(e.getSource() == vista.itemHorario) {
            FrmGestionHorarios frm = new FrmGestionHorarios();
            new ControladorHorarios(frm);
            vista.dspEscritorio.add(frm);
            frm.setVisible(true);
        }
        
        if(e.getSource() == vista.itemMatricula) {
            FrmProcesoMatricula frm = new FrmProcesoMatricula();
            new ControladorMatricula(frm);
            vista.dspEscritorio.add(frm);
            frm.setVisible(true);
        }
        
        if (e.getSource() == vista.itemListaAlumnos) {
            FrmReportes frm = new FrmReportes();
            new ControladorReportes(frm, "alumnos");
            vista.dspEscritorio.add(frm);
        }

        if (e.getSource() == vista.itemCursosDocente) {
            FrmReportes frm = new FrmReportes();
            new ControladorReportes(frm, "docente");
            vista.dspEscritorio.add(frm);
        }

        if (e.getSource() == vista.itemHorariosCompletos) {
            FrmReportes frm = new FrmReportes();
            new ControladorReportes(frm, "horarios");
            vista.dspEscritorio.add(frm);
        }
    }    
}