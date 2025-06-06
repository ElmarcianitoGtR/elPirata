import javax.swing.*;
import java.awt.*;
public class MainGUI {
    private JFrame frame;
    private GestorClientes gestorClientes;
    private GestorProveedores gestorProveedores;
    private GestorProductos gestorProductos;
    private GestorVentas gestorVentas;

    public MainGUI() {
        inicializarGestores();
        DataLoader.cargarDatosDePrueba(this);
        crearGUI();
    }

    private void inicializarGestores() {
        gestorClientes = new GestorClientes();
        gestorProveedores = new GestorProveedores();
        gestorProductos = new GestorProductos();
        gestorVentas = new GestorVentas(gestorClientes, gestorProductos);
    }

    private void crearGUI() {
        frame = new JFrame("Sistema El Pirata");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton btnClientes = new JButton("Gestión de Clientes");
        JButton btnProveedores = new JButton("Gestión de Proveedores");
        JButton btnProductos = new JButton("Gestión de Productos");
        JButton btnVentas = new JButton("Gestión de Ventas");
        JButton btnReportes = new JButton("Reportes");
        
        btnClientes.addActionListener(e -> new ClientesGUI(gestorClientes));
        btnProveedores.addActionListener(e -> new ProveedoresGUI(gestorProveedores));
        btnProductos.addActionListener(e -> new ProductosGUI(gestorProductos));
        btnVentas.addActionListener(e -> new VentasGUI(gestorVentas, gestorClientes, gestorProductos));
        btnReportes.addActionListener(e -> new ReportesGUI(gestorClientes, gestorProveedores, gestorProductos, gestorVentas));
        
        mainPanel.add(btnClientes);
        mainPanel.add(btnProveedores);
        mainPanel.add(btnProductos);
        mainPanel.add(btnVentas);
        mainPanel.add(btnReportes);
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI());
    }

    // Getters para DataLoader
    public GestorClientes getGestorClientes() { return gestorClientes; }
    public GestorProveedores getGestorProveedores() { return gestorProveedores; }
    public GestorProductos getGestorProductos() { return gestorProductos; }
    public GestorVentas getGestorVentas() { return gestorVentas; }
}