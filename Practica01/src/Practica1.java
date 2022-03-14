package Clases;

import java.util.Iterator;

/*
* @alumna: Lopez Diego Gabriela 
*
*/

public class Practica1 {

     /*
     * Metodo que agrega un elemento <Int> de forma ordenada en nuestra lista
     * 
     * @param lista
     * 
     * @param nuevo el elemento a agregar
     * 
     * @return lista ordenada con el elemento a agregar
     */
    public static Lista<Integer> AgregaOrdenado(Lista<Integer> lista, int nuevo) {

        IteradorLista<Integer> iter = lista.iteradorLista();       
        
       /*  while(lista.hasNext()){
           uno = iter.next();
          
              ////no se pudo :( 
            
         
         } */
       
        return null;
    }

    /*
     * Metodo que une dos listas<Int>
     * 
     * @param lista1, lista 2 listas a unir
     * Este metodo cumple con ser t(n)=(n*m)  y empleamos O(1)
     * ya que utilizamos dos while con iterador para cada una de las listas, 
     * condiciones y asignaciones
     * para mejorar el tiempo de ejecución de Union, podemos crear un metodo en la 
     * clase lista que elimine los elementos duplicados y solo poder llamar un metodo que 
     * realice la operacion
     */
     
    public static void Union(Lista<Integer> lista1, Lista<Integer> lista2) {
       
        IteradorLista<Integer> iter1 = lista1.iteradorLista();
         IteradorLista<Integer> iter2 = lista2.iteradorLista();

         Lista<Integer> nueva = new Lista<Integer>();

         Integer uno = null;
         Integer dos = null;

       
          if (lista1 == null && lista2 == null) {
          throw new IllegalArgumentException("Ingrese dos listas con elementos");
          }
          
          while (iter1.hasNext() && iter2.hasNext()) {
          
          if (uno == null) {
          uno = iter1.next();
          }
          if (dos == null) {
          dos = iter2.next();
          }
          if (uno < dos) {
          nueva.add(uno);
          } else if (dos < uno) {
          nueva.add(dos);
          dos = null;
          } else {
          nueva.add(uno);
          dos = null;
          uno = null;
         }
          if (uno != null) {
          nueva.add(uno);
          }
          if (dos != null) {
          nueva.add(dos);
          }
          
          while (iter1.hasNext()) {
          nueva.add(iter1.next());
          }
          
          while (iter2.hasNext()) {
          nueva.add(iter2.next());
          }
        }    
    }

    /*
     * Metodo que intersecta los elementos de dos listas<Int> sin duplicados
     * 
     * @param lista1, lista 2 listas a intersectar
     * para este metodo tenemos que T(n)=n*m y O (m)
     * n*m ya que recorre toda la lista1 y verifica si contienen un mismo elemento con lista2 
     * Es complejidad constante ya que trabajamos con un bucle whie,
     * asignaciones y un iterador. 
     * Como T(n) es constante, el metodo implementado es uno de los mejores :D jsjs 
     *
     */ 
    public static void Interseccion(Lista<Integer> lista1, Lista<Integer> lista2) {

      Lista<Integer> nueva = new Lista<Integer>();
      nueva = lista1.clone();
      lista1.empty();
      IteradorLista <Integer> iter1 = nueva.iteradorLista();
      Integer uno;
      
      while(iter1.hasNext()){
           uno = iter1.next();
           if(lista2.contains(uno)){
             lista1.add(uno);       
           }
        }
      }

    public static void main(String[] args) {
    
    

        Lista<Integer> primera = new Lista<Integer>();
        Lista<Integer> segunda = new Lista<Integer>();
        Lista<Integer> tercera = new Lista<Integer>();

        // Tests toString
        for (int i = 0; i <= 5; i++) {
            primera.add(i);
        }

        String test = "0 -> 1 -> 2 -> 3 -> 4 -> 5";
        if (!primera.toString().equals(test)) {
            System.out.println("1 El toString no funciona!");
        }
        primera = new Lista<Integer>();
        if (!primera.toString().equals("")) {
            System.out.println("2 El toString no funciona!");
        }

        // Tests Reverse
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();

        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.agregaInicio(i);
        }

        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El reverse no funciona!");
        }
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        primera.reverse();
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("2 El reverse no funciona!");
        }

        // Tests Append
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
            segunda.add(i);
        }
        for (int i = 0; i <= 10; i++) {
            segunda.add(i);
        }
        primera.append(primera.clone());

        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El Append no funciona!");
        }

        // Tests IndexOf
        if (primera.indexOf(0) != 0) {
            System.out.println("1 El IndexOf no funciona!");
        }
        if (primera.indexOf(1) != 1) {
            System.out.println("2 El IndexOf no funciona!");
        }
        if (primera.indexOf(10) != 10) {
            System.out.println("3 El IndexOf no funciona!");
        }

        // Tests Insert
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);

        }
        for (int i = 0; i <= 4; i++) {
            segunda.add(i);

        }
        segunda.add(6);
        for (int i = 5; i <= 10; i++) {
            segunda.add(i);

        }

        primera.insert(5, 6);
        if (!primera.toString().equals(segunda.toString())) {
            System.out.println("1 El insert no funciona!");
        }

        // Tests Mezcla Alternada
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) {
                primera.add(i);
            }
        }
        primera.add(11);
        for (int i = 0; i <= 10; i++) {
            if (i % 2 != 0) {
                segunda.add(i);
            }

        }
        for (int i = 0; i <= 11; i++) {

            tercera.add(i);

        }

        primera.mezclaAlternada(segunda);
        if (!primera.toString().equals(tercera.toString())) {
            System.out.println("1 la mezclaAlternada no funciona!");
        }

        // Tests Agrega Ordenado
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        for (int i = 0; i <= 10; i++) {
            primera.add(i);
        }
        for (int i = 0; i <= 9; i++) {
            segunda.add(i);
        }
        segunda.add(9);
        segunda.add(10);

        tercera = AgregaOrdenado(primera,9);
        /*
         * if (!tercera.toString().equals(segunda.toString())) {
         * System.out.println("1 el agregaOrdenado no funciona!");
         * }
         */

        // Tests Union
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Union(primera, segunda);

        if (!(primera.contains(1) && primera.contains(2) && primera.contains(3) && primera.size() == 3)) {
            System.out.println("1 La union funciona!");
        }

        // Tests interseccion
        primera = new Lista<Integer>();
        segunda = new Lista<Integer>();
        tercera = new Lista<Integer>();
        primera.add(1);
        primera.add(2);
        primera.add(3);
        segunda.add(2);
        Interseccion(primera, segunda);

        if (!(primera.contains(2) && primera.size() == 1)) {
            System.out.println("1 La intersección no funciona!");
        }

    }

}
