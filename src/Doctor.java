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

    public void registrarDoctor(){

    }

    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
