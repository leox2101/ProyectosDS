package aplicacionSaludMental;

import javax.swing.*;
import java.awt.*;

public class PanelRegistro extends JPanel {
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;

    public PanelRegistro(JFrame parentFrame) {
        setLayout(new GridBagLayout()); // Cambiamos a GridBagLayout para centrar los componentes
        setPreferredSize(new Dimension(400, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Alineación horizontal
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre componentes

        // Título "Registro de cuentas" centrado y con fuente grande
        JLabel lblTitle = new JLabel("Registro de cuentas", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24)); // Fuente más grande
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // El título ocupará las dos columnas
        add(lblTitle, gbc);

        // Nombre de usuario
        JLabel lblUsername = new JLabel("Nombre de usuario:");
        gbc.gridx = 0;
        gbc.gridy = 1; // Movemos la fila hacia abajo
        gbc.gridwidth = 1;
        add(lblUsername, gbc);

        txtUsername = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtUsername, gbc);

        // Correo institucional
        JLabel lblEmail = new JLabel("Correo institucional:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblEmail, gbc);

        txtEmail = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtEmail, gbc);

        // Contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblPassword, gbc);

        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtPassword, gbc);

        // Confirmar contraseña
        JLabel lblConfirmPassword = new JLabel("Repetir contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblConfirmPassword, gbc);

        txtConfirmPassword = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtConfirmPassword, gbc);

        // Botón Crear cuenta
        JButton btnCreateAccount = new JButton("Crear cuenta");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
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

            if(AlmacenamientoUsuario.existeUsuario(username, email)){
                JOptionPane.showMessageDialog(parentFrame, "El usuario ya existe.");
                return;
            } else {
                AlmacenamientoUsuario.guardarUsuario(username, email, password);
                JOptionPane.showMessageDialog(parentFrame, "Cuenta creada para " + username + ".");
                parentFrame.getContentPane().removeAll();
                parentFrame.getContentPane().add(new PanelPsicologo(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
        add(btnCreateAccount, gbc);
    }
}
