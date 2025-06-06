import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
        cargarDatos();
    }
    private void cargarDatos() {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split("\\|");
                    if (datos.length == 6) {
                        clientes.add(new Cliente(
                            datos[0], datos[1], datos[2], 
                            datos[3], datos[4], datos[5]
                        ));
                    }
                }
            } catch (IOException e) {
                // Si el archivo no existe, se creará al guardar
            }
        }

        public void guardarDatos() {
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                for (Cliente c : clientes) {
                    pw.println(String.join("|",
                        c.getNumeroCliente(),
                        c.getRfc(),
                        c.getRazonSocial(),
                        c.getDireccionFiscal(),
                        c.getNombreContacto(),
                        c.getTelefono()
                    ));
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al guardar clientes: " + e.getMessage());
            }
        }
}   