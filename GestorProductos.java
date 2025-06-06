import java.util.*;

public class GestorProductos {
    private ArrayList<Producto> productos;

    public GestorProductos() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Producto buscarProducto(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
}