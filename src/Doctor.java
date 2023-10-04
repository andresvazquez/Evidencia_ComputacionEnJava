import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends Usuario {
    private String especialidad;

    public Doctor(){
        super(-1,"N/A");
        this.especialidad = "N/A";
    }
    public Doctor(int idUsuario, String nombre, String especialidad){
        super(idUsuario,nombre);
        this.especialidad=especialidad;
    }

    public ArrayList<Doctor> registrarDoctor(ArrayList<Doctor> doctores, Doctor doctorToRegister){
        for(Doctor doc : doctores){
            if(doc.getIdUsuario()==doctorToRegister.getIdUsuario()){
                System.out.println("Este ID ya existe, no es posible registrar doctor duplicado.\n");
                return doctores;
            }
        }
        doctores.add(doctorToRegister);
        System.out.format("Se ha registrado con éxito el doctor id:%s, nombre:%s, especialidad:%s.\n",doctorToRegister.getIdUsuario(),doctorToRegister.getNombre(),doctorToRegister.getEspecialidad());
        return doctores;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
