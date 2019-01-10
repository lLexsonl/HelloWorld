/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

/**
 *
 * @author MIPC
 * @param <E> any type.
 */
public interface Deque<E> {
    /**
     * Returns the number of elements in the deque.
     * @return 
     */
    int size();
    /**
     * Tests whether the deque is empty.
     * @return 
     */
    boolean isEmpty();
    /**
     * Returns, but does not remove, the last element of the deque(null if empty).
     * @return 
     */
    E first();
    /**
     * Returns, but does not remove, the first element of the deque(null if empty).
     * @return 
     */
    E last();
    /**
     * Inserts an element at the front of the deque.
     * @param e 
     */
    void addFirst(E e);
    /**
     * Inserts an element at the back of the deque.
     * @param e 
     */
    void addLast(E e);
    /**
     * Removes and returns the first element of the deque(null if empty).
     * @return 
     */
    E removeFirst();
    /**
     * Removes and returns the last element of the deque(null is empty).
     * @return 
     */
    E removeLast();
}
