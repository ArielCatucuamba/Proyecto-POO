import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MenuAdministrador extends JFrame{
    private JButton gestionarRecepcionistasButton;
    private JButton gestionarHabitacionesButton;
    private JButton volverAlMenuRolesButton;

    public MenuAdministrador() {

        gestionarRecepcionistasButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SubMenuAdministrador s1=new SubMenuAdministrador();
                s1.Iniciar();
                dispose();
            }
        });
    }
}
