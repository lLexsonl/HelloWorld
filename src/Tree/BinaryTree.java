/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import Position.Position;

/**
 * An Interface for a binary tree, in which each node has at most two children.
 * @author Goodrich.
 * @param <E>
 */
public interface BinaryTree<E> extends Tree<E> {
    /**
     * Returns the position of p's left child(or null if no child exits).
     * @param p
     * @return
     * @throws IllegalArgumentException 
     */
    Position<E> left(Position<E> p) throws IllegalArgumentException;
    /**
     * Returns the position of p's right child(or null if no child exist).
     * @param p
     * @return
     * @throws IllegalArgumentException 
     */
    Position<E> right(Position<E> p) throws IllegalArgumentException;
    /**
     * Returns the position of p's sibling (or null if no sibling exist).
     * @param p
     * @return
     * @throws IllegalArgumentException 
     */
    Position<E> sibling(Position<E> p) throws IllegalArgumentException;
}
