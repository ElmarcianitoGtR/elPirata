import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;

class SistemaElPirataGUI {
  
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
        cargarDatosDePrueba(this);
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






    
private static void cargarDatosDePrueba(SistemaElPirataGUI sistema) {
    // Cargar clientes (20)
    sistema.gestorClientes.agregarCliente(new Cliente("CLI001", "ABC123456789", "Tienda Don Pepe", 
        "Calle Principal 123, Centro", "José Pérez", "5551234567"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI002", "DEF987654321", "Supermercado La Economía", 
        "Av. Revolución 456, Norte", "María López", "5557654321"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI003", "GHI456789123", "Papelería El Lápiz", 
        "Calle Secundaria 789, Sur", "Carlos Martínez", "5552345678"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI004", "JKL321654987", "Farmacia San José", 
        "Av. Juárez 321, Este", "Ana Rodríguez", "5558765432"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI005", "MNO789123456", "Restaurante La Parrilla", 
        "Blvd. Hidalgo 654, Oeste", "Luisa García", "5553456789"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI006", "PQR654321987", "Miscelánea La Bendición", 
        "Calle Tercera 987, Centro", "Pedro Sánchez", "5559876543"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI007", "STU147258369", "Carnicería El Toro", 
        "Av. Industrial 147, Norte", "Juan Morales", "5554567890"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI008", "VWX369258147", "Tortillería La Abuela", 
        "Calle Cuarta 258, Sur", "Rosa Hernández", "5550987654"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI009", "YZA258369147", "Ferretería El Clavo", 
        "Av. Tecnológico 369, Este", "Miguel Castro", "5555678901"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI010", "BCD852741963", "Panadería La Espiga", 
        "Calle Quinta 741, Oeste", "Laura Méndez", "5551098765"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI011", "EFG963852741", "Lavandería Clean", 
        "Av. Ecológica 852, Centro", "Jorge Ramírez", "5556789012"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI012", "HIJ741963852", "Tlapalería El Pintor", 
        "Calle Sexta 963, Norte", "Patricia Vargas", "5552109876"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI013", "KLM159357486", "Juguetería El Niño Feliz", 
        "Av. Comercial 357, Sur", "Fernando Jiménez", "5557890123"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI014", "NOP486159357", "Óptica Visual", 
        "Calle Séptima 159, Este", "Sofía Núñez", "5553210987"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI015", "QRS753951824", "Electrónica Digital", 
        "Av. Innovación 951, Oeste", "Ricardo Díaz", "5558901234"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI016", "TUV824753951", "Mueblería La Comfort", 
        "Calle Octava 753, Centro", "Verónica Silva", "5554321098"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI017", "WXY951824753", "Florería Rosita", 
        "Av. Jardines 824, Norte", "Oscar Mendoza", "5559012345"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI018", "ZAB357159486", "Pescadería El Mar", 
        "Calle Novena 159, Sur", "Isabel Cortés", "5555432109"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI019", "CDE486357159", "Dulcería Sugar", 
        "Av. Dulces 357, Este", "Raúl Ortega", "5550123456"));
    sistema.gestorClientes.agregarCliente(new Cliente("CLI020", "FGH159486357", "Cafetería Aroma", 
        "Calle Décima 486, Oeste", "Adriana Ríos", "5556543210"));

    // Cargar proveedores (20)
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Distribuidora Alimenticia SA", "DAS123456789", 
        "Av. Industrial 1000, Zona Industrial", "5551112233", "5551112244", "Roberto Gómez", 
        "r.gomez@das.com", "5551112255"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Bebidas Refrescantes SA", "BRS987654321", 
        "Blvd. Bebidas 200, Parque Industrial", "5552223344", "5552223355", "Laura Torres", 
        "l.torres@brs.com", "5552223366"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Papel y Oficina SA", "POS456789123", 
        "Calle Papel 300, Zona Comercial", "5553334455", "5553334466", "Carlos Ruiz", 
        "c.ruiz@pos.com", "5553334477"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Farmacéutica del Norte", "FDN321654987", 
        "Av. Salud 400, Zona Médica", "5554445566", "5554445577", "María Fernández", 
        "m.fernandez@fdn.com", "5554445588"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Carnes Selectas SA", "CSS789123456", 
        "Blvd. Carnes 500, Zona Ganadera", "5555556677", "5555556688", "Jorge Mendoza", 
        "j.mendoza@css.com", "5555556699"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Tortillería Industrial", "TIN654321987", 
        "Av. Maíz 600, Zona Agrícola", "5556667788", "5556667799", "Patricia Castro", 
        "p.castro@tin.com", "5556667700"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Ferretería Nacional", "FNA147258369", 
        "Calle Herramientas 700, Zona Construcción", "5557778899", "5557778800", "Luis Herrera", 
        "l.herrera@fna.com", "5557778811"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Panadería Industrial", "PIN369258147", 
        "Av. Trigo 800, Zona Harinera", "5558889900", "5558889911", "Sandra López", 
        "s.lopez@pin.com", "5558889922"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Lavandería Comercial", "LCO258369147", 
        "Blvd. Limpieza 900, Zona Servicios", "5559990011", "5559990022", "Ricardo Díaz", 
        "r.diaz@lco.com", "5559990033"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Pinturas y Colorantes", "PYC852741963", 
        "Av. Colores 100, Zona Química", "5550001122", "5550001133", "Ana Martínez", 
        "a.martinez@pyc.com", "5550001144"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Juguetes Educativos SA", "JES963852741", 
        "Calle Diversión 200, Zona Educativa", "5551112233", "5551112244", "Pedro Sánchez", 
        "p.sanchez@jes.com", "5551112255"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Óptica Industrial", "OPI741963852", 
        "Av. Visión 300, Zona Tecnológica", "5552223344", "5552223355", "Mónica Ramírez", 
        "m.ramirez@opi.com", "5552223366"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Electrónicos del Futuro", "EDF159357486", 
        "Blvd. Tecnología 400, Zona Digital", "5553334455", "5553334466", "Juan Pérez", 
        "j.perez@edf.com", "5553334477"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Muebles de Oficina SA", "MOS486159357", 
        "Calle Confort 500, Zona Corporativa", "5554445566", "5554445577", "Lucía Gómez", 
        "l.gomez@mos.com", "5554445588"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Flores y Plantas SA", "FPS753951824", 
        "Av. Jardines 600, Zona Verde", "5555556677", "5555556688", "Javier Torres", 
        "j.torres@fps.com", "5555556699"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Pescados y Mariscos Frescos", "PMF824753951", 
        "Blvd. Marítimo 700, Zona Pesquera", "5556667788", "5556667799", "Carmen Ruiz", 
        "c.ruiz@pmf.com", "5556667700"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Dulces y Chocolates SA", "DCS951824753", 
        "Av. Azúcar 800, Zona Dulcera", "5557778899", "5557778800", "Roberto Fernández", 
        "r.fernandez@dcs.com", "5557778811"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Café de Especialidad", "CDE357159486", 
        "Calle Aroma 900, Zona Cafetalera", "5558889900", "5558889911", "Laura Mendoza", 
        "l.mendoza@cde.com", "5558889922"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Lácteos y Derivados", "LYD486357159", 
        "Av. Lechera 1000, Zona Láctea", "5559990011", "5559990022", "Carlos Castro", 
        "c.castro@lyd.com", "5559990033"));
    sistema.gestorProveedores.agregarProveedor(new Proveedor("Hielo y Refrigeración", "HYR159486357", 
        "Blvd. Frío 1100, Zona Industrial", "5550001122", "5550001133", "María López", 
        "m.lopez@hyr.com", "5550001144"));

    // Cargar productos (20)
    sistema.gestorProductos.agregarProducto(new Producto("PROD001", "Arroz 1kg", 25.50, 100));
    sistema.gestorProductos.agregarProducto(new Producto("PROD002", "Frijol 1kg", 32.75, 80));
    sistema.gestorProductos.agregarProducto(new Producto("PROD003", "Aceite vegetal 1L", 45.90, 60));
    sistema.gestorProductos.agregarProducto(new Producto("PROD004", "Azúcar 1kg", 28.30, 120));
    sistema.gestorProductos.agregarProducto(new Producto("PROD005", "Harina 1kg", 22.40, 90));
    sistema.gestorProductos.agregarProducto(new Producto("PROD006", "Atún en lata", 18.75, 150));
    sistema.gestorProductos.agregarProducto(new Producto("PROD007", "Leche 1L", 26.80, 200));
    sistema.gestorProductos.agregarProducto(new Producto("PROD008", "Huevo cartón 30pz", 75.50, 50));
    sistema.gestorProductos.agregarProducto(new Producto("PROD009", "Jabón de baño", 15.20, 180));
    sistema.gestorProductos.agregarProducto(new Producto("PROD010", "Shampoo 400ml", 65.90, 70));
    sistema.gestorProductos.agregarProducto(new Producto("PROD011", "Pasta dental", 38.50, 110));
    sistema.gestorProductos.agregarProducto(new Producto("PROD012", "Toallas sanitarias", 42.30, 85));
    sistema.gestorProductos.agregarProducto(new Producto("PROD013", "Pañales talla M", 120.75, 40));
    sistema.gestorProductos.agregarProducto(new Producto("PROD014", "Detergente 1kg", 55.60, 75));
    sistema.gestorProductos.agregarProducto(new Producto("PROD015", "Cloro 1L", 18.90, 95));
    sistema.gestorProductos.agregarProducto(new Producto("PROD016", "Cerveza sixpack", 135.00, 60));
    sistema.gestorProductos.agregarProducto(new Producto("PROD017", "Refresco 2L", 32.50, 130));
    sistema.gestorProductos.agregarProducto(new Producto("PROD018", "Agua mineral 1L", 15.75, 200));
    sistema.gestorProductos.agregarProducto(new Producto("PROD019", "Galletas 500g", 28.90, 65));
    sistema.gestorProductos.agregarProducto(new Producto("PROD020", "Chocolate tableta", 45.25, 55));

    // Cargar ventas (20)
    Cliente cliente1 = sistema.gestorClientes.buscarCliente("CLI001");
    Cliente cliente2 = sistema.gestorClientes.buscarCliente("CLI002");
    Cliente cliente3 = sistema.gestorClientes.buscarCliente("CLI003");
    Cliente cliente4 = sistema.gestorClientes.buscarCliente("CLI004");
    Cliente cliente5 = sistema.gestorClientes.buscarCliente("CLI005");
    
    Producto prod1 = sistema.gestorProductos.buscarProducto("PROD001");
    Producto prod2 = sistema.gestorProductos.buscarProducto("PROD002");
    Producto prod3 = sistema.gestorProductos.buscarProducto("PROD003");
    Producto prod4 = sistema.gestorProductos.buscarProducto("PROD004");
    Producto prod5 = sistema.gestorProductos.buscarProducto("PROD005");
    Producto prod6 = sistema.gestorProductos.buscarProducto("PROD006");
    Producto prod7 = sistema.gestorProductos.buscarProducto("PROD007");
    Producto prod8 = sistema.gestorProductos.buscarProducto("PROD008");
    Producto prod9 = sistema.gestorProductos.buscarProducto("PROD009");
    Producto prod10 = sistema.gestorProductos.buscarProducto("PROD010");
    
    // Ventas para cliente 1
    Venta venta1 = new Venta("VTA001", cliente1);
    venta1.agregarDetalle(new DetalleVenta(prod1, 5));
    venta1.agregarDetalle(new DetalleVenta(prod2, 3));
    sistema.gestorVentas.registrarVenta(venta1);
    
    Venta venta2 = new Venta("VTA002", cliente1);
    venta2.agregarDetalle(new DetalleVenta(prod3, 2));
    venta2.agregarDetalle(new DetalleVenta(prod4, 4));
    venta2.agregarDetalle(new DetalleVenta(prod5, 1));
    sistema.gestorVentas.registrarVenta(venta2);
    
    // Ventas para cliente 2
    Venta venta3 = new Venta("VTA003", cliente2);
    venta3.agregarDetalle(new DetalleVenta(prod6, 10));
    venta3.agregarDetalle(new DetalleVenta(prod7, 8));
    sistema.gestorVentas.registrarVenta(venta3);
    
    Venta venta4 = new Venta("VTA004", cliente2);
    venta4.agregarDetalle(new DetalleVenta(prod8, 2));
    venta4.agregarDetalle(new DetalleVenta(prod9, 5));
    venta4.agregarDetalle(new DetalleVenta(prod10, 3));
    sistema.gestorVentas.registrarVenta(venta4);
    
    // Ventas para cliente 3
    Venta venta5 = new Venta("VTA005", cliente3);
    venta5.agregarDetalle(new DetalleVenta(prod1, 8));
    venta5.agregarDetalle(new DetalleVenta(prod3, 6));
    sistema.gestorVentas.registrarVenta(venta5);
    
    Venta venta6 = new Venta("VTA006", cliente3);
    venta6.agregarDetalle(new DetalleVenta(prod5, 4));
    venta6.agregarDetalle(new DetalleVenta(prod7, 12));
    sistema.gestorVentas.registrarVenta(venta6);
    
    // Ventas para cliente 4
    Venta venta7 = new Venta("VTA007", cliente4);
    venta7.agregarDetalle(new DetalleVenta(prod2, 7));
    venta7.agregarDetalle(new DetalleVenta(prod4, 5));
    venta7.agregarDetalle(new DetalleVenta(prod6, 3));
    sistema.gestorVentas.registrarVenta(venta7);
    
    Venta venta8 = new Venta("VTA008", cliente4);
    venta8.agregarDetalle(new DetalleVenta(prod8, 1));
    venta8.agregarDetalle(new DetalleVenta(prod10, 2));
    sistema.gestorVentas.registrarVenta(venta8);
    
    // Ventas para cliente 5
    Venta venta9 = new Venta("VTA009", cliente5);
    venta9.agregarDetalle(new DetalleVenta(prod1, 10));
    venta9.agregarDetalle(new DetalleVenta(prod3, 5));
    venta9.agregarDetalle(new DetalleVenta(prod5, 3));
    sistema.gestorVentas.registrarVenta(venta9);
    
    Venta venta10 = new Venta("VTA010", cliente5);
    venta10.agregarDetalle(new DetalleVenta(prod7, 15));
    venta10.agregarDetalle(new DetalleVenta(prod9, 8));
    sistema.gestorVentas.registrarVenta(venta10);
    
    // Más ventas para otros clientes
    for (int i = 11; i <= 20; i++) {
        Cliente cliente = sistema.gestorClientes.buscarCliente("CLI" + String.format("%02d", i));
        Venta venta = new Venta("VTA" + String.format("%03d", i), cliente);
        
        // Agregar entre 1 y 5 productos aleatorios
        int numProductos = 1 + (int)(Math.random() * 5);
        for (int j = 0; j < numProductos; j++) {
            int prodIndex = 1 + (int)(Math.random() * 20);
            Producto producto = sistema.gestorProductos.buscarProducto("PROD" + String.format("%03d", prodIndex));
            int cantidad = 1 + (int)(Math.random() * 10);
            venta.agregarDetalle(new DetalleVenta(producto, cantidad));
        }
        
        sistema.gestorVentas.registrarVenta(venta);
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaElPirataGUI sistema = new SistemaElPirataGUI();
        });
    }
}