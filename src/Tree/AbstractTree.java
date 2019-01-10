/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import Position.Position;
import Queue.LinkedQueue;
import Queue.Queue;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract base class providing some functionality of the Tree interface.
 * @author Goodrich.
 * @param <E> Any type.
 */
public abstract class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean isInternal(Position<E> p){return numChildren(p) > 0;}
    @Override
    public boolean isExternal(Position<E> p){return numChildren(p) == 0;}
    @Override
    public boolean isRoot(Position<E> p){return p == root();}
    @Override
    public boolean isEmpty(){return size() == 0;}
    /**
     * Returns the number of levels separating Position p from the root.
     * @param p
     * @return 
     */
    public int depth(Position<E> p){
        if(isRoot(p))
            return 0;
        else
            return 1 + depth(parent(p));
    }
    /**
     * Returns the height of the subtree rooted at Position p.
     * @param p
     * @return 
     */
    public int height(Position<E> p){
        int h = 0;
        for(Position<E> c : children(p))
            h = Math.max(h, 1 + height(c));
        return h;
    }
    /**
     * Returns the height of the tree.
     * @return 
     */
    private int heightBad(){
        int h = 0;
        for(Position<E> p : positions())
            if(isExternal(p))
                h = Math.max(h, depth(p));
        return h;
    }
    // <editor-fold defaultstate="collapsed" desc="ElementIterator Class.">
    /**
     * This class adapts the iteration produced by positions() yo return elements.s
     */
    private class ElementIterator implements Iterator<E>{
        Iterator<Position<E>> posIterator = positions().iterator();
        @Override
        public boolean hasNext(){ return posIterator.hasNext();}
        @Override
        public E next(){ return posIterator.next().getElement();}
        @Override
        public void remove(){ posIterator.remove();}
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="iterator and positions methods.">
    /**
     * Returns an iterator of the elements stored in the tree.
     * @return 
     */
    @Override
    public Iterator<E> iterator(){return new ElementIterator();}
    /**
     * positions method
     * @return 
     */
    @Override
    public Iterable<Position<E>> positions(){ return preorder();}
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="preorder Traversal.">
    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     * @param p
     * @param snapshot 
     */
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot){
        snapshot.add(p);
        for(Position<E> c : children(p))
            preorderSubtree(c, snapshot);
    }
    /**
     * Returns an iterable collection of Positions of the tree, reported in preorder.
     * @return 
     */
    public Iterable<Position<E>> preorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            preorderSubtree(root(), snapshot);
        return snapshot;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="postorder Traversal.">
    /**
     * Adds positions of the subtree rooted at Position p to given snapshot. 
     * @param p
     * @param snapshot 
     */
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot){
        for(Position<E> c : children(p))
            postorderSubtree(c, snapshot);
        snapshot.add(p); // for postorder, we add position p after in postorder.
    }
    /**
     * returns an iterable collection of Positions of the tree, reported in postorder.
     * @return 
     */
    public Iterable<Position<E>> postorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            postorderSubtree(root(), snapshot);//fill the snapshot recursively.
        return snapshot;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Breadth-first Traversal.">
    /**
     * Returns an iterable collection of positions of the tree in breadth-first order.
     * @return 
     */
    public Iterable<Position<E>> breadthfirst(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()){
            Queue<Position<E>> fringe = new LinkedQueue<>();
            fringe.enqueue(root());
            while(!fringe.isEmpty()){
                Position<E> p = fringe.dequeue();
                snapshot.add(p);
                for(Position<E> c : children(p))
                    fringe.enqueue(c);
            }
        }
        return snapshot;
    }
    // </editor-fold>
}
