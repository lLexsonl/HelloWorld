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
public class loops {
    //Use loops
    //While: encontrar un elemento en un array => retorna el indice del elemento. Si este está.
    public int encontrarWhile(int[] data,int target){
        int j = 0;
        while((j<data.length) && (data[j] != target)){
            j++;
        }
        return j;
    }
    //do while:
    /*public void inputString(){
        String input;
        do{
            input = "Hi, do while";//Queda un for Infinito OJO por eso queda comentario para evitar errores.
        }while(input.length() > 0);
    }*/
    //for:
    //algoritmo que suma los elementos de un array de tipo double Utiliza un for tradicional.
    public static double sum(double[] data){
        double total = 0;
        for(int j = 0;j < data.length;j++)
            total += data[j];
        return total;
    }
    //algoritmo que suma los elementos de un array de tipo double Utiliza un for-each.
    public static double sum_each(double[] data){
        double total = 0;
        for(double val : data)
            total += val;
        return total;
    }
    //algoritmo que encuentra el valor maximo dentro de un array.
    public static double max(double[] data){
        double currentMax = data[0];
        for(int j = 1; j < data.length;j++)
            if(data[j] > currentMax)
                currentMax = data[j];
        return currentMax;
    }
    //forma Incorrecta de escalar un valor de una array.
    public static void scaleBad(double[] data, double factor){
        for(double val : data)
         val *= val;
    }
    //forma correcta de escalar un valor de un array.
    public static void scaleGood(double[] data, double factor){
        for(int j = 0; j < data.length;j++){
            data[j] *= factor;
        }
    }
    public static void imprimirArray(double[] data){
        for(int i = 0; i<data.length;i++)
            System.out.print(data[i] + ", ");
    }
}
