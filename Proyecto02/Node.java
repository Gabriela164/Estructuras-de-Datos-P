/**
 * @author Trujillo Beltrán Zianya Nenetzi 
 * @author Lopez Diego Gabriela
 *
 * @version 1
 */

import java.io.Serializable;

public class Node<T> implements Serializable {

    T element;
    Node<T> next;

    public Node(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setElement(T element) {
        this.element = element;
    }
}
