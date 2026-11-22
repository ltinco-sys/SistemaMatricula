package DTO;

public class CursoDTO {
    private int id;
    private String nombre;
    private String grado;
    private String area;
    private String descripcion;
    private int cupos;
    
    public CursoDTO() {}
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getCupos() {    return cupos;}
    public void setCupos(int cupos) {    this.cupos = cupos;    }
}