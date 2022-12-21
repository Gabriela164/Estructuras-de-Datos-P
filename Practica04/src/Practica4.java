package Clases;

import java.util.Scanner;
import java.util.Comparator;

import Clases.ArbolBinario;
import Clases.ArbolBinarioBusqueda;
import Clases.ArbolBinarioCompleto;
import java.util.Collections;
import java.util.Iterator;

public class Practica4 {
    
    public static void main(String[] args) {             
	
        //prueba 
        Lista <Integer> prueba = new Lista<Integer>();
        prueba.add(5);
        prueba.add(7);
        prueba.add(8);
        prueba.add(1);
        prueba.add(10);
        prueba.add(15);
        prueba.add(0);
        System.out.println("Dada la lista: " + prueba.toString() + " contruyamos nuestro arbol AVL");

        ArbolAVL <Integer> arbol = new ArbolAVL<Integer>();
        arbol.add(5);
        arbol.add(7);
        arbol.add(8);
        arbol.add(1);
        arbol.add(10);
        arbol.add(15);
        arbol.add(0);
        arbol.printPreOrder();
        arbol.printInOrder();
        System.out.println("Eliminando el elemento 8 tenemos que");
        arbol.elimina(8);
        arbol.printPreOrder();
        arbol.printInOrder();
	
        System.out.println("Eliminando el elemento 1 tenemos que");
        arbol.elimina(1);
        arbol.printPreOrder();
        arbol.printInOrder();
    }
}
