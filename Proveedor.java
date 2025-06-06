public class Proveedor {
    //a se declara lo que vamos a ocupar
    private String razonSocial;
    private String rfc;
    private String direccion;
    private String telefono;
    private String fax;
    private String nombreContacto;
    private String emailContacto;
    private String telefonoContacto;

    public Proveedor(String razonSocial, String rfc, String direccion, String telefono, 
                    String fax, String nombreContacto, String emailContacto, String telefonoContacto) {
        this.razonSocial = razonSocial;
        this.rfc = rfc;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fax = fax;
        this.nombreContacto = nombreContacto;
        this.emailContacto = emailContacto;
        this.telefonoContacto = telefonoContacto;
    }
    //Estos son los metodos que permiten obtener informacion de los proveedores
    public String getRazonSocial() { return razonSocial; }
    public String getRfc() { return rfc; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getFax() { return fax; }
    public String getNombreContacto() { return nombreContacto; }
    public String getEmailContacto() { return emailContacto; }
    public String getTelefonoContacto() { return telefonoContacto; }

    @Override//se sobre escribe la funcion para poder brindar los datos en nuestro formato
    public String toString() {
        return "Proveedor: " + razonSocial + 
                "\nRFC: " + rfc + 
                "\nDirección: " + direccion + 
                "\nTel: " + telefono + " - Fax: " + fax + 
                "\nContacto: " + nombreContacto + 
                "\nEmail: " + emailContacto + " - Tel: " + telefonoContacto;
    }
    
}
