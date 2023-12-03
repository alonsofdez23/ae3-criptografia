import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class Cripto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        boolean loginExitoso = false;
        int intentos = 3;

        // Generamos una clave AES
        SecretKey clave = generarClave();

        // Frase
        String frase = null;

        // Frase encriptada en bytes
        byte[] bytesFraseEncriptada = new byte[0];

        Usuario[] usuarios = {
                new Usuario("alonso", hashPassword("passalonso")),
                new Usuario("manolo", hashPassword("passmanolo")),
                new Usuario("alejandro", hashPassword("passalejandro"))
        };

        System.out.println("======= Login =======");

        // Ciclo para gestionar el inicio de sesión
        while (intentos > 0) {
            System.out.print("Introduce usuario: ");
            String nombreUsuario = sc.nextLine();
            System.out.print("Introduce contraseña: ");
            String password = sc.nextLine();

            // Verificar credenciales
            loginExitoso = verificarCredenciales(nombreUsuario, password, usuarios);

            if (loginExitoso) {
                System.out.println("¡Bienvenido, " + nombreUsuario + "!");

                while (!salir) {
                    mostrarMenu();
                    int opcion = sc.nextInt();
                    sc.nextLine(); // Limpia el buffer de entrada

                    switch (opcion) {
                        case 1:
                            System.out.print("Introduce la frase a encriptar: ");
                            frase = sc.nextLine();
                            bytesFraseEncriptada = encriptar(frase, clave);
                            System.out.println("Frase encriptada y guardada");
                            break;
                        case 2:
                            if (frase != null) {
                                String fraseDesencriptada = desencriptar(bytesFraseEncriptada, clave);
                                System.out.println("Frase desencriptada: " + fraseDesencriptada);
                            } else {
                                System.out.println("No has introducido ninguna frase.");
                            }
                            break;
                        case 3:
                            salir = true;
                            System.out.println("Saliendo del programa. ¡Hasta luego!");
                            break;
                        default:
                            System.out.println("Opción no válida. Inténtelo de nuevo.");
                    }
                }
            } else {
                intentos--;
                System.out.println("Credenciales incorrectas. Intentos restantes: " + intentos);
            }
        }

        System.out.println("Demasiados intentos fallidos. Saliendo del programa.");
    }

    private static void mostrarMenu() {
        System.out.println("======= Menú =======");
        System.out.println("1. Encriptar frase");
        System.out.println("2. Desencriptar frase");
        System.out.println("3. Salir del programa");
        System.out.print("Seleccione una opción: ");
    }

    private static SecretKey generarClave() {
        try {
            // Instanciamos un generador de claves con el algoritmo AES
            KeyGenerator generador = KeyGenerator.getInstance("AES");

            // Generamos una clave simétrica
            return generador.generateKey();

        } catch (GeneralSecurityException gse) {
            System.out.println("Algo ha fallado... " + gse.getMessage());
            throw new RuntimeException(gse);
        }
    }

    private static byte[] encriptar(String frase, SecretKey clave) {
        try {
            // Instanciamos un cifrador que nos permitirá encriptar a partir
            // de la clave generada
            Cipher cifrador = Cipher.getInstance("AES");

            // Configuramos el cifrador para que use la clave simétrica para encriptar
            cifrador.init(Cipher.ENCRYPT_MODE, clave);

            // Convertimos la frase recibida a bytes para poder trabajar con ella
            byte[] bytesFrase = frase.getBytes();

            // Ciframos la frase
            return cifrador.doFinal(bytesFrase);

        } catch (GeneralSecurityException gse) {
            System.out.println("Algo ha fallado... " + gse.getMessage());
            throw new RuntimeException(gse);
        }
    }

    private static String desencriptar(byte[] fraseCifrada, SecretKey clave) {
        try {
            // Instanciamos un cifrador que nos permitirá desencriptar a partir
            // de la clave generada
            Cipher descifrador = Cipher.getInstance("AES");

            // Configuramos el cifrador para que use la clave simétrica para encriptar
            descifrador.init(Cipher.DECRYPT_MODE, clave);

            // Desciframos la frase
            byte[] bytesFraseDescifrada = descifrador.doFinal(fraseCifrada);

            return new String(bytesFraseDescifrada);

        } catch (GeneralSecurityException gse) {
            System.out.println("Algo ha fallado... " + gse.getMessage());
            throw new RuntimeException(gse);
        }
    }

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

    private static boolean verificarCredenciales(String nombreUsuario, String password, Usuario[] usuarios) {
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
