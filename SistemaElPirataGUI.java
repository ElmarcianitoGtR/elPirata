import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class SistemaElPirataGUI {
    private JFrame frame;
    private GestorClientes gestorClientes;
    private GestorProveedores gestorProveedores;
    private GestorProductos gestorProductos;
    private GestorVentas gestorVentas;

    public SistemaElPirataGUI() {
        gestorClientes = new GestorClientes();
        gestorProveedores = new GestorProveedores();
        gestorProductos = new GestorProductos();
        gestorVentas = new GestorVentas(gestorClientes, gestorProductos);
    
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            gestorClientes.guardarDatos();
            gestorProveedores.guardarDatos();
            gestorProductos.guardarDatos();
            gestorVentas.guardarDatos();
        }));
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sistema El Pirata");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Menú principal
        JPanel mainPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton btnClientes = new JButton("Gestión de Clientes");
        JButton btnProveedores = new JButton("Gestión de Proveedores");
        JButton btnProductos = new JButton("Gestión de Productos");
        JButton btnVentas = new JButton("Gestión de Ventas");
        JButton btnReportes = new JButton("Reportes");
        
        btnClientes.addActionListener(e -> mostrarMenuClientes());
        btnProveedores.addActionListener(e -> mostrarMenuProveedores());
        btnProductos.addActionListener(e -> mostrarMenuProductos());
        btnVentas.addActionListener(e -> mostrarMenuVentas());
        btnReportes.addActionListener(e -> mostrarReportes());
        
        mainPanel.add(btnClientes);
        mainPanel.add(btnProveedores);
        mainPanel.add(btnProductos);
        mainPanel.add(btnVentas);
        mainPanel.add(btnReportes);
        
        frame.add(mainPanel);
        frame.setVisible(true);

    
        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> {
            gestorClientes.guardarDatos();
            gestorProveedores.guardarDatos();
            gestorProductos.guardarDatos();
            gestorVentas.guardarDatos();
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
        });
    }
    private void mostrarMenuClientes() {
        JFrame clientesFrame = new JFrame("Gestión de Clientes");
        clientesFrame.setSize(600, 400);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar");
        JButton btnRegresar = new JButton("Regresar");
        
        // Área de texto para resultados
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        btnAgregar.addActionListener(e -> {
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
            
            int result = JOptionPane.showConfirmDialog(clientesFrame, formPanel, 
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
                gestorClientes.agregarCliente(nuevo);
                textArea.setText("Cliente agregado con éxito:\n" + nuevo);
            }
        });
        
        btnBuscar.addActionListener(e -> {
            String numero = JOptionPane.showInputDialog(clientesFrame, 
                "Ingrese número de cliente:");
            if (numero != null && !numero.isEmpty()) {
                Cliente cliente = gestorClientes.buscarCliente(numero);
                if (cliente != null) {
                    textArea.setText("Cliente encontrado:\n" + cliente);
                } else {
                    textArea.setText("Cliente no encontrado");
                }
            }
        });
        
        btnListar.addActionListener(e -> {
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
        });
        
        btnRegresar.addActionListener(e -> clientesFrame.dispose());
        
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnListar);
        buttonPanel.add(btnRegresar);
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        clientesFrame.add(panel);
        clientesFrame.setVisible(true);
    }

    private void mostrarMenuProveedores() {
        JFrame proveedoresFrame = new JFrame("Gestión de Proveedores");
        proveedoresFrame.setSize(600, 400);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar");
        JButton btnRegresar = new JButton("Regresar");
        
        // Área de texto para resultados
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        btnAgregar.addActionListener(e -> {
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
            
            int result = JOptionPane.showConfirmDialog(proveedoresFrame, formPanel, 
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
        });
        
        btnBuscar.addActionListener(e -> {
            String rfc = JOptionPane.showInputDialog(proveedoresFrame, 
                "Ingrese RFC del proveedor:");
            if (rfc != null && !rfc.isEmpty()) {
                Proveedor proveedor = gestorProveedores.buscarProveedorPorRFC(rfc);
                if (proveedor != null) {
                    textArea.setText("Proveedor encontrado:\n" + proveedor);
                } else {
                    textArea.setText("Proveedor no encontrado");
                }
            }
        });
        
        btnListar.addActionListener(e -> {
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
        });
        
        btnRegresar.addActionListener(e -> proveedoresFrame.dispose());
        
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnListar);
        buttonPanel.add(btnRegresar);
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        proveedoresFrame.add(panel);
        proveedoresFrame.setVisible(true);
    }

    private void mostrarMenuProductos() {
        JFrame productosFrame = new JFrame("Gestión de Productos");
        productosFrame.setSize(600, 400);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar");
        JButton btnRegresar = new JButton("Regresar");
        
        // Área de texto para resultados
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        btnAgregar.addActionListener(e -> {
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
            
            int result = JOptionPane.showConfirmDialog(productosFrame, formPanel, 
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
        });
        
        btnBuscar.addActionListener(e -> {
            String codigo = JOptionPane.showInputDialog(productosFrame, 
                "Ingrese código del producto:");
            if (codigo != null && !codigo.isEmpty()) {
                Producto producto = gestorProductos.buscarProducto(codigo);
                if (producto != null) {
                    textArea.setText("Producto encontrado:\n" + producto);
                } else {
                    textArea.setText("Producto no encontrado");
                }
            }
        });
        
        btnListar.addActionListener(e -> {
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
        });
        
        btnRegresar.addActionListener(e -> productosFrame.dispose());
        
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnListar);
        buttonPanel.add(btnRegresar);
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        productosFrame.add(panel);
        productosFrame.setVisible(true);
    }

    private void mostrarMenuVentas() {
        JFrame ventasFrame = new JFrame("Gestión de Ventas");
        ventasFrame.setSize(800, 600);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        JButton btnNuevaVenta = new JButton("Nueva Venta");
        JButton btnVerVenta = new JButton("Ver Venta");
        JButton btnRegresar = new JButton("Regresar");
        
        // Área de texto para resultados
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        btnNuevaVenta.addActionListener(e -> {
            // Paso 1: Seleccionar cliente
            String numCliente = JOptionPane.showInputDialog(ventasFrame, 
                "Ingrese número de cliente:");
            if (numCliente == null || numCliente.isEmpty()) return;
            
            Cliente cliente = gestorClientes.buscarCliente(numCliente);
            if (cliente == null) {
                textArea.setText("Cliente no encontrado");
                return;
            }
            
            // Crear venta
            String numNota = "VTA-" + System.currentTimeMillis();
            Venta venta = new Venta(numNota, cliente);
            
            // Paso 2: Agregar productos
            boolean continuar = true;
            while (continuar) {
                String codigo = JOptionPane.showInputDialog(ventasFrame, 
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
                
                String cantidadStr = JOptionPane.showInputDialog(ventasFrame, 
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
            
            // Registrar venta
            gestorVentas.registrarVenta(venta);
            textArea.setText("Venta registrada con éxito:\n" + venta.generarNota());
        });
        
        btnVerVenta.addActionListener(e -> {
            String numNota = JOptionPane.showInputDialog(ventasFrame, 
                "Ingrese número de nota de venta:");
            if (numNota == null || numNota.isEmpty()) return;
            
            for (Venta v : gestorVentas.getVentas()) {
                if (v.getNumeroNota().equals(numNota)) {
                    textArea.setText(v.generarNota());
                    return;
                }
            }
            
            textArea.setText("Venta no encontrada");
        });
        
        btnRegresar.addActionListener(e -> ventasFrame.dispose());
        
        buttonPanel.add(btnNuevaVenta);
        buttonPanel.add(btnVerVenta);
        buttonPanel.add(btnRegresar);
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        ventasFrame.add(panel);
        ventasFrame.setVisible(true);
    }

    private void mostrarReportes() {
        JFrame reportesFrame = new JFrame("Reportes");
        reportesFrame.setSize(800, 600);
        
        JPanel panel = new JPanel(new BorderLayout());
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton btnClientes = new JButton("Clientes");
        JButton btnProveedores = new JButton("Proveedores");
        JButton btnProductos = new JButton("Productos");
        JButton btnVentas = new JButton("Ventas");
        JButton btnRegresar = new JButton("Regresar");
        
        // Área de texto para resultados
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        btnClientes.addActionListener(e -> {
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
        });
        
        btnProveedores.addActionListener(e -> {
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
        });
        
        btnProductos.addActionListener(e -> {
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
        });
        
        btnVentas.addActionListener(e -> {
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
        });
        
        btnRegresar.addActionListener(e -> reportesFrame.dispose());
        
        buttonPanel.add(btnClientes);
        buttonPanel.add(btnProveedores);
        buttonPanel.add(btnProductos);
        buttonPanel.add(btnVentas);
        buttonPanel.add(btnRegresar);
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        reportesFrame.add(panel);
        reportesFrame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SistemaElPirataGUI();
        });
    }
}