package Controlador;

import Procesos.ProcesosCursos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Formatos.*;
import DTO.CursoDTO;
import Vista.FrmGestionCursos;
import DAO.*;
import Util.ImpresorTabla;

public class ControladorCursos implements ActionListener {
    FrmGestionCursos vista;
    CursoDAO crud;
    CursoDTO curso;
    
    public ControladorCursos(FrmGestionCursos f1) { 
        vista = f1;
        vista.btnBuscar.addActionListener(this);
        vista.btnGuardar.addActionListener(this);
        vista.btnEditar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        vista.btnImprimir.addActionListener(this);
        ProcesosCursos.Presentacion(f1);
        ActualizarForma();
    }
    
    void ActualizarForma() {
        crud = new CursoDAO();
        ProcesosCursos.Estado1(vista);
        ProcesosCursos.LimpiarEntradas(vista);
        ProcesosCursos.MostrarCursos(vista, crud.ListaCursos());
    }     
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnBuscar) {
            String nombre = vista.txtBuscarCurso.getText();
            if(nombre.isEmpty()) {
                Mensajes.M1("Ingrese nombre del curso a buscar");
                return;
            }
            
            crud = new CursoDAO();
            curso = crud.ConsultarRegistro(nombre);
            if(curso == null) {
                Mensajes.M1("El curso "+nombre+" no existe");
            } else {
                vista.txtNombre.setText(curso.getNombre());
                vista.cbxGrado.setSelectedItem(curso.getGrado());
                vista.cbxArea.setSelectedItem(curso.getArea());
                vista.txtDescripcion.setText(curso.getDescripcion());
                ProcesosCursos.Estado2(vista);
            }
        }
        if(e.getSource() == vista.btnGuardar) {
            curso = ProcesosCursos.LeerDatos(vista);
            crud = new CursoDAO();
            crud.InsertarCurso(curso);
            ActualizarForma();      
        }
        if(e.getSource() == vista.btnEditar) {
            curso = ProcesosCursos.LeerDatos(vista);
            curso.setNombre(vista.txtNombre.getText());
            crud = new CursoDAO();
            crud.ActualizarRegistro(curso);
            ActualizarForma(); 
        }
        if(e.getSource() == vista.btnEliminar) {
            int respuesta = Mensajes.M3("Confirmar", "¿Desea eliminar el registro?");
            if(respuesta == 0) {
                String nombre = vista.txtNombre.getText();
                crud = new CursoDAO();
                crud.EliminarRegistro(nombre);
                ActualizarForma();
            }
        }
        if(e.getSource() == vista.btnLimpiar) {
            ActualizarForma();
        }
        if(e.getSource() == vista.btnImprimir) {
            new ImpresorTabla(vista.tblCurso, "Lista de Cursos").imprimirTabla();
        }
    }    
}