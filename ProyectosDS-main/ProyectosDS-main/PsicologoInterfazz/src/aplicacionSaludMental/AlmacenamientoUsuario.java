package aplicacionSaludMental;
import java.io.*;


public class AlmacenamientoUsuario {
    private static final String RUTA_ARCHIVO = "usuarios.txt";

    public static void guardarUsuario(String username, String email, String password) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            pw.println(username + "-" + email + "-" + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean usuarioCorrecto(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("-");
                if (partes[0].equals(username) && partes[2].equals(password)) {
                    return true;
                } else if (partes[1].equals(username) && partes[2].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean existeUsuario(String username, String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("-");
                if (partes[0].equals(username) || partes[1].equals(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void actualizarUsuario(String oldUsername, String newUsername) {
        File archivoUsuarios = new File(RUTA_ARCHIVO);
        File archivoTemp = new File("usuarios_temp.txt");
    
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios));
             PrintWriter pw = new PrintWriter(new FileWriter(archivoTemp))) {
    
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("-");
    
                if (partes[0].equals(oldUsername)) {
                    // Reemplaza el nombre de usuario antiguo por el nuevo en el archivo temporal
                    partes[0] = newUsername;
                }
                // Escribe la línea (actualizada o no) en el archivo temporal
                pw.println(String.join("-", partes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Reemplaza el archivo original por el archivo temporal
        if (archivoUsuarios.delete()) {
            archivoTemp.renameTo(archivoUsuarios);
        }
    }
}
