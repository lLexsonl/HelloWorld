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
 * An implementation of soreted map using a red-black tree.
 * @author Goodrich
 * @param <K> data type of the key
 * @param <V> data type of the value
 */
public class RBTreeMap<K,V> extends TreeMap<K,V> {
    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public RBTreeMap(){ super();}
    /**
     * Constructs an empty map using the given comparator to order keys.
     * @param comp 
     */
    public RBTreeMap(Comparator comp){ super(comp);}
    //we use the intherited aux field with convention that 0 = black and 1 = red
    //(note that new leaves will be back by default, as aux = 0)
    private boolean isBlack(Position<Entry<K,V>> p){return tree.getAux(p) == 0;}
    private boolean isRed(Position<Entry<K,V>> p){return tree.getAux(p) == 1;}
    private void makeBlack(Position<Entry<K,V>> p){tree.setAux(p, 0);}
    private void makeRed(Position<Entry<K,V>> p){tree.setAux(p, 1);}
    private void setColor(Position<Entry<K,V>> p, boolean toRed){
        tree.setAux(p, toRed ? 1 : 0);
    }
    /**
     * Overrides the TreeMap rebalancing hook that is called after an insertion.
     * @param p 
     */
    @Override
    protected void rebalanceInsert(Position<Entry<K,V>> p){
        if(!isRoot(p)){
            makeRed(p);             //the new internal node is initially colored red
            resolveRed(p);          //but this may cause a double-red problem
        }
    }
    /**
     * Remedies potential double-red violation above red position p.
     * @param p 
     */
    protected void resolveRed(Position<Entry<K,V>> p){
        Position<Entry<K,V>> parent, uncle, middle, grand;
        parent = parent(p);
        if(isRed(parent)){                  //double-red problem
            uncle = sibling(parent);
            if(isBlack(uncle)){             //Case 1: misshpen 4-node
                middle = restructure(p);    //do trinode retructuring
                makeBlack(middle);
                makeRed(left(middle));
                makeRed(right(middle));
            }else{                          //Case 2: overfull 5-node
                makeBlack(parent);          //perform recoloring
                makeBlack(uncle);
                grand = parent(parent);
                if(!isRoot(grand)){
                    makeRed(grand);         //grandparent becomes red
                    resolveRed(grand);      //recur at red grandparent
                }
            }
        }
    }
    /**
     * Overrides the TreeMap rebalancing hook that is called after a deletion.
     * @param p 
     */
    @Override
    protected void rebalanceDelete(Position<Entry<K,V>> p){
        if(isRed(p))                                    //deleted parent was black
            makeBlack(p);                               //so this retores black depth
        else if(!isRoot(p)){
            Position<Entry<K,V>> sib = sibling(p);
            if(isInternal(sib) && (isBlack(sib) || isInternal(left(sib))))
                remedyDoubleBlack(p);                   //sib's subtree has nonzero black height
        }
    }
    /**
     * Remedies a presumed double-black violation at the given (nonroot) position.
     * @param p 
     */
    private void remedyDoubleBlack(Position<Entry<K,V>> p){
        Position<Entry<K,V>> z = parent(p);
        Position<Entry<K,V>> y = sibling(p);
        if(isBlack(y)){
            if(isRed(left(y)) || isRed(right(y))){              //Case 1: trinode restructuring
                Position<Entry<K,V>> x = (isRed(left(y))?left(y) : right(y));
                Position<Entry<K,V>> middle = restructure(x);
                setColor(middle, isRed(z));         //root of restructured subtree gets z's old color
                makeBlack(left(middle));
                makeBlack(right(middle));
            }else{                                              //Case 2: recoloring
                makeRed(y);
                if(isRed(z))                                    //problem is resolved
                    makeBlack(z);
                else if(!isRoot(z))
                    remedyDoubleBlack(z);                       //propagate the problem
            }
        }else{
            rotate(y);
            makeBlack(y);
            makeRed(z);
            remedyDoubleBlack(p);
        }
    }
}
