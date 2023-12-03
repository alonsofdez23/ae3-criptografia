import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash {
    public static String hashPassword(String password) {
        try {
            // Obtener una instancia de MessageDigest con el algoritmo SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // Convertir la contraseña a bytes y aplicar el hash
            byte[] passwordHashBytes = md.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(passwordHashBytes);

        } catch (NoSuchAlgorithmException e) {
            // Manejar la excepción en caso de que el algoritmo no esté disponible
            e.printStackTrace();
            return null;
        }
    }

    static boolean verificarCredenciales(String nombreUsuario, String password, Usuario[] usuarios) {
        // Buscar el usuario por el nombre de usuario ingresado
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario)) {
                // Verificar la contraseña ingresada con la contraseña hasheada almacenada
                return usuario.getPassword().equals(hashPassword(password));
            }
        }
        return false; // Usuario no encontrado
    }
}
