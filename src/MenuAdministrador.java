import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdministrador extends JFrame{
    private JButton crearButton;
    private JButton eliminarButton;
    private JButton actualizarButton;
    private JButton leerButton;
    private JButton regresarAlMenuRolesButton;
    private JPanel panel1;

    public MenuAdministrador(){
        setContentPane(panel1);

        crearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CrearAdministrador c1 = new CrearAdministrador();
                c1.Iniciar();
                dispose();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EliminarAdministrador e1 = new EliminarAdministrador();
                e1.Iniciar();
                dispose();
            }
        });


        actualizarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ActualizarAdministrador a1 = new ActualizarAdministrador();
                a1.Iniciar();
                dispose();
            }
        });

        leerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                LeerAdministrador l1 = new LeerAdministrador();
                l1.Iniciar();
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
