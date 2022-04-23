package Clases;

import Clases.IteradorLista;


public class Participante {
    
    private String nombre;
    private int apuesta;
    private int trucosGanados;
    private int puntaje;          //cada ronda
    private int puntajeFinal;     //Al finalizar todas las rondas dentro del juego 
    private Carta baza = new Carta();
    private Lista<Carta> mazo = new Lista<Carta>();

    public Participante(){
    }
    
    /**
     * constructor
     */
    // apuesta -> num. de bazas que apuesta
    // puntaje -> " n/a "
    // mazo -> las cartas del jugador
    // baza -> carta que se apuesta
    public Participante(String nombre,int apuesta,int trucosGanados,int puntaje,int puntajeFinal,Lista<Carta> mazo,Carta baza){	
	this.nombre = nombre;
	this.apuesta = apuesta;
	this.trucosGanados = trucosGanados;
	this.puntaje = puntaje;
	this.puntajeFinal= puntajeFinal;
	this.mazo = mazo;	
	this.puntaje = puntaje;
	this.baza = baza;
    }
    public void setTrucosGanados(int trucosGanados){
          this.trucosGanados = trucosGanados;
    }
    public int getTrucosGanados(){
        return trucosGanados;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }    
    public String getNombre(){
        return nombre;
    }   
    public void setApuesta(int apuesta){
        this.apuesta = apuesta;
    }
    public int getApuesta(){
        return apuesta;
    }
    public Carta getTruco(){
        return baza;
    }
    public void setTruco(Carta baza){
	this.baza = baza;
    }
    public void setTrucoMazo(Carta newBaza,Lista<Carta> mazo){
	this.baza = newBaza;
	this.mazo = mazo;
    }
    public String getMazoString(){
	return mazo.toString();
    }
    public Lista<Carta> getMazo(){
	return mazo;
    }
    public void setMazo(Lista<Carta> mazo){
	this.mazo = mazo;
    }
    public int getPuntaje(){
        return puntos();
    }
    public void setPuntaje(int puntaje){
        this.puntaje = puntaje;
    }
    public void setPuntajeFinal(int puntajeFinal){
        this.puntajeFinal = puntajeFinal;
    }
    public int getPuntajeFinal(){
	return puntajeFinal;
    }
    
    /**
     * Método para ver si hay cartas semejantes en la 
     * lista de cartas del jugador con la carta paloGuía ---(paloLider) :D
     *
     * <tt>true<tt> sí hay cartas semejantes
     * <tt>false<tt> no hay cartas semejantes 
     *
     * @param c -> carta lider
     * @param l -> mazo
     */
    public boolean haySemejantes(Carta c,Lista l){
	String color = c.getColor();	
	boolean veredicto = false;
	IteradorLista<Carta> iter = l.iteradorLista();	
	if( l.isEmpty() ){//si la lista es vacía...
	    return veredicto;
	}else{ //Si no esta vacía...
	    while( iter.hasNext() ){ //mientras sig. no sea null
		if( (iter.next().getColor()).equals(color) ){ //comparamos el color el elem.
		    veredicto = true;                         //con el color de la cartaLider
		    return veredicto;//return true si sí son semejantes
		}
	    }
	}
	return veredicto;
    }
    
    /**
     * Método para ASIGNAR TRUCO
     * pasar una carta EXISTENTE en el mazo
     *
     * newBaza -> Carta que el jugador quiere apostar
     * mazo -> Mazo del jugador
     * lider -> carta Líder
     * triunfo -> carta Triunfo
     */
    // 2 -> significa que se asigno el truco y el palo lider
    //
    // 1 -> significa que HAY paloLider y HAY cartas semejantes en el mazo del jugador
    //     y que se asigno correctamente su truco
    //
    // -1 -> significa que HAY paloLider y HAY cartas semejantes en el mazo del jugador
    //     pero NO se asigno correctamente el truco, es incorrecta la carta
    //
    // 0 -> siginifica que NO HAY cartas semejantes en el mazo al palo Lider,
    //      por lo tanto, se asigna su truco
    //
    //  Rango de {-1 -> 2}
    //
    public int AsignarTruco(Carta newbaza,Lista mazo,Carta lider,Carta triunfo,Baraja wizard){
	if(lider.getColor().equals(" ")){//solo entra cuando NO hay paloLider
	    if( wizard.asignaLider(newbaza) ){
		setTruco(newbaza);
		mazo.delete(newbaza);
		setMazo(mazo);
		return 2; 
	    }
	}
	if( haySemejantes(lider,mazo) ){ //si tenemos cartas del color LIDER en el mazo
	    String colorLider = lider.getColor();
	    if( newbaza.getColor().equals(colorLider) ){
		setTruco(newbaza);		
		mazo.delete(newbaza);
		setMazo(mazo);
		return 1;
	    }else{
		return -1;
	    }
	}else{ //si no tenemos cartas del color LIDER en el mazo
	    setTruco(newbaza);
	    mazo.delete(newbaza);
	    setMazo(mazo);
	    return 0;
	}
    }
    
    /**
     * Metodo que suma o resta al puntaje del jugador 
     * si gana exactamente el numero de bazas que aposto se suma 20 + 10 (int trucosGanados)
     * en otro caso,  su puntuación sera -10 (|int apuesta - int trucosGanados|)
     * 
     * @return puntaje final del jugador 
     */
    public int puntos(){

        if (this.trucosGanados == this.apuesta){
        this.puntaje = 20 + (10 *trucosGanados);
        return puntaje;
        } else {
            this.puntaje = -10*Math.abs(this.apuesta-this.trucosGanados);
          return puntaje;
        }
    }

}