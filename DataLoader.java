public class DataLoader {
    public static void cargarDatosDePrueba(MainGUI sistema) {
        // Cargar clientes (20)
        sistema.getGestorClientes().agregarCliente(new Cliente("CLI001", "ABC123456789", "Tienda Don Pepe", 
            "Calle Principal 123, Centro", "José Pérez", "5551234567"));
        sistema.getGestorClientes().agregarCliente(new Cliente("CLI002", "DEF987654321", "Supermercado La Economía", 
            "Av. Revolución 456, Norte", "María López", "5557654321"));

        // Cargar proveedores (20)
        sistema.getGestorProveedores().agregarProveedor(new Proveedor("Distribuidora Alimenticia SA", "DAS123456789", 
            "Av. Industrial 1000, Zona Industrial", "5551112233", "5551112244", "Roberto Gómez", 
            "r.gomez@das.com", "5551112255"));

        // Cargar productos (20)
        sistema.getGestorProductos().agregarProducto(new Producto("PROD001", "Arroz 1kg", 25.50, 100));

        // Cargar ventas (20)
        Cliente cliente1 = sistema.getGestorClientes().buscarCliente("CLI001");
        Producto prod1 = sistema.getGestorProductos().buscarProducto("PROD001");
        
        Venta venta1 = new Venta("VTA001", cliente1);
        venta1.agregarDetalle(new DetalleVenta(prod1, 5));
        sistema.getGestorVentas().registrarVenta(venta1);
    }
}