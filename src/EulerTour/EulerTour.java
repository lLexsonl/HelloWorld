/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EulerTour;

import Position.Position;
import Tree.BinaryTree;

/**
 *
 * @author MIPC
 * @param <E> Any Type.
 * @param <R> Any Type.
 */
public abstract class EulerTour<E, R> {
    protected BinaryTree<E> tree;
    public EulerTour(){}
    /**
     * Execution of the traversal. This abstract method must be
     * specified in a concrete subclass.
     * @param T
     * @return 
     */
    public abstract R execute(BinaryTree<E> T);
    /**
     * Initialization of the traversal.
     * @param T 
     */
    protected void init(BinaryTree<E> T){tree = T;}
    /**
     * Template method.
     * @param v
     * @return 
     */
    protected R eulerTour(Position<E> v){
        TourResult<R> r = new TourResult<>();
        visitLeft(v, r);
        if(tree.left(v) != null)
            r.left = eulerTour(tree.left(v));   //recursive traversal.
        visitBelow(v, r);
        if(tree.right(v) != null)
            r.right = eulerTour(tree.right(v));   //recursive traversal.
        visitRight(v,r);
        return r.out;
    }
    /**
     * Method called for the visit on the left.
     * @param v
     * @param r 
     */
    protected void visitLeft(Position<E> v, TourResult<R> r){}
    /**
     * Method called for the visit on from below.
     * @param v
     * @param r 
     */
    protected void visitBelow(Position<E> v, TourResult<R> r){}
    /**
     * Method called for the visit on the right.
     * @param v
     * @param r 
     */
    protected void visitRight(Position<E> v, TourResult<R> r){}
}
