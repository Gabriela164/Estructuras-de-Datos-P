/**
 * @author Trujillo Beltrán Zianya Nenetzi 
 * @author Lopez Diego Gabriela
 *
 * @version 1
 */

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public class BinarySearchTree <K extends Comparable,T> implements TDABinarySearchTree<K,T>, Serializable {

    private BinaryNode<K, T> root;

    public BinaryNode<K, T> getRoot() {
        return root;
    }

    /**
     * Recupera el objeto con clave k.
     *
     * @param k la clave a buscar.
     * @return el elemento con clave k o null si no existe.
     */
    @Override
    public T retrieve(K k) {
        BinaryNode node = retrieve(root,k);
        if(node == null) 
            return null;
        return (T) node.getElement();
    }

    /**
     * Método auxiliar para retrieve y obtener un nodo con tener la clave
     *
     * @param node nodo por el que empezaremos
     * @param key llave del nodo que buscamos
     * @return El nodo con la clave que buscamos
     */
    private BinaryNode retrieve(BinaryNode node, K key){
        if(node == null)
            return null;
        if(node.getKey().compareTo(key) == 0)
            return node;
        if(key.compareTo(node.getKey()) < 0){
            return retrieve(node.getLeft(), key);
        } else {
            return retrieve(node.getRight(), key);
        }
    }

    public void setElement(BinaryNode node, T e){
        node.setElement(e);
    }

    /**
     * Inserta un nuevo elemento al árbol.
     *
     * @param e el elemento a ingresar.
     * @param k la clave del elemento a ingresar.
     */
    @Override
    public void insert(T e, K k) {
        if(isEmpty()){
            root = new BinaryNode<>(e, k, null);
            return;
        }
        insert(e, k, root);
    }

    /**
     * Método auxiliar de insert para saber en que lugar irá el nodo que insertaremos.
     * @param e - elemento que ingresaremos.
     * @param key - llave del nodo.
     * @param node - nodo con el que compararemos.
     */

    private void insert(T e, K key, BinaryNode node){
        if(node.getKey().compareTo(key) >= 0){
            if(node.hasLeft()){
                insert(e, key, node.getLeft());
            }else{
                node.setLeft(new BinaryNode<>(e, key, node));
            }
        }else{
            if(node.hasRight()){
                insert(e, key, node.getRight());
            }else{
                node.setRight(new BinaryNode<>(e, key, node));
            }
        }
    }

    /**
     * Elimina el nodo con clave k del árbol.
     *
     * @param k la clave perteneciente al nodo a eliminar.
     * @return el elemento almacenado en el nodo a eliminar.
     * null si el nodo con clave k no existe.
     */
    @Override
    public T delete(K k) {
        if(this.isEmpty()){
	    return null;
	}
	return delete(retrieve(root,k));
    }

    /**
     * Método auxiliar para delete.
     * @param node nodo que eliminaremos.
     * @return elemento del nodo que se borró.
     */

    private T delete(BinaryNode node){
        if(node == null){
            return null;
        }
        BinaryNode aux;
        T element = (T) node.getElement();
        if(node.hasLeft()){
            aux = findMaxAux(node.getLeft());
            if(aux.isLeaf()){
                if(aux.getParent().equals(node)){		    
                    node.setLeft(null);
		}else{aux.getParent().setRight(null);}
            }else{
                aux.getLeft().setParent(aux.getParent());
                aux.getParent().setRight(aux.getLeft());
            }
        }else{
            if(node.hasRight()){
                aux = findMaxAux(node.getRight());
                if(aux.isLeaf()){
                    if(aux.getParent().equals(node)){
			node.setRight(null);
		    }else{
                        aux.getParent().setRight(null);
		    }
                }else{
                    aux.getLeft().setParent(aux.getParent());
                    aux.getParent().setRight(aux.getLeft());
                }
            }else{
                if(node.getKey().compareTo(node.getParent().getKey()) < 0){
		    node.getParent().setLeft(null);
		} else
                    node.getParent().setRight(null);
                return element;
            }
        }
        node.setElement(aux.getElement());
        node.setKey(aux.getKey());
        return element;
    }


    /**
     * Encuentra la clave k con valor o peso mínimo del árbol.
     * @param node - nodo base del que encontraremos el mínimo.
     * @return el elemento con llave de peso mínimo.
     */
    @Override
    public T findMin(BinaryNode node) {
        if(this.isEmpty()){
            return null;
	}
        else{
            return (T) findMinAux(node).getElement();
        }
    }

    /**
     * Método que encuentra el elemento mínimo del árbol.
     * @return El elemento con la llave de peso mínimo.
     */
    public T findMin(){
	if(this.isEmpty()){	    
	    return null;
	}       
	return (T) findMinAux(root).getElement();
    }

    /**
     * Método auxiliar de findMin para saber el nodo del elemento mínimo.
     * @param node - Nodo base del que encontraremos el mínimo.
     * @return Nodo del elemento mínimo.
     */

    private BinaryNode findMinAux(BinaryNode node){
        if(node.hasLeft()){
	    return findMinAux(node.getLeft());
	}
	return node;
    }


    /**
     * Encuentra la clave k con valor o peso máximo del árbol.
     *
     * @return el elemento con llave de peso máximo.
     */
    @Override
    public T findMax(BinaryNode node) {
        if(this.isEmpty()){	    
            return null;
	}
	return (T) findMaxAux(node).getElement();
    }

    /**
     * Método que encuentra la clave k con valor o peso máximo del árbol.
     *
     * @return el elemento con llave de peso máximo
     */
    public T findMax(){
        if(this.isEmpty()){
	    return null;
	}
	return (T) findMaxAux(root).getElement();
    }


    /**
     * Método auxiliar de findMax para encontrar la clave con mayor peso.
     * @param node - nodo del que tomaremos como base para encontrar el máximo.
     * @return nodo del elemento máximo.
     */
    private BinaryNode findMaxAux(BinaryNode node){
        if(node.hasRight()){
            return findMaxAux(node.getRight());
	}
	return node;
    }

    /**
     * Recorre el árbol en preorden.
     */
    @Override
    public void preorden() {
        preorden(root);
    }

    /**
     * Método auxiliar de preorden para hacer el recorrido del árbol.
     * @param node - nodo del que empezaremos el recorrido.
     */
    public void preorden(BinaryNode node){
         if(node == null)
            return;        
        System.out.print(node.getElement() + " ");   
        preorden(node.getLeft());  
        preorden(node.getRight());
    }

    /**
     * Recorre el árbol en inorden.
     */
    @Override
    public void inorden() {
        inorden(root);
    }

    /**
     * Método auxiliar de inorden para recorrer el árbol.
     * @param node - nodo del que empezaremos.
     */
    public void inorden(BinaryNode node){
        if(node == null){
            return;
        }
        inorden(node.getLeft());
        System.out.print(node.getElement() + " ");
        inorden(node.getRight());
    }


    /**
     * Recorre el árbol en postorden.
     */
    @Override
    public void postorden() {
        postorden(root);
    }

    /**
     * Método auxiliar de postorden para hacer recorrido del árbol.
     * @param node - nodo del que empezaremos.
     */
     public void postorden(BinaryNode node){
	 if( node == null ){
	     return ;
	 }
        postorden(node.getLeft());
        postorden(node.getRight());
        System.out.print(node.getElement() + " ");
    }

    /**
     * Verifica si el árbol es vacío.
     *
     * @return true si el árbol es vacío, false en otro caso.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public void clear(){
        root = null;
    }

}
