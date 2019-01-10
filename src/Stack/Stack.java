/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack;

/**
 *A collection of objects that are inserted and removed according to the last-in 3
 * ﬁrst-out principle. Although similar in purpose, this interface diﬀers from 4
 * java.util.Stack.
 * @author Michael T. Goodrich
 * @author Robert Tamasia
 * @author Michael H. Goldwasser
 * @param <E>
 */
public interface Stack<E> {
    /**
     * Returns the number of elements in the stack
     * @return number of elements in the stack
     */
    int size();
    /**
     * 
     * @return 
     */
    boolean isEmpty();
    /**
     * 
     * @param e 
     */
    void push(E e);
    E top();
    /**
     * 
     * @return 
     */
    E pop();
}
