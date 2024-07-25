import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VerHabitaciones extends JFrame{
    private JButton reservarHabitacionButton;
    private JTextField IDh;
    private JTextField tip;
    private JTextField pre;
    private JTextField des;
    private JTextField est;
    private JTextField num;
    private JTextField wif;
    private JTextField tv;
    private JTextField air;
    private JTextField min;
    private JTextField vis;
    private JTextField textField12;
    private JPanel panel1;

    public VerHabitaciones(){
        setContentPane(panel1);
    }

    public void LasHabitaciones(){

    }



    public void Iniciar(){
        setVisible(true);
        setTitle("Creacion de administradores");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public Connection conexion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/proyectoPOO";
        String user="root";
        String password="123456";
        return DriverManager.getConnection(url,user,password);
    }
}
