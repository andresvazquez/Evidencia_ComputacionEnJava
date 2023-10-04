## Evidencia_ComputacionEnJava
Evidencia del curso Computación en Java

Este proyecto es una aplicación de administración de citas médicas para un consultorio clínico, desarrollado por Andrés Eduardo Vázquez Pérez (Matrícula: AL03065873).

## Instalación y configuración
Para crear un archivo JAR en IntelliJ IDEA, sigue estos pasos:

1.Abre el proyecto en IntelliJ IDEA.

2.Asegúrate de que el proyecto esté configurado correctamente como un proyecto Java y que tengas todas las dependencias incluidas en el proyecto.

3.Ve al menú "File" (Archivo) en la parte superior izquierda y selecciona "Project Structure" (Estructura del proyecto).

4.En la ventana de configuración del proyecto, en la sección de "Project Settings," selecciona "Artifacts."

5.Haz clic en el botón "+" en la parte superior izquierda y elige "JAR" > "From modules with dependencies."

6.Selecciona el módulo de tu proyecto en la lista desplegable "Module" (Módulo).

7.Asegúrate de que el campo "Main Class" (Clase principal) esté configurado correctamente. Esto debe apuntar a la clase que contiene el método main de tu aplicación "Evidencia.java".

8.Haz clic en "OK" para cerrar la ventana de configuración de artifacts.

9.En la parte superior de la ventana de IntelliJ IDEA, selecciona "Build" (Construir) > "Build Artifacts."

10.En el menú emergente, selecciona la opción que corresponde a tu JAR (por lo general, es "Build").

11.IntelliJ IDEA construirá tu JAR y lo colocará en una carpeta llamada "out" en tu proyecto.

12.Encuentra el JAR generado en la carpeta "out" de tu proyecto.

13.Abre tu terminal.

14.Navega al directorio que contiene el archivo JAR usando el comando cd. Por ejemplo:

cd /ruta/a/tu/directorio

15.Una vez que estés en el directorio correcto, ejecuta el archivo JAR utilizando el comando java -jar seguido del nombre del archivo JAR. Por ejemplo:

java -jar Evidencia_ComputacionEnJava_jar/Evidencia_ComputacionEnJava.jar

## Uso del Programa
-Iniciar Sesión de Administrador: Permite iniciar sesión como administrador para acceder a las funciones de administración.

-Registrar Médico: Permite dar de alta un nuevo médico con su identificador único, nombre completo y especialidad.

-Registrar Paciente: Permite registrar un paciente con su identificador único y nombre completo.

-Crear Cita: Permite crear una nueva cita médica, asociándola con un médico y un paciente, e ingresando la fecha, hora y motivo de la cita.

-Mostrar Citas por Médico: Muestra todas las citas relacionadas con un médico seleccionado.

-Mostrar Citas por Paciente: Muestra todas las citas relacionadas con un paciente seleccionado.

-Cerrar Sesión: Finaliza la sesión de administrador.

-Salir: Sale del programa.

## Creditos
Autor: Andrés Eduardo Vázquez Pérez.
Matricula: AL03065873.
Universidad: Tec Milenio.

## Licencia
Este proyecto está bajo la [GNU General Public License, Versión 3](LICENSE). Consulta el archivo [LICENSE](LICENSE) para obtener más información sobre los términos y condiciones de la licencia.


