import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.GeneralSecurityException;

public class Clave {
    static SecretKey generarClave() {
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

    static byte[] encriptar(String frase, SecretKey clave) {
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

    static String desencriptar(byte[] fraseCifrada, SecretKey clave) {
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
}
