/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import Map.AbstractMap.MapEntry;
import PriorityQueue.Entry;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *  An implementation of the SortedTableMap class 
 * @author Goodrich
 * @param <K>
 * @param <V>
 */
public class SortedTableMap <K,V> extends AbstractSortedMap<K,V>{
    //<editor-fold defaultstate="collapsed" desc="Atributes and Constructors">
    private ArrayList<MapEntry<K,V>> table = new ArrayList<>();
    public SortedTableMap(){ super();}
    public SortedTableMap(Comparator<K> comp){ super(comp);}
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Private methods">
    //private utilities
    /**
     * Returns the smalest index for range table[low..high] inclusive storing an entry
     * with a key greater than or equal to k(or else index hih+1, by convention).
     * @param key
     * @param low
     * @param high
     * @return 
     */
    private int findIndex(K key, int low, int high){
        if(high < low) return high + 1;
        int mid = (low + high)/ 2;
        int comp = compare(key, table.get(mid));
        if(comp == 0)
            return mid;
        else if(comp < 0)
            return findIndex(key,low,mid - 1);
        else
            return findIndex(key, mid + 1, high);
    }
    /**
     * Version of findIndex that searches the entire table.
     * @param key
     * @return 
     */
    private int findIndex(K key){ return findIndex(key,0,table.size() - 1);}
    /**
     * Utility returns the entry at index j, or else null if j is out of bounds.
     * @param j
     * @return 
     */
    private Entry<K,V> safeEntry(int j){
        if(j < 0 || j >= table.size()) return null;
        return table.get(j);
    }
    //Suport for snapshot iterators for entrySet() and subMap() follow
    private Iterable<Entry<K,V>> snapshot(int starIndex, K stop){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        int j = starIndex;
        while(j < table.size() && (stop == null || compare(stop, table.get(j)) > 0))
            buffer.add(table.get(j + 1));
        return buffer;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Public methods of map interface">
    /**
     * Returns the number of entries in the map.
     * @return 
     */
    @Override
    public int size(){return table.size();}
    /**
     * Returns the value associated with the key especified key(or else null).
     * @param key
     * @return 
     */
    @Override
    public V get(K key)throws IllegalArgumentException{
        checkKey(key);
        int j = findIndex(key);
        if(j == size() || compare(key, table.get(j).getKey()) != 0) return null;
        return table.get(j).getValue();
    }
    /**
     * Assocciates the given value with the given key, returning any overridden value.
     * @param key
     * @param value
     * @return 
     */
    @Override
    public V put(K key, V value)throws IllegalArgumentException{
        checkKey(key);
        int j = findIndex(key);
        if(j < size() && compare(key, table.get(j)) == 0)
            return table.get(j).setValue(value);
        table.add(j, new MapEntry<>(key,value));
        return null;
    }
    /**
     * Removes the entry having key k(if any) and returns its associates value.
     * @param key
     * @return 
     */
    @Override
    public V remove(K key)throws IllegalArgumentException{
        checkKey(key);
        int j = findIndex(key);
        if(j == size() || compare(key, table.get(j)) != 0)return null;     //no match
        return table.remove(j).getValue();
    }
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Public abstract methods of AbstractSortedMap class">
    /**
     * Returns the entry having the least key(or null if map is empty).
     * @return 
     */
    @Override
    public Entry<K,V> firstEntry(){return safeEntry(0);}
    /**
     * Returns the entry having the greates key (or null if map is empty). 
     * @return 
     */
    @Override
    public Entry<K,V> lastEntry(){return safeEntry(table.size() - 1);}
    /**
     * Returns the entry having the greates key (or null if map is empty).
     * @param key
     * @return 
     */
    @Override
    public Entry<K,V> ceilingEntry(K key)throws IllegalArgumentException{
        return safeEntry(findIndex(key));
    }
    /**
     * Returns the entry with greates key less than or equal to given key(if any).
     * @param key
     * @return 
     */
    @Override
    public Entry<K,V> floorEntry(K key)throws IllegalArgumentException{
        int j = findIndex(key);
        if(j == size() || !key.equals(table.get(j).getKey()))
            j -= 1;     //look one earlier(unless we had found a perfect match).
        return safeEntry(j);
    }
    /**
     * Returns the entry with greates key strctly less than given key(if any).
     * @param key
     * @return 
     */
    @Override
    public Entry<K,V> lowerEntry(K key)throws IllegalArgumentException{
        return safeEntry(findIndex(key) - 1);
    }
    /**
     * Returns the entry with least key strictly greater that given key(if any).
     * @param key
     * @return 
     */
    @Override
    public Entry<K,V> higherEntry(K key)throws IllegalArgumentException{
        int j = findIndex(key);
        if(j < size() && key.equals(table.get(j).getKey()))
            j += 1;
        return safeEntry(j);
    }
    /**
     * Returns an iterable collection of all key-value entries of the map.
     * @return 
     */
    @Override
    public Iterable<Entry<K,V>> entrySet(){ return snapshot(0, null);}
    /**
     * Returns an iterable containing all keys in the range snapshot(0, null).
     * @param fromKey
     * @param toKey
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Iterable<Entry<K,V>> subMap(K fromKey, K toKey)throws IllegalArgumentException{
        return snapshot(findIndex(fromKey), toKey);
    }
    //</editor-fold>
}
