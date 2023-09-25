## Evidencia_ComputacionEnJava
Evidencia del curso Computación en Java

Este proyecto es una aplicación de administración de citas médicas para un consultorio clínico, desarrollado por Andrés Eduardo Vázquez Pérez (Matrícula: AL03065873).

## Instalación y Configuración

Para ejecutar este programa, sigue estos pasos:

1. Clona este repositorio en tu máquina local:

   ```bash
   git clone https://github.com/andresvazquez/Evidencia_ComputacionEnJava.git

Asegúrate de tener instalado Java JDK 11 o superior.

2. Abre la terminal y navega hasta la carpeta del proyecto:

cd Evidencia_ComputacionEnJava

3. Compila el programa:

javac -cp . Main.java

4. Empaqueta el programa en un FAT JAR (incluyendo las dependencias):

jar cfm Evidencia.jar MANIFEST.MF *.class

5. Ejecuta el programa:

java -jar Evidencia.jar

##Uso del Programa
-Iniciar Sesión de Administrador: Permite iniciar sesión como administrador para acceder a las funciones de administración.

-Registrar Médico: Permite dar de alta un nuevo médico con su identificador único, nombre completo y especialidad.

-Registrar Paciente: Permite registrar un paciente con su identificador único y nombre completo.

-Crear Cita: Permite crear una nueva cita médica, asociándola con un médico y un paciente, e ingresando la fecha, hora y motivo de la cita.

-Mostrar Citas por Médico: Muestra todas las citas relacionadas con un médico seleccionado.

-Mostrar Citas por Paciente: Muestra todas las citas relacionadas con un paciente seleccionado.

-Cerrar Sesión: Finaliza la sesión de administrador.

-Salir: Sale del programa.

##Creditos
Autor: Andrés Eduardo Vázquez Pérez
Matricula: AL03065873
Universidad: Tec Milenio

## Licencia
Este proyecto está bajo la [GNU General Public License, Versión 3](LICENSE). Consulta el archivo [LICENSE](LICENSE) para obtener más información sobre los términos y condiciones de la licencia.


