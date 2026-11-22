package DTO;

import java.util.Date;

public class MatriculaDTO {
    private int id;
    private String codigo;
    private String estudianteDni;
    private Date fecha;
    private String grado;
    private String seccion;
    private double totalPagar;
    
    public MatriculaDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }   
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getEstudianteDni() { return estudianteDni; }
    public void setEstudianteDni(String estudianteDni) { this.estudianteDni = estudianteDni; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }
    public String getSeccion() { return seccion; }
    public void setSeccion(String seccion) { this.seccion = seccion; }
    public double getTotalPagar() { return totalPagar; }
    public void setTotalPagar(double totalPagar) { this.totalPagar = totalPagar; }
}