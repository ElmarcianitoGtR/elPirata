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
        frame = new JFrame("Gestión de Proveedores");
        frame.setSize(600, 400);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar");
        JButton btnRegresar = new JButton("Regresar");
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        btnAgregar.addActionListener(e -> agregarProveedor(textArea));
        btnBuscar.addActionListener(e -> buscarProveedor(textArea));
        btnListar.addActionListener(e -> listarProveedores(textArea));
        btnRegresar.addActionListener(e -> frame.dispose());
        
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnListar);
        buttonPanel.add(btnRegresar);
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        frame.add(panel);
        frame.setVisible(true);
    }

    private void agregarProveedor(JTextArea textArea) {
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

    private void buscarProveedor(JTextArea textArea) {
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

    private void listarProveedores(JTextArea textArea) {
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