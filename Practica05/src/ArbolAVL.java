
package Clases;
import java.util.Collection;
import java.util.Iterator;
import Clases.ArbolBinario;


public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T>{
    /**
     * Clase interna protegida para los Vertices de árboles AVL 
     */
   protected class VerticeAVL extends Vertice {

    //contructor de vertice en arbol AVL 
    public VerticeAVL(T elemento){
        super(elemento);
    }

    /**
     * Regresa una representación en cadena del vértice del arbol AVL.
     */
    public String toString(){
        if(this == null)
            return "";
        return "[" + this.elemento.toString() + "]";
    }

    /**
     * Metodo que compara un objeto con un vertices del arbol AVL
     *  @param o el objeto con el cual se comparará el vértice.
     * return <code>true</code> si el objeto es instancia de la clase y <code>false<code> en otro caso. 
     */

     public boolean equals(Object o){
        if(o == null || getClass() != o.getClass())
            return false;
        @SuppressWarnings("unchecked")
        VerticeAVL vertice = (VerticeAVL) o;
        return super.equals(o);
      }

      //Metodo que regresa la altura del vertice en el arbol AVL
      public int getAltura(){
         return this.altura();
     }

    } //Termina clase vertice 


    

    /**
     * Contructor sin parametros
     */
    public ArbolAVL(){
        
    }

    /**
     * Contruye un arbol AVL a partir de una coleccion
     * @param coleccion 
     */
    public ArbolAVL(Collection<T> coleccion){
        Iterator<T> iterador = coleccion.iterator();
        while(iterador.hasNext()){
            insertar(iterador.next());
        }
    }

    /**
     * Metodo para colocar un elemento a un vertice AVL 
     * @param elemento
     */
    protected Vertice nuevoVertice(T elemento){
        return new VerticeAVL(elemento);
    }
 
    //Es necesario este metodo?     
    // contruye un nuevo vertice con el elemento recibido
    protected VerticeAVL convertirAVL(VerticeArbolBinario<T> vertice){
        return (VerticeAVL)vertice;
    }









    //Agrega un elemento al arbol AVL
    public void insertar(T elemento){
        if(elemento == null)
            throw new IllegalArgumentException("El elemento no puede ser nulo");
        add(elemento);
        //falta balancear el arbol



    }


    //Elimina un elemento del arbol AVL
     public void eliminar(T elemento){
        if(elemento == null)
            throw new IllegalArgumentException("El elemento no puede ser nulo");
       delete(elemento);
       //falta balancear el arbol

     }


     //Metodo que balancea nuestro arbol AVL
     public void balancea(){


     }


      

   



}