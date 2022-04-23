package Clases;

public class Carta{

   private String color = " ";
   private String numero = " ";
   private String palo = " ";


    public Carta(){
    }

    public Carta (String palo, String color, String numero){
	this.color = color;
	this.numero = numero;
	this.palo = palo;
    }

    public void setColor(String color){
        this.color = color;
    }
 
    public String getColor(){
        return color;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    public String getNumero(){
        return numero;
    }

    public void setPalo(String palo){
        this.palo = palo;
    }

    public String getPalo(){
        return palo;
    }
    public String toString(){
	return "[Carta de "+getNumero()+" de "+getPalo()+" "+getColor()+"]";
    }


}

