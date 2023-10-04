import java.util.ArrayList;

public class Paciente extends Usuario{

    public Paciente(){
        super(-1,"N/A");
    }
    public Paciente(int idUsuario, String nombre){
        super(idUsuario,nombre);
    }
    public ArrayList<Paciente> registrarPaciente(ArrayList<Paciente> pacientes, Paciente pacienteToRegister){
        for(Paciente paciente : pacientes){
            if(paciente.getIdUsuario()==pacienteToRegister.getIdUsuario()){
                System.out.println("Este ID ya existe, no es posible registrar paciente duplicado.\n");
                return pacientes;
            }
        }
        pacientes.add(pacienteToRegister);
        System.out.format("Se ha registrado con éxito el paciente id:%s, nombre:%s.\n",pacienteToRegister.getIdUsuario(),pacienteToRegister.getNombre());
        return pacientes;
    }
}
