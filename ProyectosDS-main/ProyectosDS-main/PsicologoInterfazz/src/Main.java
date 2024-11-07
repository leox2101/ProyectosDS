import aplicacionSaludMental.PanelInicio;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MentalCare");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PanelInicio(frame));
        frame.pack();
        frame.setVisible(true);
    }
}
