//Importaciones
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ProveedoresGUI {
    private JFrame frame;
    private GestorProveedores gestorProveedores;

    public ProveedoresGUI(GestorProveedores gestorProveedores) {
        this.gestorProveedores = gestorProveedores;
        crearGUI();
    }

    private void crearGUI() {
        //Se genera una ventana de 600 x 400 px con el titulo de gestion de proveedores
        frame = new JFrame("Gestión de Proveedores");
        frame.setSize(600, 400);
        //Se hace un panel con la distribucion de border layout
        JPanel panel = new JPanel(new BorderLayout());
        //Se genera la botonera en formato tabla de una sola fila, 4 columnas y 5px de espaciado
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar");
        JButton btnRegresar = new JButton("Regresar");
        //Se genera el textarea y el panel que lo va a contener y que tiene scroll
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        //Se le agregan escuchas a los botones para que ejecuten las funciones correspondientes
        btnAgregar.addActionListener(e -> agregarProveedor(textArea));
        btnBuscar.addActionListener(e -> buscarProveedor(textArea));
        btnListar.addActionListener(e -> listarProveedores(textArea));
        btnRegresar.addActionListener(e -> frame.dispose());
        //Se añaden los botones a la botonera
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnListar);
        buttonPanel.add(btnRegresar);
        //Se coloca la botonera y el textarea en posicion 
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        //Se pinta la ventana y se hace visible
        frame.add(panel);
        frame.setVisible(true);
    }

    private void agregarProveedor(JTextArea textArea) {//se genera una ventana donde se generan inputs text para registrar un proveedor
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        
        JTextField txtRazonSocial = new JTextField();
        JTextField txtRfc = new JTextField();
        JTextField txtDireccion = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtFax = new JTextField();
        JTextField txtContacto = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtTelContacto = new JTextField();
        
        formPanel.add(new JLabel("Razón Social:"));
        formPanel.add(txtRazonSocial);
        formPanel.add(new JLabel("RFC:"));
        formPanel.add(txtRfc);
        formPanel.add(new JLabel("Dirección:"));
        formPanel.add(txtDireccion);
        formPanel.add(new JLabel("Teléfono:"));
        formPanel.add(txtTelefono);
        formPanel.add(new JLabel("Fax:"));
        formPanel.add(txtFax);
        formPanel.add(new JLabel("Nombre Contacto:"));
        formPanel.add(txtContacto);
        formPanel.add(new JLabel("Email Contacto:"));
        formPanel.add(txtEmail);
        formPanel.add(new JLabel("Teléfono Contacto:"));
        formPanel.add(txtTelContacto);
        
        int result = JOptionPane.showConfirmDialog(frame, formPanel, 
            "Nuevo Proveedor", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            Proveedor nuevo = new Proveedor(
                txtRazonSocial.getText(),
                txtRfc.getText(),
                txtDireccion.getText(),
                txtTelefono.getText(),
                txtFax.getText(),
                txtContacto.getText(),
                txtEmail.getText(),
                txtTelContacto.getText()
            );
            gestorProveedores.agregarProveedor(nuevo);
            textArea.setText("Proveedor agregado con éxito:\n" + nuevo);
        }
    }

    private void buscarProveedor(JTextArea textArea) {//Se genera una mini ventanita para introducir el rfc y buscar el proveedor
        String rfc = JOptionPane.showInputDialog(frame, 
            "Ingrese RFC del proveedor:");
        if (rfc != null && !rfc.isEmpty()) {
            Proveedor proveedor = gestorProveedores.buscarProveedorPorRFC(rfc);
            if (proveedor != null) {
                textArea.setText("Proveedor encontrado:\n" + proveedor);
            } else {
                textArea.setText("Proveedor no encontrado");
            }
        }
    }

    private void listarProveedores(JTextArea textArea) {// se verifica que tengamos proveedores para desplegarlos y vaciarlos en el textarea
        ArrayList<Proveedor> proveedores = gestorProveedores.getProveedores();
        if (proveedores.isEmpty()) {
            textArea.setText("No hay proveedores registrados");
        } else {
            StringBuilder sb = new StringBuilder("=== LISTA DE PROVEEDORES ===\n");
            for (Proveedor p : proveedores) {
                sb.append(p).append("\n----------------------\n");
            }
            textArea.setText(sb.toString());
        }
    }
}