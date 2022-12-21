package Clases;

import java.util.NoSuchElementException;
import Clases.VerticeArbolBinario;
import java.util.Comparator;
import java.util.Iterator;

/**
 * <p>
 * Clase abstracta para árboles binarios genéricos.
 * </p>
 *
 * <p>
 * La clase proporciona las operaciones básicas para árboles binarios, pero
 * deja la implementación de varias en manos de las subclases concretas.
 * </p>
 */
public abstract class ArbolBinario<T> implements Collection<T> {
    /**
     * Clase interna protegida para vértices.
     */
    protected class Vertice implements VerticeArbolBinario<T> {
        /** El elemento del vértice. */
        public T elemento;
        /** El padre del vértice. */
        public Vertice padre;
        /** El izquierdo del vértice. */
        public Vertice izquierdo;
        /** El derecho del vértice. */
        public Vertice derecho;
                
        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public Vertice(T elemento) {
          if(elemento!=null)
             this.elemento=elemento;
        }

	/**
         * Regresa una representación en cadena del vértice.
         * @return una representación en cadena del vértice.
         */
        public String toString() {
            return this.elemento.toString();
        }

        /**
         * Nos dice si el vértice tiene vértice padre.
         * @return <tt>true</tt> si el vértice tiene vértice padre, <tt>false</tt>
         *         en otro caso.
         */
        @Override
	public boolean hayPadre() {
            return padre != null;
        }

        /**
         * Nos dice si el vértice tiene vértice izquierdo.
         * @return <tt>true</tt> si el vértice tiene vértice izquierdo,
         *         <tt>false</tt> en otro caso.
         */
        @Override
	public boolean hayIzquierdo() {
            return izquierdo != null;
        }

        /**
         * Nos dice si el vértice tiene vértice derecho.
         * @return <tt>true</tt> si el vértice tiene vértice derecho, <tt>false</tt>
         *         en otro caso.
         */
        @Override
	public boolean hayDerecho() {
            return derecho != null;
        }
	
        /**
         * Regresa el vértice padre del vértice.
         * @return el vértice padre del vértice.
         * @throws NoSuchElementException si el vértice no tiene padre.
         */
        public VerticeArbolBinario<T> padre() { //sería....."padre" nada más?
            if (padre == null)
                throw new NoSuchElementException("El vértice no tiene padre.");
            return padre;
        }

	/**
         * Regresa el vértice derecho del vértice.
         * @return el vértice derecho del vértice.
         * @throws NoSuchElementException si el vértice no tiene derecho.
         */
        public VerticeArbolBinario<T> derecho() { //sería....."derecho" nada más?
            if (derecho == null)
                throw new NoSuchElementException("El vértice no tiene derecho.");
            return derecho;
        }
	
        /**
         * Regresa el vértice izquierdo del vértice.
         * @return el vértice izquierdo del vértice.
         * @throws NoSuchElementException si el vértice no tiene izquierdo.
         */
        public VerticeArbolBinario<T> izquierdo() { //sería...."izquierdo" nada más?
            if (izquierdo == null)
                throw new NoSuchElementException("El vértice no tiene izquierdo.");
            return izquierdo;
        }
	
        /**
         * Regresa el elemento del vértice.
         * @return el elemento del vértice.
         */
        @Override
	public T get() {
            return elemento;
        }

        /**
         * Regresa la altura del vértice.
         * 
         * @return la altura del vértice.
         * 
         * Hay que probar. 
         */
        @Override
	public int altura() {
            
            int alturaIzquierdo = 0;
            int alturaDerecho = 0;
            
            if (hayIzquierdo())
                alturaIzquierdo = izquierdo.altura();
            if (hayDerecho())
                alturaDerecho = derecho.altura();
            return 1 + Math.max(alturaIzquierdo, alturaDerecho);

        }

        /**
         * Regresa la profundidad del vértice.
         * 
         * @return la profundidad del vértice.
        */
        @Override
	public int profundidad() {
            int profundidadPadre = 0;
            if (hayPadre())
                profundidadPadre = padre.profundidad();
            return 1 + profundidadPadre;
        }
	
	/**
         * Auxiliar de equals. Compara vertice por vertice.
         * @param v1 Vertice de arbol 1
         * @param v2 Vertice de arbol 2
         * @return <code>true</code> si arbol 1 y arbol 2
         *         son iguales; <code>false</code> en otro caso.
         */
        private boolean equals(Vertice v1, Vertice v2) {
            if (v1 == null && v2 == null) {
                return true;
            }
            if ((v1 == null && v2 != null) || (v1 != null && v2 == null) || !v1.elemento.equals(v2.elemento)) {
                return false;
            }
            return equals(v1.izquierdo, v2.izquierdo) && equals(v1.derecho, v2.derecho);
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>. Las clases que extiendan {@link Vertice} deben
         * sobrecargar el método {@link Vertice#equals}.
         * 
         * @param o el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link Vertice}, su elemento es igual al elemento de éste
         *         vértice, y los descendientes de ambos son recursivamente
         *         iguales; <code>false</code> en otro caso.
         */
         @Override
	 public boolean equals(Object o){
            if (o == null || getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked")
            Vertice vertice = (Vertice) o;
            if (!this.elemento.equals(vertice.elemento)) {
                return false;
            }
            return equalsAuxDer(this,vertice) && equalsAuxIzq(this, vertice);
        }
	
        private boolean equalsAuxDer(Vertice a,Vertice b){
            if(a.hayDerecho() && b.hayDerecho() && b.elemento.equals(a.elemento)){
                return a.derecho.equals(b.derecho);
            }
            if(!a.hayDerecho() && !b.hayDerecho() && b.elemento.equals(a.elemento)){
                return true;
            }
            if(a.hayDerecho() && !b.hayDerecho()){
                return false;
            }
            if (!a.hayDerecho() && b.hayDerecho()) {
                return false;
            }
            return false;
        }
        private boolean equalsAuxIzq(Vertice a, Vertice b) {
            if (a.hayIzquierdo() && b.hayIzquierdo() && b.elemento.equals(a.elemento)) {
                return a.izquierdo.equals(b.izquierdo);
            }
            if (!a.hayIzquierdo() && !b.hayIzquierdo() && b.elemento.equals(a.elemento)) {
                return true;
            }
            if (a.hayIzquierdo() && !b.hayIzquierdo()) {
                return false;
            }
            if (!a.hayIzquierdo() && b.hayIzquierdo()) {
                return false;
            }
            return false;
        }

    }//termina clase Vertice 

 
    /** La raíz del árbol. */
    protected Vertice raiz;
    /** El contador del número de elementos */
    protected int elementos;
    /** El vértice del último elemento agregado. */
    protected Vertice ultimoAgregado;    

    
    /**
     * Constructor sin parametros 
     */
    public ArbolBinario() {
	
    }

    /**
     * Construye un árbol binario a partir de una colección. El árbol binario
     *  tiene los mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol
     *        binario.
     */
    public ArbolBinario(Collection<T> coleccion) {
        Iterator<T> it = coleccion.iterator();
        while (it.hasNext()) {
            T elemento = it.next();
            add(elemento);
        }
       /* for (T elemento : coleccion){
            this.add(elemento);
        }*/
    }
   
    /**
     * Construye un nuevo vértice, usando una instancia de {@link Vertice}. Para
     * crear vértices se debe utilizar este método en lugar del operador
     * <code>new</code>, para que las clases herederas de ésta puedan
     * sobrecargarlo y permitir que cada estructura de árbol binario utilice
     * distintos tipos de vértices.
     * 
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice con el elemento recibido dentro del mismo.
     */
    protected Vertice nuevoVertice(T elemento) {
        return new Vertice(elemento);
    }

    /**
     * Método auxiliar para determinar la max profundidad de
     * alguno de los hijos del árbol.
     *
     */
    private int profundidad(Vertice v) {
        if (v == null) {
            return -1;
        }
        return 1 + Math.max(this.profundidad(v.izquierdo), this.profundidad(v.derecho));
    }

    /**
     * Regresa la profundidad del árbol. La profundidad de un árbol es la
     * longitud de la ruta más larga entre la raíz y una hoja.
     * @return la profundidad del árbol.
     */
    public int profundidad() {
        return profundidad(this.raiz);
    }    
    
    /**
     * Regresa la altura del vértice.
     * 
     * @return la altura del vértice.
     */
    public int altura() {
        if (isEmpty())
            return -1;
        return raiz.altura();
    }

    /**
     * Regresa el número de elementos que se han agregado al árbol.
     * @return el número de elementos en el árbol.
     */
    public int getElementos() {
        return this.elementos;
    }

    /**
     * Regresa el vértice que contiene el último elemento agregado al árbol.
     * @return el vértice que contiene el último elemento agregado al árbol.
     */
    public VerticeArbolBinario<T> getUltimoVerticeAgregado() {
        return this.ultimoAgregado;
    }

    /**
     * Nos dice si un elemento está en el árbol binario.
     * 
     * @param elemento el elemento que queremos comprobar si está en el árbol.
     * @return <code>true</code> si el elemento está en el árbol;
     *         <code>false</code> en otro caso.
     */
    @Override
    public boolean contains(T elemento){
        return busca(elemento) != null;
    }

    /**
     * Busca el vértice de un elemento en el árbol. Si no lo encuentra regresa
     * <tt>null</tt>.
     * 
     * @param elemento el elemento para buscar el vértice.
     * @return un vértice que contiene el elemento buscado si lo encuentra;
     *         <tt>null</tt> en otro caso.
     */
    public VerticeArbolBinario<T> busca(T elemento){
        return buscar(raiz,elemento);
    }

    /**
     * Busca recursivamente un elemento, a partir del vértice recibido.
     * @param vertice el vértice a partir del cuál comenzar la búsqueda. Puede
     *                ser <code>null</code>.
     * @param elemento el elemento a buscar a partir del vértice.
     * @return el vértice que contiene el elemento a buscar, si se encuentra en
     *         el árbol; <code>null</code> en otro caso.
     */
    protected Vertice buscar(Vertice v, T elemento){
        if(v == null || elemento ==null){
            return null;
        }
        if(v.elemento.equals(elemento)){
            return v;
        }
        Vertice aux = buscar(v.izquierdo, elemento);
        if(aux != null){
            return aux;
        }
        return buscar(v.derecho, elemento);
    }

    /**
     * Regresa el vértice que contiene la raíz del árbol.
     * @return el vértice que contiene la raíz del árbol.
     * @throws NoSuchElementException si el árbol es vacío.
     */
    public VerticeArbolBinario<T> raiz() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.raiz;
    }
    
    /**
     * Nos dice si el árbol es vacío.
     * 
     * @return <code>true</code> si el árbol es vacío, <code>false</code> en
     *         otro caso.
     */
    @Override
    public boolean isEmpty() {
        return raiz == null;
    }

    /**
     * Compara el árbol con un objeto.
     * 
     * @param o el objeto con el que queremos comparar el árbol.
     * @return <code>true</code> si el objeto recibido es un árbol binario y los
     *         árboles son iguales; <code>false</code> en otro caso.
     */
     @Override
     public boolean equals(Object o){
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
	    ArbolBinario<T> arbol = (ArbolBinario<T>)o;
        if(isEmpty()){
            return arbol.isEmpty();
        }
        return this.raiz.equals(arbol.raiz);
    }
    
    /**
     * Regresa una representación en cadena del árbol.
     * 
     * @return una representación en cadena del árbol.
     */
    @Override
    public String toString() {
        if (this.raiz == null) {
            return "";
        }
        int[] a = new int[this.altura() + 1];
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;
        }
        return toString(this.raiz, 0, a);
    }        
    
    /**
     * Metodo auxiliar de toString
     *
     */
    private String toString(Vertice v, int l, int[] m) {
        String s = v.toString() + "\n";
        m[l] = 1;

        if (v.izquierdo != null && v.derecho != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "├─›";
            s = s + toString(v.izquierdo, l + 1, m);
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─»";
            m[l] = 0;
            s = s + toString(v.derecho, l + 1, m);
        } else if (v.izquierdo != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─›";
            m[l] = 0;
            s = s + toString(v.izquierdo, l + 1, m);
        } else if (v.derecho != null) {
            s = s + dibujaEspacios(l, m, m.length);
            s = s + "└─»";
            m[l] = 0;
            s = s + toString(v.derecho, l + 1, m);
        }
        return s;
    }
    
    /**
     * Método auxiliar de toString
     * 
     * @return espacios
     */
    private String dibujaEspacios(int l, int[] a, int n) {
        String s = "";
        for (int i = 0; i < l; i++) {
            if (a[i] == 1) {
                s = s + "│  ";
            } else {
                s = s + "   ";
            }
        }
        return s;
    }

    /**
     * Convierte el vértice (visto como instancia de {@link
     * VerticeArbolBinario}) en vértice (visto como instancia de {@link
     * Vertice}). Método auxiliar para hacer esta audición en un único lugar.
     * 
     * @param vertice el vértice de árbol binario que queremos como vértice.
     * @return el vértice recibido visto como vértice.
     * @throws ClassCastException si el vértice no es instancia de {@link
     *                            Vertice}.
     */
    protected Vertice vertice(VerticeArbolBinario<T> vertice) {
        return (Vertice) vertice;
    }
    
    /**
     * Regresa el número de elementos que se han agregado al árbol.
     * 
     * @return el número de elementos en el árbol.
     */
    @Override
    public int size() {
        return elementos;
    }  

    @Override
    public void empty(){
        raiz = null;
        elementos=0;
    }
    
}
