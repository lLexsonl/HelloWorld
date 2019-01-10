/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstExercises;
import java.util.Scanner;
/**
 *
 * @author MIPC
 */
public class inputOutput {
    public static void main(String[] args){
        ritmoCardiaco();
        validarEntero();
    }
    public static void ritmoCardiaco(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your age in years: ");
        double age = input.nextDouble();
        System.out.print("Enter your maximum heart rate: ");
        double rate = input.nextDouble();
        double fb = (rate - age) * 0.65;
        System.out.print("Your ideal fat-burning heart rate is " + fb);
    }
    public static void validarEntero(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter an integer: ");
        while(!input.hasNextInt()){
            input.nextLine();
            System.out.print("Invalid integer; Please enter an integer: ");
        }
        int i = input.nextInt();
    }
}
