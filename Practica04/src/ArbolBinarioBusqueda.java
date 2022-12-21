
package Clases;
import java.util.NoSuchElementException;
import java.util.Comparator;
import java.util.*;
import Clases.ArbolBinario;
import Clases.Collection;
import Clases.VerticeArbolBinario;
import java.util.Iterator;

public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T>{
    
    //Clase privada para iteradores de arboles binarios de BUSQUEDA
    private class Iterador implements Iterator<T> {
        //Pila para recorrer los vertices en BST 
        Pila<Vertice> pila = new Pila<Vertice>();

        public Iterador(){
            Vertice p = raiz;
            while(p != null){
                pila.push(p);
                p = p.izquierdo;
            }    
        }
        /*Nos dice si hay un elemento siguiente */
        public boolean hasNext(){
            return !pila.isEmpty();
        }

        @Override public T next(){
            if(pila.isEmpty()){
                throw new NoSuchElementException();
            }
            Vertice v = pila.pop();
            if(v.derecho != null){
                Vertice u = v.derecho;
                while(u!=null){
                    pila.push(u);
                    u = u.izquierdo;
                }
            }
            return v.elemento;
        }  
    }

    /**
     * Contructor sin parametros  
     * */    
    public ArbolBinarioBusqueda() {
        
    }

    /**
     * Construye un arbol binario de busqueda a partir de una lista 
     * @param listaRecibida lista de elementos a insertar en el arbol
     *
     * */
    public ArbolBinarioBusqueda(Lista<T> listaRecibida) {
         if (listaRecibida == null) {
            throw new IllegalArgumentException("La lista recibida es nula");
        }
        if(isSorted(listaRecibida) == true){ //Si la lista esta ordenada
            builSorted(listaRecibida);
            }else if (isSorted(listaRecibida) == false){ //Si la lista no esta ordenada
            buildUnSorted(listaRecibida);
            }
    }

    /**
     * Metodo que dada una lista contruye un arbol desde una lista ordenada
     * @param lista lista ordenada 
     */
    public void builSorted (Lista<T> listaOrdenada){
              swapLista(listaOrdenada);  //Se invoca el metodo swapLista para intercambiar elementos de la lista
              Iterator<T> it = listaOrdenada.iterator();
              //Agregamos los elementos de la lista al arbol 
              while(it.hasNext()){
                add(it.next());
              }
     }

       /**
       *  Metodo que dada una lista NO ordenada contruye un BST 
       * @param listaNoOrdenada lista sin ordenar 
       */
        public void buildUnSorted(Lista<T> listaNoOrdenada){
        swapLista(listaNoOrdenada); //intercambiamos 2 elementos de la lista 
        Iterator<T> it = listaNoOrdenada.iterator();
        //OrdenamosLalista 
        listaNoOrdenada.mergeSort(new Comparator<T>() {
            @Override public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
        });
            //Agregamos los elementos de la lista al arbol 
            while (it.hasNext()) {
            add(it.next());
            }
        }


    /**
     * Funcion que nos dice si una lista esta ordenada 
     * <true> si lista esta ordenada
     * <false> si lista no esta ordenada
      */
     public boolean isSorted(Lista<T> lista) {
        Iterator<T> iter = lista.iterator();
        T elementoAnterior = iter.next();
        T elementoActual = iter.next();
        while (iter.hasNext()) {
            elementoActual = iter.next();
            if (elementoAnterior.compareTo(elementoActual) > 0) {
                return false;
            }
            elementoAnterior = elementoActual;
        }
        return true;
    }
     
    /** 
     * Metodo que agrega un vertice con un elemento al arbol binario de busqueda
     * agregamos a la izq el elemento menor y a la derecha el elemento mayor
     */
     @Override
    public void add(T elemento){    
        //Si el arbol ya contiene ese elemento no lo agregamos para evitar repetidos 
        if(contains(elemento) == true){
            return;
        //Si el elemento no esta en el arbol lo agregamos     
        }else if (contains(elemento) == false){
            Vertice a = nuevoVertice(elemento);
            elementos++;
            if (isEmpty()) {
                raiz = a;
            }else{
                Vertice aux = raiz;
                while(aux != null){
                    if(a.elemento.compareTo(aux.elemento) < 0){
                        if(aux.izquierdo == null){
                            aux.izquierdo = a;
                            break;
                        }else{
                            aux = aux.izquierdo;
                        }
                    }else{
                        if(aux.derecho == null){
                            aux.derecho = a;
                            break;
                        }else{
                            aux = aux.derecho;
                        }
                    }
                }
            }  
        }
    }
   

    /**
     * Metodo que contruye el BST balanceado a partir de un arbol binario de
     */

     public void convertBST(ArbolBinario arbolBinario){
         //Creamos una lista donde almacenaremos los elementos del arbol binario 
         Lista<T> listaArbolBinario = new Lista<T>();
        if(arbolBinario.isEmpty()){
            throw new IllegalArgumentException();
        }
        else{
            //Agregamos los elementos del arbol binario a una lista
            Iterator<T> it = arbolBinario.iterator();
            while(it.hasNext()){
                listaArbolBinario.add(it.next());
            }
        }
        buildUnSorted(listaArbolBinario);
    }
     
     /**
      * Metodo que nos dice la existencia de un elemento en el arbol binario de busqueda
      * @return true si el elemento esta en el arbol, false en caso contrario
      */
      public boolean contains(T elemento){
            return busca(elemento) != null;
      }

      /**
         * Metodo que elimina un elemento en nuestro arbol binario de busqueda
         * @param elemento
         */
        public void delete(T elemento){
            if(!contains(elemento)){
                throw new NoSuchElementException();
            }else{
                raiz = delete_aux(raiz, elemento);
                elementos--;
            }
        }

      //Metodo auxiliar para delete 
       public Vertice delete_aux(Vertice raiz, T elemento){
            if(raiz == null){
                return null;
            }else{
                if(elemento.compareTo(raiz.elemento) < 0){
                    raiz.izquierdo = delete_aux(raiz.izquierdo, elemento);
                }else{
                    if(elemento.compareTo(raiz.elemento) > 0){
                        raiz.derecho = delete_aux(raiz.derecho, elemento);
                    }else{
                        if(raiz.izquierdo == null){
                            return raiz.derecho;
                        }else{
                            if(raiz.derecho == null){
                                return raiz.izquierdo;
                            }else{
                                Vertice aux = raiz.derecho;
                                while(aux.izquierdo != null){
                                    aux = aux.izquierdo;
                                }
                                raiz.elemento = aux.elemento;
                                raiz.derecho = delete_aux(raiz.derecho, aux.elemento);
                            }
                        }
                    }
                }
            }
            return raiz;
        }


        /**
         * Metodo que imprime el arbol en preorden
         */
         public void printPreOrder(){
            System.out.println("Recorremos el arbol en Pre-orden");
            preOrder_aux(raiz);
            System.out.println("\n");
         }

         public void preOrder_aux(Vertice raiz){
            if(raiz != null){
                System.out.print(raiz.elemento + " ");
                preOrder_aux(raiz.izquierdo);
                preOrder_aux(raiz.derecho);
            }
         }

       /**  
        * Metodo que imprime el arbol con in-orden DFS
        */
         public void printInOrder(){
            System.out.println("Recorremos el arbol en in-orden");
           printInOrder_aux(raiz);
           System.out.println("\n");
         }
         public void printInOrder_aux(Vertice raiz){
            if(raiz != null){
                printInOrder_aux(raiz.izquierdo);
                System.out.print(raiz.elemento + " ");
                printInOrder_aux(raiz.derecho);
            }
        }             
              

       /**
        * Metodo que intercambia de posicion 2 elementos de la lista ordenada para poder 
        * obtener un arbol binario "balanceado"
        * @return vertice 
        */
        public Lista<T> swapLista(Lista<T> lista){

            ArrayList<T> listaAux = new ArrayList<T>(); //Creamos un arreglo
            Iterator<T> it = lista.iterator();
            while(it.hasNext()){ //Iteramos la lista
                listaAux.add(it.next()); //Y la agregamos a un arreglo
            }
            //Intercambiamos de posicion el primer elemento con el penultimo elemento del arreglo
            Collections.swap(listaAux, 0, lista.size()-2); //Esto porque la lista siempre esta ordenada 
            lista.empty(); //Vaciamos la lista

            //Pasamos los elementos del arreglo a la lista
            for(int i = 0; i < listaAux.size(); i++){
                lista.add(listaAux.get(i));
            }
            return lista;
        }
 
    /**
     * Regresa la altura del arbol binario de busqueda
    

     public int altura(){
        return (int) Math.floor(Math.log(elementos) / Math.log(2));
    }
     */

    /**
     * Regresa un iterador para recorrer el arbol binario de busqueda
     * El arbol se itera en orden BST?
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }


}

