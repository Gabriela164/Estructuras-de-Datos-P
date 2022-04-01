package edd.src.Automata;

import java.util.Random;
import edd.src.Estructuras.*;

public class MondrianG extends AC {

    int[][] Maux2 = new int[Imagen.numCells][Imagen.numCells];
    int[][] MauxCopia = new int[Imagen.numCells][Imagen.numCells];
    int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];

    /*
     * Metodo que pinta una matriz inicial de Blanco y le da valores aleatorios a las
     * casillas.
     *
     */
    @Override
    public int[][] getAutomata() {
        int aux1; 
        //Inicializo dos matrices en blanco
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = 2;
                MauxCopia[i][j] = 2;
            }
        }
        // Modifico cada valor de la matriz Maux de forma aleatoria. Para empezar con un estado random de colores
        for (int i = 0; i < Maux2.length; i++) {
            for (int j = 0; j < Maux2.length; j++) {

                aux1 = (int)(Math.random() * 30); // Random del 0 al 14*
		
		if ( aux1%3 == 0 ) {		    
		    Maux2[i][j] = 0; // amarillo
		}else if ( aux1%4 == 0 ) {
		    Maux2[i][j] = 1; // Azul
		}else if ( aux1%5 == 0 ) {
		    Maux2[i][j] = 2; // blanco
		}else if ( aux1%2 == 0 ) {
		    Maux2[i][j] = 3; //negro
		}else {
		    Maux2[i][j] = 4; //rojo
		}
            }
        }
        return Maux2;
    }

    /*
     * Metodo para evolucionar el automata.
     *
     */
    @Override
    public void evoluciona() {

        // Se crea una matriz copia para reemplazar los Valores.
        int[][] CopiaM = new int[Imagen.numCells][Imagen.numCells];
        
        // Aqui empieza una evolucion.

        //Creamos una pila y una cola para que te diviertas joven Artista. 
        Pila<Integer> pila1 = new Pila<Integer>();
        Pila<Integer> pila2 = new Pila<Integer>();

        int negro; 
        int azul;
        int amarillo;
        int rojo;
        int blanco;

        //La matriz Maux2 Contiene el estado actual de la matriz.
        //La matriz CopiaM es una matriz copia de Maux2 donde debes poner los nuevos valores

	//For que escanea toda la matriz.
	for (int i=0;i<Maux2.length;i++) { 
	    for (int j=0;j<Maux2.length;j++) {	
                negro = 0;
                azul = 0;
                amarillo =0;
                rojo = 0;
                blanco = 0;
		for (int k=i-1;k<=i+1;k++) {
		    for (int l=j-1;l<=j+1;l++) {
			//Analisis de casillas vecindad.
			if (k>=0&&l>=0&&k<Maux2.length&&l<Maux2.length&&(k!=i|| l!=j)) {
			    if ( Maux2[k][l] == 1) { 
                                pila1.push(1); 
                                azul++; 
                            } else if (Maux2[k][l] == 3) {
                                pila1.push(3); 
                                negro++; 
                            } else if (Maux2[k][l] == 0){
                                pila1.push(0); 
                                amarillo++;
                            } else if (Maux2[k][l] == 4){
                                pila1.push(4); 
                                rojo++;
                            } else {
                                pila1.push(2); 
                                blanco++;
                            }
			       }
		    }
		}


                //Reglas del juego 
		     if(  pila1.pop() == 2 ){   //cuando el primer elemento eliminado de pila es blanco
                    if(amarillo ==2 || azul == 3){
                        CopiaM[i][j] = 3;
                    } else { CopiaM[i][j] = 1;}

                } else if ( pila1.pop() == 1){      //cuando el primer elemento eliminado de pila es azul
                    if(amarillo ==2 || azul == 3){
                        CopiaM[i][j] = 1;
                    } else { CopiaM[i][j] = 3;}
                    
                } else if ( pila1.pop() == 3){     //cuando el primer elemento eliminado de pila es negro
                    if(amarillo ==2 || azul == 3){
                        CopiaM[i][j] = 3;
                    } else { CopiaM[i][j] = 4;}
              
                } else if ( pila1.pop() == 0){    //cuando el primer elemento eliminado de pila es amarillo
                    if(amarillo ==2 || azul == 3){
                        CopiaM[i][j] = 0;
                    } else { CopiaM[i][j] = 4;}
              
                } else {                          //cuando el primer elemento eliminado de pila es rojo 
                    if(amarillo ==2 || azul == 3){
                        CopiaM[i][j] = 4;
                    } else { CopiaM[i][j] = 2;}
                    
                }
                				


	    } //segundo for 
	} //primer for 



	//No modificamos nada 
	// Fors que arreglan la matriz a regresar en la copia.
        for (int i = 0; i < Maux2.length; i++) { 
            for (int j = 0; j < Maux2.length; j++) {
                Maux2[i][j] = CopiaM[i][j];
            }
        }
    }

    
    /*Metodo que regresa la evolucion de la matriz
     *
     */
    public int[][] getAutomata2() {
        return Maux2;
    }

}
