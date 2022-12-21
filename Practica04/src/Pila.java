package Clases;

/**
 * Clase Pila<T> -> Tipo de lista LIFO
 * @author Grupo EDD, 7038
 * @author López Diego Gabriela
 * @author Trujillo Beltrán Zianya Nenetzi
 * version 1
 */

public class Pila<T> extends PushPop<T>{
    
    /**
     * Agrega un elemento de tipo T a nuestra Lista tipo Pila
     * siguiendo la estructura LIFO, 
     * los elementos se agregan aL inicio de nuestra pila
     *
     * @param T elemento-elemento de tipo genérico
     */
    public void push(T elemento){
        if(elemento == null){
            throw new IllegalArgumentException("");
        }
        Nodo aux = new Nodo(elemento);
        if(isEmpty()){
            this.cabeza=ultimo=aux;
            longi++;
            return ;
        }
        aux.siguiente = cabeza;
        cabeza = aux;
        longi ++;

    }

    /**
     * Regresa un clon de la estructura.
     * 
     * @return un clon de la estructura.
     */  
    public Pila<T> clone(){
        Pila<T> nueva = new Pila<T>();
        
        if (this.isEmpty()) {
            return nueva;
        }else{
            nueva.agregaFinal(this.cabeza.elemento);
            Nodo n = this.cabeza;
            while (n.siguiente != null) {
               nueva.agregaFinal(n.siguiente.elemento);
               n = n.siguiente;
            }
        }
        return nueva;
    }

    /**
     * Regresa una representación en cadena de la estructura.
     * 
     * @return una representación en cadena de la estructura.
     *         a, b, c, d
     */
    public String toString(){
        if (this.isEmpty()) {
            return "";
        }
        String regreso = this.cabeza.elemento.toString();
        Nodo n = this.cabeza;
        while (n.siguiente != null) {
            regreso += ", " + n.siguiente.elemento.toString();
            n = n.siguiente;
        }
        return regreso;
    }    

}
