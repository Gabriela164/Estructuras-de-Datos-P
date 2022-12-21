package Clases;

import java.util.Scanner;
import java.util.Comparator;

import Clases.ArbolBinario;
import Clases.ArbolBinarioBusqueda;
import Clases.ArbolBinarioCompleto;

import java.util.Collections;
import java.util.Iterator;
public class Practica3 {
    

    /**
     * Metodo que encuentra la pareja de numeros en una lista de enteros
     *  tal que la suma de los dos sea la mas cercana a un numero N 
     * @param lista lista de numeros
     * @param N numero dado
     */
    public static void sumaCercana(Lista<Integer> lista, int N){
        Iterator<Integer> iter = lista.iterator();
        

      
    }

    /**
     * Metodo donde obtenemos cada permutacion de una cadena 
     */
    public static void permutacionesCadena(String cadena){
        
        if(cadena == null || cadena.length() == 0){
           return;
        }
        permutaciones(cadena.toCharArray(), 0);
    }

    /**
     * Metod que genera de forma recursiva todas las permutaciones de una cadena
     */
    public static void permutaciones(char[] letras, int indiceActual){
        if (indiceActual == letras.length-1)
            System.out.println(String.valueOf(letras));
        for(int i = indiceActual; i < letras.length; i++){
            swap(letras,indiceActual,i);
            permutaciones(letras,indiceActual+1);
            swap(letras,indiceActual,i);
        } 
    }

    /**
     * Metodo que intercambia dos elementos de un arreglo
     */
    public static void swap(char[] letras, int i, int j){
        char temp = letras[i];
        letras[i] = letras[j];
        letras[j] = temp;
    }

    public static void primosQueSuman(int S, int P, int N){

    }

    /**
     * Metodo que resuelve para N reinas la posicion de las reinas en un
     * tablero de N * N
     * @param N numero de reinas 
     */
    public static void N_Reinas(int N){
    //Creamos nuestro tablero N * N e inicializamos cada una de sus casillas en 0
    // 0 significa que no hay ninguna reina en esa casilla
    // 1 significa que hay una reina en esa casilla
     Integer tablero[][]=new Integer [N][N];
     for(int i=0;i<N;i++)
         for(int j=0;j<N;j++)
             tablero[i][j] = 0;
     if(resolverReinas(tablero,0,N)) {
        tableroResuelto(tablero, N);
     } else{ System.out.println("No hay solucion para "+ String.valueOf(N) + " reinas");
      }
    }

    /**
     * Metodo que resuelve el problema de las N reinas
     * @param tablero tablero donde se almacenan las reinas
     * @param columna columna en la que se va a colocar la reina
     * @param N numero de reinas
     */
    public static boolean resolverReinas(Integer tablero[][], int columna, int n){
        if(columna>=n) return true;
        for(int i=0;i<n;i++){
            if(esValido(tablero,i,columna,n)){
                tablero[i][columna]= 1;
                if(resolverReinas(tablero, columna + 1,n)) 
                return true;
                tablero[i][columna]= 0;
            }
        }
        return false;
    }

    /**
     * Metodo que verifica si una reina se puede colocar en una casilla
     * @param tablero tablero donde se almacenan las reinas
     * @param fila fila en la que se va a colocar la reina
     * @param columna columna en la que se va a colocar la reina
     * @param N numero de reinas
     */
    public static boolean esValido(Integer tablero[][], int fila, int columna, int n){
        int i,j;
        for(i=0;i<columna;i++){
            if(tablero[fila][i]==1) 
            return false;
        }
        for(i=fila,j=columna; i>=0 && j>=0;i--,j--){
            if(tablero[i][j]==1)
             return false;
        }
        for(i=fila,j=columna; i<n && j>=0;i++,j--){
            if(tablero[i][j]==1) 
            return false;
        }
        return true;
    }

    //Metodo que imprime el tablero resulto para N reinas 
    public static void tableroResuelto(Integer tablero[][], int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                System.out.print(" "+tablero[i][j]+" ");
            System.out.println();
        }
    }
  

    public static void main(String[] args) {
        //Puedes hacer tus pruebas aqui
        

      String prueba ="ABC";
     // permutacionesCadena(prueba); 
      // N_Reinas(8);

      System.out.println("CONSTRUYENDO EL ARBOL DESDE UNA LISTA DADA");
      Lista<Integer> nuevo = new Lista<Integer>();
        nuevo.add(1);
        nuevo.add(2);
        nuevo.add(3);
        nuevo.add(4);
        nuevo.add(5);
       
      ArbolBinarioBusqueda BST = new ArbolBinarioBusqueda(nuevo);
        //BST.add(56);
        //BST.add(6);
        //BST.add(1);
        //BST.add(0);
        //BST.add(90);
       BST.printPreOrder();
       BST.printInOrder();
      
       




    }


}
