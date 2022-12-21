package Clases;

/**
 * Clase abstracta PushPop<T>
 * @author Grupo EDD, 7038
 * @author López Diego Gabriela
 * @author Trujillo Beltrán Zianya Nenetzi
 * version 1
 */

import java.util.NoSuchElementException;

public abstract class PushPop<T> {
    // Clase Nodo
    protected class Nodo {
	/** El elemento del nodo. */
        public T elemento;
	/** El siguiente nodo. */
        public Nodo siguiente;

	/**
         * Construye un nodo con un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }//termina clase Nodo

    /** La cabeza de la estructura. */
    protected Nodo cabeza;
    /** El rabo de la estructura. */
    protected Nodo ultimo;
    /** La longitud de la estructura. */
    protected int longi;

    /**
     * Agrega un elemento al extremo de la estructura.
     * @param elemento el elemento a agregar.
     */
    public abstract void push(T elemento);

    /**
     * Elimina del inicio un elemento de nuestra Lista
     *
     * @return T valor-elemento eliminado
     */ 
    public T pop(){
        if (longi == 0 || cabeza == null) {
            throw new NoSuchElementException("");
        }
        if (longi == 1) {
            T valor = cabeza.elemento;
            cabeza = ultimo = null;
            longi = 0;
            return valor;
        }
        T valor = cabeza.elemento;
        cabeza = cabeza.siguiente;
        longi --;
        return valor;
    }
    
    /**
     * Devuelve el valor del primer nodo
     *
     * @param T elemento-elemento del 1er nodo
     */
    public T peek(){
        if (isEmpty()) {
            throw new NoSuchElementException("");
        }
        return cabeza.elemento;
    } 

    /**
     * Regresa el número de elementos en la estructura.
     * 
     * @return el número de elementos en la estructura.
     */
    public int size() {
        return longi;
    }

    /**
     * Vacía la estructura.
     * 
     */
    public void empty() {
        cabeza = ultimo = null;
        longi = 0;
    }

    /**
     * Nos dice si la estructura es vacía.
     * 
     * @return <code>true</code> si la estructura es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty() {
        return longi == 0 || (cabeza == null && ultimo == null);
    }
    
    /**
     * Compara la estructura con un objeto.
     * @param o el objeto con el que queremos comparar la estructura.
     * @return <code>true</code> si el objeto recibido es una instancia de la
     *         misma clase que la estructura, y sus elementos son iguales en el
     *         mismo orden; <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            System.out.println("Ejemplares distintos");
            return false;
        }
        @SuppressWarnings("unchecked") PushPop<T> pp = (PushPop<T>)o;
        if (this.longi != pp.longi) {
            System.out.println("Los tamaños no son iguales.");
            return false;
        }
        if (this.isEmpty() && pp.isEmpty()) {
            return true;
        }
        Nodo aux1 = this.cabeza;
        Nodo aux2 = pp.cabeza;
        while (aux1!=null && aux2 != null) {
            if (!aux1.elemento.equals(aux2.elemento)) {
                return false;
            } 
            aux1 = aux1.siguiente;
            aux2 = aux2.siguiente;
        }  
        return true;
    }

    /**
     * Regresa un clon de la estructura.
     * 
     * @return un clon de la estructura.
     */
    public abstract PushPop<T> clone();
    
    public abstract String toString();

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * 
     * SOLO SE USO PARA EL METODO CLONE DE PILA Y COLA 
     *
     *  @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void agregaFinal(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("");
        }
        Nodo nuevo = new Nodo(elemento);
        if(isEmpty()){
            this.cabeza=ultimo=nuevo;
            longi++;
            return ;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
            longi ++;
        }
    }
    
}
