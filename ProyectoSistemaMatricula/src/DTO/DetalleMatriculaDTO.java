package DTO;

public class DetalleMatriculaDTO {
    private int id;
    private String codigoMatricula;
    private int idCurso;
    
    public DetalleMatriculaDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCodigoMatricula() { return codigoMatricula; }
    public void setCodigoMatricula(String codigoMatricula) { this.codigoMatricula = codigoMatricula; }
    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }
}

