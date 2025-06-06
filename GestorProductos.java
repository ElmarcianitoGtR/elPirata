
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
        cargarDatos();
    }
    private void cargarDatos() {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split("\\|");
                    if (datos.length == 4) {
                        productos.add(new Producto(
                            datos[0], 
                            datos[1], 
                            Double.parseDouble(datos[2]), 
                            Integer.parseInt(datos[3])
                        ));
                    }
                }
            } catch (IOException | NumberFormatException e) {
                // Si el archivo no existe o hay error en formato
            }
        }

    public void guardarDatos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Producto p : productos) {
                pw.println(String.join("|",
                    p.getCodigo(),
                    p.getDescripcion(),
                    String.valueOf(p.getPrecioUnitario()),
                    String.valueOf(p.getExistencia())
                ));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar productos: " + e.getMessage());
        }
    }


}