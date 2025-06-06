import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ProductosGUI {
    private JFrame frame;
    private GestorProductos gestorProductos;

    public ProductosGUI(GestorProductos gestorProductos) {
        this.gestorProductos = gestorProductos;
        crearGUI();
    }

    private void crearGUI() {
        frame = new JFrame("Gestión de Productos");
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
        
        btnAgregar.addActionListener(e -> agregarProducto(textArea));
        btnBuscar.addActionListener(e -> buscarProducto(textArea));
        btnListar.addActionListener(e -> listarProductos(textArea));
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

    private void agregarProducto(JTextArea textArea) {
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        
        JTextField txtCodigo = new JTextField();
        JTextField txtDescripcion = new JTextField();
        JTextField txtPrecio = new JTextField();
        JTextField txtExistencia = new JTextField();
        
        formPanel.add(new JLabel("Código:"));
        formPanel.add(txtCodigo);
        formPanel.add(new JLabel("Descripción:"));
        formPanel.add(txtDescripcion);
        formPanel.add(new JLabel("Precio Unitario:"));
        formPanel.add(txtPrecio);
        formPanel.add(new JLabel("Existencia Inicial:"));
        formPanel.add(txtExistencia);
        
        int result = JOptionPane.showConfirmDialog(frame, formPanel, 
            "Nuevo Producto", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                Producto nuevo = new Producto(
                    txtCodigo.getText(),
                    txtDescripcion.getText(),
                    Double.parseDouble(txtPrecio.getText()),
                    Integer.parseInt(txtExistencia.getText())
                );
                gestorProductos.agregarProducto(nuevo);
                textArea.setText("Producto agregado con éxito:\n" + nuevo);
            } catch (NumberFormatException ex) {
                textArea.setText("Error: Precio y existencia deben ser números válidos");
            }
        }
    }

    private void buscarProducto(JTextArea textArea) {
        String codigo = JOptionPane.showInputDialog(frame, 
            "Ingrese código del producto:");
        if (codigo != null && !codigo.isEmpty()) {
            Producto producto = gestorProductos.buscarProducto(codigo);
            if (producto != null) {
                textArea.setText("Producto encontrado:\n" + producto);
            } else {
                textArea.setText("Producto no encontrado");
            }
        }
    }

    private void listarProductos(JTextArea textArea) {
        ArrayList<Producto> productos = gestorProductos.getProductos();
        if (productos.isEmpty()) {
            textArea.setText("No hay productos registrados");
        } else {
            StringBuilder sb = new StringBuilder("=== INVENTARIO DE PRODUCTOS ===\n");
            sb.append("Código\tDescripción\tPrecio\tExistencia\n");
            for (Producto p : productos) {
                sb.append(String.format("%s\t%s\t$%.2f\t%d%n", 
                    p.getCodigo(), p.getDescripcion(), 
                    p.getPrecioUnitario(), p.getExistencia()));
            }
            textArea.setText(sb.toString());
        }
    }
}