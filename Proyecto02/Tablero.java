/**
 * @author Trujillo Beltrán Zianya Nenetzi 
 * @author Lopez Diego Gabriela
 *
 * @version 1
 */


import java.util.Random;

public class Tablero {
    private String[][] tablero = new String[9][7];
    private String reset = "\u001B[0m";
    private String green = "\u001B[32m";
    private String blue = "\u001B[34m";
    private List<Integer> vecinos1 = new List<>();
    private List<Integer> vecinos2 = new List<>();
    private List<Integer> vecinos3 = new List<>();
    private List<Integer> vecinos4 = new List<>();
    private List<Integer> vecinos5 = new List<>();

    public Tablero(){
        vecinos1.add(0,2);
        vecinos1.add(1,3);
        vecinos1.add(2,5);

        vecinos2.add(0,1);
        vecinos2.add(1,4);
        vecinos2.add(2,5);

        vecinos3.add(0,1);
        vecinos3.add(1,5);

        vecinos4.add(0,2);
        vecinos4.add(1,5);

        vecinos5.add(0,1);
        vecinos5.add(1,2);
        vecinos5.add(2,3);
        vecinos5.add(3,4);

        for(int i = 1; i < 6; i++){ //Lineas horizontales
            tablero[1][i] = "-";
        }
        //Lineas verticales
        for(int i = 0; i < 7; i+=6){//Columnas
            for(int j = 2; j < 7; j++){//Filas
                tablero[j][i] = "|";
            }
        }
        for(int i = 2; i < 7; i++){//Diagonales
            tablero[i][i-1] = "\\";
        }
        for(int i = 2; i < 7; i++){//Diagonales invertidas
            int aux = ((i-2)*-1) + 5;
            tablero[i][aux] = "/";
        }

        //Rellenar espacion vacíos
        for(int i = 0; i < 9; i++ ){
            for(int j = 0; j < 7; j++){
                if(tablero[i][j] == null)
                    tablero[i][j] = " ";
            }
        }

        //Casillas
        tablero[1][0] = "_";
        tablero[1][6] = "_";
        tablero[4][3] = "_";
        tablero[7][0] = "_";
        tablero[7][6] = "_";

        //etiquetas
        tablero[0][0] = "1";
        tablero[0][6] = "2";
        tablero[8][0] = "3";
        tablero[8][6] = "4";
        tablero[3][3] = "5";


        //Rellenar espacion vacíos
        for(int i = 0; i < 9; i++ ){
            for(int j = 0; j < 7; j++){
                if(tablero[i][j] == null)
                    tablero[i][j] = " ";
            }
        }
    }

    public boolean casillaOcupada(int casilla){
        switch(casilla){
            case 1:
                if(tablero[1][0].equals("_"))
                    return false;
                else
                    return true;
            case 2:
                if(tablero[1][6].equals("_"))
                    return false;
                else
                    return true;
            case 3:
                if(tablero[7][0].equals("_"))
                    return false;
                else
                    return true;
            case 4:
                if(tablero[7][6].equals("_"))
                    return false;
                else
                    return true;
            case 5:
                if(tablero[4][3].equals("_"))
                    return false;
                else
                    return true;
        }
        return true;
    }

    public void ponerFicha(int opc, Jugador jugador){
        String color;

        if(jugador.getColor().equals("green")){
            color = green;
        }else{
            color = blue;
        }


        switch(opc){
            case 1:
                tablero[1][0] = color + "O" + reset;
                break;
            case 2:
                tablero[1][6] = color + "O" + reset;
                break;
            case 3:
                tablero[7][0] = color + "O" + reset;
                break;
            case 4:
                tablero[7][6] = color + "O" + reset;
                break;
        }
    }

    public void movimiento(Jugador jugador, int origen, int destino){
        String color;

        if(jugador.getColor().equals("green")){
            color = green;
        }else{
            color = blue;
        }

        switch(origen){
            case 1:
                tablero[1][0] = "_";
                break;
            case 2:
                tablero[1][6] = "_";
                break;
            case 3:
                tablero[7][0] = "_";
                break;
            case 4:
                tablero[7][6] = "_";
                break;
            case 5:
                tablero[4][3] = "_";
                break;
        }

        switch(destino){
            case 1:
                tablero[1][0] = color + "O" + reset;
                break;
            case 2:
                tablero[1][6] = color + "O" + reset;
                break;
            case 3:
                tablero[7][0] = color + "O" + reset;
                break;
            case 4:
                tablero[7][6] = color + "O" + reset;
                break;
            case 5:
                tablero[4][3] = color + "O" + reset;
                break;
        }
        actualizarMovimiento(jugador,origen,destino);
    }

    public void actualizarMovimiento(Jugador jugador, int origen, int destino){
        if(jugador.getFicha1() == origen){
            jugador.setFicha1(destino);
        }else{
            jugador.setFicha2(destino);
        }
    }

    public List<Integer> vecinos(int ficha){
        List<Integer> vecinosFicha = new List<>();
        switch(ficha){
            case 1:
                vecinosFicha = vecinos1;
                break;
            case 2:
                vecinosFicha = vecinos2;
                break;
            case 3:
                vecinosFicha = vecinos3;
                break;
            case 4:
                vecinosFicha = vecinos4;
                break;
            case 5:
                vecinosFicha = vecinos5;
                break;
        }
        return vecinosFicha;
    }

    public boolean comprobarMovimiento(Jugador jugador, int ficha){
        List<Integer> movimientos = vecinos(ficha);
        int size = movimientos.size();
        for(int i = 0; i < size; i++){
            if(!(movimientoLegal(jugador, ficha, movimientos.get(0)) == 1)){
                movimientos.remove(0);
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * Método que verifica si un movimiento es legal
     * @param jugador - jugador que hará el movimiento
     * @param origen - casilla de origen
     * @param destino - casilla de destino
     * @return -1 cuando se intenta mover una ficha que no es tuya, 0 cuando una casilla está ocupada, 1 cuando es movimiento legal.
     */
    public int movimientoLegal(Jugador jugador, int origen, int destino){
        actualizarVecinos();
        String color;
        if(jugador.getColor().equals("green")){
            color = green;
        }else{
            color = blue;
        }
        List<Integer> vecinos = new List<>();
        switch(origen){
            case 1:
                vecinos = vecinos1;
                if(!tablero[1][0].equals(color + "O" + reset))
                    return -1;
                break;
            case 2:
                vecinos = vecinos2;
                if(!tablero[1][6].equals(color + "O" + reset))
                    return -1;
                break;
            case 3:
                vecinos = vecinos3;
                if(!tablero[7][0].equals(color + "O" + reset))
                    return -1;
                break;
            case 4:
                vecinos = vecinos4;
                if(!tablero[7][6].equals(color + "O" + reset))
                    return -1;
                break;
            case 5:
                vecinos = vecinos5;
                if(!tablero[4][3].equals(color + "O" + reset))
                    return -1;
                break;
        }


        switch(destino){
            case 1:
                if(!tablero[1][0].equals("_") || !vecinos.contains(destino))
                    return 0;
                else
                    return 1;
            case 2:
                if(!tablero[1][6].equals("_") || !vecinos.contains(destino)) {
                    return 0;
                }else
                    return 1;
            case 3:
                if(!tablero[7][0].equals("_") || !vecinos.contains(destino))
                    return 0;
                else
                    return 1;
            case 4:
                if(!tablero[7][6].equals("_") || !vecinos.contains(destino))
                    return 0;
                else
                    return 1;
            case 5:
                if(!tablero[4][3].equals("_") || !vecinos.contains(destino))
                    return 0;
                else
                    return 1;
        }
        return 1;
    }

    public boolean hayMovimientos(Jugador jugador){
        if(comprobarMovimiento(jugador, jugador.getFicha1())){
            return true;
        }else if(comprobarMovimiento(jugador, jugador.getFicha2())){
            return true;
        }else{
            return false;
        }
    }

    public void movimientoAlAzar(Jugador jugador){
        Random random = new Random();
        int rand = random.nextInt(2);
        int ficha;
        if(rand == 0){
            ficha = jugador.getFicha1();
        }else{
            ficha = jugador.getFicha2();
        }
        hayMovimientos(jugador,ficha);

    }

    public void hayMovimientos(Jugador jugador, int ficha){
        List<Integer> vecinosFicha = vecinos(ficha);
        List<Integer> movimientos = opciones(vecinosFicha);
        Random random = new Random();
        int rand = random.nextInt(vecinosFicha.size());
        boolean repe = false;

        do{
            int destino = movimientos.get(0);
            if(movimientoLegal(jugador,ficha,vecinosFicha.get(destino)) == 1){
                movimiento(jugador,ficha,vecinosFicha.get(destino));
                repe = false;
                return;
            }else{
                movimientos.remove(0);
                repe = true;
            }
        }while(repe);

        //Cambiamos a la otra ficha si es que la primera ficha escogida no tiene movimientos disponibles

        if(jugador.getFicha1() == ficha){//Cambiamos a ficha 2
            ficha = jugador.getFicha2();
        }else{//Cambiamos a ficha 1
            ficha = jugador.getFicha1();
        }

        vecinosFicha= vecinos(ficha);
        movimientos = opciones(vecinosFicha);
        rand = random.nextInt(vecinosFicha.size());
        repe = false;

        do{
            int destino = movimientos.get(0);
            if(movimientoLegal(jugador,ficha,vecinosFicha.get(destino)) == 1){
                movimiento(jugador,ficha,vecinosFicha.get(destino));
                repe = false;
                return;
            }else{
                movimientos.remove(0);
                repe = true;
            }
        }while(repe);


    }

    public List<Integer> opciones(List<Integer> vecinos){
        List<Integer> aux = new List<>();
        Random random = new Random();
        int rand = random.nextInt(4);
        for(int i = 0; i < vecinos.size(); i++){
            while(aux.contains(rand)){
               rand = random.nextInt(vecinos.size());
            }
            aux.add(0,rand);
        }
        return aux;
    }

    public void movimientoMiniMax(Jugador jugador){
        System.out.println("La implementacion del minimax queda al usuario porque esta muy dificil :c");
    }

    public void actualizarVecinos(){
        vecinos1.clear();
        vecinos2.clear();
        vecinos3.clear();
        vecinos4.clear();
        vecinos5.clear();

        vecinos1.add(0,2);
        vecinos1.add(1,3);
        vecinos1.add(2,5);

        vecinos2.add(0,1);
        vecinos2.add(1,4);
        vecinos2.add(2,5);

        vecinos3.add(0,1);
        vecinos3.add(1,5);

        vecinos4.add(0,2);
        vecinos4.add(1,5);

        vecinos5.add(0,1);
        vecinos5.add(1,2);
        vecinos5.add(2,3);
        vecinos5.add(3,4);
    }

    public int movimientoDisponible(Jugador jugador,int ficha){
        actualizarVecinos();
        List<Integer> vecinosFicha = vecinos(ficha);
        for (int i = 0; i < vecinosFicha.size(); i++) {
            if(movimientoLegal(jugador, ficha, vecinosFicha.get(0)) == 1){
                return vecinosFicha.get(0);
            }else{
                vecinosFicha.remove(0);
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        String tab = "\t\t";
        for(int i = 0; i < 9; i++ ){
            for(int j = 0; j < 7; j++)
                tab = tab + tablero[i][j] + " ";
            tab = tab + "\n\t\t";
        }
        return tab;
    }
}
