import java.time.LocalDateTime;
import java.util.Date;

public class Cita {
    private int idCita;
    private LocalDateTime fechaHora;
    private String motivo;

    public Cita(){
        this.idCita=-1;
        this.fechaHora= LocalDateTime.now();
    }

    public Cita(int idCita, String motivo, LocalDateTime fechaHora){
        this.idCita=idCita;
        this.motivo =motivo;
        this.fechaHora = fechaHora;
    }

    public void mostrarCitasPorDoctor(){

    }
    public void mostrarCitasPorPaciente(){

    }
    public void crearCita(){

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
}
