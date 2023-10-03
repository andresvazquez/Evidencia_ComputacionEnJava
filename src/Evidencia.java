import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Evidencia {
    private ArrayList<Doctor> doctores = new ArrayList();
    private ArrayList<Paciente> pacientes = new ArrayList();
    private ArrayList<Cita> citas = new ArrayList();

    public Evidencia() {
    }

    public void guardarDatosEnArchivos() throws IOException {
        BufferedWriter writerDoc = new BufferedWriter(new FileWriter("db/doctores.csv"));
        Iterator var2 = this.doctores.iterator();

        int var10001;
        while(var2.hasNext()) {
            Doctor doctor = (Doctor)var2.next();
            var10001 = doctor.getIdUsuario();
            writerDoc.write("" + var10001 + "," + doctor.getNombre() + "," + doctor.getEspecialidad());
            writerDoc.newLine();
        }

        writerDoc.close();
        BufferedWriter writerCita = new BufferedWriter(new FileWriter("db/citas.csv"));
        Iterator var7 = this.citas.iterator();

        while(var7.hasNext()) {
            Cita cita = (Cita)var7.next();
            var10001 = cita.getIdCita();
            writerCita.write("" + var10001 + "," + cita.getMotivo() + "," + String.valueOf(cita.getFechaHora()));
            writerCita.newLine();
        }

        writerCita.close();
        BufferedWriter writerPaciente = new BufferedWriter(new FileWriter("db/pacientes.csv"));
        Iterator var9 = this.pacientes.iterator();

        while(var9.hasNext()) {
            Paciente paciente = (Paciente)var9.next();
            var10001 = paciente.getIdUsuario();
            writerPaciente.write("" + var10001 + "," + paciente.getNombre());
            writerPaciente.newLine();
        }

        writerPaciente.close();
    }

    public void cargarDatosDesdeArchivos() throws IOException {
        BufferedReader readerDoc = new BufferedReader(new FileReader("db/doctores.csv"));

        String line;
        String motivo;
        while((line = readerDoc.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int idUsuario = Integer.parseInt(parts[0]);
                String nombre = parts[1];
                motivo = parts[2];
                this.doctores.add(new Doctor(idUsuario, nombre, motivo));
            }
        }

        readerDoc.close();
        BufferedReader readerCita = new BufferedReader(new FileReader("db/citas.csv"));

        while((line = readerCita.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int idCita = Integer.parseInt(parts[0]);
                motivo = parts[1];
                LocalDateTime fechaHora = LocalDateTime.parse(parts[2]);
                this.citas.add(new Cita(idCita, motivo, fechaHora));
            }
        }

        readerCita.close();
        BufferedReader readerPaciente = new BufferedReader(new FileReader("db/pacientes.csv"));

        while((line = readerPaciente.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                int idUsuario = Integer.parseInt(parts[0]);
                String nombre = parts[1];
                this.pacientes.add(new Paciente(idUsuario, nombre));
            }
        }

        readerPaciente.close();
    }

    public static void main(String[] args) {
        System.out.println("Test");
        Evidencia ev = new Evidencia();

        try {
            ev.cargarDatosDesdeArchivos();
        } catch (IOException e) {
            System.out.println("Se obtuvo un error al cargar db: " +e);
        }

        ev.doctores.add(new Doctor());
        ev.pacientes.add(new Paciente());
        ev.citas.add(new Cita());

        try {
            ev.guardarDatosEnArchivos();
        } catch (IOException e) {
            System.out.println("Se obtiene error al guardar db: " + e);
        }

    }
}

