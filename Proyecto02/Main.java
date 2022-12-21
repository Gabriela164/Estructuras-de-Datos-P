/**
 * Clase principal
 *
 * @author Trujillo Beltrán Zianya Nenetzi 
 * @author Lopez Diego Gabriela
 *
 * @version 1
 */

import javax.swing.*;
import java.util.Scanner;


public class Main{
	public static void main(String []args) {
        String amarillo = "\033[33m";
        String rojo = "\u001B[31m";
        String reinicio = "\u001B[0m";
        Jugador jug1, jug2;
        List<Jugador> jugadores = new List<>();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean bol = true; //repe
        String nombre;

        System.out.println("\n******** Juego Encerrado ********\n");
        scanner.next();

        do {
            try {
                System.out.println("*** MENU ***");
                System.out.println("1. JUGADOR VS JUGADOR");
                System.out.println("2. JUGADOR VS CPU");
                System.out.println("Ingresa el numero de la opción deseada");
                opcion = scanner.nextInt();
                if (opcion == 1 || opcion == 2) {
                    bol = false;
                } else {
                    System.out.println(rojo + "Ingresa solamente 1 o 2" + reinicio);
                    bol = true;
                }
            } catch (Exception e) {
                System.out.println(rojo + "Debes ingresar un numero" + reinicio);
                bol = true;
                scanner.next();
            }
        } while (bol);

        if (opcion == 1) {
            System.out.println("Nombre del jugador 1: ");
            nombre = scanner.next().trim();
            jugadores.add(0, new Jugador(nombre, "verde"));
            System.out.println("Nombre del jugador 2: ");
            nombre = scanner.next().trim();
            jugadores.add(1, new Jugador(nombre, "azul"));
        } else {
            System.out.println("Ingresa tu nombre: ");
            nombre = scanner.next().trim();
            jugadores.add(0, new Jugador(nombre, "verde"));
            jugadores.add(1, new Jugador("CPU", "azul"));
        }
        Encerrado encerrado = new Encerrado(jugadores);
        encerrado.jugar(opcion);
	}
}
