import java.io.*;

public class SistemaElPirata {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        GestorClientes gestorClientes = new GestorClientes();
        GestorProveedores gestorProveedores = new GestorProveedores();
        GestorProductos gestorProductos = new GestorProductos();
        GestorVentas gestorVentas = new GestorVentas(gestorClientes, gestorProductos);
        
        InterfazUsuario interfaz = new InterfazUsuario(reader, gestorClientes, 
                                                      gestorProveedores, gestorProductos,
                                                      gestorVentas);
        
        try {
            interfaz.menuPrincipal();
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el lector: " + e.getMessage());
            }
        }
    }
}
