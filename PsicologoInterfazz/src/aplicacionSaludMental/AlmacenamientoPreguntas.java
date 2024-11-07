package aplicacionSaludMental;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class AlmacenamientoPreguntas {

    private static Set<String> preguntasGuardadas = new HashSet<>();

    public static void guardarPregunta(String pregunta) {
        if (!preguntasGuardadas.contains(pregunta)) {
            preguntasGuardadas.add(pregunta);
            try (PrintWriter pw = new PrintWriter(new FileWriter("preguntas.txt", true))) {
                pw.println(pregunta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
