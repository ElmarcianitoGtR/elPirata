import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportesGUI {
    private JFrame frame;
    private GestorClientes gestorClientes;
    private GestorProveedores gestorProveedores;
    private GestorProductos gestorProductos;
    private GestorVentas gestorVentas;

    public ReportesGUI(GestorClientes gestorClientes, GestorProveedores gestorProveedores, 
                      GestorProductos gestorProductos, GestorVentas gestorVentas) {
        this.gestorClientes = gestorClientes;
        this.gestorProveedores = gestorProveedores;
        this.gestorProductos = gestorProductos;
        this.gestorVentas = gestorVentas;
        crearGUI();
    }

    private void crearGUI() {
        frame = new JFrame("Reportes");
        frame.setSize(800, 600);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton btnClientes = new JButton("Clientes");
        JButton btnProveedores = new JButton("Proveedores");
        JButton btnProductos = new JButton("Productos");
        JButton btnVentas = new JButton("Ventas");
        JButton btnRegresar = new JButton("Regresar");
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        btnClientes.addActionListener(e -> reporteClientes(textArea));
        btnProveedores.addActionListener(e -> reporteProveedores(textArea));
        btnProductos.addActionListener(e -> reporteProductos(textArea));
        btnVentas.addActionListener(e -> reporteVentas(textArea));
        btnRegresar.addActionListener(e -> frame.dispose());
        
        buttonPanel.add(btnClientes);
        buttonPanel.add(btnProveedores);
        buttonPanel.add(btnProductos);
        buttonPanel.add(btnVentas);
        buttonPanel.add(btnRegresar);
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        frame.add(panel);
        frame.setVisible(true);
    }

    private void reporteClientes(JTextArea textArea) {
        ArrayList<Cliente> clientes = gestorClientes.getClientes();
        if (clientes.isEmpty()) {
            textArea.setText("No hay clientes registrados");
        } else {
            StringBuilder sb = new StringBuilder("=== REPORTE DE CLIENTES ===\n");
            for (Cliente c : clientes) {
                sb.append(c).append("\n----------------------\n");
            }
            textArea.setText(sb.toString());
        }
    }

    private void reporteProveedores(JTextArea textArea) {
        ArrayList<Proveedor> proveedores = gestorProveedores.getProveedores();
        if (proveedores.isEmpty()) {
            textArea.setText("No hay proveedores registrados");
        } else {
            StringBuilder sb = new StringBuilder("=== REPORTE DE PROVEEDORES ===\n");
            for (Proveedor p : proveedores) {
                sb.append(p).append("\n----------------------\n");
            }
            textArea.setText(sb.toString());
        }
    }

    private void reporteProductos(JTextArea textArea) {
        ArrayList<Producto> productos = gestorProductos.getProductos();
        if (productos.isEmpty()) {
            textArea.setText("No hay productos registrados");
        } else {
            StringBuilder sb = new StringBuilder("=== REPORTE DE INVENTARIO ===\n");
            sb.append("Código\tDescripción\tPrecio\tExistencia\n");
            for (Producto p : productos) {
                sb.append(String.format("%s\t%s\t$%.2f\t%d%n", 
                    p.getCodigo(), p.getDescripcion(), 
                    p.getPrecioUnitario(), p.getExistencia()));
            }
            textArea.setText(sb.toString());
        }
    }

    private void reporteVentas(JTextArea textArea) {
        ArrayList<Venta> ventas = gestorVentas.getVentas();
        if (ventas.isEmpty()) {
            textArea.setText("No hay ventas registradas");
        } else {
            StringBuilder sb = new StringBuilder("=== REPORTE DE VENTAS ===\n");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            double totalGeneral = 0;
            
            sb.append("Nota\t\tFecha\t\tCliente\t\tTotal\n");
            for (Venta v : ventas) {
                sb.append(String.format("%s\t%s\t%s\t$%.2f%n", 
                    v.getNumeroNota(), sdf.format(v.getFecha()),
                    v.getCliente().getRazonSocial(), v.getTotal()));
                totalGeneral += v.getTotal();
            }
            
            sb.append("\nTOTAL GENERAL: $").append(String.format("%.2f", totalGeneral));
            textArea.setText(sb.toString());
        }
    }
}