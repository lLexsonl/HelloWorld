/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstExercises;

/**
 *
 * @author MIPC
 */
public class Exercises1 {
    public static void main(String[] args){
        long n = 1422, m = 4;
        int i = -490, j = 25;
        String word = "hola, ¿Como estas?";
        int[] arrayInt = {90,54,1,100,30,45,67,23,2,11,87,23,65,102};
        if(isMultiple(n,m))
            System.out.print(String.format("%d: es multiplo de %d", m, n));
        else
            System.out.print(String.format("%d: no es multiplo de %d", m, n));
        System.out.println();
        if(isEven(i))
            System.out.print(String.format("%d: es par", i));
        else
            System.out.print(String.format("%d: no es par", i));
        System.out.println();
        System.out.print(String.format("La suma de todos los enteros positivos menores o iguales a %d es: %d", j,sumPositiveInteger(j)));
        System.out.println();
        System.out.print(String.format("La suma de todos los enteros impares positivos menores o iguales a %d es: %d", j,sumPositiveOddInteger(j)));
        System.out.println();
        System.out.print(String.format("La suma de todos los cuadrados de los enteros positivos menores o iguales a %d es: %d", j,sumSquaresPositiveInteger(j)));
        System.out.println();
        System.out.print(String.format("La cantidad de vocales en la frase => %s <= es: %d", word,volwesString(word)));
        System.out.println();
        System.out.print(String.format("La frase => %s <= sin signos de puntuacion es: %s",word,StringBuilder(word)));
        System.out.println();
        int maxmin[] = maxAndmin(arrayInt);
        System.out.print("El valor minimo del array es:" + maxmin[0] + " y el valor maximo del array es: " + maxmin[1]);
        System.out.println();
    }
    
    public void inputAllTypes(int entero, double doble, long largo,
                                String cadena,float flotante, char caracter){
        System.out.print("Entero: "+ entero + ", " + "Doble: " + doble + "," + "\n" +
                        "Largo: " + largo + ", " + "String: " + cadena + "," + "\n" +
                        "Cadena: " + cadena + ", " + "Flotante: " + flotante + "\n" +
                        "Caracter: " + caracter);
    }
    //Determina si un numero m es multiplo de un numero n.
    public static boolean isMultiple(long n, long m){
        int i = 0;
        long multiple;
        while(true){
            multiple = m*i;
            if(multiple == n){
                return true;
            }
            else if(multiple > n){
                return false;
            }
            i += 1;
        }
    }
    //Determina si un numero es par.
    public static boolean isEven(int i){
        int j = 0;
        if(i >= 0){
            while(true){
                if(j == i)
                    return true;
                if(j > i)
                    return false;
                j += 2;
            } 
        }
        else{
            while(true){
                if(j == i)
                    return true;
                if(j < i)
                    return false;
                j -= 2;
            } 
        }
    }
    public static int sumPositiveInteger(int n){
        int suma = 0;
        for(int i = 1;i <= n;i++){
            suma += i;
        }
        return suma;
    }
    public static int sumPositiveOddInteger(int n){
        int suma = 0;
        for(int i = 1; i <= n; i++){
            if(!isEven(i)){
                suma += i;
            }
        }
        return suma;
    }
    //
    public static int sumSquaresPositiveInteger(int n){
        int suma = 0;
        for(int i = 1;i <= n;i++){
            suma += Math.pow(i,2);//Equivalente: suma += (i*i);
        }
        return suma;
    }
    public static int volwesString(String word){
        int longitud = word.length(), vocales = 0;
        for(int i = 0;i < longitud;i++){
            if(word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u' ||
                    word.charAt(i) == 'A' || word.charAt(i) == 'E' || word.charAt(i) == 'I' || word.charAt(i) == 'O' || word.charAt(i) == 'U')
                vocales += 1;
        }
        return vocales;
    }
    public static String StringBuilder(String word){
        int longitud = word.length();
        String newWord = "";
        char letter;
        for(int i = 0; i < longitud; i++){
            letter = word.charAt(i);
            if(((int)letter > 64 && (int)letter < 91) || ((int) letter > 96 && (int) letter < 123) || ((int) letter == 32))
                newWord += letter;
        }
        return newWord;
    }
    public static int[] maxAndmin(int[] array){
        int longitud = array.length;
        int[] maxmin = new int [2];
        maxmin[0] = array[0];
        maxmin[1] = array[0];
        for(int i = 1;i < longitud;i++){
            if(maxmin[0] >= array[i])
                maxmin[0] = array[i];
            else if(maxmin[1] <= array[i])
                maxmin[1] = array[i];
        }
        return maxmin;
    }
    
    //Insertion Sort: Lo coloco por que no sabia donde implementarlo
    public static void insertionSort(char[] data){
        int n = data.length;
        for(int k = 1; k < n;k++){
            char cur = data[k];
            int j = k;
            while(j > 0 && data[j-1] > cur){
                data[j] = data[j-1];
                j -= 1;
            }
            data[j] = cur;
        }
    }
}
