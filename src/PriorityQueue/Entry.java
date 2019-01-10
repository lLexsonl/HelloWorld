/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PriorityQueue;

/**
 * Interface for a key-value pair.
 * @author MIPC
 * @param <K> data type of the Key.
 * @param <V> data type of the Value.
 */
public interface Entry<K, V> {
    K getKey();     //Returns the key stored in this entry.
    V getValue();   //Returns the value stored in this entry.
}
