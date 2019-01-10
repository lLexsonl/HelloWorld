/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstExercises;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author MIPC
 */
public class Triangulo {
    public static void main (String[] args){
        
        int n = 10;
        ArrayList<String> list = triangulo(n);
        imprimirList(list);
        trianguloEntero(n);
        
        imprimirPiramide(n);
    }
    //Imprime un triangulo de enteros.
    public static void trianguloEntero(int n){
        //int fila,retro;
        
        for(int k = 0;k<=n;k++){
            for(int blancos = 1; blancos<=n-k; blancos++){
                System.out.print(" ");
            }
            for(int i = 0;i<k;i++){
                System.out.print(i + 1);
            }
            for(int j = k-1;j > 0; j--){
                System.out.print(j);
            }
            System.out.println();
        }
    }
    //Imprime un triangulo de string que tambien forma un triangulo.
    public static void imprimirList(ArrayList<String> list){
        Iterator<String> indx = list.iterator();
        while(indx.hasNext()){
            String elemento = indx.next();
            System.out.print(elemento + "\n");
        }
    }
    //Añade a la lista cada fila de string que forma el triangulo.
    public static ArrayList<String> triangulo(int n){
        ArrayList<String> list = new ArrayList<> ();
        String fila = "";String retro = "";
        for(int i = 0;i<n;i++){
            fila += String.valueOf(i + 1) + " ";
            //list.add(fila);
            //System.out.print("fila" + i + ": " + fila);
            for(int j = i;j > 0; j--){
                retro += j + " ";
                //System.out.print(j +" ");
            }
            list.add(fila + retro);
            retro = "";
            //System.out.println();
        }
        return list;
    }
    
    public static void imprimirListEntero(ArrayList<Integer> list){
        Iterator<Integer> indx = list.iterator();
        while(indx.hasNext()){
            int elemento = indx.next();
            System.out.print(elemento + " ");
        }
    }
    
    public static void imprimirPiramide(int n){
        Integer [][] piramide = new Integer [n][2*n - 1];
        
        for (int indx = 0;indx < piramide.length;indx++) {
            for(int indy = 0;indy < piramide[indx].length;indy++){
               piramide[indx][indy] = 0; 
            }
        }
        
        for (int indx = 0;indx < piramide.length;indx++) {
            for(int i = 0;i<=indx;i++){
                piramide[indx][(n-indx)+(-1+i)]=(i + 1);
            }
            for(int j = indx;j > 0; j--){
                piramide[indx][(indx-j)+n] = (j);
            }
        }
        
        //for (int indx = 0;indx < piramide.length;indx++) {
            /*for(int indy = 0;indy < piramide[indx].length;indy++){
            
            //System.out.print("[" + indx + ", "+ indy + "]");
            //System.out.print(piramide[indx][indy]);
            }*/
        //}
       
        for (int indx = 0;indx < piramide.length;indx++) {
            for(int indy = 0;indy < piramide[indx].length;indy++){
                System.out.print(piramide[indx][indy]);
            }
            System.out.println();
        }
    }
    
}
