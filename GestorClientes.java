
import java.util.*;
public class GestorClientes {
    private ArrayList<Cliente> clientes;

    public GestorClientes() {
        clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarCliente(String numeroCliente) {
        for (Cliente c : clientes) {
            if (c.getNumeroCliente().equals(numeroCliente)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
}