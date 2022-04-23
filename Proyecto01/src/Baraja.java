package Clases;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;
public class Baraja{
    private Lista<Carta> barajita = new Lista<Carta>();
    private Carta paloTriunfo = new Carta();
    private Carta paloLider = new Carta();

    Scanner sc = new Scanner(System.in);

    public Baraja(){	
	for(int a=1;a<=13;a++){
	    Carta humano = new Carta("humano","azul",String.valueOf(a));
	    barajita.add(humano);
	}	
	for(int k=1;k<=13;k++){
	    Carta gigante = new Carta("gigante","amarillo",String.valueOf(k));
	    barajita.add(gigante);   
	}
	for(int j=1;j<=13;j++){
	    Carta elfo = new Carta("elfo","verde",String.valueOf(j));
	    barajita.add(elfo);
	}
	for(int h=1;h<=13;h++){
	    Carta enano = new Carta("enano","rojo",String.valueOf(h));
	    barajita.add(enano);
	}	
	Carta mago1 = new Carta("mago","azul","0");
	Carta mago2 = new Carta("mago","amarillo","0");
	Carta mago3 = new Carta("mago","verde","0");
	Carta mago4 = new Carta("mago","rojo","0");
	barajita.add(mago1);barajita.add(mago2);
	barajita.add(mago3);barajita.add(mago4);

	Carta elfo1 = new Carta("bufon","azul","0");
	Carta elfo2 = new Carta("bufon","amarillo","0");
	Carta elfo3 = new Carta("bufon","verde","0");
	Carta elfo4 = new Carta("bufon","rojo","0");
	barajita.add(elfo1);barajita.add(elfo2);
	barajita.add(elfo3);barajita.add(elfo4);

	Carta triunfo = new Carta();
	Carta lider = new Carta();
	this.paloTriunfo = triunfo;
	this.paloLider = lider;
    }            
    /////
    public void setPaloTriunfo(Carta c){
	paloTriunfo = c;
    }
    public Carta getPaloTriunfo(){
	return this.paloTriunfo;
    }
    public void setPaloLider(Carta c){
	paloLider = c;
    }
    public Carta getPaloLider(){
	return this.paloLider;
    }
    public Lista<Carta> getBaraja(){
	return barajita;
    }
    public void setBaraja(Lista<Carta> barajita){
	this.barajita = barajita;
    }
    /////
    /**
     * Método para elegir el palo de triunfo
     * (SE SACA LA PRIMERA CARTA, CARTA DE INICIO)
     *
     * existen 3 casos:
     * Case 1) MAGO-> 
     * Case 2) BUFON->  
     * CASE 3) NUM.-> 
     * default: sin palo
     */
    public Carta eligeTriunfo(){	
	Carta c = barajita.peek();	
	if( c.getPalo().equals("mago") ){// case1) MAGO	    
	    System.out.println("Salió mago!!");
	    System.out.println("El que barajeó tiene que escoger la carta que será el palo de Triunfo, las cartas disponibles son:");
	    System.out.println(toString()); //mostramos la baraja
	    int caso = 0;
	    String palo, number;	    
	    do{  //Palo
		System.out.print("¿Qué palo eliges? ");
		palo = sc.nextLine();		
		if(palo.equals("humano")){
		    caso = 1; //y color AZUL
		}else if(palo.equals("gigante")){
		    caso = 2; //y color AMARILLO
		}else if(palo.equals("elfo")){
		    caso = 3; //y color VERDE
		}else if(palo.equals("enano")){
		    caso = 4; //y color ROJO
		}				
	    }while(caso!=1 && caso!=2 && caso!=3 && caso!=4);//need Try-Cath???
	    int num = 0;	    
	    do{ //num. {1-13}
		System.out.print("¿Qué número eliges? ");
		number = sc.nextLine();		
		if(number.equals("1")){
		    num = 1;		    
		}else if(number.equals("2")){
		    num = 2;
		}else if(number.equals("3")){
		    num = 3;
		}else if(number.equals("4")){
		    num = 4;
		}else if(number.equals("5")){
		    num = 5;
		}else if(number.equals("6")){
		    num = 6;
		}else if(number.equals("7")){
		    num = 7;
		}else if(number.equals("8")){
		    num = 8;
		}else if(number.equals("9")){
		    num = 9;
		}else if(number.equals("10")){
		    num = 10;
		}else if(number.equals("11")){
		    num = 11;
		}else if(number.equals("12")){
		    num = 12;
		}else if(number.equals("13")){
		    num = 13;
		}						
	    }while( !(num>0 && num<14) );//need Try-Cath???

	    if(palo.equals("humano")){
		c = new Carta("humano","azul",String.valueOf(num)); //y color AZUL
	    }else if(palo.equals("gigante")){
		c = new Carta("gigante","amarillo",String.valueOf(num)); //y color AMARILLO
	    }else if(palo.equals("elfo")){
		c = new Carta("elfo","verde",String.valueOf(num)); //y color VERDE
	    }else if(palo.equals("enano")){
		c = new Carta("enano","rojo",String.valueOf(num)); //y color ROJO		
	    }
	    setPaloTriunfo(c); //ASIGNAMOS nuevo palo de Triunfo
	    barajita.delete(c);
	    //barajita.eliminaNodo(c);
	    return c;
	    
	}else if( c.getPalo().equals("bufon") ){ //case2) BUFON
	    System.out.println("Salió bufón!!");
	    System.out.println("sin palo esta ronda");
	    c = new Carta();
	    setPaloTriunfo(c);
	    
	}else if( barajita.isEmpty() ){ //default: si ya no hay cartas
	    System.out.println("Ya no hay cartas!!");
	    System.out.println("sin palo esta ronda");
	    c = new Carta();
	    setPaloTriunfo(c);
	    
	}else{  //case3) cuando es num. 
	    setPaloTriunfo(c);//ASIGNAMOS nuevo palo de Triunfo
	    barajita.delete(c);
	    return c;
	}
	return c;

	/**
	 * NOTA: hay que checar el caso en que nos den una carta inexistente
	 */
    }
    //////    
    public String toString(){	
	IteradorLista<Carta> iter = barajita.iteradorLista();	
	String cadena = "";
	if(barajita.isEmpty()){
	    cadena = " ";
	}else{
	    while( iter.hasNext() ){
		cadena += iter.next()+"\n";
	    }
	}	       
	cadena += "Palo de Triunfo: "+getPaloTriunfo()+"\n";
	cadena += "Palo Líder: "+getPaloLider();
	return cadena ;
    }
    ///////     
    /**
     * Metodo que barajea todas las cartas de wizard 
     * 
     */
    public Lista<Carta> mezclaBaraja(){
	Lista<Carta> barajeado = new Lista<Carta>();

	ArrayList<Carta> barajaMezclada = new ArrayList<Carta>(); //Creamos una lista de JAVA para agregar las cartas de wizard
	
	IteradorLista<Carta> iter = barajita.iteradorLista();
	
	if( barajita.isEmpty() ){//si la lista es vacía...
	    return barajita;
	}// si NO esta vacía la lista
	
	while( iter.hasNext() ){ //mientras sig. no sea null
	    barajaMezclada.add( iter.next() );
	}
	
	Collections.shuffle(barajaMezclada); //Mezclamos la baraja
	
	barajita.empty(); //vaciamos nuestra lista original
	
	for(int x=0;x<barajaMezclada.size();x++){//Agregamos los valores a la baraja original
	    barajita.add( barajaMezclada.get(x) );
	}
	return barajita;     //devolvemos la baraja ya mezclada    		
    }
    /////
    public Lista<Carta> reparteCartas(int n){
	Lista<Carta> mazo = new Lista<Carta>();
	mezclaBaraja(); 
	for(int i=1; i<=n ;i++){
	    if( !barajita.isEmpty() ){
		Carta c = barajita.peek();
		mazo.add(c);
		barajita.delete(c);		
	    } 
	}
	return mazo;//regresamos el mazo con las n cartas
    }
    ////
    /**
     * Método para decidir si es correcta la carta Líder
     * 
     * <tt>true<tt> -> sí es correcta
     * <tt>false<tt> -> no es correcta
     */
    public boolean asignaLider(Carta c){
	if( !c.getPalo().equals("mago") && !c.getPalo().equals("bufon") ){
	    setPaloLider(c);//asigno carta Líder
	    return true;
	}
	return false;
    }
    
    public boolean hayPaloLider(){
	Carta nueva = new Carta();
	if(getPaloLider().equals(nueva)){
            return false;
	}else{
	    return true;
	}
    }
    //meotodo para regresar la carta con mayor puntaje
    public Carta maxPuntaje(Lista<Carta> cartas){
	IteradorLista<Carta> iter = cartas.iteradorLista();
	Carta a;Carta b;Carta c=new Carta();
	int parametro1; int parametro2;int mayor;
	
	if(cartas.size()==1){//1 elemento
	    c = iter.next();
	    return c;	    
	}else if(cartas.size()==2){//2 elementos
	    a = iter.next();
	    b = iter.next();
	    parametro1 = Integer.parseInt( a.getNumero() );//num a
	    parametro2 = Integer.parseInt( b.getNumero() );//num b
	    if(parametro1 < parametro2){
		c = b;
	    }else{
		c = a;
	    }
	    return c;
	}
	// si son más de 2 elementos...
	// sabemos que al menos tiene 3 elementos!
	iter.hasNext();iter.hasNext();
	a = iter.next();//adelantamos 1 nodo...
	b = iter.next();
	parametro1 = Integer.parseInt( a.getNumero() );//num a
	parametro2 = Integer.parseInt( b.getNumero() );//num b
	if(parametro1 < parametro2){
	    c = b;
	    mayor = parametro2;
	}else{
	    c = a;
	    mayor = parametro1;
	}
	// mayor -> c (carta)
	//vamos con el nodo3 *
	while( iter.hasNext() ){
	    a = iter.next();
	    parametro1 = Integer.parseInt( a.getNumero() );//num a
	    if(mayor>parametro1){
		//se quedan igual
		// mayor = mayor y c = c
	    }else{
		mayor = parametro1;
		c = a;
	    }
	}
	return c;
    }
    //método para cual es el truco ganador
    /*
     * triunfo -> carta de triunfo
     * lider -> carta lider
     * mesa -> lista con las cartas de los participantes
     */
    public Carta quienGana(Carta triunfo,Carta lider,Lista<Carta> mesa){
	Lista<Carta> ganadoras = new Lista<Carta>();//lista Aux
	IteradorLista<Carta> iter = mesa.iteradorLista();
	IteradorLista<Carta> iter2 = ganadoras.iteradorLista();
	Carta c = new Carta();
	
	//vemos si hay cartas Mago
	iter = mesa.iteradorLista();	
	while(iter.hasNext()){
	    c = iter.next();
	    if( c.getPalo().equals("mago") ){
		return c;
	    }
	}
	//vemos si hay cartas semejantes a la del triunfo
        iter = mesa.iteradorLista();
	while(iter.hasNext()){
	    c = iter.next();
	    if( c.getPalo().equals(triunfo.getPalo()) ){
		ganadoras.add(c);
	    }
	}//vemos puntaje más alto :
	iter2 = ganadoras.iteradorLista();
	if( !ganadoras.isEmpty() ){
	    if(ganadoras.size() == 1){
		return iter2.next();
	    }else{
		c = maxPuntaje(ganadoras);
		return c;
	    }  
	}	
	//vemos si hay cartas semejantes al líder 
	iter = mesa.iteradorLista();
	while(iter.hasNext()){
	    c = iter.next();
	    if( c.getPalo().equals(lider.getPalo()) ){
		return c;
	    }
	}//vemos puntaje más alto :
	iter2 = ganadoras.iteradorLista();
	if( !ganadoras.isEmpty() ){
	    if(ganadoras.size() == 1){
		return iter2.next();
	    }else{
		c = maxPuntaje(ganadoras);
		return c;
	    }  
	}
	//vemos si hay cartas bufón
	iter = mesa.iteradorLista();
	while(iter.hasNext()){
	    c = iter.next();
	    if( c.getPalo().equals("bufon") ){
		return c;
	    }
	}
	return c;
    }
    
}
