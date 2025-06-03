import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneradorIDs {
    private static int secuenciaVentas = 1;

    public static String generarIdVenta() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fecha = sdf.format(new Date());
        return "VTA-" + fecha + "-" + String.format("%04d", secuenciaVentas++);
    }

    public static String generarIdCliente() {
        return "CLI-" + System.currentTimeMillis();
    }

    public static String generarIdProducto() {
        return "PROD-" + System.currentTimeMillis();
    }
}