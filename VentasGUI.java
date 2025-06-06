import javax.swing.*;
import java.awt.*;

public class VentasGUI {
    private JFrame frame;
    private GestorVentas gestorVentas;
    private GestorClientes gestorClientes;
    private GestorProductos gestorProductos;

    public VentasGUI(GestorVentas gestorVentas, GestorClientes gestorClientes, GestorProductos gestorProductos) {
        this.gestorVentas = gestorVentas;
        this.gestorClientes = gestorClientes;
        this.gestorProductos = gestorProductos;
        crearGUI();
    }

    private void crearGUI() {
        frame = new JFrame("Gestión de Ventas");
        frame.setSize(800, 600);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        JButton btnNuevaVenta = new JButton("Nueva Venta");
        JButton btnVerVenta = new JButton("Ver Venta");
        JButton btnRegresar = new JButton("Regresar");
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        btnNuevaVenta.addActionListener(e -> nuevaVenta(textArea));
        btnVerVenta.addActionListener(e -> verVenta(textArea));
        btnRegresar.addActionListener(e -> frame.dispose());
        
        buttonPanel.add(btnNuevaVenta);
        buttonPanel.add(btnVerVenta);
        buttonPanel.add(btnRegresar);
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        frame.add(panel);
        frame.setVisible(true);
    }

    private void nuevaVenta(JTextArea textArea) {
        String numCliente = JOptionPane.showInputDialog(frame, 
            "Ingrese número de cliente:");
        if (numCliente == null || numCliente.isEmpty()) return;
        
        Cliente cliente = gestorClientes.buscarCliente(numCliente);
        if (cliente == null) {
            textArea.setText("Cliente no encontrado");
            return;
        }
        
        String numNota = "VTA-" + System.currentTimeMillis();
        Venta venta = new Venta(numNota, cliente);
        
        boolean continuar = true;
        while (continuar) {
            String codigo = JOptionPane.showInputDialog(frame, 
                "Ingrese código del producto (o 'fin' para terminar):");
            
            if (codigo == null || codigo.equalsIgnoreCase("fin")) {
                if (venta.getDetalles().isEmpty()) {
                    textArea.setText("Debe agregar al menos un producto");
                    return;
                }
                continuar = false;
                continue;
            }
            
            Producto producto = gestorProductos.buscarProducto(codigo);
            if (producto == null) {
                textArea.setText("Producto no encontrado");
                continue;
            }
            
            String cantidadStr = JOptionPane.showInputDialog(frame, 
                "Ingrese cantidad para " + producto.getDescripcion() + ":");
            if (cantidadStr == null || cantidadStr.isEmpty()) continue;
            
            try {
                int cantidad = Integer.parseInt(cantidadStr);
                if (cantidad > producto.getExistencia()) {
                    textArea.setText("No hay suficiente existencia");
                    continue;
                }
                
                venta.agregarDetalle(new DetalleVenta(producto, cantidad));
                textArea.setText("Producto agregado a la venta");
            } catch (NumberFormatException ex) {
                textArea.setText("Cantidad debe ser un número válido");
            }
        }
        
        gestorVentas.registrarVenta(venta);
        textArea.setText("Venta registrada con éxito:\n" + venta.generarNota());
    }

    private void verVenta(JTextArea textArea) {
        String numNota = JOptionPane.showInputDialog(frame, 
            "Ingrese número de nota de venta:");
        if (numNota == null || numNota.isEmpty()) return;
        
        for (Venta v : gestorVentas.getVentas()) {
            if (v.getNumeroNota().equals(numNota)) {
                textArea.setText(v.generarNota());
                return;
            }
        }
        
        textArea.setText("Venta no encontrada");
    }
}