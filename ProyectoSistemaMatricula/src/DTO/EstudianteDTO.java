package DTO;

import java.util.Date;

public class EstudianteDTO {
    private int id;
    private String nombres;
    private String apellidos;
    private String dni;
    private Date fechaNacimiento;
    private String direccion;
    private byte[] foto;
    
    public EstudianteDTO() {}
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public byte[] getFoto() {        return foto;    }    
    public void setFoto(byte[] foto) {        this.foto = foto;    }
}