/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PriorityQueue;

import Position.LinkedPositionalList;
import Position.Position;
import Position.PositionalList;
import java.util.Comparator;

/**
 * An implementation of a priority queue with a sorted list.
 * @author MIPC
 * @param <K> data type of the Key.
 * @param <V> data type of the Value.
 */
public class SortedPriorityQueue <K,V> extends AbstractPriorityQueue<K, V>{
    //<editor-fold defaultstate="collapsed" desc="Atributes and Contructors.">
    /**
     *  primary collection of priority queue entries.
     */
    private PositionalList<Entry<K,V>> list = new LinkedPositionalList<>();
    /**
     *  Creates an empty priority queue based on the natural ordering of its keys. 
     */
    public SortedPriorityQueue(){super();}
    /**
     * Creates an empty priority queue using the given comparator to order keys. 
     * @param comp 
     */
    public SortedPriorityQueue(Comparator<K> comp){super(comp);}
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Implementation PriorityQueue Interface.">
    /**
     * Inserts a key-value pair and returns the entry created. 
     * @param key
     * @param value
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Entry<K,V> insert(K key, V value)throws IllegalArgumentException{
        checkKey(key);  // auxiliary key-checking method (could throw exception).
        Entry<K,V> newest = new PQEntry<>(key, value);
        Position<Entry<K,V>> walk = list.last();
        while(walk != null && compare(newest, walk.getElement()) < 0)
            walk = list.before(walk);
        if(walk == null)
            list.addFirst(newest);
        else
            list.addAfter(walk, newest);
        return newest;
    }
    /**
     * Returns (but does not remove) an entry with minimal key. 
     * @return 
     */
    @Override
    public Entry<K,V> min(){
        if(list.isEmpty())return null;
        return list.first().getElement();
    }
    /**
     * Removes and returns an entry with minimal key. 
     * @return 
     */
    @Override
    public Entry<K,V> removeMin(){
        if(list.isEmpty())return null;
        return list.remove(list.first());
    }
    /**
     * Returns the number of items in the priority queue. 
     * @return 
     */
    @Override
    public int size(){return list.size();}
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Test">
    //</editor-fold>
}
