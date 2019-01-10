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
public class CouterDemo {
    
    public enum Day{LUNES,MARTES,MIERCOLES,JUEVES,VIERNES}
    
    public static void main (String[] args){
        Counter c;
        c = new Counter();
        c.increment();
        c.incremente(3);
        int temp = c.getCount();
        c.reset();
        Counter d = new Counter(5);
       d.increment();
       Counter e = d;
       temp = e.getCount();
       e.incremente(2);
       
       //pruebas loops
       double[] num1 = {1.0,2.2,7.9,8,4.3,6.3};
       double[] num2 = {12.3,9.9,7.9,8,4.3,6.3};
       double factor = 2.0;
       loops.imprimirArray(num1);
       loops.scaleBad(num1, factor);
       System.out.println();
       loops.imprimirArray(num1);
       loops.scaleGood(num1, factor);
       System.out.println();
       loops.imprimirArray(num1);
    }
}
