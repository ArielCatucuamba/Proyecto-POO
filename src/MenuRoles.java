import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuRoles extends JFrame{
    private JButton accederComoClienteButton;
    private JButton accederComoAdministradorButton;
    private JButton accederComoPersonalButton;
    private JPanel panel1;

    public MenuRoles(){
        setTitle("Ingreso segun el rol");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel1);

        accederComoClienteButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuCliente m2=new MenuCliente();
                m2.Iniciar();
                dispose();
            }
        });

        accederComoAdministradorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MenuRoles m1 = new MenuRoles();
                m1.Iniciar();
                dispose();
            }
        });
        accederComoPersonalButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

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
