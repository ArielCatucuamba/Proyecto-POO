import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LeerAdministrador extends JFrame{
    private JButton verInfromacionDelAdministradorButton;
    private JTextField IDADMIN;
    private JPanel Panel1;
    public LeerAdministrador(){
        setTitle("Infromacion de los administradores");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(Panel1);

        verInfromacionDelAdministradorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InformaciondelAdministrador();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void InformaciondelAdministrador() throws SQLException {
        String id=IDADMIN.getText();
        Connection conecta=conexion();
        String sql="select * from administrador where id_administrador=?";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setInt(1,Integer.parseInt(id));
        ResultSet rs= pstmt.executeQuery();
        if (rs.next()){
            JOptionPane.showMessageDialog(null,"ADMINISTRADOR ENCONTRADO");
            String idADM=rs.getString("id_administrador");
            String usuADM=rs.getString("usuario");
            String conADM=rs.getString("contrasenia");
            JOptionPane.showMessageDialog(null, "ID del administrador: " + idADM +
                    "\nUsuario: " + usuADM+"\nNombre:");


        }else {
            JOptionPane.showMessageDialog(null,"ADMINISTRADOR NO! ENCONTRADO");

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
