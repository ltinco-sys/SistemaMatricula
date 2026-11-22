package DTO;

public class DocenteDTO {
    private int id;
    private String nombres;
    private String apellidos;
    private String dni;
    private int añosExperiencia;
    private String especialidad;
    private byte[] foto;
    
    public DocenteDTO() {}
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public int getAniosExperiencia() { return añosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) { this.añosExperiencia = aniosExperiencia; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public byte[] getFoto() {        return foto;    }    
    public void setFoto(byte[] foto) {        this.foto = foto;    }
}