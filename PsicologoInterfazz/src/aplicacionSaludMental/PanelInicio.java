package aplicacionSaludMental;

import javax.swing.*;
import java.awt.*;

public class PanelInicio extends JPanel {
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public PanelInicio(JFrame parentFrame) {
        setLayout(new GridBagLayout()); // Cambiamos a GridBagLayout para centrar los elementos
        setPreferredSize(new Dimension(400, 300));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Alineación horizontal
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre los componentes

        // Título centrado con fuente más grande
        JLabel lblTitle = new JLabel("Bienvenidos a MentalCare", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24)); // Fuente más grande (puedes ajustar el tamaño)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // El título ocupará las dos columnas
        add(lblTitle, gbc);

        // Usuario
        JLabel lblUsername = new JLabel("Usuario o correo:");
        gbc.gridx = 0;
        gbc.gridy = 1; // Movemos la fila para que esté debajo del título
        gbc.gridwidth = 1;
        add(lblUsername, gbc);

        txtUsername = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtUsername, gbc);

        // Contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtPassword, gbc);

        // Botón Ingresar
        JButton btnLogin = new JButton("Ingresar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());

            if (AlmacenamientoUsuario.usuarioCorrecto(username, password)) {
                parentFrame.getContentPane().removeAll();
                parentFrame.getContentPane().add(new PanelPsicologo(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            } else {
                JOptionPane.showMessageDialog(parentFrame, "Usuario o contraseña incorrectos.");
            }
        });
        add(btnLogin, gbc);

        // Botón Registrarse
        JButton btnRegister = new JButton("Registrarse");
        gbc.gridx = 1;
        gbc.gridy = 3;
        btnRegister.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new PanelRegistro(parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        add(btnRegister, gbc);
    }
}
