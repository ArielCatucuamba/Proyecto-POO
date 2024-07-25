import javax.swing.*;
import java.sql.*;

public class LogueoCliente extends JFrame{
    private JButton verificarCredencialesButton;
    private JTextField user;
    private JTextField contra;
    public LogueoCliente(){

    }
    public void ValidacionCliente() throws SQLException {
        String usuario=user.getText();
        String contraseña=contra.getText();
        Connection conecta=conexion();
        String sql="select * from USUARIO where username=? and password=?";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setString(1,usuario);
        pstmt.setString(2,contraseña);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()){
            JOptionPane.showMessageDialog(null,"-------Credenciales correctas-------");
            MenuCliente m1=new MenuCliente();
            m1.Iniciar();
            dispose();
        }else {
            JOptionPane.showMessageDialog(null,"Credenciales incorrectas!!!!!!");
            user.setText("");
            contra.setText("");
        }
        conecta.close();
        pstmt.close();
        rs.close();
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
