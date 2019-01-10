/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import Position.Position;
import PriorityQueue.Entry;
import java.util.Comparator;

/**
 * An implementation of sorted map using an AVL tree.
 * @author Goodrich
 * @param <K> data type of the key
 * @param <V> data type of the value
 */
public class AVLTreeMap<K,V> extends TreeMap<K,V> {
    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public AVLTreeMap(){ super();}
    /**
     * Constructs an empty map using the given comparator to order keys.
     * @param comp 
     */
    public AVLTreeMap(Comparator<K> comp){ super(comp);}
    /**
     * Returns the height of the given tree position.
     * @param p
     * @return 
     */
    protected int height(Position<Entry<K,V>> p){
        return tree.getAux(p);
    }
    /**
     * Recomputes the height of the given position based on its children's height.
     * @param p
     */
    protected void recomputeHeight(Position<Entry<K,V>> p){
        tree.setAux(p, 1 + Math.max(height(left(p)), height(right(p))));
    }
    /**
     * Returns wheter a position has balance factor bettween -1 and 1 inclusive.
     * @param p
     * @return 
     */
    protected boolean isBalanced(Position<Entry<K,V>> p){
        return Math.abs(height(left(p)) - height(right(p))) <= 1;
    }
    /**
     * Returns a child of p with height no smaller than of the other child.
     * @param p
     * @return 
     */
    protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p){
        if(height(left(p)) > height(right(p))) return left(p);
        if(height(left(p)) < height(right(p))) return right(p);
        //equal height children; break tie while mathching parent´s orientation
        if(isRoot(p)) return left(p);                   //choice irrelevant
        if(p == left(parent(p))) return left(p);        //return aligned child
        else return right(p);
    }
    /**
     * utility used to rebalance after an insert or removal operation. This traverses the
     * path upward from p, performing a trinode restructuring when inbalanced is found,
     * continuing until balance is restored.
     * @param p 
     */
    protected void rebalance(Position<Entry<K,V>> p){
        int oldHeight, newHeight;
        do{
            oldHeight = height(p);          //not yet recalculated if internal
            if(!isBalanced(p)){             //inbalanced detected
                //perform trinode retructuring, setting p to resulting root,
                //and recompute new local heights after the restructuring
                p = restructure(tallerChild(tallerChild(p)));
                recomputeHeight(left(p));
                recomputeHeight(right(p));
            }
            recomputeHeight(p);
            newHeight = height(p);
            p = parent(p);
        }while(oldHeight != newHeight && p != null);
    }
    /**
     * Overrides the TreeMap rebalancing hook that is called after an insertion.
     * @param p 
     */
    @Override
    protected void rebalanceInsert(Position<Entry<K,V>> p){
        rebalance(p);
    }
    /**
     * Overrides the TreeMap rebalancing hook that is called after an deletion.
     * @param p 
     */
    @Override
    protected void rebalanceDelete(Position<Entry<K,V>> p){
        if(!isRoot(p))
            rebalance(parent(p));
    }
}
