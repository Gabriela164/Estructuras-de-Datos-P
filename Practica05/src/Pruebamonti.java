package Clases;

import java.util.Arrays;
import java.util.Comparator;

public class Pruebamonti{

    public static void main(String[] args) {
        Pokemon poke1 = new Pokemon("a", "Planta", 1, 110);
        Pokemon poke2 = new Pokemon("b", "Fuego", 2, 100);
        Pokemon poke3 = new Pokemon("c", "Electrico", 3, 100);
        Pokemon poke4 = new Pokemon("d", "Agua", 4, 100);
        Pokemon poke5 = new Pokemon("e", "Volador", 5, 100);
        
        MonticuloMinimo<Pokemon> monticulo = new MonticuloMinimo<Pokemon>();
        // Agregamos elementos
        monticulo.add(poke1);
        monticulo.add(poke2);
        monticulo.add(poke3);
        monticulo.add(poke4);
        monticulo.add(poke5);
    
        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        // Eliminamos el elemento con el mínimo valor
        monticulo.delete();
        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        // Eliminamos el elemento con el mínimo valor
        monticulo.delete();
        // Mostramos el monticulo
        System.out.println(monticulo);
        System.out.println(monticulo.size());
        System.out.println("########");
        System.out.println(monticulo.contains(poke5));
        
        monticulo.empty();
        System.out.println("########");

        System.out.println(monticulo);
        System.out.println(monticulo.size());


        //creamos un arreglo de enteros 
        int[] arreglo = {1,2,3,4,5,6,7,8,9,10};
        System.out.println("----------Test practica 05--------------");
        System.out.println( "Probemos el metodo heapSort en Min Heap");
        System.out.println("Antes del heapSort" + Arrays.toString(arreglo));
        monticulo.heapSort(arreglo, arreglo.length);
        System.out.println("Despues del heapSort" + Arrays.toString(arreglo));

        System.out.println("probemos el metodo esMontMin");
        boolean esMontMin = monticulo.esMontMin(arreglo);
        System.out.println("El arreglo "+Arrays.toString(arreglo)+" es monticulo min?"+"\n"+ String.valueOf(esMontMin));
       
        int [] A  = {1,2,3,4};
        boolean esMontMin2 = monticulo.esMontMin(A);
        System.out.println("El arreglo "+Arrays.toString(A)+" es monticulo min? "+"\n"+ String.valueOf(esMontMin2));

        System.out.println("Y probemos el metodo convertir Max heap a Min");
        System.out.println("Arreglo Max heap: "+ Arrays.toString(arreglo));
        monticulo.MontMax_MontMin(arreglo);
        System.out.println("A Min heap queda de la manera: "+ Arrays.toString(arreglo));

        System.out.println("Probemos el metodo convertir de min heap a max heap");
        MonticuloMaximo<Pokemon> monticuloMax = new MonticuloMaximo<Pokemon>();
        int arr[] = {3, 5, 9, 6, 8, 20, 10, 12, 18, 9};
        int n = arr.length;
        System.out.println("Arreglo min heap : " + Arrays.toString(arr));
        monticuloMax.MontMin_MontMax(arr, n);
        System.out.println("Arreglo convertido a max heap : " + Arrays.toString(arr));
       
        System.out.println("Probemos el metodo ¿es Max heap?");
        int arr2[] = { 90, 15, 10, 7, 12, 2, 7, 3 };
        int n2 = arr2.length - 1;
        if(monticuloMax.esMontMax(arr2, 0, n2)){
            System.out.println("El arreglo "+Arrays.toString(arr2)+" SI es monticulo max");
        }else{
            System.out.println("El arreglo "+Arrays.toString(arr2)+" NO es monticulo max");
        }
    }


}
