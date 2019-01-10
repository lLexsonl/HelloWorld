/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import PriorityQueue.DefaultComparator;
import PriorityQueue.Entry;
import java.util.Comparator;

/**
 *
 * @author MIPC
 * @param <K>
 * @param <V>
 */
public abstract class AbstractSortedMap<K,V> extends AbstractMap<K,V> implements SortedMap<K,V>{
    private Comparator<K> comp;
    protected AbstractSortedMap(Comparator<K> c){comp = c;}
    protected AbstractSortedMap(){this(new DefaultComparator<K>());}
    
    protected int compare(Entry<K,V> a, Entry<K,V> b){
        return comp.compare(a.getKey(), b.getKey());
    }
    protected int compare(Entry<K,V> a, K b){
        return compare(a.getKey(), b);
    }
    protected int compare(K a, Entry<K,V> b){
        return compare(a, b.getKey());
    }
    protected int compare(K a, K b){
        return comp.compare(a, b);
    }
    /**
     * Determines wheter a key is valid.
     * @param key
     * @return
     * @throws IllegalArgumentException 
     */
    protected boolean checkKey(K key) throws IllegalArgumentException{
        try{
            return (comp.compare(key, key) == 0);
        }catch(ClassCastException e){
            throw new IllegalArgumentException("Incompatible key");
        }
    }
    /*
    public abstract Entry<K,V> firstEntry();
    
    public abstract Entry<K,V> lastEntry();
    
    public abstract Entry<K,V> ceilingEntry(K k);
    
    public abstract Entry<K,V> floorEntry(K k);
    
    public abstract Entry<K,V> lowerEntry(K k);
    
    public abstract Entry<K,V> higherEntry(K k);
    
    public abstract Iterable<Entry<K,V>> subMap(K k1, K k2);*/
    
}
