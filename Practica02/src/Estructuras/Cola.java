package edd.src.Estructuras;

public class Cola<T>  extends PushPop<T>{
    
    // Agregar al final de la lista 
    
    public void push(T elemento){
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
        String imprime = this.cabeza.elemento.toString();
        Nodo n = this.cabeza;
        while (n.siguiente != null) {
            imprime += ", " + n.siguiente.elemento.toString();
            n = n.siguiente;
        }
        return imprime;
    }


}
