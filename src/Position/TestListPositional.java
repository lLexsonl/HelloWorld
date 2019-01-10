/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Position;

import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author MIPC
 */
public class TestListPositional {
    public static void main(String[] args){
        LinkedPositionalList<String> list = new LinkedPositionalList<>();
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        System.out.print("Prueba de Lista Posicional. Digite 's' para Salir. Digite 'n' para Next Test.\n");
        do{
            System.out.println("Ingrese un String: ");
            String str = sc.next();
            if(!"s".equals(str) && !"n".equals(str)){
                list.addFirst(str); 
            }
            else if("n".equals(str)){
                System.out.println("Siguiente prueba.");
                String strNext;
                do{
                    System.out.println("Ingrese un String: ");
                    strNext = sc.next();
                    if(!"s".equals(strNext))
                        list.addLast(strNext);
                    else
                        salir = true;
                }while(!salir);
            }
            else{
                salir = true;
            }
        }while(!salir);
        Position<String> p = list.addLast("last");
        Position<String> p1 = list.addBefore(p, "before");
        list.set(p1,"seteado");
        Position<String> p2 = list.first();
        list.remove(p2);
        System.out.println(); 
        Iterator<String> it = list.iterator();
        int count = 1;
        while(it.hasNext()){
            System.out.println(String.format("Element %d: %s.\n", count, it.next()));
            count += 1;
        } 
    }
    /**
     * Insertion-Sort of a positional list of integer into nondecreasing order.
     * @param list 
     */
    public static void insertionSort(PositionalList<Integer> list){
        Position<Integer> marker = list.first();
        while(marker != list.last()){
            Position<Integer> pivot = list.after(marker);
            int value = pivot.getElement();
            if(value > marker.getElement())
                marker = pivot;
            else{
                Position<Integer> walk = marker;
                while(walk != list.first() && list.before(walk).getElement() > value)
                    walk = list.before(walk);
                list.remove(pivot);
                list.addBefore(walk, value);
            }
        }
        
    }
}
