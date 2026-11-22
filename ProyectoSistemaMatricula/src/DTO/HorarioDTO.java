package DTO;

public class HorarioDTO {
    private int id;
    private String curso;
    private String docente;
    private String salon;
    private String dia;
    private String horaInicio;
    private String horaFin;
    
    public HorarioDTO() {}
    
    public int getId() {        return id;    }
    public void setId(int id) {        this.id = id;    }
    public String getCurso() {        return curso;    }
    public void setCurso(String curso) {        this.curso = curso;    }
    public String getDocente() {        return docente;    }
    public void setDocente(String docente) {        this.docente = docente;    }
    public String getSalon() {        return salon;    }
    public void setSalon(String salon) {        this.salon = salon;    }
    public String getDia() {        return dia;    }
    public void setDia(String dia) {        this.dia = dia;    }
    public String getHoraInicio() {        return horaInicio;    }
    public void setHoraInicio(String horaInicio) {        this.horaInicio = horaInicio;    }
    public String getHoraFin() {        return horaFin;    }
    public void setHoraFin(String horaFin) {        this.horaFin = horaFin;    }
}