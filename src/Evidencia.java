import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
            writerCita.write(cita.getIdCita()+ "," + cita.getMotivo() + "," + cita.getFechaHora() + "," +cita.getIdDoc() + "," + cita.getIdPac());
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
            if (parts.length == 5) {
                int idCita = Integer.parseInt(parts[0]);
                String motivo = parts[1];
                LocalDateTime fechaHora = LocalDateTime.parse(parts[2]);
                int idDoc = Integer.parseInt(parts[3]);
                int idPac = Integer.parseInt(parts[4]);
                Cita cita = new Cita(idCita,motivo,fechaHora,idDoc,idPac);
                this.citas.add(cita);
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

    public Doctor buscarDoctorPorId(int id){
        for(Doctor doc : this.doctores){
            if(doc.getIdUsuario()==id){
                return doc;
            }
        }
        System.out.println("El doctor no existe.");
        return null;
    }

    public Paciente buscarPacientePorId(int id) {
        for (Paciente pac : this.pacientes) {
            if (pac.getIdUsuario() == id) {
                return pac;
            }
        }
        System.out.println("El paciente no existe.");
        return null;
    }

    public void mostrarCitasPorDoctor(int idDoc){
        int numcita=1;
        for(Cita cita : citas){
            if(idDoc==cita.getIdDoc()){
                System.out.format("Citas del doctor %s:\n"
                        + "Cita #%s\n"
                        + "ID cita:%s\n"
                        + "Motivo:%s\n"
                        + "Fecha hora:%s\n"
                        + "Paciente:%s\n"
                ,buscarDoctorPorId(cita.getIdDoc()).getNombre(),numcita,cita.getIdCita(),cita.getMotivo(),cita.getFechaHora().toString(),buscarPacientePorId(cita.getIdPac()).getNombre());
                numcita++;
            }
        }
    }

    public void mostrarCitasPorPaciente(int idPac){
        int numcita=1;
        for(Cita cita : citas){
            if(idPac==cita.getIdPac()){
                System.out.format("Citas del paciente %s:\n"
                                + "Cita #%s\n"
                                + "ID cita:%s\n"
                                + "Motivo:%s\n"
                                + "Fecha hora:%s\n"
                                + "Paciente:%s\n"
                        ,buscarPacientePorId(cita.getIdPac()).getNombre(),numcita,cita.getIdCita(),cita.getMotivo(),cita.getFechaHora().toString(),buscarDoctorPorId(cita.getIdDoc()).getNombre());
                numcita++;
            }
        }
    }

    public void Salir(){
        System.out.println("Saliendo del programa...");
        try {
            guardarDatosEnArchivos();
        } catch (IOException e) {
            System.out.println("Error al guardar en archivos: " + e);
        }
    }

    public void CerrarSesion(){
        this.sesionIniciada=false;
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
            int choice=-1;
            try {
                 choice= scanner.nextInt();
                scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Se ingreso un caracter invalido como opción: " +e);
            }

            if((choice>= 2 && choice<=7) && !ev.sesionIniciada){
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
                            int idUsuario = 0;
                            try {
                                idUsuario = scanner.nextInt();
                                scanner.nextLine();
                            }catch (InputMismatchException e){
                                System.out.println("Se ingreso un caracter invalido como id:" +e);
                                break;
                            }
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
                        int idUsuario=0;
                        try {
                            idUsuario = scanner.nextInt();
                            scanner.nextLine();
                        }catch (InputMismatchException e){
                            System.out.println("Se ingreso un caracter invalido como id:" +e);
                            break;
                        }
                        if(idUsuario<0 || idUsuario>2000){
                            System.out.println("El id se sale del rango de IDs validos, favor de ingresar un id entre 0 y 2000.\nSe cancela registro.");
                            break;
                        }
                        System.out.print("Ingrese el nombre del doctor a registrar:");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese la especialidad del doctor a registrar:");
                        String especialidad = scanner.nextLine();
                        Doctor nuevodoctor = new Doctor(idUsuario,nombre,especialidad);
                        ev.doctores = nuevodoctor.registrarDoctor(ev.doctores,nuevodoctor);
                        break;
                    case 3:
                        System.out.print("Ingrese el id del paciente a registrar:");
                        try{
                            idUsuario = scanner.nextInt();
                            scanner.nextLine();
                        }catch (InputMismatchException e){
                            System.out.println("Se ingreso un caracter invalido como id:" +e);
                            break;
                        }
                        if(idUsuario<0 || idUsuario>2000){
                            System.out.println("El id se sale del rango de IDs validos, favor de ingresar un id entre 0 y 2000.\nSe cancela registro.");
                            break;
                        }
                        System.out.print("Ingrese el nombre del paciente a registrar:");
                        nombre = scanner.nextLine();
                        Paciente nuevopaciente = new Paciente(idUsuario,nombre);
                        ev.pacientes = nuevopaciente.registrarPaciente(ev.pacientes,nuevopaciente);
                        break;
                    case 4:
                        System.out.print("Ingrese el id de la cita a registrar:");
                        int idCita=0;
                        try{
                            idCita = scanner.nextInt();
                            scanner.nextLine();
                        }catch (InputMismatchException e){
                            System.out.println("Se ingreso un caracter invalido como id:" +e);
                            break;
                        }
                        if(idCita<0 || idCita>2000){
                            System.out.println("El id se sale del rango de IDs validos, favor de ingresar un id entre 0 y 2000.\nSe cancela registro.");
                            break;
                        }
                        boolean checaSiExiste = false;
                        for (Cita cita : ev.citas){
                            if(cita.getIdCita()==idCita){
                                System.out.println("El id de cita ya existe, se cancela registro.");
                                checaSiExiste=true;
                                break;
                            }
                        }
                        if(checaSiExiste){
                            break;
                        }
                        System.out.print("Ingrese el motivo de la cita a registrar:");
                        String motivo = scanner.nextLine();
                        System.out.print("Ingrese el día de la cita:");
                        int dia =0;
                        try{
                            dia = scanner.nextInt();
                        }catch (InputMismatchException e){
                            System.out.println("Se ingreso un caracter invalido en vez de número día:" +e);
                            break;
                        }
                        if(dia<1 || dia>31){
                            System.out.println("Este no es un día del mes valido, se cancela registro.");
                            break;
                        }
                        System.out.print("Ingrese el mes de la cita:");
                        int mes =0;
                        try{
                            mes = scanner.nextInt();
                        }catch (InputMismatchException e){
                            System.out.println("Se ingreso un caracter invalido en vez de número del mes:" +e);
                            break;
                        }
                        if(mes<1 || mes>12){
                            System.out.println("Este no es un mes valido, se cancela registro.");
                            break;
                        }
                        System.out.print("Ingrese el año de la cita:");
                        int year=0;
                        try{
                        year = scanner.nextInt();
                        scanner.nextLine();
                        }catch (InputMismatchException e){
                            System.out.println("Se ingreso un caracter invalido en vez del número de año:" +e);
                            break;
                        }
                        if(year<2023 || year>2100){
                            System.out.println("Este no es un año valido, favor de registrar entre el 2023 y 2100, se cancela registro.");
                            break;
                        }
                        System.out.print("Ingrese la hora de la cita:");
                        String hora = scanner.nextLine();
                        if(hora.length()>5 || !hora.contains(":")){
                            System.out.println("Esta no es una hora valida, favor de registrar hora valida, ejemplo '15:45', se cancela registro.");
                            break;
                        }
                        String[] parts = hora.split(":");
                        try{
                            if(Integer.parseInt(parts[0])<0 || Integer.parseInt(parts[0])>23){
                                System.out.println("Esta no es una hora valida, favor de registrar hora valida, ejemplo '15:45', se cancela registro.");
                                break;
                            }
                            if(Integer.parseInt(parts[1])<0 || Integer.parseInt(parts[1])>59){
                                System.out.println("Esta no es una hora valida, favor de registrar hora valida, ejemplo '15:45', se cancela registro.");
                                break;
                            }
                        }catch (NumberFormatException e){
                            System.out.println("La hora no es númerica, el programa da error: " + e);
                            break;
                        }
                        LocalDateTime fechaHora = LocalDateTime.of(year, mes, dia, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), 0);
                        System.out.print("Ingrese el id del doctor asignado a la cita:");
                        int idDoc=0;
                        try{
                            idDoc = scanner.nextInt();
                        }catch (InputMismatchException e){
                            System.out.println("Se ingreso un caracter invalido en vez del id númerico:" +e);
                            break;
                        }
                        System.out.print("Ingrese el id del paciente asignado a la cita:");
                        int idPac=0;
                        try {
                            idPac = scanner.nextInt();
                        }catch (InputMismatchException e){
                            System.out.println("Se ingreso un caracter invalido en vez del id númerico:" +e);
                            break;
                        }
                        Cita citanueva = new Cita(idCita,motivo,fechaHora,idDoc,idPac);
                        citanueva.crearCita(ev.buscarDoctorPorId(idDoc),ev.buscarPacientePorId(idPac));
                        if(citanueva.getIdDoc()==-1 || citanueva.getIdPac()==-1){
                            System.out.println("A la cita le falta asignar un doctor y/o un paciente existente, se cancela registro.");
                        }else{
                            ev.citas.add(citanueva);
                            System.out.println("Se registra cita exitosamente.");
                        }
                        break;
                    case 5:
                        System.out.print("Ingrese el id del doctor a consultar sus citas:");
                        idDoc=0;
                        try{
                            idDoc = scanner.nextInt();
                        }catch (InputMismatchException e){
                            System.out.println("Se ingreso un caracter invalido en vez del id númerico:" +e);
                            break;
                        }
                        ev.mostrarCitasPorDoctor(idDoc);
                        break;
                    case 6:
                        System.out.print("Ingrese el id del pacienta a consultar sus citas:");
                        idPac=0;
                        try{
                            idPac = scanner.nextInt();
                        }catch (InputMismatchException e){
                            System.out.println("Se ingreso un caracter invalido en vez del id númerico:" +e);
                            break;
                        }
                        ev.mostrarCitasPorPaciente(idPac);
                        break;
                    case 7:
                        ev.CerrarSesion();
                        break;
                    case 8:
                        exit=true;
                        ev.Salir();
                        break;
                    default:
                        System.out.println("La opción elegida no es valida, intente nuevamente.");
                }
            }
        }
    }
}

