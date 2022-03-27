package edd.src.Estructuras;
import java.util.Iterator;
import javax.smartcardio.ATR;


public class Practica2 {

    public static void torresHanoi(Integer cantidadDiscos, Pila<Integer> origen, Pila<Integer> auxiliar, Pila<Integer> destino){

        Integer pasos = Numero_elevado(2, cantidadDiscos)-1;
     
        for(int i=1; i<= pasos;i++){
            if(cantidadDiscos %2 ==0){ //Cuando el numero de discos es par
                if(i %3== 1){ //Primer paso
                     //transferir(origen, auxiliar);
                     if(origen.isEmpty()){
                        origen.push(auxiliar.pop());
                     System.out.print("PilarInicial < " +origen.toString()+ "\n");
                     System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                     System.out.print("PilarFinal < "+ destino.toString() + "\n");
                     System.out.print("\n");
                    } else if (auxiliar.isEmpty()){
                        auxiliar.push(origen.pop());
                     System.out.print("PilarInicial < " +origen.toString()+ "\n");
                     System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                     System.out.print("PilarFinal < "+ destino.toString() + "\n");
                     System.out.print("\n");  
                    } else {
                        Integer uno = origen.peek();
                        Integer dos = auxiliar.peek();
                        if(uno < dos){
                            origen.pop();
                            auxiliar.push(uno);
                     System.out.print("PilarInicial < " +origen.toString()+ "\n");
                     System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                     System.out.print("PilarFinal < "+ destino.toString() + "\n");
                     System.out.print("\n"); 
                        } else {
                            auxiliar.pop();
                            origen.push(dos);
                            System.out.print("PilarInicial < " +origen.toString()+ "\n");
                            System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                            System.out.print("PilarFinal < "+ destino.toString() + "\n");
                            System.out.print("\n"); 
                        }
                    }
                } else if (i %3 == 2){ //Segundo paso 
                   //transferir(origen, destino);
                   if(origen.isEmpty()){
                    origen.push(destino.pop());
                    System.out.print("PilarInicial < " +origen.toString()+ "\n");
                    System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                    System.out.print("PilarFinal < "+ destino.toString() + "\n");
                    System.out.print("\n"); 
                } else if (destino.isEmpty()){
                    destino.push(origen.pop());
                    System.out.print("PilarInicial < " +origen.toString()+ "\n");
                    System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                    System.out.print("PilarFinal < "+ destino.toString() + "\n");
                    System.out.print("\n"); 
                } else {
                    Integer uno = origen.peek();
                    Integer dos = destino.peek();
                    if(uno < dos){
                        origen.pop();
                        destino.push(uno);
                    System.out.print("PilarInicial < " +origen.toString()+ "\n");
                    System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                    System.out.print("PilarFinal < "+ destino.toString() + "\n");
                    System.out.print("\n"); 
                    } else {
                        destino.pop();
                        origen.push(dos);
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n"); 
                        
                    }
                  }
                } else { //Tercer paso 
                   // transferir(auxiliar, destino );
                    if(auxiliar.isEmpty()){
                        auxiliar.push(destino.pop());
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n"); 
               
                    } else if (destino.isEmpty()){
                        destino.push(auxiliar.pop());
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n"); 
                    } else {
                        Integer uno = auxiliar.peek();
                        Integer dos = destino.peek();
                        if(uno < dos){
                            auxiliar.pop();
                            destino.push(uno);
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n"); 
                        } else {
                            destino.pop();
                            auxiliar.push(dos);
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n"); 
                        }
                    }
                }
            } else { //CUANDO LA CANTIDAD DE DISCOS IMPAR 
                if(i %3==1 ){  //Primer paso 
                   // transferir(origen, destino);
                    if(origen.isEmpty()){
                        destino.push(origen.pop());
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n");
                    } else if (destino.isEmpty()){
                        destino.push(origen.pop());
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n");
                    } else {
                        Integer uno = origen.peek();
                        Integer dos = destino.peek();
                        if(uno < dos){
                            origen.pop();
                            destino.push(uno);
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n");
                          
                        } else {
                            destino.pop();
                            origen.push(dos);
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n");
                        }
                    }
                } else if ( i %3 == 2){ // Segundo paso
                   //transferir(origen, auxiliar);
                   if(origen.isEmpty()){
                    origen.push(auxiliar.pop());
                    System.out.print("PilarInicial < " +origen.toString()+ "\n");
                    System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                    System.out.print("PilarFinal < "+ destino.toString() + "\n");
                    System.out.print("\n");
                } else if (auxiliar.isEmpty()){
                    auxiliar.push(origen.pop());
                    System.out.print("PilarInicial < " +origen.toString()+ "\n");
                    System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                    System.out.print("PilarFinal < "+ destino.toString() + "\n");
                    System.out.print("\n");
                } else {
                    Integer uno = origen.peek();
                    Integer dos = auxiliar.peek();
                    if(uno < dos){
                        origen.pop();
                        auxiliar.push(uno);
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n");
                    } else {
                        auxiliar.pop();
                        origen.push(dos);
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n");    
                    }
                }
                } else { //Tercer paso 
                  // transferir(destino, auxiliar);
                   if(destino.isEmpty()){
                    destino.push(auxiliar.pop());
                    System.out.print("PilarInicial < " +origen.toString()+ "\n");
                    System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                    System.out.print("PilarFinal < "+ destino.toString() + "\n");
                    System.out.print("\n");
           
                } else if (auxiliar.isEmpty()){
                    auxiliar.push(destino.pop());
                    System.out.print("PilarInicial < " +origen.toString()+ "\n");
                    System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                    System.out.print("PilarFinal < "+ destino.toString() + "\n");
                    System.out.print("\n");
                  
                } else {
                    Integer uno = destino.peek();
                    Integer dos = auxiliar.peek();
                    if(uno < dos){
                        destino.pop();
                        auxiliar.push(uno);
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n");
                      
                    } else {
                        auxiliar.pop();
                        destino.push(dos);
                        System.out.print("PilarInicial < " +origen.toString()+ "\n");
                        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
                        System.out.print("PilarFinal < "+ destino.toString() + "\n");
                        System.out.print("\n");
                       
                    }
                }

                }
            }
        }  
        
    }

    /*
    * Metodo que eleva un entero x a la potencia z 
    *
    */
      public static int Numero_elevado(int x, int z) {
      int resultado = 1;
       for (int i = 1; i <= z; i++) {
           resultado = resultado * x;
       }
       return resultado;
      }



    public static void binarioColas(int N){

    }

    public static void main(String[] args) {

       //tests TORRES DE HANOI
        Pila <Integer> origen = new Pila<Integer>();
        Pila <Integer> auxiliar = new Pila<Integer>();
        Pila <Integer> destino = new Pila<Integer>();

        origen.push(5);
        origen.push(4);
        origen.push(3);
        origen.push(2);
        origen.push(1);
        //Imprime en pantalla los pilares iniciales
        System.out.println("***** Resolviendo Torres de Hanoi con N=5: ******");
        System.out.print("PilarInicial < " +origen.toString()+ "\n");
        System.out.print("PilarAux < " + auxiliar.toString() + "\n");
        System.out.print("PilarFinal < "+ destino.toString() + "\n");
        System.out.print("\n");
        torresHanoi(5, origen, auxiliar, destino);

        

        //////////////////////////////////////////////////////
        //Tests to String en Clase Cola y Clase Pila
        Pila<Integer> primero = new Pila<Integer>();
        Cola<Integer> segundo = new Cola<Integer>();

        for (int i = 5; i <= 10; i++) {
            primero.push(i);
        }
        String test2 = "10, 9, 8, 7, 6, 5";
        if (!primero.toString().equals(test2)) {
            System.out.println("1 El toString en clase PILA NO funciona!");
        }

        for (int i = 0; i <= 5; i++) {
            segundo.push(i);
        }
        String test = "0, 1, 2, 3, 4, 5";
        if (!segundo.toString().equals(test)) {
            System.out.println("1 El toString en clase COLA NO funciona!");
        }


         //Tests push en Clase Cola y Clase Pila
         Pila<Integer> uno = new Pila<Integer>();
         Cola<Integer> dos = new Cola<Integer>();

         uno.push(1);
         uno.push(2);
         uno.push(3);
         String prueba = "3, 2, 1";  //Lista tipo pila
         if(!uno.toString().equals(prueba)){
            System.out.println("El metodo PUSH en PILA NO funciona");
         }

        dos.push(1);
        dos.push(2);
        dos.push(3);
        String prueba2= "1, 2, 3"; //Lista tipo cola 
        if(!dos.toString().equals(prueba2)){
            System.out.println("El metodo PUSH en COLA NO funciona");
         }

       
        //Tests CLONE en Clase Cola y Clase Pila
        String clon = uno.clone().toString();
        String clon2 = segundo.clone().toString();
        if(!uno.toString().equals(clon)){
            System.out.println("El metodo clon en clase PILA NO funciona");
        } 
        if(!segundo.toString().equals(clon2)){
            System.out.println("El metodo clon en clase COLA NO funciona");
        }

        




   
    }
        


     


}
