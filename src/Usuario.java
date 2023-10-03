public class Usuario {
    private int idUsuario;
    private String nombre, contrasena;

    public Usuario(){
        this.idUsuario = -1;
        this.nombre = "N/A";
        this.contrasena = "";
    }
    public Usuario(int idUsuario, String nombre, String contrasena){
        this.idUsuario= idUsuario;
        this.nombre=nombre;
        this.contrasena=contrasena;
    }
    public Usuario(int idUsuario, String nombre){
        this.idUsuario= idUsuario;
        this.nombre=nombre;
    }

    public void iniciarSesionAdministrador(){

    }
    public void cerrarSesion(){

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario){
        this.idUsuario=idUsuario;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
