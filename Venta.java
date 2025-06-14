//Importaciones
import java.util.*;
import java.text.SimpleDateFormat;

public class Venta {
    //Se declaran las cosas a ocupar
    private String numeroNota;
    private Date fecha;
    private Cliente cliente;
    private ArrayList<DetalleVenta> detalles;
    private double subtotal;
    private double impuestos;
    private double total;

    public Venta(String numeroNota, Cliente cliente) {
        this.numeroNota = numeroNota;
        this.fecha = new Date();//Generamos un dato con formato fecha
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        //Se inicializan los datos arrastrables
        this.subtotal = 0;
        this.impuestos = 0;
        this.total = 0;
    }
    //Se le añaden los detalles a la venta
    public void agregarDetalle(DetalleVenta detalle) {
        detalles.add(detalle);
        subtotal += detalle.getImporte();
        impuestos = subtotal * 0.16; // IVA 16%
        total = subtotal + impuestos;
    }

    //Estos son los metodos que permiten interactiar con la venta y obtener informacion sobre ella
    public String getNumeroNota() { return numeroNota; }
    public Date getFecha() { return fecha; }
    public Cliente getCliente() { return cliente; }
    public ArrayList<DetalleVenta> getDetalles() { return detalles; }
    public double getSubtotal() { return subtotal; }
    public double getImpuestos() { return impuestos; }
    public double getTotal() { return total; }

    public String generarNota() {//Generamos la nota de venta
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder nota = new StringBuilder();
        
        nota.append("=== NOTA DE VENTA ===\n");
        nota.append("No: ").append(numeroNota).append("\n");
        nota.append("Fecha: ").append(sdf.format(fecha)).append("\n");
        nota.append("Cliente: ").append(cliente.getRazonSocial()).append("\n");
        nota.append("RFC: ").append(cliente.getRfc()).append("\n");
        nota.append("Dirección: ").append(cliente.getDireccionFiscal()).append("\n");
        nota.append("\n--- DETALLE ---\n");
        
        for (DetalleVenta detalle : detalles) {
            nota.append(detalle).append("\n");
        }
        
        nota.append("\n--- TOTALES ---\n");
        nota.append("Subtotal: $").append(String.format("%.2f", subtotal)).append("\n");
        nota.append("Impuestos (16%): $").append(String.format("%.2f", impuestos)).append("\n");
        nota.append("TOTAL: $").append(String.format("%.2f", total)).append("\n");
        
        return nota.toString();
    }
}