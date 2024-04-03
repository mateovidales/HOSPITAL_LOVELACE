package entities;

import java.sql.Date;
import java.sql.Time;

public class Cita {
    private int id_cita;
    private Time hora;
    private String motivo;
    private Date fecha_cita;
    private int fk_id_paciente;
    private int fk_id_medico;
    private String medico;
    private String paciente;

    public Cita() {
    }

    public Cita(int id_cita, Time hora, String motivo, Date fecha_cita, int fk_id_paciente, int fk_id_medico, String medico, String paciente) {
        this.id_cita = id_cita;
        this.hora = hora;
        this.motivo = motivo;
        this.fecha_cita = fecha_cita;
        this.fk_id_paciente = fk_id_paciente;
        this.fk_id_medico = fk_id_medico;
        this.medico = medico;
        this.paciente = paciente;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public int getFk_id_paciente() {
        return fk_id_paciente;
    }

    public void setFk_id_paciente(int fk_id_paciente) {
        this.fk_id_paciente = fk_id_paciente;
    }

    public int getFk_id_medico() {
        return fk_id_medico;
    }

    public void setFk_id_medico(int fk_id_medico) {
        this.fk_id_medico = fk_id_medico;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id_cita=" + id_cita +
                ", hora='" + hora + '\'' +
                ", motivo='" + motivo + '\'' +
                ", fecha_cita='" + fecha_cita + '\'' +
                ", paciente="+paciente+
                ", medico="+medico+
                '}';
    }
}
