import java.util.*;

public class GestorProveedores {
    private ArrayList<Proveedor> proveedores;

    public GestorProveedores() {
        proveedores = new ArrayList<>();
    }

    public void agregarProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }

    public Proveedor buscarProveedorPorRFC(String rfc) {
        for (Proveedor p : proveedores) {
            if (p.getRfc().equalsIgnoreCase(rfc)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }
}