/**
 * @author Trujillo Beltrán Zianya Nenetzi 
 * @author Lopez Diego Gabriela
 *
 * @version 1
 */

public class Jugador {
    private String nombre, color;
    private int ficha1;
    private int ficha2;

    public Jugador(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public String getColor() {
        return color;
    }

    public void setFicha1(int ficha1) {
        this.ficha1 = ficha1;
    }

    public void setFicha2(int ficha2) {
        this.ficha2 = ficha2;
    }

    public int getFicha1() {
        return ficha1;
    }

    public int getFicha2() {
        return ficha2;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

