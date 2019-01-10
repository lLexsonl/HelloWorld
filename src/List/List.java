/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

/**
 *A simplified version of the java.util.List
 * @author MIPC
 * @param <E> any type.
 */
public interface List<E> {
    /**
     * Returns the number of elements in this list.
     * @return 
     */
    int size();
    /**
     * Returns whether the list is empty.
     * @return 
     */
    boolean isEmpty();
    /**
     * Returns (but does not remove) the element at index.
     * @param i
     * @return
     * @throws IndexOutOfBoundsException 
     */
    E get(int i) throws IndexOutOfBoundsException;
    /**
     * Replaces the element at index i, with e, and returns the replaced element.
     * @param i
     * @param e
     * @return
     * @throws IndexOutOfBoundsException 
     */
    E set(int i, E e) throws IndexOutOfBoundsException;
    /**
     * Inserts element e to be at index i, shifting subsequent earlier.
     * @param i
     * @param e
     * @throws IndexOutOfBoundsException 
     */
    void add(int i, E e) throws IndexOutOfBoundsException;
    /**
     * Removes/returns the element at index i, shifting subsequent elements earlier.
     * @param i
     * @return
     * @throws IndexOutOfBoundsException 
     */
    E remove(int i) throws IndexOutOfBoundsException;
}
