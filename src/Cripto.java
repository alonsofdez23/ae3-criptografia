import javax.crypto.SecretKey;
import java.util.Scanner;

public class Cripto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        boolean loginExitoso = false;
        int intentos = 3;

        // Generamos una clave AES
        SecretKey clave = Clave.generarClave();

        // Frase
        String frase = null;

        // Frase encriptada en bytes
        byte[] bytesFraseEncriptada = new byte[0];

        Usuario[] usuarios = {
                new Usuario("alonso", Hash.hashPassword("passalonso")),
                new Usuario("manolo", Hash.hashPassword("passmanolo")),
                new Usuario("alejandro", Hash.hashPassword("passalejandro"))
        };

        System.out.println("======= Login =======");

        // Ciclo para gestionar el inicio de sesión
        while (intentos > 0) {
            System.out.print("Introduce usuario: ");
            String nombreUsuario = sc.nextLine();
            System.out.print("Introduce contraseña: ");
            String password = sc.nextLine();

            // Verificar credenciales
            loginExitoso = Hash.verificarCredenciales(nombreUsuario, password, usuarios);

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
                            bytesFraseEncriptada = Clave.encriptar(frase, clave);
                            System.out.println("Frase encriptada y guardada");
                            break;
                        case 2:
                            if (frase != null) {
                                String fraseDesencriptada = Clave.desencriptar(bytesFraseEncriptada, clave);
                                System.out.println("Frase desencriptada: " + fraseDesencriptada);
                            } else {
                                System.out.println("No has introducido ninguna frase.");
                            }
                            break;
                        case 3:
                            salir = true;
                            intentos = 0;
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
        if (!salir) {
            System.out.println("Demasiados intentos fallidos. Saliendo del programa.");
        }
    }

    private static void mostrarMenu() {
        System.out.println("======= Menú =======");
        System.out.println("1. Encriptar frase");
        System.out.println("2. Desencriptar frase");
        System.out.println("3. Salir del programa");
        System.out.print("Seleccione una opción: ");
    }
}
