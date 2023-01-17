package org.encriptador;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Base64;
import java.util.Scanner;

public class Encriptador {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("Ingresa la clave a encriptar: ");
        String input = sc.nextLine();
        System.out.println("Selecciona un algoritmo de encriptación:");
        System.out.println("1. AES");
        System.out.println("2. DES");
        System.out.println("3. Blowfish");
        System.out.println("4. Triple DES");
        System.out.println("5. Twofish");
        int choice = sc.nextInt();
        String algorithm = "";
        switch (choice) {
            case 1:
                algorithm = "AES";
                break;
            case 2:
                algorithm = "DES";
                break;
            case 3:
                algorithm = "Blowfish";
                break;
            case 4:
                algorithm = "DESede";
                break;
            case 5:
                algorithm = "Twofish";
                break;
            default:
                System.out.println("Opción inválida");
                System.exit(1);
        }
        SecretKey secretKey = KeyGenerator.getInstance(algorithm).generateKey();
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(input.getBytes());
        String encoded = Base64.getEncoder().encodeToString(encrypted);
        System.out.println("Clave encriptada: " + encoded);
        System.out.println("¿Desea copiar la clave encriptada al portapapeles? (S/N)");

        String copyOption = sc.next();
        if (copyOption.toUpperCase().equals("S")) {
            StringSelection stringSelection = new StringSelection(encoded);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            System.out.println("Clave encriptada copiada al portapapeles");
        }

    }
}
