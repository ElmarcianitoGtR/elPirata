public class Producto {
        private String codigo;
        private String descripcion;
        private double precioUnitario;
        private int existencia;

        public Producto(String codigo, String descripcion, double precioUnitario, int existencia) {
            this.codigo = codigo;
            this.descripcion = descripcion;
            this.precioUnitario = precioUnitario;
            this.existencia = existencia;
        }

        // Getters y Setters
        public String getCodigo() { return codigo; }
        public String getDescripcion() { return descripcion; }
        public double getPrecioUnitario() { return precioUnitario; }
        public int getExistencia() { return existencia; }
        public void setExistencia(int existencia) { this.existencia = existencia; }

        @Override
        public String toString() {
            return "Producto #" + codigo + " - " + descripcion + 
                   "\nPrecio: $" + precioUnitario + " - Existencia: " + existencia;
        }
    
}
