import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public boolean iniciarSesionAdministrador(int idUsuario, String contrasena) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("db/administradores.csv"));
        String line;
        String motivo;
        while((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                if(Integer.parseInt(parts[0])==idUsuario){
                    if(contrasena.equals(parts[1])){
                        System.out.format("Inicio de sesión exitoso, bienvenido usuario %s \n",parts[0]);
                        return true;
                    }
                }
            }
        }
        System.out.println("El usuario y/o contraseña son incorrectos.");
        return false;
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
