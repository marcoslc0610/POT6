package utils;

import java.util.Random;
import java.util.Scanner;

public class Utils {
    public static final Scanner S = new Scanner(System.in);
    public static final int IVA = 21;

    //Metodo pulsar para continuar
    public static void pulsaParaContinuar() {
        System.out.println();
        System.out.print("Pulse para continuar...");
        S.nextLine();
    }

    //Mensaje que cierra un programa
    public static void mensajeCierraPrograma() {
        System.out.print("Saliendo");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 3; i++) {
            System.out.print(" .");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println();
    }

    //Metodo para agregar un tiempo de espera
    public static void tiempoEspera(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo para generar un número aleatorio de 6 dígitos
    public static int generaTokenRegistro() {
        Random random = new Random();
        int numAleatorio = 100000 + random.nextInt(900000);
        return numAleatorio;
    }
}
