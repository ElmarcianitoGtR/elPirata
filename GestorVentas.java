import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
        cargarDatos();
    }
    

    private void cargarDatos() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datosVenta = linea.split("\\|");
                if (datosVenta.length >= 3) {
                    Cliente cliente = gestorClientes.buscarCliente(datosVenta[1]);
                    if (cliente != null) {
                        Venta venta = new Venta(datosVenta[0], cliente);
                        
                        // Leer detalles
                        for (int i = 2; i < datosVenta.length; i++) {
                            String[] detalle = datosVenta[i].split(",");
                            if (detalle.length == 2) {
                                Producto p = gestorProductos.buscarProducto(detalle[0]);
                                if (p != null) {
                                    int cantidad = Integer.parseInt(detalle[1]);
                                    venta.agregarDetalle(new DetalleVenta(p, cantidad));
                                }
                            }
                        }
                        ventas.add(venta);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            // Si el archivo no existe o hay error en formato
        }
    }

    public void guardarDatos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Venta v : ventas) {
                StringBuilder sb = new StringBuilder();
                sb.append(v.getNumeroNota()).append("|");
                sb.append(v.getCliente().getNumeroCliente()).append("|");
                
                for (DetalleVenta dv : v.getDetalles()) {
                    sb.append(dv.getProducto().getCodigo()).append(",");
                    sb.append(dv.getCantidad()).append("|");
                }
                
                pw.println(sb.toString());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar ventas: " + e.getMessage());
        }
    }
}