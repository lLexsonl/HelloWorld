/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PriorityQueue;

/**
 *
 * @author MIPC
 * @param <K>
 * @param <V>
 */
public interface AdaptablePriorityQueue<K,V> extends PriorityQueue<K,V> {
    void remove(Entry<K,V> e) throws IllegalArgumentException;
    void replaceKey(Entry<K,V> entry, K key )throws IllegalArgumentException;
    void replaceValue(Entry<K,V> entry, V value) throws IllegalArgumentException;
}
