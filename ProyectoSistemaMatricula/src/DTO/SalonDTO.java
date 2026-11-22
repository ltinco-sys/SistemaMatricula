package DTO;

public class SalonDTO {
    private int id;
    private String nombre;
    private int piso;
    private int capacidad;
    private boolean tieneProyector;
    private boolean tieneAire;
    
    public SalonDTO() {}
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNumero() { return nombre; }
    public void setNumero(String numero) { this.nombre = numero; }
    public int getPiso() { return piso; }
    public void setPiso(int piso) { this.piso = piso; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public boolean isTieneProyector() { return tieneProyector; }
    public void setTieneProyector(boolean tieneProyector) { this.tieneProyector = tieneProyector; }
    public boolean isTieneAire() { return tieneAire; }
    public void setTieneAire(boolean tieneAire) { this.tieneAire = tieneAire; }
}