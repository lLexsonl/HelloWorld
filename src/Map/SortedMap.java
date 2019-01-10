/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import PriorityQueue.Entry;

/**
 *
 * @author MIPC
 * @param <K>
 * @param <V>
 */
public interface SortedMap<K,V> extends Map<K,V> {
   /**
   * Returns the entry having the least key (or null if map is empty).
   * @return entry with least key (or null if map is empty)
   */
    Entry<K,V> firstEntry();
   /**
   * Returns the entry having the greatest key (or null if map is empty).
   * @return entry with greatest key (or null if map is empty)
   */
    Entry<K,V> lastEntry();
    /**
     *  Returns the entry with least key greater than or equal to given key
     * @param key
     * @return
     * @throws IllegalArgumentException 
     */
    Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException;
    /**
     * Returns the entry with greatest key less than or equal to given key
     * @param key
     * @return
     * @throws IllegalArgumentException 
     */
    Entry<K,V> floorEntry(K key) throws IllegalArgumentException;
    /**
     * Returns the entry with greatest key strictly less than given key
     * @param key
     * @return
     * @throws IllegalArgumentException 
     */
    Entry<K,V> lowerEntry(K key) throws IllegalArgumentException;
    /**
     * Returns the entry with least key strictly greater than given key
     * @param key
     * @return
     * @throws IllegalArgumentException 
     */
    Entry<K,V> higherEntry(K key) throws IllegalArgumentException;
    /**
     * Returns an iterable containing all keys in the range from
   * <code>fromKey</code> inclusive to <code>toKey</code> exclusive.
     * @param formKey
     * @param tokey
     * @return
     * @throws IllegalArgumentException 
     */
    Iterable<Entry<K,V>> subMap(K formKey, K tokey) throws IllegalArgumentException;
}
