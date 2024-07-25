import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Roles extends JFrame {
    private JButton Administrador;
    private JButton clienteButton;
    private JButton personalButton;
    private JPanel panel1;

    public Roles(){
        setTitle("Menu de roles");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel1);

    }

}
