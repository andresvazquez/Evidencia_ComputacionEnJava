import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Cita {
    private int idCita;
    private LocalDateTime fechaHora;
    private String motivo;
    private int idDoc, idPac;

    public Cita(){
        this.idCita=-1;
        this.fechaHora= LocalDateTime.now();
    }

    public Cita(int idCita, String motivo, LocalDateTime fechaHora){
        this.idCita=idCita;
        this.motivo =motivo;
        this.fechaHora = fechaHora;
    }

    public Cita(int idCita, String motivo, LocalDateTime fechaHora,int idDoc, int idPac){
        this.idCita=idCita;
        this.motivo =motivo;
        this.fechaHora = fechaHora;
        this.idDoc = idDoc;
        this.idPac = idPac;
    }

    public void crearCita(Doctor doctorCita, Paciente pacienteCita){
        if(doctorCita==null){
            this.idDoc=-1;
            System.out.println("Cita sin doctor asignado.");
        }
        if(pacienteCita==null){
            this.idPac=-1;
            System.out.println("Cita sin paciente asignado.");
        }
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    public int getIdPac() {
        return idPac;
    }

    public void setIdPac(int idPac) {
        this.idPac = idPac;
    }
}
