/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

import Queue.LinkedQueue;
import Queue.Queue;
import java.util.Comparator;

/**
 * Quick-sort for a sequence S implemented.
 * @author MIPC
 */
public class QuickSort {
    //<editor-fold defaultstate="collapsed" desc="QuickSort Queue">
    /**
     * Quick-sort for a sequence S implemented as a queue.
     * @param <K>
     * @param S
     * @param comp 
     */
    public static <K> void quickSortQueue(Queue<K> S, Comparator<K> comp){
        int n = S.size();
        if(n < 2)return;                                //queue is trivial sorted
        //divide
        K pivot = S.first();                            //using first as arbitrary pivot
        Queue<K> L = new LinkedQueue<>();
        Queue<K> E = new LinkedQueue<>();
        Queue<K> G = new LinkedQueue<>();
        while(!S.isEmpty()){                            //divide original into L, E and G
            K element = S.dequeue();
            int c = comp.compare(element, pivot);
            if(c < 0)                                   //element is less than pivot
                L.enqueue(element);
            else if(c == 0)                             //element is equal to pivot
                E.enqueue(element);
            else                                        //element is grater than pivot
                G.enqueue(element);
        }
        //conquer
        quickSortQueue(L, comp);                        //sort elements less than pivot
        quickSortQueue(G, comp);                        //sort elements grater than pivot
        //concatenate results
        while(!L.isEmpty())
            S.enqueue(L.dequeue());
        while(!E.isEmpty())
            S.enqueue(E.dequeue());
        while(!G.isEmpty())
            S.enqueue(G.dequeue());
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="QuickSortInPlace Array">
    /**
     * Calls the private method quicksort in place.
     * @param <K>
     * @param S
     * @param comp 
     */
    public static <K> void quickSortInPlace(K[] S, Comparator<K> comp){
        quickSortInPlace(S, comp, 0, S.length - 1);
    }
    /**
     * Sort the subarray S[a...b] inclusive.
     * @param <K>
     * @param S
     * @param comp
     * @param a
     * @param b 
     */
    private static <K> void quickSortInPlace(K[] S, Comparator<K> comp, int a, int b){
        if(a >= b) return;                              //subarray is trivially sorted
        int left = a;
        int right = b - 1;
        K pivot = S[b];
        K temp;
        while(left <= right){
            //scan until reaching value equal or larger than pivot (or right marker)
            while(left <= right && comp.compare(S[left], pivot) < 0)left++;
            //scan until reaching value equal or samaller than pivot (or left marker)
            while(left <= right && comp.compare(S[right], pivot) > 0) right--;
            if(left <= right){      //indices did not strictly cross
                //so swamp values shrink range
                temp = S[left]; S[left] = S[right];S[right] = temp;
                left ++; right --;
            }
            //but pivot into its final place (currently marked by left index)
            temp = S[left]; S[left] = S[b]; S[b] = temp;
            //make recursive calls
            quickSortInPlace(S, comp, a, left - 1);
            quickSortInPlace(S, comp, left + 1, b);
        }
    }
    //</editor-fold>
}
