import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InterfazUsuario {
        private BufferedReader reader;
        private GestorClientes gestorClientes;
        private GestorProveedores gestorProveedores;
        private GestorProductos gestorProductos;
        private GestorVentas gestorVentas;

        public InterfazUsuario(BufferedReader reader, GestorClientes gestorClientes, 
                             GestorProveedores gestorProveedores, GestorProductos gestorProductos,
                             GestorVentas gestorVentas) {
            this.reader = reader;
            this.gestorClientes = gestorClientes;
            this.gestorProveedores = gestorProveedores;
            this.gestorProductos = gestorProductos;
            this.gestorVentas = gestorVentas;
        }

        public void menuPrincipal() throws IOException {
            boolean salir = false;
            while (!salir) {
                System.out.println("\n=== SISTEMA EL PIRATA ===");
                System.out.println("1. Gestión de Clientes");
                System.out.println("2. Gestión de Proveedores");
                System.out.println("3. Gestión de Productos");
                System.out.println("4. Gestión de Ventas");
                System.out.println("5. Reportes");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");
                
                String opcion = reader.readLine();
                
                switch (opcion) {
                    case "1":
                        menuClientes();
                        break;
                    case "2":
                        menuProveedores();
                        break;
                    case "3":
                        menuProductos();
                        break;
                    case "4":
                        menuVentas();
                        break;
                    case "5":
                        menuReportes();
                        break;
                    case "6":
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }

        private void menuClientes() throws IOException {
            boolean salir = false;
            while (!salir) {
                System.out.println("\n--- GESTIÓN DE CLIENTES ---");
                System.out.println("1. Registrar nuevo cliente");
                System.out.println("2. Buscar cliente");
                System.out.println("3. Listar todos los clientes");
                System.out.println("4. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                
                String opcion = reader.readLine();
                
                switch (opcion) {
                    case "1":
                        registrarCliente();
                        break;
                    case "2":
                        buscarCliente();
                        break;
                    case "3":
                        listarClientes();
                        break;
                    case "4":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }

        private void registrarCliente() throws IOException {
            System.out.println("\n--- NUEVO CLIENTE ---");
            
            String numeroCliente = GeneradorIDs.generarIdCliente();
            System.out.println("Número de cliente asignado: " + numeroCliente);
            
            System.out.print("RFC: ");
            String rfc = reader.readLine();
            
            System.out.print("Razón Social: ");
            String razonSocial = reader.readLine();
            
            System.out.print("Dirección Fiscal: ");
            String direccion = reader.readLine();
            
            System.out.print("Nombre de Contacto: ");
            String contacto = reader.readLine();
            
            System.out.print("Teléfono: ");
            String telefono = reader.readLine();
            
            Cliente nuevo = new Cliente(numeroCliente, rfc, razonSocial, direccion, contacto, telefono);
            gestorClientes.agregarCliente(nuevo);
            
            System.out.println("Cliente registrado con éxito!");
        }

        private void buscarCliente() throws IOException {
            System.out.print("\nIngrese número de cliente: ");
            String numero = reader.readLine();
            
            Cliente cliente = gestorClientes.buscarCliente(numero);
            
            if (cliente != null) {
                System.out.println("\n--- DATOS DEL CLIENTE ---");
                System.out.println(cliente);
            } else {
                System.out.println("Cliente no encontrado.");
            }
        }

        private void listarClientes() {
            ArrayList<Cliente> clientes = gestorClientes.getClientes();
            
            if (clientes.isEmpty()) {
                System.out.println("\nNo hay clientes registrados.");
                return;
            }
            
            System.out.println("\n--- LISTA DE CLIENTES ---");
            for (Cliente c : clientes) {
                System.out.println(c);
                System.out.println("----------------------");
            }
        }





        private void menuProveedores() throws IOException {
            boolean salir = false;
            while (!salir) {
                System.out.println("\n--- GESTIÓN DE PROVEEDORES ---");
                System.out.println("1. Registrar nuevo proveedor");
                System.out.println("2. Buscar proveedor por RFC");
                System.out.println("3. Listar todos los proveedores");
                System.out.println("4. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                
                String opcion = reader.readLine();
                
                switch (opcion) {
                    case "1":
                        registrarProveedor();
                        break;
                    case "2":
                        buscarProveedor();
                        break;
                    case "3":
                        listarProveedores();
                        break;
                    case "4":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }

        private void registrarProveedor() throws IOException {
            System.out.println("\n--- NUEVO PROVEEDOR ---");
            
            System.out.print("Razón Social: ");
            String razonSocial = reader.readLine();
            
            System.out.print("RFC: ");
            String rfc = reader.readLine();
            
            System.out.print("Dirección: ");
            String direccion = reader.readLine();
            
            System.out.print("Teléfono: ");
            String telefono = reader.readLine();
            
            System.out.print("Fax: ");
            String fax = reader.readLine();
            
            System.out.print("Nombre de Contacto: ");
            String nombreContacto = reader.readLine();
            
            System.out.print("Email de Contacto: ");
            String emailContacto = reader.readLine();
            
            System.out.print("Teléfono de Contacto: ");
            String telefonoContacto = reader.readLine();
            
            Proveedor nuevo = new Proveedor(razonSocial, rfc, direccion, telefono, 
                                        fax, nombreContacto, emailContacto, telefonoContacto);
            gestorProveedores.agregarProveedor(nuevo);
            
            System.out.println("Proveedor registrado con éxito!");
        }

        private void buscarProveedor() throws IOException {
            System.out.print("\nIngrese RFC del proveedor: ");
            String rfc = reader.readLine();
            
            Proveedor proveedor = gestorProveedores.buscarProveedorPorRFC(rfc);
            
            if (proveedor != null) {
                System.out.println("\n--- DATOS DEL PROVEEDOR ---");
                System.out.println(proveedor);
            } else {
                System.out.println("Proveedor no encontrado.");
            }
        }

        private void listarProveedores() {
            ArrayList<Proveedor> proveedores = gestorProveedores.getProveedores();
            
            if (proveedores.isEmpty()) {
                System.out.println("\nNo hay proveedores registrados.");
                return;
            }
            
            System.out.println("\n--- LISTA DE PROVEEDORES ---");
            for (Proveedor p : proveedores) {
                System.out.println(p);
                System.out.println("----------------------");
            }
        }

        private void menuProductos() throws IOException {
            boolean salir = false;
            while (!salir) {
                System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
                System.out.println("1. Registrar nuevo producto");
                System.out.println("2. Buscar producto por código");
                System.out.println("3. Listar todos los productos");
                System.out.println("4. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                
                String opcion = reader.readLine();
                
                switch (opcion) {
                    case "1":
                        registrarProducto();
                        break;
                    case "2":
                        buscarProducto();
                        break;
                    case "3":
                        listarProductos();
                        break;
                    case "4":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }

        private void registrarProducto() throws IOException {
            System.out.println("\n--- NUEVO PRODUCTO ---");
            
            System.out.print("Código: ");
            String codigo = reader.readLine();
            
            System.out.print("Descripción: ");
            String descripcion = reader.readLine();
            
            System.out.print("Precio Unitario: ");
            double precio = Double.parseDouble(reader.readLine());
            
            System.out.print("Existencia Inicial: ");
            int existencia = Integer.parseInt(reader.readLine());
            
            Producto nuevo = new Producto(codigo, descripcion, precio, existencia);
            gestorProductos.agregarProducto(nuevo);
            
            System.out.println("Producto registrado con éxito!");
        }

        private void buscarProducto() throws IOException {
            System.out.print("\nIngrese código del producto: ");
            String codigo = reader.readLine();
            
            Producto producto = gestorProductos.buscarProducto(codigo);
            
            if (producto != null) {
                System.out.println("\n--- DATOS DEL PRODUCTO ---");
                System.out.println(producto);
            } else {
                System.out.println("Producto no encontrado.");
            }
        }

        private void listarProductos() {
            ArrayList<Producto> productos = gestorProductos.getProductos();
            
            if (productos.isEmpty()) {
                System.out.println("\nNo hay productos registrados.");
                return;
            }
            
            System.out.println("\n--- INVENTARIO DE PRODUCTOS ---");
            System.out.println("Código\tDescripción\tPrecio\tExistencia");
            for (Producto p : productos) {
                System.out.printf("%s\t%s\t$%.2f\t%d%n", 
                                p.getCodigo(), p.getDescripcion(), 
                                p.getPrecioUnitario(), p.getExistencia());
            }
        }









        private void menuVentas() throws IOException {
            boolean salir = false;
            while (!salir) {
                System.out.println("\n--- GESTIÓN DE VENTAS ---");
                System.out.println("1. Registrar nueva venta");
                System.out.println("2. Ver nota de venta");
                System.out.println("3. Listar todas las ventas");
                System.out.println("4. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                
                String opcion = reader.readLine();
                
                switch (opcion) {
                    case "1":
                        registrarVenta();
                        break;
                    case "2":
                        verNotaVenta();
                        break;
                    case "3":
                        listarVentas();
                        break;
                    case "4":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }

        private void registrarVenta() throws IOException {
            System.out.println("\n--- NUEVA VENTA ---");
            
            System.out.print("Número de cliente: ");
            String numCliente = reader.readLine();
            Cliente cliente = gestorClientes.buscarCliente(numCliente);
            
            if (cliente == null) {
                System.out.println("Cliente no encontrado!");
                return;
            }
            
            String numNota = GeneradorIDs.generarIdVenta();
            System.out.println("Número de nota asignado: " + numNota);
            
            Venta venta = new Venta(numNota, cliente);
            boolean agregandoProductos = true;
            
            while (agregandoProductos) {
                System.out.print("Código de producto (o 'fin' para terminar): ");
                String codigo = reader.readLine();
                
                if (codigo.equalsIgnoreCase("fin")) {
                    if (venta.getDetalles().isEmpty()) {
                        System.out.println("Debe agregar al menos un producto!");
                        continue;
                    }
                    agregandoProductos = false;
                    continue;
                }
                
                Producto producto = gestorProductos.buscarProducto(codigo);
                if (producto == null) {
                    System.out.println("Producto no encontrado!");
                    continue;
                }
                
                System.out.print("Cantidad: ");
                int cantidad = Integer.parseInt(reader.readLine());
                
                if (cantidad > producto.getExistencia()) {
                    System.out.println("No hay suficiente existencia!");
                    continue;
                }
                
                venta.agregarDetalle(new DetalleVenta(producto, cantidad));
                System.out.println("Producto agregado a la venta");
            }
            
            gestorVentas.registrarVenta(venta);
            System.out.println("\nVenta registrada con éxito!");
            System.out.println(venta.generarNota());
        }

        private void verNotaVenta() throws IOException {
            System.out.print("\nIngrese número de nota de venta: ");
            String numNota = reader.readLine();
            
            for (Venta v : gestorVentas.getVentas()) {
                if (v.getNumeroNota().equals(numNota)) {
                    System.out.println("\n" + v.generarNota());
                    return;
                }
            }
            
            System.out.println("Nota de venta no encontrada.");
        }

        private void listarVentas() {
            ArrayList<Venta> ventas = gestorVentas.getVentas();
            
            if (ventas.isEmpty()) {
                System.out.println("\nNo hay ventas registradas.");
                return;
            }
            
            System.out.println("\n--- LISTA DE VENTAS ---");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for (Venta v : ventas) {
                System.out.println("Nota: " + v.getNumeroNota());
                System.out.println("Fecha: " + sdf.format(v.getFecha()));
                System.out.println("Cliente: " + v.getCliente().getRazonSocial());
                System.out.println("Total: $" + String.format("%.2f", v.getTotal()));
                System.out.println("----------------------");
            }
        }

        private void menuReportes() throws IOException {
            boolean salir = false;
            while (!salir) {
                System.out.println("\n--- REPORTES ---");
                System.out.println("1. Reporte de Clientes");
                System.out.println("2. Reporte de Proveedores");
                System.out.println("3. Reporte de Inventario");
                System.out.println("4. Reporte de Ventas");
                System.out.println("5. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                
                String opcion = reader.readLine();
                
                switch (opcion) {
                    case "1":
                        listarClientes();
                        break;
                    case "2":
                        listarProveedores();
                        break;
                    case "3":
                        reporteInventario();
                        break;
                    case "4":
                        reporteVentas();
                        break;
                    case "5":
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        }

        private void listarProveedores() {
            ArrayList<Proveedor> proveedores = gestorProveedores.getProveedores();
            
            if (proveedores.isEmpty()) {
                System.out.println("\nNo hay proveedores registrados.");
                return;
            }
            
            System.out.println("\n--- LISTA DE PROVEEDORES ---");
            for (Proveedor p : proveedores) {
                System.out.println(p);
                System.out.println("----------------------");
            }
        }

        private void reporteInventario() {
            ArrayList<Producto> productos = gestorProductos.getProductos();
            
            if (productos.isEmpty()) {
                System.out.println("\nNo hay productos registrados.");
                return;
            }
            
            System.out.println("\n--- INVENTARIO ---");
            System.out.println("Código\tDescripción\tPrecio\tExistencia");
            for (Producto p : productos) {
                System.out.printf("%s\t%s\t$%.2f\t%d%n", 
                                 p.getCodigo(), p.getDescripcion(), 
                                 p.getPrecioUnitario(), p.getExistencia());
            }
        }

        private void reporteVentas() {
            ArrayList<Venta> ventas = gestorVentas.getVentas();
            
            if (ventas.isEmpty()) {
                System.out.println("\nNo hay ventas registradas.");
                return;
            }
            
            double totalVentas = 0;
            System.out.println("\n--- REPORTE DE VENTAS ---");
            System.out.println("Nota\t\tFecha\t\tCliente\t\tTotal");
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            for (Venta v : ventas) {
                System.out.printf("%s\t%s\t%s\t$%.2f%n", 
                                 v.getNumeroNota(), sdf.format(v.getFecha()),
                                 v.getCliente().getRazonSocial(), v.getTotal());
                totalVentas += v.getTotal();
            }
            
            System.out.println("\nTOTAL GENERAL: $" + String.format("%.2f", totalVentas));
        }

    
}
