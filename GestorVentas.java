import java.util.*;
public class GestorVentas {
    private ArrayList<Venta> ventas;
    private GestorClientes gestorClientes;
    private GestorProductos gestorProductos;

    public GestorVentas(GestorClientes gestorClientes, GestorProductos gestorProductos) {
        this.ventas = new ArrayList<>();
        this.gestorClientes = gestorClientes;
        this.gestorProductos = gestorProductos;
    }

    public void registrarVenta(Venta venta) {
        ventas.add(venta);
        // Actualizar inventario
        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = detalle.getProducto();
            producto.setExistencia(producto.getExistencia() - detalle.getCantidad());
        }
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }
}