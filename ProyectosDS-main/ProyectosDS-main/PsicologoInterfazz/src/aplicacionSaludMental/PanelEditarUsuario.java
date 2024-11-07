package aplicacionSaludMental;

import javax.swing.*;
import java.awt.*;

public class PanelEditarUsuario extends JPanel {
    private JLabel lblUsername;
    private JButton btnEditar;
    private JButton btnVolver; // Botón para volver a la vista principal
    private String username;

    public PanelEditarUsuario(JFrame parentFrame, String usernameInicial) {
        this.username = usernameInicial;
        setLayout(new FlowLayout());

        lblUsername = new JLabel("Usuario: " + username);
        add(lblUsername);

        btnEditar = new JButton("Editar Nombre de Usuario");
        btnEditar.addActionListener(e -> {
            String oldUsername = username; // Guarda el nombre de usuario actual
            String nuevoUsername = JOptionPane.showInputDialog(parentFrame, "Ingrese nuevo nombre de usuario:", username);

            if (nuevoUsername != null && !nuevoUsername.trim().isEmpty() && !nuevoUsername.equals(username)) {
                if (!AlmacenamientoUsuario.existeUsuario(nuevoUsername, "")) {
                    username = nuevoUsername;
                    lblUsername.setText("Usuario: " + username);
                    JOptionPane.showMessageDialog(parentFrame, "Nombre de usuario actualizado con éxito.");

                    // Llama a actualizarUsuario para cambiar el nombre en la base de datos
                    AlmacenamientoUsuario.actualizarUsuario(oldUsername, username);
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "El nombre de usuario ya está en uso.");
                }
            }
        });
        add(btnEditar);

        // Botón para volver a la vista principal
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new PanelPsicologo(parentFrame, username)); // Agrega PanelPsicologo
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        add(btnVolver);
    }

    public String getUsername() {
        return username;
    }
}