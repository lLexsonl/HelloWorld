/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting;

import Queue.LinkedQueue;
import Queue.Queue;
import java.util.Arrays;
import java.util.Comparator;

/**
 * An implementation of the Merge Sort in different data structures.
 * @author Goodrich, Tamassia
 * @param <K>
 */
public class MergeSort<K> {
    //<editor-fold defaultstate="collapsed" desc="MergeSort Array">
    /**
     * Merge contents of arrays S1 y S2 into properly sized array S.
     * @param <K> 
     * @param S1 
     * @param S2 
     * @param S 
     * @param comp 
     */
    private static <K> void mergeArray(K[] S1, K[] S2, K[] S, Comparator<K> comp){
        int i = 0, j = 0;
        while(i + j < S.length){
            if(j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
                S[i + j] = S1[i++];             //copy the element of S1 and increment i
            else
                S[i + j] = S2[j++];             //copy the element if S2 and increment j
        }
    }
    /**
     * Merge-sort contents of array S.
     * @param <K>
     * @param S
     * @param comp 
     */
    public static <K> void mergeSortArray(K[] S, Comparator<K> comp){
        int n = S.length;
        if(n < 2) return;                                   //array is trivially sorted
        int mid = n / 2;
        K[] S1 = Arrays.copyOfRange(S, 0, mid);             //copy of first half
        K[] S2 = Arrays.copyOfRange(S, mid, n);             //copy of second half
        //merge results
        mergeArray(S1, S2, S, comp);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="MergeSort Queue">
    /**
     * Merge contents of sorted queues S1 and S2 into wmpty queue S.
     * @param <K>
     * @param S1
     * @param S2
     * @param S
     * @param comp 
     */
    private static <K> void mergeQueue(Queue<K> S1, Queue<K> S2, Queue<K> S, Comparator<K> comp){
        while(!S1.isEmpty() && !S2.isEmpty()){
            if(comp.compare(S1.first(), S2.first()) < 0)
                S.enqueue(S1.dequeue());                        //take next element from S1
            else
                S.enqueue(S2.dequeue());                        //take next element from S2
            while(!S1.isEmpty())
                S.enqueue(S1.dequeue());                        //move any element that remain in S1
            while(!S2.isEmpty())
                S.enqueue(S2.dequeue());                        //move any elements that remain in S2
        }
    }
    /**
     * Merge-sort contents of queue.
     * @param <K>
     * @param S
     * @param comp 
     */
    public static <K> void mergeSortQueue(Queue<K> S, Comparator<K> comp){
        int n = S.size();
        if(n < 2) return;
        //divide
        Queue<K> S1 = new LinkedQueue<>();
        Queue<K> S2 = new LinkedQueue<>();
        while(S1.size() < n/2)
            S1.enqueue(S.dequeue());
        while(!S.isEmpty())
            S2.enqueue(S.dequeue());
        //Conquer (with recursion)
        mergeSortQueue(S1,comp);
        mergeSortQueue(S2, comp);
        //merge results
        mergeQueue(S1, S2, S, comp);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="MergeSort BottonUp Array">
    /**
     * Merges in[start..start + start + inc - 1] and in[start + inc...start + 2 * inc] int out.
     * @param <K>
     * @param in
     * @param out
     * @param comp
     * @param start
     * @param inc 
     */
    private static <K> void mergeBottonUp(K[] in, K[] out, Comparator<K> comp, int start, int inc){
        int end1 = Math.min(start + inc, in.length);
        int end2 = Math.min(start + 2 * inc, in.length);
        int x = start;
        int y = start + inc;
        int z = start;
        while(x < end1 && y < end2)
            if(comp.compare(in[x], in[y]) < 0)
                out[z++] = in[x++];
            else
                out[z++] = in[y++];
        if(x < end1)System.arraycopy(in, x, out, z, end1 - x);
        else if(y < end2)System.arraycopy(in, y, out, z, end2 - y);
    }
    /**
     * Merge Sort contents of data array.
     * @param <K>
     * @param orig
     * @param comp 
     */
    public static <K> void mergeSortBottomUp(K[] orig, Comparator<K> comp){
        int n = orig.length;
        K[] src = orig;                                 //alias for the original
        K[] dest = (K[]) new Object[n];                 //make a new temporary
        K[] temp;                                       //reference used only for swapping
        for(int i = 1; i < n; i*= 2){                   //each iteration sorts all of runs of the length i
            for(int j = 0; j  < n; j += 2 * i)          //each pass merges two runs of the lenght i
                mergeBottonUp(src, dest, comp, j, i);
            temp = src; src = dest; dest = temp;        //reverse roles of the arrays
        }
        if(orig != src)
            System.arraycopy(src, 0, orig, 0, n);       //additional copy to get result to original
    }
    //</editor-fold>
}
