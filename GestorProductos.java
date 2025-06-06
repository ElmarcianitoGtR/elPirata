//Importaciones

import java.util.*;

public class GestorProductos {
    // Se declara el arreglo a trabajar
    private ArrayList<Producto> productos;

    public GestorProductos() {
        productos = new ArrayList<>();
    }
    // Se declaran los metodos que se necesitan para gestionar los productos
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