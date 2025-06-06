public class Cliente {
    private String numeroCliente;
    private String rfc;
    private String razonSocial;
    private String direccionFiscal;
    private String nombreContacto;
    private String telefono;

    public Cliente(String numeroCliente, String rfc, String razonSocial, 
                  String direccionFiscal, String nombreContacto, String telefono) {
        this.numeroCliente = numeroCliente;
        this.rfc = rfc;
        this.razonSocial = razonSocial;
        this.direccionFiscal = direccionFiscal;
        this.nombreContacto = nombreContacto;
        this.telefono = telefono;
    }

    // Getters
    public String getNumeroCliente() { return numeroCliente; }
    public String getRfc() { return rfc; }
    public String getRazonSocial() { return razonSocial; }
    public String getDireccionFiscal() { return direccionFiscal; }
    public String getNombreContacto() { return nombreContacto; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return "Cliente #" + numeroCliente + " - " + razonSocial + 
               "\nRFC: " + rfc + 
               "\nDirección Fiscal: " + direccionFiscal + 
               "\nContacto: " + nombreContacto + " - Tel: " + telefono;
    }
}