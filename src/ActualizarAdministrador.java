import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarAdministrador extends JFrame {
    private JTextField columna;
    private JTextField nvalor;
    private JTextField idADM;
    private JPanel Panel1;
    private JButton ActualizarADMIN;

    public ActualizarAdministrador(){
        setTitle("Actualizacion de los administradores");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(Panel1);

        ActualizarADMIN.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ACADMIN();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void ACADMIN() throws SQLException {
        String COL=columna.getText();
        String VAL=nvalor.getText();
        String ID=idADM.getText();

        Connection conecta=conexion();
        String sql="update administrador set "+COL+"=? where id_administrador=?";
        PreparedStatement pstmt=conecta.prepareStatement(sql);
        pstmt.setString(1,VAL);
        pstmt.setInt(2,Integer.parseInt(ID));

        int ariel= pstmt.executeUpdate();
        if (ariel>0){
            JOptionPane.showMessageDialog(null,"ADMINISTRADOR ACTUALIZADO");
            columna.setText("");
            nvalor.setText("");
            idADM.setText("");
        }else {
            JOptionPane.showMessageDialog(null,"FALLO AL ACTUALIZAR ");
            columna.setText("");
            nvalor.setText("");
            idADM.setText("");
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
