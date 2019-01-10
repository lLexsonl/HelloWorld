/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PriorityQueue;

import Position.LinkedPositionalList;
import Position.PositionalList;
import Position.Position;
import java.util.Comparator;

/**
 * An implementation of a priority queue with an unsorted list. 
 * @author MIPC
 * @param <K> data type of the key.
 * @param <V> data type of the value.
 */
public class UnsortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    //<editor-fold defaultstate="collapsed" desc="Atributes and Constructors">
    /**
     * Primary collection of priority queue entrie
     */
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();
    /**
     *  Creates an empty priority queue based on the natural ordering of its keys. 
     */
    public UnsortedPriorityQueue(){super();}
    /**
     * Creates an empty priority queue using the given comparator to order keys
     * @param comp 
     */
    public UnsortedPriorityQueue(Comparator<K> comp){super(comp);}
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="findMin method.">    
    /**
     *  Returns the Position of an entry having minimal key.
     * @return 
     */
    private Position<Entry<K,V>> findMin(){ //only called when nonempty.
        Position<Entry<K,V>> small = list.first();
        for(Position<Entry<K,V>> walk : list.positions())
            if(compare(walk.getElement(), small.getElement()) < 0)
                small = walk; //found an even smaller key.
        return small;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="PriorityQueue Interface.">
    /**
     * Inserts a key-value pair and returns the entry created. 
     * @param key Key of the entry.
     * @param value Value of the entry.
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Entry<K,V> insert(K key, V value)throws IllegalArgumentException{
        checkKey(key); //auxiliar key-checking method(could throw exception).
        Entry<K,V> newest = new PQEntry<>(key,value);
        list.addLast(newest);
        return newest;
    }
    /**
     *  Returns (but does not remove) an entry with minimal key. 
     * @return 
     */
    @Override
    public Entry<K,V> min(){
        if(list.isEmpty())return null;
        return findMin().getElement();
    }
    /**
     * Removes and returns an entry with minimal key.
     * @return 
     */
    @Override
    public Entry<K,V> removeMin(){
        if(list.isEmpty()) return null;
        return list.remove(findMin());
    }
    /**
     *  Returns the number of items in the priority queue. 
     * @return 
     */
    @Override
    public int size(){return list.size();}
    //</editor-fold>
    
}
