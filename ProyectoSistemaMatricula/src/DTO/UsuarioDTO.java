package DTO;

public class UsuarioDTO {
    private int id;
    private String usuario;
    private String password;
    private String nombres;
    private String rol;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}