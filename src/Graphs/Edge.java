/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

/**
 * An edge of a graph.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @param <E> data type of the edge
 * 
 */
public interface Edge<E>  {
    /** Returns the element associated with the edge.
     * @return  */
    E getElement();
    
}
