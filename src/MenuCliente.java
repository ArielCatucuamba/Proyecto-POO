import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCliente extends JFrame{
    private JButton reservarHabitacionButton;
    private JButton verHabitacionButton;
    private JPanel panel1;

    public MenuCliente(){
        setContentPane(panel1);
        verHabitacionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                VerHabitaciones v1 = new VerHabitaciones();
                v1.Iniciar();
                dispose();
            }
        });
    }
    public void Iniciar(){
        setVisible(true);
        setTitle("Creacion de administradores");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


}
