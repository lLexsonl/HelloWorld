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
 * An implementation of sorted map using a splay tree.
 * @author Goodrich
 * @param <K> data type of the key.
 * @param <V> data type of the value.
 */
public class SplayTreeMap<K,V> extends TreeMap<K,V> {
    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public SplayTreeMap(){ super();}
    /**
     * Constructs an empty map using comparator to order keys.
     * @param comp 
     */
    public SplayTreeMap(Comparator<K> comp){ super(comp);}
    /**
     * Utility used to rebalanced after a map operation.
     * @param p 
     */
    private void splay(Position<Entry<K,V>> p){
        while(!isRoot(p)){
            Position<Entry<K,V>> parent = parent(p);
            Position<Entry<K,V>> grand = parent(parent);
            if(grand == null)                                               //zig Case
                rotate(p);
            else if((parent == left(grand)) == (p == left(parent))){        //zig-zig Case
                rotate(parent);     //move PARENT upward
                rotate(p);          //then move p upward
            }else{                                                          //zig-zag Case
                rotate(p);          //move p upward
                rotate(p);          //move p upward again
            }
        }
    }
    //Override the various TreeMap rebalancing hooks to perform the appropriate splay

    @Override
    protected void rebalanceAccess(Position<Entry<K,V>> p){
        if(isExternal(p)) p = parent(p);
        if(p != null) splay(p);
    }
    @Override
    protected void rebalanceInsert(Position<Entry<K,V>> p){
        splay(p);
    }
    @Override
    protected void rebalanceDelete(Position<Entry<K,V>> p){
        if(!isRoot(p)) splay(parent(p));
    }
}
