import java.io.*;
import java.util.*;

public class Evidencia {

    private ArrayList<Doctor> doctores;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Cita> citas;

    public Evidencia(){
        this.doctores = new ArrayList<Doctor>();
        this.pacientes = new ArrayList<Paciente>();
        this.citas = new ArrayList<Cita>();
    }

    public void guardarDatosEnArchivos() throws IOException{
        BufferedWriter writerDoc = new BufferedWriter(new FileWriter("db/doctores.csv"));
        for (Doctor doctor : this.doctores) {
            writerDoc.write(doctor.getIdUsuario()+ "," + doctor.getNombre() + "," + doctor.getEspecialidad());
            writerDoc.newLine();
        }
        writerDoc.close();
        BufferedWriter writerCita = new BufferedWriter(new FileWriter("db/citas.csv"));
        for (Cita cita : this.citas) {
            writerCita.write(cita.getIdCita()+ "," + cita.getMotivo() + "," + cita.getFechaHora());
            writerCita.newLine();
        }
        writerCita.close();
        BufferedWriter writerPaciente = new BufferedWriter(new FileWriter("db/pacientes.csv"));
        for (Paciente paciente : this.pacientes) {
            writerPaciente.write(paciente.getIdUsuario()+ "," + paciente.getNombre());
            writerPaciente.newLine();
        }
        writerPaciente.close();
    }

    public static void main(String [] args){
        System.out.println("Test");
        Evidencia ev = new Evidencia();
        ev.doctores.add(new Doctor());
        ev.pacientes.add(new Paciente());
        ev.citas.add(new Cita());
        try {
            ev.guardarDatosEnArchivos();
        } catch (IOException e) {
            System.out.println("Se obtiene error al guardar: " + e);
        }
    }
}
