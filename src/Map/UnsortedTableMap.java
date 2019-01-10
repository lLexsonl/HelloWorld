/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import PriorityQueue.Entry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author MIPC
 * @param <K>
 * @param <V>
 */
public class UnsortedTableMap<K,V> extends AbstractMap<K,V> {
    //<editor-fold defaultstate="collapsed" desc="Atributes and Contructor">
    /**
     * Underlying storage for the map of entries.
     */
    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();
    /**
     * Constructs an initially empty map.
     */
    public UnsortedTableMap(){}
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="private utility">
    /**
     * Returns the index of an entry with equal key, or -1 if none found.
     * @param key
     * @return 
     */
    private int findIndex(K key){
        int n = table.size();
        for(int j = 0; j < n; j++){
            if(table.get(j).getKey().equals(key))
                return j;
        }
        return -1;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="public utility">
    /**
     * Returns the number of entries in the map.
     * @return 
     */
    @Override
    public int size(){ return table.size();}
    /**
     * Returns the value associated with the specified key (or else null).
     * @param key
     * @return 
     */
    @Override
    public V get(K key){
        int j = findIndex(key);
        if(j == -1) return null;
        return table.get(j).getValue();
    }
    /**
     * Associates given value with given key, replacing a previous value (if any).
     * @param key
     * @param value
     * @return 
     */
    @Override
    public V put(K key, V value){
        int j = findIndex(key);
        if(j == -1){
            table.add(new MapEntry<>(key, value));
            return null;
        }else
            return table.get(j).setValue(value);
    }
    /**
     * Removes the entry with the specified key (if any) and returns its value.
     * @param key
     * @return 
     */
    @Override
    public V remove(K key){
        int j = findIndex(key);
        int n = size();
        if(j == -1) return null;
        V answer = table.get(j).getValue();
        if(j != n - 1)
            table.set(j, table.get(n - 1));
        table.remove(n - 1);
        return answer;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="EntryIterator class">
    //Support for public entrySet method...
    private class EntryIterator implements Iterator<Entry<K, V>>{
        private int j = 0;
        @Override
        public boolean hasNext(){ return j < table.size();}
        @Override
        public Entry<K, V> next(){
            if(j == table.size()) throw new NoSuchElementException();
            return table.get(j + 1);
        }
        @Override
        public void remove(){ throw new UnsupportedOperationException();}
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="EntryIterable class">
    private class EntryIterable implements Iterable<Entry<K, V>>{
        @Override
        public Iterator<Entry<K,V>> iterator(){ return new EntryIterator();}
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="entrySet method">
    /**
     * Returns an iterable collection of all key-value entries of the map.
     * @return 
     */
    @Override
    public Iterable<Entry<K,V>> entrySet(){ return new EntryIterable();}
    //</editor-fold>
}
