/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import java.util.AbstractMap;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author MIPC
 * @param <K>
 * @param <V>
 */
public class HashMultimap<K,V> {
    Map<K,List<V>> map = new HashMap<>();       //the primary map
    int total = 0;                              //total numer of entries in thhe multimap
    /**
     * Contructs an empty multimap.
     */
    public HashMultimap(){}
    /**
     * Returns the total number in the multimap.
     * @return 
     */
    public int size(){ return total;}
    /**
     * Return wheter the multimap is empty.
     * @return 
     */
    public boolean isEmpty(){ return total == 0;}
    /**
     * Returns a(possibly empty) iteration of al values associated with the key.
     * @param key
     * @return 
     */
    public Iterable<V> get(K key){
        List<V> secondary = map.get(key);
        if(secondary != null)
            return secondary;
        return new ArrayList<>();           //return an empty list of values
    }
    /**
     * Adds a new entry associating key with value.
     * @param key
     * @param value 
     */
    public void put(K key, V value){
        List<V> secondary = map.get(key);
        if(secondary == null){
            secondary = new ArrayList<>();
            map.put(key, secondary);
        }
        secondary.add(value);
        total += 1;
    }
    /**
     * Removes the (key, valu) entry, if it exist.
     * @param key
     * @param value
     * @return 
     */
    public boolean remove(K key, V value){
        boolean wasRemoved = false;
        List<V> secondary = map.get(key);
        if(secondary != null){
            wasRemoved = secondary.remove(value);
            if(wasRemoved){
                total -= 1;
                if(secondary.isEmpty())
                    map.remove(key);            //remove secondary structure form primary map
            }
        }
        return wasRemoved;
    }
    /**
     * Removes all entries with the given key.
     * @param key
     * @return 
     */
    Iterable<V> removeAll(K key){
        List<V> secondary = map.get(key);
        if(secondary != null){
            total -= secondary.size();
            map.remove(key);
        }else
            secondary = new ArrayList<>();          //return empty list of removed values
        return secondary;
    }
    /**
     * Returns an iteration of the all entries in the multimap.
     * @return 
     */
    public Iterable<Map.Entry<K,V>> entries(){
        List<Map.Entry<K,V>> result = new ArrayList<>();
        for(Map.Entry<K,List<V>> secondary : map.entrySet()){
            K key = secondary.getKey();
            for(V value : secondary.getValue())
                result.add(new AbstractMap.SimpleEntry<>(key,value));
        }
        return result;
    }
}
