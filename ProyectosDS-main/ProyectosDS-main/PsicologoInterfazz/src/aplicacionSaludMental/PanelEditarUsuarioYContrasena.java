package aplicacionSaludMental;

import javax.swing.*;
import java.awt.*;

public class PanelEditarUsuarioYContrasena extends JPanel {
    private JLabel lblUsername;
    private JTextField txtNuevoUsername;
    private JPasswordField txtNuevaContrasena;
    private JPasswordField txtConfirmarContrasena;
    private JButton btnGuardar;
    private JButton btnVolver;
    private String username;

    public PanelEditarUsuarioYContrasena(JFrame parentFrame, String usernameInicial) {
        this.username = usernameInicial;
        setLayout(new GridBagLayout()); // Cambia a GridBagLayout para control más preciso
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Asegura que el componente ocupe todo el ancho
        gbc.insets = new Insets(5, 10, 5, 10); // Espacio alrededor de cada componente
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupará dos columnas de ancho

        lblUsername = new JLabel("Usuario actual: " + username);
        lblUsername.setHorizontalAlignment(JLabel.CENTER); // Centra el texto en el JLabel
        add(lblUsername, gbc);

        // Campo para el nuevo nombre de usuario
        gbc.gridy++;
        gbc.gridwidth = 1; // Restablece el ancho de una columna
        add(new JLabel("Nuevo Nombre de Usuario:"), gbc);
        
        gbc.gridx = 1;
        txtNuevoUsername = new JTextField(username);
        add(txtNuevoUsername, gbc);

        // Campos para la nueva contraseña y su confirmación
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Nueva Contraseña:"), gbc);

        gbc.gridx = 1;
        txtNuevaContrasena = new JPasswordField();
        add(txtNuevaContrasena, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Confirmar Contraseña:"), gbc);

        gbc.gridx = 1;
        txtConfirmarContrasena = new JPasswordField();
        add(txtConfirmarContrasena, gbc);

        // Botón para guardar cambios
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> {
            String oldUsername = username; // Guarda el nombre de usuario actual
            String nuevoUsername = txtNuevoUsername.getText().trim();
            String nuevaContrasena = new String(txtNuevaContrasena.getPassword());
            String confirmarContrasena = new String(txtConfirmarContrasena.getPassword());

            if (nuevoUsername.isEmpty() || nuevaContrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Todos los campos son obligatorios.");
                return;
            }

            if (!nuevaContrasena.equals(confirmarContrasena)) {
                JOptionPane.showMessageDialog(parentFrame, "Las contraseñas no coinciden.");
                return;
            }

            if (!nuevoUsername.equals(username) && AlmacenamientoUsuario.existeUsuario(nuevoUsername, "")) {
                JOptionPane.showMessageDialog(parentFrame, "El nombre de usuario ya está en uso.");
                return;
            }

            // Actualiza el nombre de usuario y la contraseña en el archivo
            AlmacenamientoUsuario.actualizarUsuarioYContrasena(oldUsername, nuevoUsername, nuevaContrasena);

            username = nuevoUsername;
            lblUsername.setText("Usuario actual: " + username);
            JOptionPane.showMessageDialog(parentFrame, "Nombre de usuario y contraseña actualizados con éxito.");
        });
        add(btnGuardar, gbc);

        // Botón para volver a la vista principal
        gbc.gridy++;
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new PanelPsicologo(parentFrame, username));
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        add(btnVolver, gbc);
    }

    public String getUsername() {
        return username;
    }
}