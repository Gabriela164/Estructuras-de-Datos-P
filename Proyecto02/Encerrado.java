/**
 * @author Trujillo Beltrán Zianya Nenetzi 
 * @author Lopez Diego Gabriela
 *
 * @version 1
 */

import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

public class Encerrado {
    
    private Tablero tablero;
    private List<Jugador> jugadores = new List<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean repe, turno, ganador;
    private int opc, origen, destino;
    private String movimiento;
    
    //Colores de letra
    String red = "\u001B[31m";
    String yellow="\033[33m";
    String purple = "\u001B[35m";

    // Reset
    String reset="\u001B[0m";



    public Encerrado(List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.tablero = new Tablero();
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void jugar(int opc){
        if(opc == 1){
            jugarPVP();
        }else{
            jugarPVCPU();
        }
    }

    public void jugarPVCPU(){// JUGADOR VS CPU
        do {
            try {
                System.out.println("Indica quien será el primero en jugar");
                System.out.println("1. " + jugadores.get(0).getNombre());
                System.out.println("2. " + jugadores.get(1).getNombre());
                opc = scanner.nextInt();
                if(opc == 1 || opc == 2){
                    repe = false;
                }else{
                    System.out.println(red + "Ingresa solo 1 o 2" + reset);
                    repe = true;
                }
            }catch (Exception e){
                System.out.println(red + "Ingresa solamente numeros" + reset);
                repe = true;
                scanner.next();
            }
        }while(repe);
        asingarTurno(opc);
        do{
            try {
                System.out.println("Quieres acomodar de alguna forma las fichas?");
                System.out.println("1. Si");
                System.out.println("2. No");
                opc = scanner.nextInt();
                if(opc == 1 || opc == 2){
                    repe = false;
                }else{
                    System.out.println(red + "Ingresa solo 1 o 2" + reset);
                    repe = true;
                }
            }catch (Exception e){
                System.out.println(red + "Ingresa solamente numeros" + reset);
                repe = true;
                scanner.next();
            }
        }while(repe);
        acomodoFichas(opc);

        do{
            System.out.println(tablero);
            if(!turno){//Turno JUGADOR
                do {
                    try {
                        System.out.println("Turno de: " + jugadores.get(0).getNombre() + " Color: " + jugadores.get(0).getColor());
                        System.out.println("Ingrese casillaOrigen,casillaDestino. Ej 1,5");
                        movimiento = scanner.next();
                        String[] jugada = movimiento.split(",");
                        origen = Integer.parseInt(jugada[0]);
                        destino = Integer.parseInt(jugada[1]);
                        if((origen > 0 && origen < 6) && (destino > 0 && destino < 6) ){
                            if(tablero.movimientoLegal(jugadores.get(0),origen,destino) == 1){//Cuando es un movimiento legal
                                tablero.movimiento(jugadores.get(0),origen,destino);
                                repe = false;
                                if(!hayGanador()){
                                    ganador = false;
                                }else{
                                    ganador = true;
                                }
                            }else if(tablero.movimientoLegal(jugadores.get(0),origen,destino) == 0){//Cuando la casilla de destino está ocupada
                                System.out.println(red + "La casilla esta ocupada, intenta otra casilla" + reset);
                                System.out.println(tablero);
                                repe = true;
                            }else{//Cuando quiere mover la ficha del adversario
                                System.out.println(red + "Mueve unicamente las fichas de tu color" + reset);
                                System.out.println(tablero);
                                repe = true;
                            }
                        }else{
                            System.out.println(yellow + "Ingresa numeros de 1 a 5 como se te pide. Ej 1,5" + reset);
                            repe = true;
                        }
                    }catch(Exception e){
                        System.out.println(red + "Ingresa solamente numeros" + reset);
                        repe = true;
                    }
                }while(repe);
                turno = !turno;
            }else {//TURNO CPU
                do {
                    try {
                        System.out.println("Turno de: " + jugadores.get(1).getNombre() + " Color: " + jugadores.get(1).getColor());
                        System.out.println("Ingrese la opcion deseada");
                        System.out.println("1. Tiro al azar");
                        System.out.println("2. Tiro Minimax");
                        opc = scanner.nextInt();
                        if (opc == 1) { //Tiro al azar
                            tablero.movimientoAlAzar(jugadores.get(1));
                            repe = false;
                        } else if (opc == 2) {//Tiro Minimax
                            tablero.movimientoMiniMax(jugadores.get(1));
                            repe = false;
                        } else {
                            System.out.println(yellow + "Ingresa solo 1 o 2." + reset);
                            repe = true;
                        }
                    } catch (Exception e) {
                        System.out.println(red + "Ingresa solamente numeros" + reset);
                        repe = true;
                        scanner.next();
                    }
                } while (repe);

                if (!hayGanador()) {
                    ganador = false;
                } else {
                    ganador = true;
                }
                turno = !turno;
                System.out.println("Termino turno CPU");

            }
        }while (!ganador);
        System.out.println(tablero);
        if(turno){
            System.out.println(purple + "Ganador: " + jugadores.get(0).getNombre());
            System.out.println("Perdedor: " + jugadores.get(1).getNombre() + reset);
        }else{
            System.out.println(purple + "Ganador: " + jugadores.get(1).getNombre());
            System.out.println("Perdedor: " + jugadores.get(0).getNombre() + reset);
        }
    }

    public void jugarPVP(){//JUGADOR VS JUGADOR
        do {
            try {
                System.out.println("Indica quien será el primero en jugar");
                System.out.println("1. " + jugadores.get(0).getNombre());
                System.out.println("2. " + jugadores.get(1).getNombre());
                opc = scanner.nextInt();
                if(opc == 1 || opc == 2){
                    repe = false;
                }else{
                    System.out.println(red + "Ingresa solo 1 o 2" + reset);
                    repe = true;
                }
            }catch (Exception e){
                System.out.println(red + "Ingresa solamente numeros" + reset);
                repe = true;
                scanner.next();
            }
        }while(repe);
        asingarTurno(opc);
        do{
            try {
                System.out.println("Quieres acomodar de alguna forma las fichas?");
                System.out.println("1. Si");
                System.out.println("2. No");
                opc = scanner.nextInt();
                if(opc == 1 || opc == 2){
                    repe = false;
                }else{
                    System.out.println(red + "Ingresa solo 1 o 2" + reset);
                    repe = true;
                }
            }catch (Exception e){
                System.out.println(red + "Ingresa solamente numeros" + reset);
                repe = true;
                scanner.next();
            }
        }while(repe);
        acomodoFichas(opc);

        do{
            System.out.println(tablero);
            if(!turno){//Turno JUGADOR 1
                do {
                    try {
                        System.out.println("Turno de: " + jugadores.get(0).getNombre() + " Color: " + jugadores.get(0).getColor());
                        System.out.println("Ingrese casillaOrigen,casillaDestino. Ej 1,5");
                        movimiento = scanner.next();
                        String[] jugada = movimiento.split(",");
                        origen = Integer.parseInt(jugada[0]);
                        destino = Integer.parseInt(jugada[1]);
                        if((origen > 0 && origen < 6) && (destino > 0 && destino < 6) ){
                            if(tablero.movimientoLegal(jugadores.get(0),origen,destino) == 1){//Cuando es un movimiento legal
                                tablero.movimiento(jugadores.get(0),origen,destino);
                                repe = false;
                                if(!hayGanador()){
                                    ganador = false;
                                }else{
                                    ganador = true;
                                }
                            }else if(tablero.movimientoLegal(jugadores.get(0),origen,destino) == 0){//Cuando la casilla de destino está ocupada
                                System.out.println(red + "La casilla esta ocupada, intenta otra casilla" + reset);
                                System.out.println(tablero);
                                repe = true;
                            }else{//Cuando quiere mover la ficha del adversario
                                System.out.println(red + "Mueve unicamente las fichas de tu color" + reset);
                                System.out.println(tablero);
                                repe = true;
                            }
                        }else{
                            System.out.println(yellow + "Ingresa numeros de 1 a 5 como se te pide. Ej 1,5" + reset);
                            repe = true;
                        }
                    }catch(Exception e){
                        System.out.println(red + "Ingresa solamente numeros" + reset);
                        repe = true;
                    }
                }while(repe);
                turno = !turno;
            }else{//Turno JUGADOR 2
                do {
                    try {
                        System.out.println("Turno de: " + jugadores.get(1).getNombre() + " Color: " + jugadores.get(1).getColor());
                        System.out.println("Ingrese casillaOrigen,casillaDestino. Ej 1,5");
                        movimiento = scanner.next();
                        String[] jugada = movimiento.split(",");
                        origen = Integer.parseInt(jugada[0]);
                        destino = Integer.parseInt(jugada[1]);
                        if((origen > 0 && origen < 6) && (destino > 0 && destino < 6) ){
                            if(tablero.movimientoLegal(jugadores.get(1),origen,destino) == 1){//Cuando es un movimiento legal
                                tablero.movimiento(jugadores.get(1),origen,destino);
                                repe = false;
                                if(!hayGanador()){
                                    ganador = false;
                                }else{
                                    ganador = true;
                                }
                            }else if(tablero.movimientoLegal(jugadores.get(1),origen,destino) == 0){//Cuando la casilla de destino está ocupada
                                System.out.println(red + "La casilla esta ocupada, intenta otra casilla" + reset);
                                System.out.println(tablero);
                                repe = true;
                            }else{//Cuando quiere mover la ficha del adversario
                                System.out.println(red + "Mueve unicamente las fichas de tu color" + reset);
                                System.out.println(tablero);
                                repe = true;
                            }
                        }else{
                            System.out.println(yellow + "Ingresa numeros de 1 a 5 como se te pide. Ej 1,5" + reset);
                            repe = true;
                        }
                    }catch(Exception e){
                        System.out.println(red + "Ingresa solamente numeros" + reset);
                        repe = true;
                    }
                }while(repe);
                turno = !turno;
            }
        }while (!ganador);
        System.out.println(tablero);
        if(turno){
            System.out.println(purple + "Ganador: " + jugadores.get(0).getNombre());
            System.out.println("Perdedor: " + jugadores.get(1).getNombre() + reset);
        }else{
            System.out.println(purple + "Ganador: " + jugadores.get(1).getNombre());
            System.out.println("Perdedor: " + jugadores.get(0).getNombre() + reset);
        }
    }

    public boolean hayGanador(){
        if(turno){
            return !tablero.hayMovimientos(jugadores.get(0));
        }else{
            return !tablero.hayMovimientos(jugadores.get(1));
        }
    }

    public void acomodoFichas(int opc){
        boolean repetir;
        int aux;
        if(opc == 1){//Acomodo del usuario
            System.out.println(tablero);
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j < 3; j++) {
                    do {
                        try {
                            System.out.println("Ingresa el numero de casilla (1-4) donde quieras que esté la ficha " + j + " de " + jugadores.get(i).getNombre());
                            aux = scanner.nextInt();
                            if (aux > 0 && aux < 5) {
                                if (tablero.casillaOcupada(aux)) {
                                    System.out.println(yellow + "La casilla ya está ocupada, escoge otra" + reset);
                                    repetir = true;
                                } else {
                                    tablero.ponerFicha(aux, jugadores.get(i));
                                    if(j == 1){
                                        jugadores.get(i).setFicha1(aux);
                                    }else{
                                        jugadores.get(i).setFicha2(aux);
                                    }
                                    System.out.println(tablero);
                                    repetir = false;
                                }
                            } else {
                                System.out.println(red + "Ingresa un numero entre 1 y 4" + reset);
                                repetir = true;
                            }
                        } catch (Exception e) {
                            System.out.println(red + "Ingresa solamente numeros" + reset);
                            repetir = true;
                            scanner.next();
                        }
                    } while (repetir);
                }
            }
        }else{//Fichas Alternadas
            tablero.ponerFicha(1,jugadores.get(0));
            jugadores.get(0).setFicha1(1);
            tablero.ponerFicha(4,jugadores.get(0));
            jugadores.get(0).setFicha2(4);
            tablero.ponerFicha(2, jugadores.get(1));
            jugadores.get(1).setFicha1(2);
            tablero.ponerFicha(3, jugadores.get(1));
            jugadores.get(1).setFicha2(3);
        }
    }


    public void asingarTurno(int opc){
        if(opc == 1){
            turno = false;
        }else{
            turno = true;
        }

    }


}
