public class DetalleVenta {
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double importe;

    public DetalleVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecioUnitario();
        this.importe = cantidad * precioUnitario;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getImporte() { return importe; }

    @Override
    public String toString() {
        return producto.getCodigo() + " - " + producto.getDescripcion() + 
                "\n" + cantidad + " x $" + precioUnitario + " = $" + importe;
    }
}