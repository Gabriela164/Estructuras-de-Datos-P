package Clases;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
**
**
*/

public class Lista<T> implements Collection<T> {

    // Clase Nodo
    private class Nodo {
        public T elemento;
        public Nodo anterior;
        public Nodo siguiente;
       
        public Nodo(T elemento) {
            this.elemento = elemento;
        }
    }

    // Iterador
    private class Iterador implements IteradorLista<T> {
        public Nodo anterior;
        public Nodo siguiente;

        public Iterador() {
            siguiente = cabeza;
        }

        @Override
        public boolean hasNext() {
            return siguiente != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T regresar = siguiente.elemento;

            this.anterior = this.siguiente;
            this.siguiente = siguiente.siguiente;
            return regresar;

        }

        @Override
        public boolean hasPrevious() {
            return anterior != null;
        }

        @Override
        public T previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            T regresar = anterior.elemento;

            this.siguiente = this.anterior;
            this.anterior = anterior.anterior;
            return regresar;

        }

        @Override
        public void start() {
            this.anterior = null;
            this.siguiente = cabeza;
        }

        @Override
        public void end() {
            this.anterior = ultimo;
            this.siguiente = null;
        }

    }

    private Nodo cabeza;
    private Nodo ultimo;
    private int longi;

    /**
     * Agrega un elemento a la lista.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    @Override
    public void add(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        agregaFinal(elemento);
    }
    /**
     * Elimina un elemento de la lista.
     * 
     * @param elemento el elemento a eliminar.
     */
    public boolean delete(T elemento) {
        if (elemento == null)
            return false;
        Nodo n = buscaElemento(elemento);
        if (n == null) {
            return false;
        }
        if (longi == 1) {
            empty();
            return true;
        }
        if (n == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longi--;
            return true;
        }
        if (n == ultimo) {
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
            longi--;
            return true;
        }
        n.siguiente.anterior = n.anterior;
        n.anterior.siguiente = n.siguiente;
        longi--;
        return true;
    }

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

    //método más
    public T peek(){
        if (isEmpty()) {
            throw new NoSuchElementException("");
        }
        return cabeza.elemento;
    }
    

    /**
     * Regresa el número de elementos en la lista.
     * 
     * @return el número de elementos en la lista.
     */
    public int size() {
        return longi;
    }

    /**
     * Nos dice si un elemento está contenido en la lista.
     * 
     * @param elemento el elemento que queremos verificar si está contenido en
     *                 la lista.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contains(T elemento) {
        if (buscaElemento(elemento) == null) {
            return false;
        }
        return true;
    }

    /**
     * Vacía la lista.
     * 
     */
    public void empty() {
        cabeza = ultimo = null;
        longi = 0;
    }

    /**
     * Nos dice si la lista es vacía.
     * 
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean isEmpty() {
        return longi == 0;
    }
    /**
     * Nos dice si la coleccion es igual a otra coleccion recibida.
     * 
     * @param coleccion la coleccion con el que hay que comparar.
     * @return <tt>true</tt> si la coleccion es igual a la coleccion recibido
     *         <tt>false</tt> en otro caso.
     */
    public boolean equals(Collection<T> coleccion) {
        // lo vemos en clase
        if (coleccion instanceof Lista) {
            return true;
        }
        return false;
    }
    /**
     * Regresa una representación en cadena de la coleccion.
     * 
     * @return una representación en cadena de la coleccion.
     *         a -> b -> c -> d
     */
    public String toString() {    
	Nodo uno = cabeza;
        if (uno == null){
	    return "";
	} else {
	    String resultado = "" + uno.elemento;
	    Nodo nuevo = uno.siguiente ;
	    while (nuevo != null){
		resultado += "\n" +nuevo.elemento ;
		nuevo = nuevo.siguiente ;
	    }
	    return resultado;
	}
    }
    /**
     * Metodo que invierte el orden de la lista .
     * cumple con T(n)=O(1) esto por solo trabajar con el bucle while 
     * y tener asignaciones.
     */
    public void reverse() {
       
	Nodo aux = null;
	Nodo nuevo = cabeza;
        
	while(nuevo != null){
	    Nodo siguiente = nuevo.siguiente;
       
	    nuevo.siguiente = aux; // Nodo siguiente apunta a Nodo aux
	    aux = nuevo;        //Nodo aux apunta a nodo nuevo     
	    nuevo = siguiente; 
	}          
	cabeza = aux;
    }    
    /**
     * Regresa una copia de la lista.
     * 
     * @return una copia de la lista.
     */
    public Lista<T> clone() {
        Lista<T> nueva = new Lista<T>();
        Nodo nodo = cabeza;
        while (nodo != null) {
            nueva.add(nodo.elemento);
            nodo = nodo.siguiente;
        }
        return nueva;
    }
    
    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            this.cabeza = this.ultimo = nuevo;
        } else {
            this.cabeza.anterior = nuevo;
            nuevo.siguiente = this.cabeza;
            this.cabeza = nuevo;
        }
        longi++;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * 
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento es null");
        }
        Nodo nuevo = new Nodo(elemento);
        if (cabeza == null) {
            this.cabeza = this.ultimo = nuevo;
        } else {
            this.ultimo.siguiente = nuevo;
            nuevo.anterior = this.ultimo;
            this.ultimo = nuevo;
        }
        longi++;
    }

    //método de más
    /**
     * Método que elimina del inicio un elemento
     * NO HACER CASO, No sirvió de nada unu
     */
    public void eliminaNodo(T elemento) {
	Nodo n = cabeza;
	if (longi == 0 || cabeza == null) {
	    throw new NoSuchElementException("");
	}
	if (longi == 1) {	    
	    cabeza = ultimo = null;
	    longi = 0;	    
	}
	if(elemento == ultimo){
	    ultimo.anterior.siguiente = null;
	    ultimo = ultimo.anterior;
	    longi--;
	}
	while(n != null){
	    if(elemento == n){
		n.anterior.siguiente = n.siguiente;
		n.siguiente.anterior = n.anterior;
		longi--;
	    }
	    n = n.siguiente;	    
	}
	
	/**T valor = cabeza.elemento;
	cabeza = cabeza.siguiente;
	longi --;*/
    }
    
    /**
     * Método PRIVADO para recorrer la lista buscando el elemento.
     * 
     * @param elemento el elemento que se busca.
     * @return nodo 
     */
    private Nodo buscaElemento(T elemento) {
        Nodo n = cabeza;
        while (n != null) {
            if (elemento.equals(n.elemento)) {
                return n;
            }
            n = n.siguiente;
        }
        return null;
    }

    
    /**
     * Junta dos listas siempre y cuando sean del mismo tipo.
     * 
     */
    public void append (Lista<T> lista) {
    
      if(lista.cabeza == null){
          return;
      }else{
        Nodo actual = lista.cabeza;
          while(actual != null){
        agregaFinal(actual.elemento);
        actual = actual.siguiente;  
        }
     
      }
     
    }   

    /**
     * Regresa un entero con la posicion del elemento.
     * Solo nos importara la primera aparición del elemento
     * Empieza a contar desde 0.
     * 
     * @param elemento elemento del cual queremos conocer la posición.
     * @return entero con la posicion del elemento
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
     
    public int indexOf(T elemento) {
        Nodo n = cabeza;
        
        if (elemento == null) {
            throw new IllegalArgumentException("Ingrese un elemento ");
        }
        
        while (n != null) {
	    for (int i = 0; i < size(); i++){
		if (elemento.equals(n.elemento)) 
		    return i;
		n = n.siguiente;   
	    }
        }   
	return -1;   //Regresa -1 cuando  el elemento no esta en la lista 
    }


    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor que cero, el elemento se agrega al inicio de la
     * lista. Si el índice es mayor o igual que el número de elementos en la
     * lista, el elemento se agrega al fina de la misma. En otro caso, después
     * de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * 
     * @param i        el índice dónde insertar el elemento. Si es menor que 0 el
     *                 elemento se agrega al inicio, y si es mayor o igual que el
     *                 número
     *                 de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *                                  <code>null</code>.
     */
    public void insert(int i, T elemento) {
        
      Nodo uno = new Nodo (elemento);
      uno.siguiente = null;
      int indice = i;
      
      if ( elemento == null){
       throw new IllegalArgumentException("Ingrese el elemento a agregar ");
      }
      
      //1er caso --la lista es vacia y el indice es 0
      if(this.cabeza == null){ 
           if(indice != 0){
           return;
      }else{
        this.cabeza = uno;
        }
      }
      
      //2do caso--- la lista no es vacia y el indice es 0 
      if(cabeza != null && indice == 0){
         uno.siguiente = this.cabeza;
         this.cabeza = uno;
         return;
      }
      
      Nodo actual = this.cabeza;
      Nodo anterior = null;
      int indice2 = 0;
       
      //3er caso--el indice es mayor que 0
      while(indice > indice2){
         anterior = actual;
         actual = actual.siguiente;
            if (actual == null){
                break;
            }
      indice2 ++; 
     }
      uno.siguiente = actual;
      anterior.siguiente = uno;
      longi ++;
    }
    

    /** 
     * Metodo que une los elementos de una lista con otra de forma alternada
     * @param lista lista contiene los elemenos a unir 
     * Cumple con T(n+m) = O(1) ya que solo trabajamos con el bucle while, 
     * asignaciones y nodos para cambiar los apuntadores :D
     **/
    public void mezclaAlternada(Lista<T> lista) {
    
       Nodo actual_1 = this.cabeza; //Nodo auxiliar
       Nodo actual_2 = lista.cabeza; //Nodo auxiliar 
        
       while(actual_1 != null && actual_2 != null){
       Nodo siguiente_1 = actual_1.siguiente;
       Nodo siguiente_2 = actual_2.siguiente;
       
       actual_2.siguiente = siguiente_1;
       actual_1.siguiente = actual_2;
       
       actual_1 = siguiente_1;
       actual_2 = siguiente_2;
       
       }
        lista.cabeza=actual_2;
    }

     //indice a partir del cero
     public T index(int n){
        if(n == 1){
            return cabeza.elemento;
        }
        int indice = 1;	
        Nodo aux = cabeza.siguiente;       
        while(aux != null){
            indice++;
            if(n==indice){
            return aux.elemento;
            }
            aux = aux.siguiente;
        }
        return null;
        }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * 
     * @return un iterador para recorrer la lista en una dirección.
     */
    public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * 
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }
    
}


