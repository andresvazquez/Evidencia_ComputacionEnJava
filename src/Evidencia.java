import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Evidencia {
    private ArrayList<Doctor> doctores = new ArrayList();
    private ArrayList<Paciente> pacientes = new ArrayList();
    private ArrayList<Cita> citas = new ArrayList();
    private  boolean sesionIniciada;
    public Evidencia() {
        this.doctores=new ArrayList<Doctor>();
        this.pacientes=new ArrayList<Paciente>();
        this.citas =new ArrayList<Cita>();
        this.sesionIniciada=false;
        try {
            cargarDatosDesdeArchivos();
        } catch (IOException e) {
            System.out.println("Error IO: " + e);
        }
    }

    public void guardarDatosEnArchivos() throws IOException {
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
        System.out.println("Se realiza el guardado con éxito.");
    }

    public void cargarDatosDesdeArchivos() throws IOException {
        BufferedReader readerDoc = new BufferedReader(new FileReader("db/doctores.csv"));
        String line;
        while((line = readerDoc.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int idUsuario = Integer.parseInt(parts[0]);
                String nombre = parts[1];
                String especialidad = parts[2];
                this.doctores.add(new Doctor(idUsuario, nombre, especialidad));
            }
        }
        readerDoc.close();
        BufferedReader readerCita = new BufferedReader(new FileReader("db/citas.csv"));
        while((line = readerCita.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int idCita = Integer.parseInt(parts[0]);
                String motivo = parts[1];
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
        boolean exit=false;
        Evidencia ev = new Evidencia();
        while(!exit) {
            if(ev.sesionIniciada) {
                System.out.println("\nMenú Principal\n" +
                        "1. Iniciar Sesión de Administrador\n" +
                        "2. Registrar Doctor\n" +
                        "3. Registrar Paciente\n" +
                        "4. Crear Cita\n" +
                        "5. Mostrar Citas por Doctor\n" +
                        "6. Mostrar Citas por Paciente\n" +
                        "7. Cerrar Sesión de Administrador\n" +
                        "8. Salir\n");
            }else{
                System.out.println("Menú Principal\n" +
                        "1. Iniciar Sesión de Administrador\n" +
                        "8. Salir\n");
            }
            System.out.print("Seleccione una opción:");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            if((choice!= 1 && choice!=8) && !ev.sesionIniciada){
                System.out.println("Se requiere iniciar sesión primero antes de poder acceder a otras opciones.");
            }else{
                switch (choice) {
                    case 1:
                        if(ev.sesionIniciada){
                            System.out.println("La sesión está iniciada, ¿Deseas cambiar de usuario? Y/N");
                            String respuesta = scanner.nextLine();
                            if(respuesta.toUpperCase().equals("Y")){
                                ev.sesionIniciada=false;
                            }else{
                                break;
                            }
                        }
                        if(!ev.sesionIniciada) {
                            System.out.print("Ingrese el id del usuario administrador:");
                            int idUsuario = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Ingrese la contraseña:");
                            String contrasena = scanner.nextLine();
                            try {
                                ev.sesionIniciada = new Usuario().iniciarSesionAdministrador(idUsuario, contrasena);
                            } catch (IOException e) {
                                System.out.println("Se obtuvo un error al leer la db de administradores: " + e);
                            }
                        }
                        break;
                    case 2:
                        System.out.print("Ingrese el id del usuario del doctor a registrar:");
                        int idUsuario = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese el nombre del doctor a registrar:");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese la especialidad del doctor a registrar:");
                        String especialidad = scanner.nextLine();
                        Doctor nuevodoctor = new Doctor(idUsuario,nombre,especialidad);
                        ev.doctores = nuevodoctor.registrarDoctor(ev.doctores,nuevodoctor);
                        break;
                    case 3:
                        System.out.print("Ingrese el id del paciente a registrar:");
                        idUsuario = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese el nombre del paciente a registrar:");
                        nombre = scanner.nextLine();
                        Paciente nuevopaciente = new Paciente(idUsuario,nombre);
                        ev.pacientes = nuevopaciente.registrarPaciente(ev.pacientes,nuevopaciente);
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        exit=true;
                        break;
                    default:
                        System.out.println("La opción elegida no es valida, intente nuevamente.");
                }
            }
        }
        try {
            ev.guardarDatosEnArchivos();
        } catch (IOException e) {
            System.out.println("Error al guardar en archivos: " + e);
        }
    }
}

