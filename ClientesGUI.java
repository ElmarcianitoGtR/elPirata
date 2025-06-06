//Importaciones
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ClientesGUI {
    private JFrame frame;
    private GestorClientes gestorClientes;

    public ClientesGUI(GestorClientes gestorClientes) {
        this.gestorClientes = gestorClientes;
        crearGUI();
    }

    private void crearGUI() {//Se genera una ventanita de 600 x 400 px con borderlayout
        frame = new JFrame("Gestión de Clientes");
        frame.setSize(600, 400);
        
        JPanel panel = new JPanel(new BorderLayout());
        //se genera una botonera de una fila por cuatro columnas y 5px de espaciado, esto se genera junto con los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar");
        JButton btnRegresar = new JButton("Regresar");
        //Se genera el textarea y el panel que lo contiene
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        //Se les agrega una escucha para que ejecuten la funcion correspondiente
        btnAgregar.addActionListener(e -> agregarCliente(textArea));
        btnBuscar.addActionListener(e -> buscarCliente(textArea));
        btnListar.addActionListener(e -> listarClientes(textArea));
        btnRegresar.addActionListener(e -> frame.dispose());
        //Se añaden los botones al panel principal
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnListar);
        buttonPanel.add(btnRegresar);
        //Se añade todo al panel principal, se pinta la ventana y se hace visible
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        frame.add(panel);
        frame.setVisible(true);
    }

    private void agregarCliente(JTextArea textArea) {//se hace un panel de 6 filas 2 columnas que contiene el formulario para agregar cliente
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        
        JTextField txtNumero = new JTextField();
        JTextField txtRfc = new JTextField();
        JTextField txtRazonSocial = new JTextField();
        JTextField txtDireccion = new JTextField();
        JTextField txtContacto = new JTextField();
        JTextField txtTelefono = new JTextField();
        
        formPanel.add(new JLabel("Número Cliente:"));
        formPanel.add(txtNumero);
        formPanel.add(new JLabel("RFC:"));
        formPanel.add(txtRfc);
        formPanel.add(new JLabel("Razón Social:"));
        formPanel.add(txtRazonSocial);
        formPanel.add(new JLabel("Dirección Fiscal:"));
        formPanel.add(txtDireccion);
        formPanel.add(new JLabel("Nombre Contacto:"));
        formPanel.add(txtContacto);
        formPanel.add(new JLabel("Teléfono:"));
        formPanel.add(txtTelefono);
        //Se hace una pequeña ventanita para la confirmacion
        int result = JOptionPane.showConfirmDialog(frame, formPanel, 
            "Nuevo Cliente", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            Cliente nuevo = new Cliente(
                txtNumero.getText(),
                txtRfc.getText(),
                txtRazonSocial.getText(),
                txtDireccion.getText(),
                txtContacto.getText(),
                txtTelefono.getText()
            );
            gestorClientes.agregarCliente(nuevo);//Se hace el registro y se notifica
            textArea.setText("Cliente agregado con éxito:\n" + nuevo);
        }
    }

    private void buscarCliente(JTextArea textArea) {//se hace una mini ventanita que nos permite pedir un dato a partir del cual podemos buscar al cliente
        String numero = JOptionPane.showInputDialog(frame, 
            "Ingrese número de cliente:");
        if (numero != null && !numero.isEmpty()) {
            Cliente cliente = gestorClientes.buscarCliente(numero);
            if (cliente != null) {
                textArea.setText("Cliente encontrado:\n" + cliente);
            } else {
                textArea.setText("Cliente no encontrado");
            }
        }
    }

    private void listarClientes(JTextArea textArea) {//Verifica que tengamos clientes y los despliegue y los vacie en el text area
        ArrayList<Cliente> clientes = gestorClientes.getClientes();
        if (clientes.isEmpty()) {
            textArea.setText("No hay clientes registrados");
        } else {
            StringBuilder sb = new StringBuilder("=== LISTA DE CLIENTES ===\n");
            for (Cliente c : clientes) {
                sb.append(c).append("\n----------------------\n");
            }
            textArea.setText(sb.toString());
        }
    }
}