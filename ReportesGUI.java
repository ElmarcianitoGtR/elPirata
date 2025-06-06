// Importaciones

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportesGUI {
    // Se declara todo lo que se va a manejar
    private JFrame frame;
    private GestorClientes gestorClientes;
    private GestorProveedores gestorProveedores;
    private GestorProductos gestorProductos;
    private GestorVentas gestorVentas;

    public ReportesGUI(GestorClientes gestorClientes, GestorProveedores gestorProveedores, 
                      GestorProductos gestorProductos, GestorVentas gestorVentas) {
        // Se inicializan los getores a ocupar
        this.gestorClientes = gestorClientes;
        this.gestorProveedores = gestorProveedores;
        this.gestorProductos = gestorProductos;
        this.gestorVentas = gestorVentas;
        crearGUI();// Se manda a llamar la funcion que pinta la ventana
    }
    // Se declaran los metodos que necesitamos para poder pintar la ventana
    private void crearGUI() {
        // Se genera una ventana de nombre Reportes y de un tamaño de 800px X 600px
        frame = new JFrame("Reportes");
        frame.setSize(800, 600);
        
        // Se le pone el border layout para dividirlo en 5 partes
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));//Se hace una tabla de una solo fila con 5 columnas con un espaciado de 5px
        // Se crean los botones que nos permitiran interactuar
        JButton btnClientes = new JButton("Clientes");
        JButton btnProveedores = new JButton("Proveedores");
        JButton btnProductos = new JButton("Productos");
        JButton btnVentas = new JButton("Ventas");
        JButton btnRegresar = new JButton("Regresar");
        // Se agrega un campo de texto que posteriormente se ocupara para mostrar los datos
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);// Se le quita la propiedad de ser editable para que solo nosotros lo podamos modificar
        JScrollPane scrollPane = new JScrollPane(textArea);// Se le genera un panel que tenga scroll
        //A todos los botones se les agrega una escucha para que puedan ejecutar su funcion correspondiente
        btnClientes.addActionListener(e -> reporteClientes(textArea));
        btnProveedores.addActionListener(e -> reporteProveedores(textArea));
        btnProductos.addActionListener(e -> reporteProductos(textArea));
        btnVentas.addActionListener(e -> reporteVentas(textArea));
        btnRegresar.addActionListener(e -> frame.dispose());
        // Se agregan los botones al panel de botones que es la que tenia la tabla 
        buttonPanel.add(btnClientes);
        buttonPanel.add(btnProveedores);
        buttonPanel.add(btnProductos);
        buttonPanel.add(btnVentas);
        buttonPanel.add(btnRegresar);
        //Se le asigna una posicion a los componentes, la botonera la ponemos arriba y donde vamos a mostrar la informacion se queda en el centro
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        // Se agrega a la ventanita el panel y hace visible la ventana
        frame.add(panel);
        frame.setVisible(true);
    }

    private void reporteClientes(JTextArea textArea) {
        ArrayList<Cliente> clientes = gestorClientes.getClientes();
        if (clientes.isEmpty()) {
            textArea.setText("No hay clientes registrados");
        } else {
            StringBuilder sb = new StringBuilder("=== REPORTE DE CLIENTES ===\n");
            for (Cliente c : clientes) {
                sb.append(c).append("\n----------------------\n");
            }
            textArea.setText(sb.toString());
        }
    }
    //se generan los metodos que nos permitiran realizar los reportes
    private void reporteProveedores(JTextArea textArea) {
        ArrayList<Proveedor> proveedores = gestorProveedores.getProveedores();
        if (proveedores.isEmpty()) {//validamos si nuestra lista de proveedores esta vacia
            textArea.setText("No hay proveedores registrados");
        } else {//En caso que no lo este, desplegamos todos nuestros proveedores y lo vaciamos en el textarea
            StringBuilder sb = new StringBuilder("=== REPORTE DE PROVEEDORES ===\n");
            for (Proveedor p : proveedores) {
                sb.append(p).append("\n----------------------\n");
            }
            textArea.setText(sb.toString());
        }
    }

    private void reporteProductos(JTextArea textArea) {
        ArrayList<Producto> productos = gestorProductos.getProductos();
        if (productos.isEmpty()) {//Validamos si nuestra lista de productos esta vacia
            textArea.setText("No hay productos registrados");
        } else {// En caso que si tengamos productos, desplegamos todos nuestros productos en el inventario y los vaciamos en el textarea
            StringBuilder sb = new StringBuilder("=== REPORTE DE INVENTARIO ===\n");
            sb.append("Código\tDescripción\tPrecio\tExistencia\n");
            for (Producto p : productos) {
                sb.append(String.format("%s\t%s\t$%.2f\t%d%n",//necesitamos dar formato para poder mostrar cadenas de texto, el $, solo dos decimales, numeros enteros, darle salto de linea y tabulacion 
                    p.getCodigo(), p.getDescripcion(), 
                    p.getPrecioUnitario(), p.getExistencia()));
            }
            textArea.setText(sb.toString());// Toda la informacion la vaciamos en el textarea
        }
    }

    private void reporteVentas(JTextArea textArea) {
        //Se declara la lista que se jala del gestor de ventas
        ArrayList<Venta> ventas = gestorVentas.getVentas();
        if (ventas.isEmpty()) {// verificamos si esta vacia
            textArea.setText("No hay ventas registradas");
        } else {// Se despliega toda la informacion de las ventas y se vacia en el textarea
            StringBuilder sb = new StringBuilder("=== REPORTE DE VENTAS ===\n");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            double totalGeneral = 0;
            
            sb.append("Nota\t\tFecha\t\tCliente\t\tTotal\n");
            for (Venta v : ventas) {
                sb.append(String.format("%s\t%s\t%s\t$%.2f%n", 
                    v.getNumeroNota(), sdf.format(v.getFecha()),
                    v.getCliente().getRazonSocial(), v.getTotal()));
                totalGeneral += v.getTotal();
            }
            
            sb.append("\nTOTAL GENERAL: $").append(String.format("%.2f", totalGeneral));
            textArea.setText(sb.toString());
        }
    }
}