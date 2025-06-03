
public class Validador {
    public static boolean validarRFC(String rfc) {
        return rfc != null && rfc.matches("[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}");
    }

    public static boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("\\d{10}");
    }

    public static boolean validarEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean validarNumeroPositivo(String numero) {
        try {
            double valor = Double.parseDouble(numero);
            return valor > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
