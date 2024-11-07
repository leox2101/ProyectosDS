package aplicacionSaludMental;
import aplicacionSaludMental.PanelEditarUsuario;
import aplicacionSaludMental.AlmacenamientoPreguntas;
import aplicacionSaludMental.AlmacenamientoRespuestas;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PanelPsicologo extends JPanel {
    public static JPanel textPanel = new JPanel();
    private static ArrayList<String> preguntas = new ArrayList<>();

    public PanelPsicologo(JFrame parentFrame,String username) {

        cargarRespuestas(); // Cargar respuestas después de cargar las preguntas
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel panelCentral = new JPanel(new CardLayout());
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.white);
        panelCentral.add(textPanel, "TextPanel");

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelSuperior = new JPanel();
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(e -> {
            AlmacenamientoPreguntas.eliminarPreguntas();
            AlmacenamientoRespuestas.eliminarRespuestas();
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new PanelInicio(parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
            
        });
        panelSuperior.add(btnCerrarSesion);
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridLayout(5, 1));
        JButton btnVerPreguntas = new JButton("Escribir Pregunta ");
        JButton btnVerRespuestas = new JButton("Escribir Respuesta");
        JButton btnVerPublicacionesPropias = new JButton("Ver Mis Publicaciones");
        JButton btnEditarPerfil = new JButton("Editar Perfil");
        JButton btnBuscar = new JButton("Buscar Publicaciones");

        btnVerPreguntas.addActionListener(e -> {
            String pregunta = JOptionPane.showInputDialog(parentFrame, "Escribe tu pregunta:");
            if (pregunta != null && !pregunta.trim().isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Pregunta publicada: " + pregunta);
                AlmacenamientoPreguntas.guardarPregunta(pregunta);
                agregarPregunta(pregunta);
                preguntas.add(pregunta); // Añadimos la pregunta a la lista para su posterior selección
            } else {
                JOptionPane.showMessageDialog(parentFrame, "No se ingresó ninguna pregunta.");
            }
        });

        btnVerRespuestas.addActionListener(e -> responderPregunta(parentFrame));

        panelIzquierdo.add(btnVerPreguntas);
        panelIzquierdo.add(btnVerRespuestas);
        panelIzquierdo.add(btnVerPublicacionesPropias);
        panelIzquierdo.add(btnEditarPerfil);
        panelIzquierdo.add(btnBuscar);
        add(panelIzquierdo, BorderLayout.WEST);
        

        btnEditarPerfil.addActionListener(e -> {
            parentFrame.getContentPane().removeAll(); // Limpia todo el contenido del frame principal
            PanelEditarUsuario panelEditarUsuario = new PanelEditarUsuario(parentFrame, username);
            parentFrame.getContentPane().add(panelEditarUsuario); // Agrega PanelEditarUsuario directamente al frame
            parentFrame.revalidate();
            parentFrame.repaint();
            }
        );
    }
    
    

    
    public static void agregarPregunta(String pregunta) {
        JPanel panelPregunta = new JPanel(new BorderLayout());
        panelPregunta.setBackground(Color.WHITE);

        JLabel lblPregunta = new JLabel(pregunta);
        panelPregunta.add(lblPregunta, BorderLayout.NORTH);

        textPanel.add(panelPregunta, BorderLayout.CENTER);
        textPanel.revalidate();
        textPanel.repaint();
    }

    public static void agregarPreguntas(String pregunta, ArrayList<String> respuestas) {
        JPanel panelPregunta = new JPanel(new BorderLayout());
        panelPregunta.setBackground(Color.WHITE);

        JLabel lblPregunta = new JLabel(pregunta);
        panelPregunta.add(lblPregunta, BorderLayout.NORTH);

        JPanel panelRespuestas = new JPanel();
        panelRespuestas.setLayout(new BoxLayout(panelRespuestas, BoxLayout.Y_AXIS));
        for (String respuesta : respuestas) {
            JLabel lblRespuesta = new JLabel("- " + respuesta);
            panelRespuestas.add(lblRespuesta);
        }
        panelPregunta.add(panelRespuestas, BorderLayout.CENTER);

        textPanel.add(panelPregunta);
        textPanel.revalidate();
        textPanel.repaint();
    }

    public void responderPregunta(JFrame parentFrame) {
        String pregunta = (String) JOptionPane.showInputDialog(parentFrame, "Selecciona la pregunta a responder:",
                "Responder Pregunta", JOptionPane.QUESTION_MESSAGE, null, preguntas.toArray(), preguntas.get(0));

        if (pregunta != null) {
            String respuesta = JOptionPane.showInputDialog(parentFrame, "Escribe tu respuesta:");
            if (respuesta != null && !respuesta.trim().isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Respuesta publicada: " + respuesta);
                AlmacenamientoRespuestas.guardarRespuesta(pregunta, respuesta);
                agregarRespuestaAPregunta(pregunta, respuesta);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "No se ingresó ninguna respuesta.");
            }
        }
    }

    public void agregarRespuestaAPregunta(String pregunta, String respuesta) {
        for (Component component : textPanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel panelPregunta = (JPanel) component;
                JLabel lblPregunta = (JLabel) panelPregunta.getComponent(0);

                if (lblPregunta.getText().equals(pregunta)) {
                    JPanel panelRespuestas;

                    if (panelPregunta.getComponentCount() > 1) {
                        panelRespuestas = (JPanel) panelPregunta.getComponent(1);
                    } else {
                        panelRespuestas = new JPanel();
                        panelRespuestas.setLayout(new BoxLayout(panelRespuestas, BoxLayout.Y_AXIS));
                        panelPregunta.add(panelRespuestas, BorderLayout.CENTER);
                    }

                    JLabel lblRespuesta = new JLabel("- " + respuesta);
                    panelRespuestas.add(lblRespuesta);
                    panelPregunta.revalidate();
                    panelPregunta.repaint();
                    break;
                }
            }
        }
    }


    public static void cargarRespuestas() {
        try (BufferedReader br = new BufferedReader(new FileReader("respuestas.txt"))) {
            String linea;
            String pregunta = null;
            ArrayList<String> respuestas = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Pregunta: ")) {
                    if (pregunta != null) {
                        agregarPreguntas(pregunta, respuestas);
                    }
                    pregunta = linea.substring(10).trim();
                    respuestas = new ArrayList<>();
                } else if (linea.startsWith("Respuesta: ")) {
                    respuestas.add(linea.substring(11).trim());
                }
            }

            if (pregunta != null) {
                agregarPreguntas(pregunta, respuestas);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
