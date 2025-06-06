import javax.swing.*;
import java.awt.*;
public class MainGUI {
    private JFrame frame;
    private GestorClientes gestorClientes;
    private GestorProveedores gestorProveedores;
    private GestorProductos gestorProductos;
    private GestorVentas gestorVentas;

    public MainGUI() {
        // Se mandana a llamar las funciones primordiales para el funcionamiento del sistema 
        inicializarGestores();
        DataLoader.cargarDatosDePrueba(this);
        crearGUI();
    }

    private void inicializarGestores() {
        // Se crean las instancias de los gestores
        gestorClientes = new GestorClientes();
        gestorProveedores = new GestorProveedores();
        gestorProductos = new GestorProductos();
        gestorVentas = new GestorVentas(gestorClientes, gestorProductos);
    }

    private void crearGUI() {
        //Aqui se genera la ventana
        frame = new JFrame("Sistema El Pirata");//Este es el titulo de nuestra ventana
        frame.setSize(800, 600);//La medida de la ventana es 800px X 600px
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Para que se pare cuando se cierre
        
        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));// Se hace un panel de 5 filas con un espaciado de 10px
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));//Se le agrega un margen de 20px por lado
        
        //Estos son los Botones que apareceran en el menu principal que nos permite desplegar las demas ventanas
        JButton btnClientes = new JButton("Gestión de Clientes");
        JButton btnProveedores = new JButton("Gestión de Proveedores");
        JButton btnProductos = new JButton("Gestión de Productos");
        JButton btnVentas = new JButton("Gestión de Ventas");
        JButton btnReportes = new JButton("Reportes");
        //Se les pone una escucha como el onClick en js
        btnClientes.addActionListener(e -> new ClientesGUI(gestorClientes));
        btnProveedores.addActionListener(e -> new ProveedoresGUI(gestorProveedores));
        btnProductos.addActionListener(e -> new ProductosGUI(gestorProductos));
        btnVentas.addActionListener(e -> new VentasGUI(gestorVentas, gestorClientes, gestorProductos));
        btnReportes.addActionListener(e -> new ReportesGUI(gestorClientes, gestorProveedores, gestorProductos, gestorVentas));
        //Se le asigna al panel principal los botones que anteriormente creamos y que les agregamos la escucha
        mainPanel.add(btnClientes);
        mainPanel.add(btnProveedores);
        mainPanel.add(btnProductos);
        mainPanel.add(btnVentas);
        mainPanel.add(btnReportes);
        //El panel se añade a la ventana y  hacemos visible la ventana
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //segun el video esta linea de codigo nos permite gestionar de manera segura los eventos de swing por que los ejecuta desde el hilo de eventos EDT(event dispathc thread)
        SwingUtilities.invokeLater(() -> new MainGUI());
    }
    //Estos son los metodos que se ocupan para acceder a los gestores de sde otras clases
    public GestorClientes getGestorClientes() { return gestorClientes; }
    public GestorProveedores getGestorProveedores() { return gestorProveedores; }
    public GestorProductos getGestorProductos() { return gestorProductos; }
    public GestorVentas getGestorVentas() { return gestorVentas; }
}