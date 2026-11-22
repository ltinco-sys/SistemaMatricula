package DTO;

import java.util.Date;

public class ReporteCursosDocenteDTO {
    private int id;
    private int idDocente;
    private String nombreDocente;
    private String nombreCurso;
    private String grado;
    private String seccion;
    private String horario;
    private String aula;
    private int cuposOcupados;
    private int cuposTotales;
    private Date fechaGeneracion;

    public ReporteCursosDocenteDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdDocente() { return idDocente; }
    public void setIdDocente(int idDocente) { this.idDocente = idDocente; }
    public String getNombreDocente() { return nombreDocente; }
    public void setNombreDocente(String nombreDocente) { this.nombreDocente = nombreDocente; }
    public String getNombreCurso() { return nombreCurso; }
    public void setNombreCurso(String nombreCurso) { this.nombreCurso = nombreCurso; }
    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }
    public String getSeccion() { return seccion; }
    public void setSeccion(String seccion) { this.seccion = seccion; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
    public String getAula() { return aula; }
    public void setAula(String aula) { this.aula = aula; }
    public int getCuposOcupados() { return cuposOcupados; }
    public void setCuposOcupados(int cuposOcupados) { this.cuposOcupados = cuposOcupados; }
    public int getCuposTotales() { return cuposTotales; }
    public void setCuposTotales(int cuposTotales) { this.cuposTotales = cuposTotales; }
    public Date getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(Date fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }
}

