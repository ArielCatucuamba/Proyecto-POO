import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;

public class PantallaAdmin extends JFrame{
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox1;
    private JTextField textNombreCompleto;
    public JPanel VentanaPrincipal;
    private JTextField textEmail;
    private JTextField textTelefono;
    private JTextField textDireccion;
    private JTextField textFechaDeNacimiento;
    private JTextField textCedula;
    private JButton buscarButton1;
    private JButton eliminarButton1;
    private JButton agregarButton;
    private JButton actualizarButton1;
    private JTabbedPane tabbedPane2;
    private JTextField textIdCancha;
    private JTextField textTipoCancha;
    private JTextField textUbicacion;
    private JTextField textJugadoresCancha;
    private JTextField textPrecioCancha;
    private JButton seleccionarUnaImagenButton;
    private JButton insertarCanchaButton;
    private JTextField textField1;
    private JLabel lblFoto;
    private JTextField textID_cancha;
    private JTextArea textBuscarArea;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JLabel lblFotoVer;

    private File selectedFile; // Para almacenar el archivo de imagen seleccionado

    public PantallaAdmin() {
        super("Menu Administrador");
        setContentPane(VentanaPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(825, 550);
        setLocationRelativeTo(null);
        //Agregar
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InsertarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //Buscar Cliente
        buscarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerRegistro();
            }
        });
        //Actualizar Cliente
        actualizarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarCliente();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //Eliminar Cliente
        eliminarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedulaEliminar = textCedula.getText().trim();
                try {
                    eliminarCliente(cedulaEliminar);
                    JOptionPane.showMessageDialog(null, "Registro del Cliente Eliminado Exitosamente");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + ex.getMessage());
                }
            }
        });
        //Insertar cancha
        insertarCanchaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    IngresarCancha();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //Subir una Imagen
        seleccionarUnaImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser archivo = new JFileChooser();
                int ventana = archivo.showOpenDialog(null);
                if (ventana == JFileChooser.APPROVE_OPTION) {
                    selectedFile = archivo.getSelectedFile();
                    textField1.setText(String.valueOf(selectedFile));
                    Image foto = getToolkit().getImage(textField1.getText());
                    foto = foto.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
                    lblFoto.setIcon(new ImageIcon(foto));
                }
            }
        });
        // Buscar Cancha con ID
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //Boton Buscar y Ver Cancha
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerCancha();
            }
        });
        // Boton para Eliminar Cancha con el IdCancha
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCancha();
            }
        });
    }
    // Método para Eliminar Cancha
    public void eliminarCancha() {
        try {
            String idCancha = textID_cancha.getText().trim(); // Obtener el ID de la cancha

            if (idCancha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID de la cancha.");
                return;
            }

            Connection conecta = conexionBase();
            String query = "DELETE FROM Canchas WHERE idCancha = ?";
            PreparedStatement pstmt = conecta.prepareStatement(query);
            pstmt.setString(1, idCancha);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                JOptionPane.showMessageDialog(null, "CANCHA NO ENCONTRADA");
            } else {
                JOptionPane.showMessageDialog(null, "CANCHA ELIMINADA EXITOSAMENTE.");
            }

            pstmt.close();
            conecta.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Método para Buscar Cancha
    public void VerCancha() {
        try {
            String idCancha = textID_cancha.getText().trim(); // Obtener el ID de la cancha para las operaciones

            Connection conn = conexionBase();
            String query = "SELECT * FROM Canchas WHERE idCancha = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, idCancha);
            textBuscarArea.setText("");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String tipoCancha = rs.getString("tipo");
                String ubicacion = rs.getString("ubicacion");
                int numJugadores = rs.getInt("numJugadores");
                double precio = rs.getDouble("precio");
                byte[] imagenBytes = rs.getBytes("imagen");

                textBuscarArea.append("Tipo de Cancha: " + tipoCancha + "\n");
                textBuscarArea.append("Ubicacion: " + ubicacion + "\n");
                textBuscarArea.append("Numero de Jugadores admitidos: " + numJugadores + "\n");
                textBuscarArea.append("Precio: " + precio + "\n");

                if (imagenBytes != null) {
                    ImageIcon imageIcon = new ImageIcon(imagenBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                    lblFotoVer.setIcon(new ImageIcon(scaledImage));
                } else {
                    lblFotoVer.setIcon(null);
                }
            } else {
                textBuscarArea.append("No se encontró la cancha con el ID proporcionado.\n");
                lblFotoVer.setIcon(null);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método Ingresar datos de Cancha
    public void IngresarCancha() throws SQLException {
        String IDcancha = textIdCancha.getText();
        String tipoCancha = textTipoCancha.getText();
        String ubicacion = textUbicacion.getText();
        String numeroJugadores = textJugadoresCancha.getText();
        String precio = textPrecioCancha.getText();

        Connection conecta = conexionBase();

        String query = "INSERT INTO Canchas(idCancha, tipo, ubicacion, numJugadores, precio, imagen) VALUES(?,?,?,?,?,?)";
        PreparedStatement pstmt = conecta.prepareStatement(query);
        pstmt.setString(1, IDcancha);
        pstmt.setString(2, tipoCancha);
        pstmt.setString(3, ubicacion);
        pstmt.setString(4, numeroJugadores);
        pstmt.setDouble(5, Double.parseDouble(precio));

        // Leer el archivo de imagen y establecerlo en el PreparedStatement
        if (selectedFile != null) {
            try {
                byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());
                pstmt.setBytes(6, imageBytes);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al leer la imagen: " + ex.getMessage());
                return;
            }
        } else {
            pstmt.setNull(6, Types.BLOB);
        }

        // Ejecutar la inserción
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "REGISTRO INSERTADO CORRECTAMENTE");
        }
        pstmt.close();
        conecta.close();
    }
    // Metodo Ingresar datos de Cliente
    public void InsertarDatos() throws SQLException {
        String nombreCompleto = textNombreCompleto.getText();
        String email = textEmail.getText();
        String telefono = textTelefono.getText();
        String direccion = textDireccion.getText();
        String fechaNacimiento = textFechaDeNacimiento.getText();
        String cedula = textCedula.getText();

        Connection conecta =conexionBase();

        String query ="insert into Jugador(nombreCompleto,email,telefono,direccion,fechaNacimiento,cedula) values(?,?,?,?,?,?)";
        PreparedStatement pstmt = conecta.prepareStatement(query);
        pstmt.setString(1,nombreCompleto);
        pstmt.setString(2,email);
        pstmt.setInt(3, Integer.parseInt(telefono));
        pstmt.setString(4,direccion);
        pstmt.setString(5,fechaNacimiento);
        pstmt.setDouble(6,Double.parseDouble(cedula));
        // ACTUALIZAR
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0){
            JOptionPane.showMessageDialog(null,"REGISTRO INSERTADO CORRECTAMENTE");
        }
        pstmt.close();
        conecta.close();

    }
    //Actualizar Cliente
    public void actualizarCliente() throws SQLException {

        String cedula = textCedula.getText().trim();
        String nombre = textNombreCompleto.getText().trim();
        String email = textEmail.getText().trim();
        String telefonoStr = textTelefono.getText().trim();
        String direccion = textDireccion.getText().trim();
        String fechaNacimiento = textFechaDeNacimiento.getText().trim();

        Connection conn = conexionBase();

        // Construir la consulta dinámicamente
        StringBuilder queryBuilder = new StringBuilder("UPDATE Jugador SET ");
        boolean isFirst = true;

        if (!nombre.isEmpty()) {
            queryBuilder.append("nombreCompleto = ?");
            isFirst = false;
        }
        if (!email.isEmpty()) {
            if (!isFirst) queryBuilder.append(", ");
            queryBuilder.append("email = ?");
            isFirst = false;
        }
        if (!telefonoStr.isEmpty()) {
            if (!isFirst) queryBuilder.append(", ");
            queryBuilder.append("telefono = ?");
            isFirst = false;
        }
        if (!direccion.isEmpty()) {
            if (!isFirst) queryBuilder.append(", ");
            queryBuilder.append("direccion = ?");
            isFirst = false;
        }
        if (!fechaNacimiento.isEmpty()) {
            if (!isFirst) queryBuilder.append(", ");
            queryBuilder.append("fechaNacimiento = ?");
        }

        queryBuilder.append(" WHERE cedula = ?");

        PreparedStatement pstmt = conn.prepareStatement(queryBuilder.toString());
        int paramIndex = 1;

        if (!nombre.isEmpty()) {
            pstmt.setString(paramIndex++, nombre);
        }
        if (!email.isEmpty()) {
            pstmt.setString(paramIndex++, email);
        }
        if (!telefonoStr.isEmpty()) {
            pstmt.setInt(paramIndex++, Integer.parseInt(telefonoStr));
        }
        if (!direccion.isEmpty()) {
            pstmt.setString(paramIndex++, direccion);
        }
        if (!fechaNacimiento.isEmpty()) {
            pstmt.setString(paramIndex++, fechaNacimiento);
        }
        pstmt.setString(paramIndex, cedula);

        int filasActualizadas = pstmt.executeUpdate();
        if (filasActualizadas > 0) {
            JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente");
        }

        pstmt.close();
        conn.close();
    }
    // Método para Buscar Cliente
    public void VerRegistro() {
        try {
            //limpiarCampos(); // Limpiar campos antes de la búsqueda
            String cedula = textCedula.getText().trim(); // Obtener la cédula ingresada
            Connection conn = conexionBase();
            String query = "SELECT * FROM Jugador WHERE cedula = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cedula);
            ResultSet rs = pstmt.executeQuery();

            boolean registroEncontrado = false; // Bandera para verificar si se encontró algún registro

            while (rs.next()) {
                String Nombre = rs.getString("nombreCompleto");
                String Email = rs.getString("email");
                Integer Telefono = rs.getInt("telefono");
                String FechaNacimiento = rs.getString("fechaNacimiento");
                String Direccion = rs.getString("direccion");

                textNombreCompleto.setText(Nombre);
                textEmail.setText(Email);
                textTelefono.setText(String.valueOf(Telefono));
                textDireccion.setText(Direccion);
                textFechaDeNacimiento.setText(FechaNacimiento);

                registroEncontrado = true; // Se encontró un registro
            }

            if (!registroEncontrado) {
                JOptionPane.showMessageDialog(this, "NO SE ENCONTRO NINGUN CLIENTE");
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Eliminar Cliente
    public void eliminarCliente(String cedula) throws SQLException {
        Connection conecta = conexionBase();
        String query = "DELETE FROM Jugador WHERE cedula = ?";
        PreparedStatement pstmt = conecta.prepareStatement(query);
        pstmt.setString(1, cedula);
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected == 0) {
            JOptionPane.showMessageDialog(null, "Cedula No Encontrada");
        }

        pstmt.close();
        conecta.close();
    }



    // Metodo Conexion base
    public Connection conexionBase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/GestionDeCanchas";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url, user, password);
    }

    // Método para limpiar los campos de texto
    private void limpiarCampos() {
        textNombreCompleto.setText("");
        textEmail.setText("");
        textTelefono.setText("");
        textDireccion.setText("");
        textFechaDeNacimiento.setText("");
        textCedula.setText(""); // Limpiar la cédula ingresada también
    }




}
