/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Progression;

/**
 *
 * @author MIPC
 */
public class TestProgression {
    public static void main(String[] args){
        Progression prog;
        //Test: Progresion Aritmetica
        System.out.print("Progresion Aritmetica con incremento por defecto: ");
        prog = new ArithmeticProgression();
        prog.printProgression(10);
        System.out.print("Progresion Aritmetica incrementanto de 5: ");
        prog = new ArithmeticProgression(5);
        prog.printProgression(10);
        System.out.print("Progresion Aritmetica incrementando de 5 y empezando en 2: ");
        prog = new ArithmeticProgression(5, 2);
        prog.printProgression(10);
        //Test: Progresion Geometrica
        System.out.print("Progresion Geometrica con base por defecto: ");
        prog = new GeometricProgression();
        prog.printProgression(10);
        System.out.print("Progresion Geometric con base 3: ");
        prog = new GeometricProgression(3);
        prog.printProgression(10);
        //Test: Progresion Fibonacci
        System.out.println("Progresion de Fibonacci comenzando con valores por defecto: ");
        prog = new FibonacciProgression();
        prog.printProgression(10);
        System.out.print("Progresion Fibonacci comenzando con los valores iniciales 4 y 6: ");
        prog = new FibonacciProgression(4,6);
        prog.printProgression(8);
    }
}
