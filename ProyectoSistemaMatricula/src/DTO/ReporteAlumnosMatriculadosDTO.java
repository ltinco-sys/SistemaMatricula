package DTO;

import java.util.Date;

public class ReporteAlumnosMatriculadosDTO {
    private int id;
    private String codigoMatricula;
    private String nombreCompleto;
    private String dni;
    private String grado;
    private String seccion;
    private String anioAcademico;
    private Date fechaGeneracion;

    public ReporteAlumnosMatriculadosDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigoMatricula() { return codigoMatricula; }
    public void setCodigoMatricula(String codigoMatricula) { this.codigoMatricula = codigoMatricula; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }
    public String getSeccion() { return seccion; }
    public void setSeccion(String seccion) { this.seccion = seccion; }
    public String getAnioAcademico() { return anioAcademico; }
    public void setAnioAcademico(String anioAcademico) { this.anioAcademico = anioAcademico; }
    public Date getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(Date fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }
}
