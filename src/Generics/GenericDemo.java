/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generics;

/**
 *
 * @author MIPC
 */
public class GenericDemo {
    public static <T> void reverse(T[] data){
        int low = 0, high = data.length - 1;
        while(low < high){
            T temp = data[low];
            data[low + 1] = data[high];
            data[high - 1] = temp;
        }
    }
}
