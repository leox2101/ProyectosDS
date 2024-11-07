package aplicacionSaludMental;

import java.io.*;

public class AlmacenamientoRespuestas {

    public static void guardarRespuesta(String pregunta, String respuesta) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("respuestas.txt", true))) {
            System.out.println("Abriendo el archivo respuestas.txt para guardar la respuesta.");

            pw.println("Pregunta: " + pregunta);
            pw.println("Respuesta: " + respuesta);
            pw.println();

            System.out.println("Respuesta guardada con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar la respuesta.");
            e.printStackTrace();
        }
    }
}
