import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Inicio de Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PanelInicio(frame));
        frame.pack();
        frame.setVisible(true);
    }
}

class PanelInicio extends JPanel {
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public PanelInicio(JFrame parentFrame) {
        setLayout(null);
        setPreferredSize(new Dimension(400, 300));

        JLabel lblUsername = new JLabel("Usuario o correo:");
        lblUsername.setBounds(50, 50, 150, 25);
        add(lblUsername);

        txtUsername = new JTextField(15);
        txtUsername.setBounds(200, 50, 150, 25);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 100, 150, 25);
        add(lblPassword);

        txtPassword = new JPasswordField(15);
        txtPassword.setBounds(200, 100, 150, 25);
        add(txtPassword);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setBounds(50, 150, 100, 25);
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());
            // Verificar si el usuario es "admin" y la contraseña es "1234"
            if (username.equals("admin") && password.equals("1234")) {
                parentFrame.getContentPane().removeAll(); // Limpiar panel anterior
                parentFrame.getContentPane().add(new PanelPsicologo(parentFrame)); // Cambiar al panel del psicólogo
                parentFrame.revalidate();
                parentFrame.repaint();
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Usuario o contraseña incorrectos.");
            }
        });
        add(btnLogin);

        JButton btnRegister = new JButton("Registrarse");
        btnRegister.setBounds(200, 150, 150, 25);
        btnRegister.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new PanelRegistro(parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        add(btnRegister);
    }
}

class PanelRegistro extends JPanel {
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;

    public PanelRegistro(JFrame parentFrame) {
        setLayout(null);
        setPreferredSize(new Dimension(400, 300));

        JLabel lblUsername = new JLabel("Nombre de usuario:");
        lblUsername.setBounds(50, 50, 150, 25);
        add(lblUsername);

        txtUsername = new JTextField(15);
        txtUsername.setBounds(200, 50, 150, 25);
        add(txtUsername);

        JLabel lblEmail = new JLabel("Correo institucional:");
        lblEmail.setBounds(50, 100, 150, 25);
        add(lblEmail);

        txtEmail = new JTextField(15);
        txtEmail.setBounds(200, 100, 150, 25);
        add(txtEmail);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 150, 150, 25);
        add(lblPassword);

        txtPassword = new JPasswordField(15);
        txtPassword.setBounds(200, 150, 150, 25);
        add(txtPassword);

        JLabel lblConfirmPassword = new JLabel("Repetir contraseña:");
        lblConfirmPassword.setBounds(50, 200, 150, 25);
        add(lblConfirmPassword);

        txtConfirmPassword = new JPasswordField(15);
        txtConfirmPassword.setBounds(200, 200, 150, 25);
        add(txtConfirmPassword);

        JButton btnCreateAccount = new JButton("Crear cuenta");
        btnCreateAccount.setBounds(50, 250, 150, 25);
        btnCreateAccount.addActionListener(e -> {
            String username = txtUsername.getText();
            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Todos los campos son obligatorios.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(parentFrame, "Las contraseñas no coinciden.");
                return;
            }

            JOptionPane.showMessageDialog(parentFrame, "Cuenta creada para " + username + ".");
            parentFrame.getContentPane().removeAll(); // Limpiar panel anterior
            parentFrame.getContentPane().add(new PanelPsicologo(parentFrame)); // Cambiar al panel del psicólogo
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        add(btnCreateAccount);
    }
}

class PanelPsicologo extends JPanel {
    public PanelPsicologo(JFrame parentFrame) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));

        JPanel panelSuperior = new JPanel();
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(e -> {
            parentFrame.getContentPane().removeAll(); // Limpiar panel anterior
            parentFrame.getContentPane().add(new PanelInicio(parentFrame)); // Volver al panel de inicio de sesión
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        panelSuperior.add(btnCerrarSesion);
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new GridLayout(5, 1));
        JButton btnVerPreguntas = new JButton("Ver Preguntas Publicadas");
        JButton btnVerRespuestas = new JButton("Ver Respuestas Publicadas");
        JButton btnVerPublicacionesPropias = new JButton("Ver Mis Publicaciones");
        JButton btnEditarPerfil = new JButton("Editar Perfil");
        JButton btnBuscar = new JButton("Buscar Publicaciones");

        btnVerPreguntas.addActionListener(e -> {
            String pregunta = JOptionPane.showInputDialog(parentFrame, "Escribe tu pregunta:");
            if (pregunta != null && !pregunta.trim().isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Pregunta publicada: " + pregunta);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "No se ingresó ninguna pregunta.");
            }
        });

        btnVerRespuestas.addActionListener(e -> {
            String respuesta = JOptionPane.showInputDialog(parentFrame, "Escribe tu respuesta:");
            if (respuesta != null && !respuesta.trim().isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Respuesta publicada: " + respuesta);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "No se ingresó ninguna respuesta.");
            }
        });

        btnVerPublicacionesPropias.addActionListener(e -> JOptionPane.showMessageDialog(parentFrame, "Aquí se mostrarán mis publicaciones."));
        btnEditarPerfil.addActionListener(e -> JOptionPane.showMessageDialog(parentFrame, "Funcionalidad de editar perfil aún no implementada."));
        btnBuscar.addActionListener(e -> {
            String keyword = JOptionPane.showInputDialog(parentFrame, "Introduce la palabra clave para buscar:");
            if (keyword != null && !keyword.trim().isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Buscando publicaciones con: " + keyword);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "No se introdujo ninguna palabra clave.");
            }
        });

        panelIzquierdo.add(btnVerPreguntas);
        panelIzquierdo.add(btnVerRespuestas);
        panelIzquierdo.add(btnVerPublicacionesPropias);
        panelIzquierdo.add(btnEditarPerfil);
        panelIzquierdo.add(btnBuscar);
        add(panelIzquierdo, BorderLayout.WEST);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new CardLayout());

        JPanel panelPreguntas = new JPanel();
        panelPreguntas.add(new JLabel("Aquí se mostrarán las preguntas..."));
        panelCentral.add(panelPreguntas, "Preguntas");

        JPanel panelRespuestas = new JPanel();
        panelRespuestas.add(new JLabel("Aquí se mostrarán las respuestas..."));
        panelCentral.add(panelRespuestas, "Respuestas");

        JPanel panelMisPublicaciones = new JPanel();
        panelMisPublicaciones.add(new JLabel("Aquí se mostrarán mis publicaciones..."));
        panelCentral.add(panelMisPublicaciones, "MisPublicaciones");

        add(panelCentral, BorderLayout.CENTER);
    }
}
