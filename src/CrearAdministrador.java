import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearAdministrador extends JFrame{
    private JButton crearAdministradorButton;
    private JTextField idADMIN;
    private JTextField UsuarioADMIN;
    private JTextField ConADMIN;
    private JPanel panel1;

    public CrearAdministrador(){
        setTitle("Creacion de administradores");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel1);

        crearAdministradorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CAdministrador();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void CAdministrador() throws SQLException {
        String IDA=idADMIN.getText();
        String USUA=UsuarioADMIN.getText();
        String CONA=ConADMIN.getText();

        Connection conecta=conexion();
        String sql="insert into administrador(id_administrador,usuario,contrasenia) values(?,?,?)";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setInt(1,Integer.parseInt(IDA));
        pstmt.setString(2,USUA);
        pstmt.setString(3,CONA);

        int administrador=pstmt.executeUpdate();
        if (administrador>0){
            JOptionPane.showMessageDialog(null,"Administrador ingresado con EXITO");
            idADMIN.setText("");
            UsuarioADMIN.setText("");
            ConADMIN.setText("");
        }else {
            JOptionPane.showMessageDialog(null,"Fallo al ingresar el administrador");
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
