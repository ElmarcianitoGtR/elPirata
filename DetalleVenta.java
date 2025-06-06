public class DetalleVenta {

    // Se declaran los datos a manejar
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double importe;

    public DetalleVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecioUnitario();//Por el metodo getPrecioUnitario se extrae el precio de la lista de productos
        this.importe = cantidad * precioUnitario;
    }

    // Se declaran los metodos necesarios para mostrar detalles especificos de la venta
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getImporte() { return importe; }

    @Override //se sobreescribe el metodo tostring para que nos devuelva el texto en un formato que nosotros le pasamos para que sea mas legible
    public String toString() {
        return producto.getCodigo() + " - " + producto.getDescripcion() + 
                "\n" + cantidad + " x $" + precioUnitario + " = $" + importe;
    }
}