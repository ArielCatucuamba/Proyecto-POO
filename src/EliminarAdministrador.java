import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarAdministrador extends JFrame {
    private JButton eliminarAdministradorButton;
    private JTextField idADMIN;
    private JPanel panel1;

    public EliminarAdministrador(){
        setTitle("Eliminacion de los administradores");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        eliminarAdministradorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EliminarADMIN();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void EliminarADMIN() throws SQLException {
        String id=idADMIN.getText();
        Connection conecta=conexion();
        String sql="delete from administrador where id_administrador=? ";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setInt(1,Integer.parseInt(id));
        int ariel = pstmt.executeUpdate();
        if (ariel>0){
            JOptionPane.showMessageDialog(null,"Administrador eliminado");

        }else {
            JOptionPane.showMessageDialog(null,"ALGO SALIO MAL ");

        }
        conecta.close();
        pstmt.close();

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
