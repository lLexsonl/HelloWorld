/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PriorityQueue;

import Position.PositionalList;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of a priority queue using an array-based heap.
 * @author MIPC
 * @param <K> data type of the key.
 * @param <V> data type of the value.
 */
public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    //<editor-fold defaultstate="collapsed" desc="Atributes anf Constructors.">
    /**
     * primary collection of priority queue entries.
     */
    protected ArrayList<Entry<K,V>> heap = new ArrayList<>();
    /**
     * Creates an empty priority queue based o the natural ordering of its keys.
     */
    public HeapPriorityQueue(){super();}
    /**
     * Creates an empty priority queue using the given comparator to order keys.
     * @param comp 
     */
    public HeapPriorityQueue(Comparator<K> comp){super(comp);}
    /**
     * Creates a priority queue initialized with the given key-value pairs.
     * @param keys
     * @param values 
     */
    public HeapPriorityQueue(K[] keys, V[] values){
        super();
        for(int j = 0; j < Math.min(keys.length, values.length); j++)
            heap.add(new PQEntry<>(keys[j], values[j]));
        heapify();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="protected utilities">
    /**
     * 
     * @param j
     * @return 
     */
    protected int parent(int j){return (j-1)/2;}
    protected int left(int j){return 2*j + 1;}
    protected int right(int j){return 2*j +2;}
    protected boolean hasLeft(int j){return left(j) < heap.size();}
    protected boolean hasRight(int j){return right(j) < heap.size();}
    /**
     * Exchanges the entries at indices i and j of the array list.
     * @param i
     * @param j 
     */
    protected void swap(int i, int j){
        Entry<K,V> temp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j, temp);
    }
    /**
     * Moves the entry at index j higher, if necessary, to restore the heap property.
     * @param j 
     */
    protected void upheap(int j){
        while(j > 0){   //continue until reaching root(or break statement).
            int p = parent(j);
            if(compare(heap.get(j),heap.get(p)) >= 0)break; //heap property verified.
            swap(j, p);
            j = p;  //continue from the parent's location.
        }
    }
    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     * @param j 
     */
    protected void downheap(int j){
        while(hasLeft(j)){  //continue to buttom(or break statement).
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;    //although right may be smaller.
            if(hasRight(j)){
                int rightIndex = right(j);
                if(compare(heap.get(leftIndex),heap.get(rightIndex)) > 0)
                    smallChildIndex = rightIndex;   //right child is smaller.
            }
            if(compare(heap.get(smallChildIndex),heap.get(j)) >= 0)
                break;  //heap property has been restored
            swap(j, smallChildIndex);
            j = smallChildIndex;   //continue at position of the child.
        }
    }
    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify(){
        int startIndex = parent(size() - 1);    //start at PARENT of last entry-
        for(int j = startIndex; j >= 0; j--)    //loop until processing the root.
            downheap(j);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="public methods.">
    /**
     * Returns the numer of items in the property queue.
     * @return 
     */
    @Override
    public int size(){return heap.size();}
    /**
     * Returns (but doesn't remove) an entry with minimal key(if any).
     * @return 
     */
    @Override
    public Entry<K,V> min(){
        if(heap.isEmpty())return null;
        return heap.get(0);
    }
    /**
     * Inserts a key-value pair and return the entry created.
     * @param key
     * @param value
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Entry<K,V> insert(K key, V value)throws IllegalArgumentException{
            checkKey(key);
            Entry<K,V> newest = new PQEntry<>(key,value);
            heap.add(newest);
            upheap(heap.size() - 1);
            return newest;
    }
    /**
     * Removes and returns an entry with minimal key (if any).
     * @return 
     */
    @Override
    public Entry<K,V> removeMin(){
        if(heap.isEmpty())return null;
        Entry<K,V> answer = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return answer;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Sorting">
    /**
     * Sorts sequence S, using initially empty priority queue P to produce the order.
     * @param <E>
     * @param S
     * @param P 
     */
    public static <E> void pqSort(PositionalList<E> S, PriorityQueue<E,?> P){
        int n = S.size();
        for(int j = 0; j < n; j++){
            E element = S.remove(S.first());
            P.insert(element, null);    //element is key; null value.
        }
        for(int j = 0; j < n; j++){
            E element = P.removeMin().getKey();
            S.addLast(element); //the samllest key in P is next placed in S.
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Default">
    
    //</editor-fold>
}
