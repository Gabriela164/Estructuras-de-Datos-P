package Clases;

import java.util.Scanner;
import java.util.Random;
import java.util.Iterator;

public class Juego {
            
    public static void main(String[] args ){    

	Scanner lector = new Scanner(System.in);
	
	Baraja wizard = new Baraja();
	Lista <Carta> mazo = new Lista<Carta>();   
	Lista <Carta> mazo1 = new Lista<Carta>();   //Listas que contienen las cartas de los jugadores para cada ronda 
	Lista <Carta> mazo2 = new Lista<Carta>();
	Lista <Carta> mazo3 = new Lista<Carta>();
	Lista <Carta> mazo4 = new Lista<Carta>();
	Lista <Carta> mazo5 = new Lista<Carta>();
	Lista <Carta> mazo6 = new Lista<Carta>();

	Carta baza = new Carta();//Carta Aux Baza
	Carta newBaza = new Carta();
	Carta aux = new Carta();

	Participante jugAux;
	Participante jugAux2;
	Participante jugAux3;
	
	Participante jugador1 = new Participante("Jugador 1", 0, 0, 0, 0, mazo1, baza); 
	Participante jugador2 = new Participante("Jugador 2", 0, 0, 0, 0, mazo2, baza);
	Participante jugador3 = new Participante("Jugador 3", 0, 0, 0, 0, mazo3, baza);
	Participante jugador4 = new Participante("Jugador 4", 0, 0, 0, 0, mazo4, baza);
	Participante jugador5 = new Participante("Jugador 5", 0, 0, 0, 0, mazo5, baza);
	Participante jugador6 = new Participante("Jugador 6", 0, 0, 0, 0, mazo6, baza);

	
	Lista <Participante> jugadores = new Lista<Participante>();   

	int rondas = 0;  //numero de rondas
	int numPart = 0; //numero de participantes
	
	//IteradorLista<Participante> iter1 = jugadores.iteradorLista();  //Iterador en lista jugadores
	
	// ITERADORES AUXILIARES...
	IteradorLista<Participante> iter1 = jugadores.iteradorLista();//Iterador1 en lista jugadores
	IteradorLista<Participante> iter2 = jugadores.iteradorLista();//Iterador2 en lista jugadores
	IteradorLista<Participante> iter3 = jugadores.iteradorLista();//Iterador3 en lista jugadores
	System.out.println("");
	System.out.println("           *** Bienvenid@s al juego Wizard ***  ");
	System.out.println("");

	do{
	    System.out.println("¿Cuantos jugadores habra? Ingrese un numero entre 3 y 6");
	    numPart = lector.nextInt();        //Guardamos el numero de participantes que habra en el juego 
	} while (!( numPart>=3 && numPart<=6));    //Mientras el numero de jugadores sea mayor o igual a 3 y menor e igual a 6		

	//Ejecutamos por casos 
	switch(numPart){
	    
	    case 3: // 1er CASO----> 3 jugadores se jugaran 20 rondas
		rondas = 20;
		
		jugadores.add(jugador1);
		jugadores.add(jugador2); // Añadimos jugadores
		jugadores.add(jugador3);
		
		System.out.println("¿List@? ¡Comenzamos el juego!");
		for (int i = 1; i<=rondas;i++){//FOR PRINCIPAL	
			Scanner sc = new Scanner(System.in);
			System.out.println("");	    
		    System.out.println("DAMOS INICIO A LA RONDA "+String.valueOf(i) +"\n"+"*Jugador2* ¡barajee las cartas!");
		   
			String teclab = "";
			do{
				System.out.println("Para esta accion presione la tecla con la letra [b] :D");
				teclab = sc.nextLine();
			}while( !(teclab.equals("b"))); 		    
		    wizard.mezclaBaraja(); //PASO1. BARAJEAMOS
		    System.out.println("¡La baraja ha sido barajeada correctamente!");
		    		    
		    iter1 = jugadores.iteradorLista();//PASO2. REPARTIMOS N CARTAS, N=num.Rondas
		    mazo1 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo1);      //Repartimos al jugador1
		    mazo2 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo2);      //Repartimos al jugador2
		    mazo3 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo3);      //Repartimos al jugador3
		    iter1 = jugadores.iteradorLista();
		    
		    System.out.println("Al ser la ronda "+ String.valueOf (i) +" otorgamos "+ String.valueOf(i) +" carta(s) a cada uno de los jugadores");
		    wizard.eligeTriunfo(); //PASO3. ASIGNAMOS PALO TRIUNFO
		    System.out.println("EL PALO DE TRIUNFO para la ronda "+ String.valueOf(i) +" es --> "+ wizard.getPaloTriunfo().toString() );
		    System.out.println(" ");
		    
		    Lista<Lista> listaMazos = new Lista<Lista>();//Lista de los mazos de los jugadores
		    IteradorLista<Lista> iterMazos = listaMazos.iteradorLista();  //*Iterador en lista mazos
		    listaMazos.add(mazo1);
		    listaMazos.add(mazo2);
		    listaMazos.add(mazo3);

		    iter1 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    //iter1.start();    //Nos posicionamos al principio de la lista de jugadores 
		    int apuestas = -1;
		    int numero = 1;  		    
		    while(iter1.hasNext()){ //PASO4. ASIGNAMOS NUM.BAZAS/APUESTAS
			System.out.println("* Jugador"+ numero +"* Éstas son tus cartas ");
			System.out.println( listaMazos.pop() );
			do{
			    System.out.println(" ¿Cuántas bazas crees ganar? Digite un numero entre 0 y "+ String.valueOf(i));
			    apuestas = lector.nextInt(); //FALTA try-catch 
			} while ( !(apuestas>=0 && apuestas<=i)  );			
			iter1.next().setApuesta(apuestas);
			apuestas = -1;
			numero ++;
			System.out.println(" ");
		    }
		    		    
		    //PASO5. INICIAMOS LOS TRUCOS(cartas apostadas)!!

            for(int trucos=1;trucos<=i;trucos++){ //La cantidad de trucos depende del numero de cartas de cada jugador
             //El jugador inicial sera el de la izq del 2do en la lista, es decir, el primero en la lista		    		    
		    System.out.println("           INICIA TRUCO " + String.valueOf(trucos) +" de la ronda " + String.valueOf(i) );
		    System.out.println("// NOTA: si presiona la tecla [1] jugara la 1er carta del mazo, si presiona la tecla [2] jugara la segunda carta del mazo, etc //");
		    System.out.println(" ");
		    
		    listaMazos.add(mazo1);
		    listaMazos.add(mazo2);  //llenamos nuestra lista de mazos nuevamente
		    listaMazos.add(mazo3);
		    
		    Carta cartaAux = new Carta();
		    int numero2 = 1;
		    int indiceMazo = -1;
		    iter1 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    iter3 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    
		    while(iter1.hasNext()){ //itera el numero de jugadores 
			
			System.out.println("* Jugador"+ numero2 +"* Éstas son tus cartas:");
			System.out.println( listaMazos.pop().toString() );
			numero2++;
			
			System.out.println( "¿Qué carta quieres jugar?"+"\n"+"Por favor, eliga un numero entre 1 y "+String.valueOf(i));
			indiceMazo = lector.nextInt(); //falta TRY-CATCH
			jugAux3 = iter3.next();//JUGAUX3->participante variable, para tener fijo los datos
			
			while( jugAux3.getMazo().index(indiceMazo) == null ){//en caso de que la carta no exista
			    System.out.println( "Error,esa carta no existe,¿Qué carta quieres jugar?\n"+"Por favor, eliga un numero entre 1 y "+String.valueOf(i));
			    indiceMazo = lector.nextInt(); //falta TRY-CATCH
			    if( jugAux3.getMazo().index(indiceMazo) != null ){
				break;
			    }
			}
			
			newBaza = jugAux3.getMazo().index(indiceMazo);
			mazo = jugAux3.getMazo();
			mazo.delete(newBaza);

			//cuando HAY cartas semejantes al lider
			if( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == -1 ){
			    System.out.println("Error, tienes al menos 1 carta semejante al palo Líder");
			    while( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == -1 ){
				System.out.println("Elige correctamente tus bazas");
				indiceMazo = lector.nextInt(); //falta TRY-CATCH
				if(jugAux3.getMazo().index(indiceMazo) == null){
				    newBaza = new Carta();
				}else{
				    newBaza = jugAux3.getMazo().index(indiceMazo);
				}
			    }//aquí a fuerza la carta es semejante a líder			    
			    newBaza = jugAux3.getTruco();
			    mazo = jugAux3.getMazo();
			    mazo.delete(newBaza);
			    iter1.next().setTrucoMazo(newBaza,mazo); //asignamos
			    
			//cuando se ASIGNA el truco y el palo lider
			}else if( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == 2 ){
			    wizard.asignaLider(newBaza);//asignamos lider a nuestra baraja
			    iter1.next().AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard);
			    			    
			//cuando hay palo lider pero NO HAY cartas semejantes en el mazo
			}else{
			    iter1.next().AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard);			    
			    
			}
			
		    } //termina while participantes 
			
            System.out.println("");
            System.out.println("LA MESA ACTUAL ES ");
            System.out.println(jugador1.getTruco().toString());
            System.out.println(jugador2.getTruco().toString());
            System.out.println(jugador3.getTruco().toString());
            System.out.println("Con palo lider--> "+wizard.getPaloLider() );
            System.out.println("Con palo triunfo--> "+wizard.getPaloTriunfo());
			

		    //PASO6.DETERMINAMOS LA CARTA GANADORA
		    iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores
            Lista<Carta> mesa = new Lista();
		    while( iter2.hasNext() ){
			mesa.add( iter2.next().getTruco() );//Llenamos nuestra mesa con los trucos de los jug.
		    }
		    Carta ganadora = wizard.quienGana(wizard.getPaloTriunfo(),wizard.getPaloLider(),mesa);//vemos la carta ganadora.
		   
            int num = mesa.indexOf(ganadora);
            System.out.println("La carta ganadora del truco "+ String.valueOf(trucos)+" de la ronda "+ String.valueOf(i)+ " es "+ ganadora.toString());
            System.out.println("Y el GANADOR del truco es el jugador "+String.valueOf(num+1));
			iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores..
		    iter3 = jugadores.iteradorLista();
		    //PASO7.DETERMINAMOS DE QUIÉN ES LA CARTA GANADORA
            Carta nueva = new Carta();
			wizard.setPaloLider(nueva);
            
			 
			Participante ganador = jugadores.index(num+1);
            ganador.setTrucosGanados(1);
			
			System.out.println("MARCADOR ACTUAL de la ronda "+String.valueOf(i));
			System.out.println("jugador 1 aposto ganar "+String.valueOf(jugador1.getApuesta()) +" baza y gano " +String.valueOf(jugador1.getTrucosGanados())  );
			System.out.println("jugador 2 aposto ganar "+String.valueOf(jugador1.getApuesta()) +" baza y gano " +String.valueOf(jugador2.getTrucosGanados())  );
			System.out.println("jugador 3 aposto ganar "+String.valueOf(jugador1.getApuesta()) +" baza y gano " +String.valueOf(jugador3.getTrucosGanados())  );
		   }//Termina for truco-cartas
		   

		   Carta nuevaa = new Carta();
		   wizard.setPaloTriunfo(nuevaa);


		}//Termina for principal 
         break;


	    case 4: // 4 jugadores se jugaran 15 rondas 
		rondas = 15;
		
		jugadores.add(jugador1);
		jugadores.add(jugador2); // Añadimos jugadores
		jugadores.add(jugador3);
		jugadores.add(jugador4);

		System.out.println("¿List@? ¡Comenzamos el juego!");
		for (int i = 1; i<=rondas;i++){//FOR PRINCIPAL	
			Scanner sc = new Scanner(System.in);
			System.out.println("");	    
		    System.out.println("DAMOS INICIO A LA RONDA "+String.valueOf(i) +"\n"+"*Jugador2* ¡barajee las cartas!");
		   
			String teclab = "";
			do{
				System.out.println("Para esta accion presione la tecla con la letra [b] :D");
				teclab = sc.nextLine();
			}while( !(teclab.equals("b"))); 		    
		    wizard.mezclaBaraja(); //PASO1. BARAJEAMOS
		    System.out.println("¡La baraja ha sido barajeada correctamente!");
		    		    
		    iter1 = jugadores.iteradorLista();//PASO2. REPARTIMOS N CARTAS, N=num.Rondas
		    mazo1 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo1);      //Repartimos al jugador1
		    mazo2 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo2);      //Repartimos al jugador2
		    mazo3 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo3);      //Repartimos al jugador3
			mazo4 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo4);      //Repartimos al jugador4
		    iter1 = jugadores.iteradorLista();
		    
		    System.out.println("Al ser la ronda "+ String.valueOf (i) +" otorgamos "+ String.valueOf(i) +" carta(s) a cada uno de los jugadores");
		    wizard.eligeTriunfo(); //PASO3. ASIGNAMOS PALO TRIUNFO
		    System.out.println("EL PALO DE TRIUNFO para la ronda "+ String.valueOf(i) +" es --> "+ wizard.getPaloTriunfo().toString() );
		    System.out.println(" ");
		    
		    Lista<Lista> listaMazos = new Lista<Lista>();//Lista de los mazos de los jugadores
		    IteradorLista<Lista> iterMazos = listaMazos.iteradorLista();  //*Iterador en lista mazos
		    listaMazos.add(mazo1);
		    listaMazos.add(mazo2);
		    listaMazos.add(mazo3);
			listaMazos.add(mazo4);

		    iter1 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    //iter1.start();    //Nos posicionamos al principio de la lista de jugadores 
		    int apuestas = -1;
		    int numero = 1;  		    
		    while(iter1.hasNext()){ //PASO4. ASIGNAMOS NUM.BAZAS/APUESTAS
			System.out.println("* Jugador"+ numero +"* Éstas son tus cartas ");
			System.out.println( listaMazos.pop() );
			do{
			    System.out.println(" ¿Cuántas bazas crees ganar? Digite un numero entre 0 y "+ String.valueOf(i));
			    apuestas = lector.nextInt(); //FALTA try-catch 
			} while ( !(apuestas>=0 && apuestas<=i)  );			
			iter1.next().setApuesta(apuestas);
			apuestas = -1;
			numero ++;
			System.out.println(" ");
		    }
		    		    
		    //PASO5. INICIAMOS LOS TRUCOS(cartas apostadas)!!
            for(int trucos=1;trucos<=i;trucos++){
             //El jugador inicial sera el de la izq del 2do en la lista, es decir, el primero en la lista		    		    
		    System.out.println("           INICIA TRUCO " + String.valueOf(trucos) +" de la ronda " + String.valueOf(i) );
		    System.out.println("// NOTA: si presiona la tecla [1] jugara la 1er carta del mazo, si presiona la tecla [2] jugara la segunda carta del mazo, etc //");
		    System.out.println(" ");
		    
		    listaMazos.add(mazo1);
		    listaMazos.add(mazo2);  //llenamos nuestra lista de mazos nuevamente
		    listaMazos.add(mazo3);
			listaMazos.add(mazo4);

		    Carta cartaAux = new Carta();
		    int numero2 = 1;
		    int indiceMazo = -1;
		    iter1 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    iter3 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    
		    while(iter1.hasNext()){ //itera el numero de jugadores 
			
			System.out.println("* Jugador"+ numero2 +"* Éstas son tus cartas:");
			System.out.println( listaMazos.pop().toString() );
			numero2++;
			
			System.out.println( "¿Qué carta quieres jugar?"+"\n"+"Por favor, eliga un numero entre 1 y "+String.valueOf(i));
			indiceMazo = lector.nextInt(); //falta TRY-CATCH
			jugAux3 = iter3.next();//JUGAUX3->participante variable, para tener fijo los datos
			
			while( jugAux3.getMazo().index(indiceMazo) == null ){//en caso de que la carta no exista
			    System.out.println( "Error,esa carta no existe,¿Qué carta quieres jugar?\n"+"Por favor, eliga un numero entre 1 y "+String.valueOf(i));
			    indiceMazo = lector.nextInt(); //falta TRY-CATCH
			    if( jugAux3.getMazo().index(indiceMazo) != null ){
				break;
			    }
			}//la carta sí existe porque existe awbo
			
			newBaza = jugAux3.getMazo().index(indiceMazo);
			mazo = jugAux3.getMazo();
			mazo.delete(newBaza);

			//cuando HAY cartas semejantes al lider
			if( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == -1 ){
			    System.out.println("Error, tienes al menos 1 carta semejante al palo Líder");
			    while( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == -1 ){
				System.out.println("Elige correctamente tus bazas");
				indiceMazo = lector.nextInt(); //falta TRY-CATCH
				if(jugAux3.getMazo().index(indiceMazo) == null){
				    newBaza = new Carta();
				}else{
				    newBaza = jugAux3.getMazo().index(indiceMazo);
				}
			    }//aquí a fuerza la carta es semejante a líder			    
			    newBaza = jugAux3.getTruco();
			    mazo = jugAux3.getMazo();
			    mazo.delete(newBaza);
			    iter1.next().setTrucoMazo(newBaza,mazo); //asignamos
			    
			//cuando se ASIGNA el truco y el palo lider
			}else if( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == 2 ){
			    wizard.asignaLider(newBaza);//asignamos lider a nuestra baraja
			    iter1.next().AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard);
			    			    
			//cuando hay palo lider pero NO HAY cartas semejantes en el mazo
			}else{
			    iter1.next().AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard);			    
			    
			}
			
		    } //termina while participantes 
			
            System.out.println("");
            System.out.println("LA MESA ACTUAL ES ");
            System.out.println(jugador1.getTruco().toString());
            System.out.println(jugador2.getTruco().toString());
            System.out.println(jugador3.getTruco().toString());
			System.out.println(jugador4.getTruco().toString());

            System.out.println("Con palo lider--> "+wizard.getPaloLider() );
            System.out.println("Con palo triunfo--> "+wizard.getPaloTriunfo());
			

		    //PASO6.DETERMINAMOS LA CARTA GANADORA
		    iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores
            Lista<Carta> mesa = new Lista();
		    while( iter2.hasNext() ){
			mesa.add( iter2.next().getTruco() );//Llenamos nuestra mesa con los trucos de los jug.
		    }
		    Carta ganadora = wizard.quienGana(wizard.getPaloTriunfo(),wizard.getPaloLider(),mesa);//vemos la carta ganadora.
		   
            int num = mesa.indexOf(ganadora);
            System.out.println("La carta ganadora del truco "+ String.valueOf(trucos)+" de la ronda "+ String.valueOf(i)+ " es "+ ganadora.toString());
            System.out.println("Y el GANADOR del truco es el jugador "+String.valueOf(num+1));
			iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores..
		    iter3 = jugadores.iteradorLista();
		    //PASO7.DETERMINAMOS DE QUIÉN ES LA CARTA GANADORA
			
            Carta nueva = new Carta();
			wizard.setPaloLider(nueva);

			Participante ganador = jugadores.index(num+1);
			int cantidad = 1;
			ganador.setTrucosGanados(cantidad);
			
			System.out.println("MARCADOR ACTUAL de la ronda "+String.valueOf(i));
			System.out.println("jugador 1 aposto ganar "+String.valueOf(jugador1.getApuesta()) +" baza y gano " +String.valueOf(jugador1.getTrucosGanados())  );
			System.out.println("jugador 2 aposto ganar "+String.valueOf(jugador2.getApuesta()) +" baza y gano " +String.valueOf(jugador2.getTrucosGanados())  );
			System.out.println("jugador 3 aposto ganar "+String.valueOf(jugador3.getApuesta()) +" baza y gano " +String.valueOf(jugador3.getTrucosGanados())  );
			System.out.println("jugador 4 aposto ganar "+String.valueOf(jugador4.getApuesta()) +" baza y gano " +String.valueOf(jugador4.getTrucosGanados())  );

		   }//Termina for truco-cartas

		   Carta nuevaa = new Carta();
		   wizard.setPaloTriunfo(nuevaa);


		}//Termina for principal 
		break;
	    case 5: // 5 jugadores se jugaran 12 rondas 
		rondas = 12;
		
		jugadores.add(jugador1);
		jugadores.add(jugador2); // Añadimos jugadores
		jugadores.add(jugador3);
		jugadores.add(jugador4);
		jugadores.add(jugador5);

		System.out.println("¿List@? ¡Comenzamos el juego!");
		for (int i = 1; i<=rondas;i++){//FOR PRINCIPAL	
			Scanner sc = new Scanner(System.in);
			System.out.println("");	    
		    System.out.println("DAMOS INICIO A LA RONDA "+String.valueOf(i) +"\n"+"*Jugador2* ¡barajee las cartas!");
		   
			String teclab = "";
			do{
				System.out.println("Para esta accion presione la tecla con la letra [b] :D");
				teclab = sc.nextLine();
			}while( !(teclab.equals("b"))); 		    
		    wizard.mezclaBaraja(); //PASO1. BARAJEAMOS
		    System.out.println("¡La baraja ha sido barajeada correctamente!");
		    		    
		    iter1 = jugadores.iteradorLista();//PASO2. REPARTIMOS N CARTAS, N=num.Rondas
		    mazo1 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo1);      //Repartimos al jugador1
		    mazo2 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo2);      //Repartimos al jugador2
		    mazo3 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo3);      //Repartimos al jugador3
			mazo4 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo4);      //Repartimos al jugador4
			mazo5 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo5);       //Repartimos al jugador5
		    iter1 = jugadores.iteradorLista();
		    
		    System.out.println("Al ser la ronda "+ String.valueOf (i) +" otorgamos "+ String.valueOf(i) +" carta(s) a cada uno de los jugadores");
		    wizard.eligeTriunfo(); //PASO3. ASIGNAMOS PALO TRIUNFO
		    System.out.println("EL PALO DE TRIUNFO para la ronda "+ String.valueOf(i) +" es --> "+ wizard.getPaloTriunfo().toString() );
		    System.out.println(" ");
		    
		    Lista<Lista> listaMazos = new Lista<Lista>();//Lista de los mazos de los jugadores
		    IteradorLista<Lista> iterMazos = listaMazos.iteradorLista();  //*Iterador en lista mazos
		    listaMazos.add(mazo1);
		    listaMazos.add(mazo2);
		    listaMazos.add(mazo3);
			listaMazos.add(mazo4);
			listaMazos.add(mazo5);

		    iter1 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    //iter1.start();    //Nos posicionamos al principio de la lista de jugadores 
		    int apuestas = -1;
		    int numero = 1;  		    
		    while(iter1.hasNext()){ //PASO4. ASIGNAMOS NUM.BAZAS/APUESTAS
			System.out.println("* Jugador"+ numero +"* Éstas son tus cartas ");
			System.out.println( listaMazos.pop() );
			do{
			    System.out.println(" ¿Cuántas bazas crees ganar? Digite un numero entre 0 y "+ String.valueOf(i));
			    apuestas = lector.nextInt(); //FALTA try-catch 
			} while ( !(apuestas>=0 && apuestas<=i)  );			
			iter1.next().setApuesta(apuestas);
			apuestas = -1;
			numero ++;
			System.out.println(" ");
		    }
		    		    
		    //PASO5. INICIAMOS LOS TRUCOS(cartas apostadas)!!
            for(int trucos=1;trucos<=i;trucos++){
             //El jugador inicial sera el de la izq del 2do en la lista, es decir, el primero en la lista		    		    
		    System.out.println("           INICIA TRUCO " + String.valueOf(trucos) +" de la ronda " + String.valueOf(i) );
		    System.out.println("// NOTA: si presiona la tecla [1] jugara la 1er carta del mazo, si presiona la tecla [2] jugara la segunda carta del mazo, etc //");
		    System.out.println(" ");
		    
		    listaMazos.add(mazo1);
		    listaMazos.add(mazo2);  //llenamos nuestra lista de mazos nuevamente
		    listaMazos.add(mazo3);
			listaMazos.add(mazo4);
			listaMazos.add(mazo5);

		    Carta cartaAux = new Carta();
		    int numero2 = 1;
		    int indiceMazo = -1;
		    iter1 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    iter3 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    
		    while(iter1.hasNext()){ //itera el numero de jugadores 
			
			System.out.println("* Jugador"+ numero2 +"* Éstas son tus cartas:");
			System.out.println( listaMazos.pop().toString() );
			numero2++;
			
			System.out.println( "¿Qué carta quieres jugar?"+"\n"+"Por favor, eliga un numero entre 1 y "+String.valueOf(i));
			indiceMazo = lector.nextInt(); //falta TRY-CATCH
			jugAux3 = iter3.next();//JUGAUX3->participante variable, para tener fijo los datos
			
			while( jugAux3.getMazo().index(indiceMazo) == null ){//en caso de que la carta no exista
			    System.out.println( "Error,esa carta no existe,¿Qué carta quieres jugar?\n"+"Por favor, eliga un numero entre 1 y "+String.valueOf(i));
			    indiceMazo = lector.nextInt(); //falta TRY-CATCH
			    if( jugAux3.getMazo().index(indiceMazo) != null ){
				break;
			    }
			}//la carta sí existe porque existe awbo
			
			newBaza = jugAux3.getMazo().index(indiceMazo);
			mazo = jugAux3.getMazo();
			mazo.delete(newBaza);

			//cuando HAY cartas semejantes al lider
			if( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == -1 ){
			    System.out.println("Error, tienes al menos 1 carta semejante al palo Líder");
			    while( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == -1 ){
				System.out.println("Elige correctamente tus bazas");
				indiceMazo = lector.nextInt(); //falta TRY-CATCH
				if(jugAux3.getMazo().index(indiceMazo) == null){
				    newBaza = new Carta();
				}else{
				    newBaza = jugAux3.getMazo().index(indiceMazo);
				}
			    }//aquí a fuerza la carta es semejante a líder			    
			    newBaza = jugAux3.getTruco();
			    mazo = jugAux3.getMazo();
			    mazo.delete(newBaza);
			    iter1.next().setTrucoMazo(newBaza,mazo); //asignamos
			    
			//cuando se ASIGNA el truco y el palo lider
			}else if( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == 2 ){
			    wizard.asignaLider(newBaza);//asignamos lider a nuestra baraja
			    iter1.next().AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard);
			    			    
			//cuando hay palo lider pero NO HAY cartas semejantes en el mazo
			}else{
			    iter1.next().AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard);			    
			    
			}
			
		    } //termina while participantes 
			
            System.out.println("");
            System.out.println("LA MESA ACTUAL ES ");
            System.out.println(jugador1.getTruco().toString());
            System.out.println(jugador2.getTruco().toString());
            System.out.println(jugador3.getTruco().toString());
			System.out.println(jugador4.getTruco().toString());
			System.out.println(jugador5.getTruco().toString());

            System.out.println("Con palo lider--> "+wizard.getPaloLider() );
            System.out.println("Y palo triunfo--> "+wizard.getPaloTriunfo());
			

		    //PASO6.DETERMINAMOS LA CARTA GANADORA
		    iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores
            Lista<Carta> mesa = new Lista();
		    while( iter2.hasNext() ){
			mesa.add( iter2.next().getTruco() );//Llenamos nuestra mesa con los trucos de los jug.
		    }
		    Carta ganadora = wizard.quienGana(wizard.getPaloTriunfo(),wizard.getPaloLider(),mesa);//vemos la carta ganadora.
		   
            int num = mesa.indexOf(ganadora);
            System.out.println("La carta ganadora del truco "+ String.valueOf(trucos)+" de la ronda "+ String.valueOf(i)+ " es "+ ganadora.toString());
            System.out.println("Y el GANADOR del truco es el jugador "+String.valueOf(num+1));
			iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores..
		    iter3 = jugadores.iteradorLista();
		    //PASO7.DETERMINAMOS DE QUIÉN ES LA CARTA GANADORA
			
            Carta nueva = new Carta();
			wizard.setPaloLider(nueva);

			Participante ganador = jugadores.index(num+1);
			int cantidad = 1;
			ganador.setTrucosGanados(cantidad);
			
			System.out.println("MARCADOR ACTUAL de la ronda "+String.valueOf(i));
			System.out.println("jugador 1 aposto ganar "+String.valueOf(jugador1.getApuesta()) +" baza y gano " +String.valueOf(jugador1.getTrucosGanados())  );
			System.out.println("jugador 2 aposto ganar "+String.valueOf(jugador2.getApuesta()) +" baza y gano " +String.valueOf(jugador2.getTrucosGanados())  );
			System.out.println("jugador 3 aposto ganar "+String.valueOf(jugador3.getApuesta()) +" baza y gano " +String.valueOf(jugador3.getTrucosGanados())  );
			System.out.println("jugador 4 aposto ganar "+String.valueOf(jugador4.getApuesta()) +" baza y gano " +String.valueOf(jugador4.getTrucosGanados())  );
			System.out.println("jugador 5 aposto ganar "+String.valueOf(jugador5.getApuesta()) +" baza y gano " +String.valueOf(jugador5.getTrucosGanados())  );


		   }//Termina for truco-cartas

		   Carta nuevaa = new Carta();
		   wizard.setPaloTriunfo(nuevaa);


		}//Termina for principal 
		
		break;
	    case 6: // 6 jugadores se jugaran 10 rondas 
		rondas = 10;
		
		jugadores.add(jugador1);
		jugadores.add(jugador2); // Añadimos jugadores
		jugadores.add(jugador3);
		jugadores.add(jugador4);
		jugadores.add(jugador5);
		jugadores.add(jugador6);

		System.out.println("¿List@? ¡Comenzamos el juego!");
		for (int i = 1; i<=rondas;i++){//FOR PRINCIPAL	
			Scanner sc = new Scanner(System.in);
			System.out.println("");	    
		    System.out.println("DAMOS INICIO A LA RONDA "+String.valueOf(i) +"\n"+"*Jugador2* ¡barajee las cartas!");
		   
			String teclab = "";
			do{
				System.out.println("Para esta accion presione la tecla con la letra [b] :D");
				teclab = sc.nextLine();
			}while( !(teclab.equals("b"))); 		    
		    wizard.mezclaBaraja(); //PASO1. BARAJEAMOS
		    System.out.println("¡La baraja ha sido barajeada correctamente!");
		    		    
		    iter1 = jugadores.iteradorLista();//PASO2. REPARTIMOS N CARTAS, N=num.Rondas
		    mazo1 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo1);      //Repartimos al jugador1
		    mazo2 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo2);      //Repartimos al jugador2
		    mazo3 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo3);      //Repartimos al jugador3
			mazo4 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo4);      //Repartimos al jugador4
			mazo5 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo5);       //Repartimos al jugador5
			mazo6 = wizard.reparteCartas(i);
		    iter1.next().setMazo(mazo6);         //Repartimos al jugador6
		    iter1 = jugadores.iteradorLista();
		    
		    System.out.println("Al ser la ronda "+ String.valueOf (i) +" otorgamos "+ String.valueOf(i) +" carta(s) a cada uno de los jugadores");
		    wizard.eligeTriunfo(); //PASO3. ASIGNAMOS PALO TRIUNFO
		    System.out.println("EL PALO DE TRIUNFO para la ronda "+ String.valueOf(i) +" es --> "+ wizard.getPaloTriunfo().toString() );
		    System.out.println(" ");
		    
		    Lista<Lista> listaMazos = new Lista<Lista>();//Lista de los mazos de los jugadores
		    IteradorLista<Lista> iterMazos = listaMazos.iteradorLista();  //*Iterador en lista mazos
		    listaMazos.add(mazo1);
		    listaMazos.add(mazo2);
		    listaMazos.add(mazo3);
			listaMazos.add(mazo4);
			listaMazos.add(mazo5);
			listaMazos.add(mazo6);

		    iter1 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    //iter1.start();    //Nos posicionamos al principio de la lista de jugadores 
		    int apuestas = -1;
		    int numero = 1;  		    
		    while(iter1.hasNext()){ //PASO4. ASIGNAMOS NUM.BAZAS/APUESTAS
			System.out.println("* Jugador"+ numero +"* Éstas son tus cartas ");
			System.out.println( listaMazos.pop() );
			do{
			    System.out.println(" ¿Cuántas bazas crees ganar? Digite un numero entre 0 y "+ String.valueOf(i));
			    apuestas = lector.nextInt(); //FALTA try-catch 
			} while ( !(apuestas>=0 && apuestas<=i)  );			
			iter1.next().setApuesta(apuestas);
			apuestas = -1;
			numero ++;
			System.out.println(" ");
		    }
		    		    
		    //PASO5. INICIAMOS LOS TRUCOS(cartas apostadas)!!
            for(int trucos=1;trucos<=i;trucos++){
             //El jugador inicial sera el de la izq del 2do en la lista, es decir, el primero en la lista		    		    
		    System.out.println("           INICIA TRUCO " + String.valueOf(trucos) +" de la ronda " + String.valueOf(i) );
		    System.out.println("// NOTA: si presiona la tecla [1] jugara la 1er carta del mazo, si presiona la tecla [2] jugara la segunda carta del mazo, etc //");
		    System.out.println(" ");
		    
		    listaMazos.add(mazo1);
		    listaMazos.add(mazo2);  //llenamos nuestra lista de mazos nuevamente
		    listaMazos.add(mazo3);
			listaMazos.add(mazo4);
			listaMazos.add(mazo5);
			listaMazos.add(mazo6);


		    Carta cartaAux = new Carta();
		    int numero2 = 1;
		    int indiceMazo = -1;
		    iter1 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    iter3 = jugadores.iteradorLista();  //Iterador en lista jugadores
		    
		    while(iter1.hasNext()){ //itera el numero de jugadores 
			
			System.out.println("* Jugador"+ numero2 +"* Éstas son tus cartas:");
			System.out.println( listaMazos.pop().toString() );
			numero2++;
			
			System.out.println( "¿Qué carta quieres jugar?"+"\n"+"Por favor, eliga un numero entre 1 y "+String.valueOf(i));
			indiceMazo = lector.nextInt(); //falta TRY-CATCH
			jugAux3 = iter3.next();//JUGAUX3->participante variable, para tener fijo los datos
			
			while( jugAux3.getMazo().index(indiceMazo) == null ){//en caso de que la carta no exista
			    System.out.println( "Error,esa carta no existe,¿Qué carta quieres jugar?\n"+"Por favor, eliga un numero entre 1 y "+String.valueOf(i));
			    indiceMazo = lector.nextInt(); //falta TRY-CATCH
			    if( jugAux3.getMazo().index(indiceMazo) != null ){
				break;
			    }
			}//la carta sí existe porque existe awbo
			
			newBaza = jugAux3.getMazo().index(indiceMazo);
			mazo = jugAux3.getMazo();
			mazo.delete(newBaza);

			//cuando HAY cartas semejantes al lider
			if( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == -1 ){
			    System.out.println("Error, tienes al menos 1 carta semejante al palo Líder");
			    while( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == -1 ){
				System.out.println("Elige correctamente tus bazas");
				indiceMazo = lector.nextInt(); //falta TRY-CATCH
				if(jugAux3.getMazo().index(indiceMazo) == null){
				    newBaza = new Carta();
				}else{
				    newBaza = jugAux3.getMazo().index(indiceMazo);
				}
			    }//aquí a fuerza la carta es semejante a líder			    
			    newBaza = jugAux3.getTruco();
			    mazo = jugAux3.getMazo();
			    mazo.delete(newBaza);
			    iter1.next().setTrucoMazo(newBaza,mazo); //asignamos
			    
			//cuando se ASIGNA el truco y el palo lider
			}else if( jugAux3.AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard) == 2 ){
			    wizard.asignaLider(newBaza);//asignamos lider a nuestra baraja
			    iter1.next().AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard);
			    			    
			//cuando hay palo lider pero NO HAY cartas semejantes en el mazo
			}else{
			    iter1.next().AsignarTruco(newBaza,mazo,wizard.getPaloLider(),wizard.getPaloTriunfo(),wizard);			    
			    
			}
			
		    } //termina while participantes 
			
            System.out.println("");
            System.out.println("LA MESA ACTUAL ES ");
            System.out.println(jugador1.getTruco().toString());
            System.out.println(jugador2.getTruco().toString());
            System.out.println(jugador3.getTruco().toString());
			System.out.println(jugador4.getTruco().toString());
			System.out.println(jugador5.getTruco().toString());
			System.out.println(jugador6.getTruco().toString());


            System.out.println("Con palo lider--> "+wizard.getPaloLider() );
            System.out.println("Y palo triunfo--> "+wizard.getPaloTriunfo());
			

		    //PASO6.DETERMINAMOS LA CARTA GANADORA
		    iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores
            Lista<Carta> mesa = new Lista();
		    while( iter2.hasNext() ){
			mesa.add( iter2.next().getTruco() );//Llenamos nuestra mesa con los trucos de los jug.
		    }
		    Carta ganadora = wizard.quienGana(wizard.getPaloTriunfo(),wizard.getPaloLider(),mesa);//vemos la carta ganadora.
		   
            int num = mesa.indexOf(ganadora);
            System.out.println("La carta ganadora del truco "+ String.valueOf(trucos)+" de la ronda "+ String.valueOf(i)+ " es "+ ganadora.toString());
            System.out.println("Y el GANADOR del truco es el jugador "+String.valueOf(num+1));
			iter2 = jugadores.iteradorLista();  //Iterador en lista jugadores..
		    iter3 = jugadores.iteradorLista();
		    //PASO7.DETERMINAMOS DE QUIÉN ES LA CARTA GANADORA
			
            Carta nueva = new Carta();
			wizard.setPaloLider(nueva);

			Participante ganador = jugadores.index(num+1);
			int cantidad = 1;
			ganador.setTrucosGanados(cantidad);
			
			System.out.println("MARCADOR ACTUAL de la ronda "+String.valueOf(i));
			System.out.println("jugador 1 aposto ganar "+String.valueOf(jugador1.getApuesta()) +" baza y gano " +String.valueOf(jugador1.getTrucosGanados())  );
			System.out.println("jugador 2 aposto ganar "+String.valueOf(jugador2.getApuesta()) +" baza y gano " +String.valueOf(jugador2.getTrucosGanados())  );
			System.out.println("jugador 3 aposto ganar "+String.valueOf(jugador3.getApuesta()) +" baza y gano " +String.valueOf(jugador3.getTrucosGanados())  );
			System.out.println("jugador 4 aposto ganar "+String.valueOf(jugador4.getApuesta()) +" baza y gano " +String.valueOf(jugador4.getTrucosGanados())  );
			System.out.println("jugador 5 aposto ganar "+String.valueOf(jugador5.getApuesta()) +" baza y gano " +String.valueOf(jugador5.getTrucosGanados())  );
			System.out.println("jugador 6 aposto ganar "+String.valueOf(jugador5.getApuesta()) +" baza y gano " +String.valueOf(jugador6.getTrucosGanados())  );


		   }//Termina for truco-cartas

		   Carta nuevaa = new Carta();
		   wizard.setPaloTriunfo(nuevaa);


		}//Termina for principal 

		break;

          
         

	    }




   
    }

}
