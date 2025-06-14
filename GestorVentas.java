// Importaciones
import java.util.*;
public class GestorVentas {
    //Se declaran los datos a manejar
    private ArrayList<Venta> ventas;
    private GestorClientes gestorClientes;
    private GestorProductos gestorProductos;

    public GestorVentas(GestorClientes gestorClientes, GestorProductos gestorProductos) {
        this.ventas = new ArrayList<>();
        this.gestorClientes = gestorClientes;
        this.gestorProductos = gestorProductos;
    }

    public void registrarVenta(Venta venta) {
        ventas.add(venta);// Agrega la venta al resto de las ventas
        // Este for vuelve a recorrer las ventas para actualizar y se vea la que agregamos
        for (DetalleVenta detalle : venta.getDetalles()) {
            Producto producto = detalle.getProducto();
            producto.setExistencia(producto.getExistencia() - detalle.getCantidad());
        }
    }
    // Se declara un metodo para que se pueda acceder a las ventas
    public ArrayList<Venta> getVentas() {
        return ventas;
    }
}