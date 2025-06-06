// Importaciones

import javax.swing.*;
import java.awt.*;

public class VentasGUI {

    // Declaramos los datos que vamos a ocupar
    private JFrame frame;
    private GestorVentas gestorVentas;
    private GestorClientes gestorClientes;
    private GestorProductos gestorProductos;

    public VentasGUI(GestorVentas gestorVentas, GestorClientes gestorClientes, GestorProductos gestorProductos) {
        this.gestorVentas = gestorVentas;
        this.gestorClientes = gestorClientes;
        this.gestorProductos = gestorProductos;
        crearGUI();// LLamamos la funcion para pintar la ventana
    }
    // Declaramos los metodos que vamos a necesitar
    private void crearGUI() {
        // Generamos una ventana con el titulo de gestion de ventas y d3e un tamaño de 800px X 600px

        frame = new JFrame("Gestión de Ventas");
        frame.setSize(800, 600);
        //Se genera un panel con borderlayout para dividirlo en 5        
        JPanel panel = new JPanel(new BorderLayout());
        //Se genera un panel para los botones de una sola fila y tres columnas con un espaciado de 5px
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        //Se declaran los botones que nos van a permitir ejecutar las funciones
        JButton btnNuevaVenta = new JButton("Nueva Venta");
        JButton btnVerVenta = new JButton("Ver Venta");
        JButton btnRegresar = new JButton("Regresar");
        //Se declara un text area donde vamos a vaciar la informacion
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);// Lo hacemos que no sea editable
        JScrollPane scrollPane = new JScrollPane(textArea);//Generamos un panel con scroll
        

        //Le agregamos una escucha a los botones para que cuando sean precionados ejecuten una funcion en especifico
        btnNuevaVenta.addActionListener(e -> nuevaVenta(textArea));
        btnVerVenta.addActionListener(e -> verVenta(textArea));
        btnRegresar.addActionListener(e -> frame.dispose());
        //Se agregan los botones al panel
        buttonPanel.add(btnNuevaVenta);
        buttonPanel.add(btnVerVenta);
        buttonPanel.add(btnRegresar);
        //Al panel principal se le añade la botonera y el panel que tiene el scroll poniendo la botonera arriba y el del scroll en el centro
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        //se agrega el panel a la ventanita y se hace visible
        frame.add(panel);
        frame.setVisible(true);
    }

    private void nuevaVenta(JTextArea textArea) {

        //Se genera una mini ventanita para que ingrese el numero del cliente y validamos si es nulo o si si escribio el numero
        String numCliente = JOptionPane.showInputDialog(frame, 
            "Ingrese número de cliente:");
        if (numCliente == null || numCliente.isEmpty()) return;
        //En caso de no encontrar al cliente se muestra en el textarea un mensaje de error
        Cliente cliente = gestorClientes.buscarCliente(numCliente);
        if (cliente == null) {
            textArea.setText("Cliente no encontrado");
            return;
        }
        //Esto es para seriar la nota de venta
        String numNota = "VTA-" + System.currentTimeMillis();
        Venta venta = new Venta(numNota, cliente);
        
        boolean continuar = true;
        while (continuar) {
            // Esto genera otra mini ventanita para poder ingresar el codigo del producto del cual se realizo la venta
            String codigo = JOptionPane.showInputDialog(frame, 
                "Ingrese código del producto (o 'fin' para terminar):");
            //En caso de que no se ponga ningun producto le mostraremos en el textarea un mensaje de error
            if (codigo == null || codigo.equalsIgnoreCase("fin")) {
                if (venta.getDetalles().isEmpty()) {
                    textArea.setText("Debe agregar al menos un producto");
                    return;
                }
                continuar = false;
                continue;
            }
            //Aqui se busca el producto en caso de no encontrarlo se muestra el error en el textarea
            Producto producto = gestorProductos.buscarProducto(codigo);
            if (producto == null) {
                textArea.setText("Producto no encontrado");
                continue;
            }
            // Se hace una mini ventanita que nos ayuda a pedir la cantidad del producto que se va a vender
            String cantidadStr = JOptionPane.showInputDialog(frame, 
                "Ingrese cantidad para " + producto.getDescripcion() + ":");
            if (cantidadStr == null || cantidadStr.isEmpty()) continue;
            // se hace hace un try por que el usuario puede meter un valor invalido y necesitamos que no marque error
            try {
                int cantidad = Integer.parseInt(cantidadStr);
                if (cantidad > producto.getExistencia()) {//Verifica si hay la suficiente existencia en el inventario
                    textArea.setText("No hay suficiente existencia");
                    continue;
                }
                
                venta.agregarDetalle(new DetalleVenta(producto, cantidad));
                textArea.setText("Producto agregado a la venta");//notifica en el text area que fue agregado
            } catch (NumberFormatException ex) {
                textArea.setText("Cantidad debe ser un número válido");//notifica si el numero no es valido
            }
        }
        
        gestorVentas.registrarVenta(venta);//registramos la venta y notificamos que fue registrada
        textArea.setText("Venta registrada con éxito:\n" + venta.generarNota());
    }

    private void verVenta(JTextArea textArea) {//metodo para buscar una venta
        //se genera una miniventanita donde pide el numero de la venta
        String numNota = JOptionPane.showInputDialog(frame, 
            "Ingrese número de nota de venta:");
        if (numNota == null || numNota.isEmpty()) return;
        
        for (Venta v : gestorVentas.getVentas()) {//la buscamos en la lista de ventas
            if (v.getNumeroNota().equals(numNota)) {
                textArea.setText(v.generarNota());
                return;
            }
        }
        textArea.setText("Venta no encontrada");
    }
}