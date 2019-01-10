/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import Position.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * An abstract base class providing some functionality of the BinaryTree interface.
 * @author Goodrich
 * @param <E> Any type.
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    /**
     * Returns the Position of p's sibling (or null if no sibling exists).
     * @param p
     * @return 
     */
    @Override
    public Position<E> sibling(Position<E> p){
        Position<E> parent = parent(p);
        if(parent == null) return null;
        if(p == left(parent))
            return right(parent);
        else
            return left(parent);
    }
    /**
     * Returns the number of children of Position p.
     * @param p
     * @return 
     */
    @Override
    public int numChildren(Position<E> p){
        int count = 0;
        if(left(p) != null)
            count += 1;
        if(right(p) != null)
            count += 1;
        return count;
    }
    /**
     * Returns a Iterable collection of the Positions representing p's children.
     * @param p
     * @return 
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p){
        ArrayList<Position<E>> snapshot = new ArrayList<>(2);
        if(left(p) != null)
            snapshot.add(left(p));
        if(right(p) != null)
            snapshot.add(right(p));
        return snapshot;
    }
    /**
     * Adds positions of the subtree rooted at Position p to the given snapshot.
     * @param p
     * @param snapshot 
     */
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot){
        if(left(p) != null)
            inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if(right(p) != null)
            inorderSubtree(right(p), snapshot);
    }
    /**
     * Returns an iterable collection of position of the tree, reported in inorder.
     * @return 
     */
    public Iterable<Position<E>> inorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            inorderSubtree(root(), snapshot);
        return snapshot;
    }
    /**
     * Overrides positions to make inorder the default order for bianry trees.
     * @return 
     */
    @Override
    public Iterable<Position<E>> positions(){ return inorder();}
}
