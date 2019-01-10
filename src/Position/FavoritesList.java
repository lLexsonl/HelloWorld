/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Position;

import java.util.Iterator;

/**
 * Maintains a list of elements ordered accordeing to access frequency.
 * @author MIPC
 * @param <E> Any type.
 */
public class FavoritesList<E>{
    // <editor-fold defaultstate="collapsed" desc="Item Class.">
    protected static class Item<E>{
        private E value;
        private int count = 0;
        
        public Item(E val){ value = val;}
        public int getCount(){return count;}
        public E getValue(){return value;}
        public void increment(){count += 1;}
    }
    // </editor-fold>
    
    PositionalList<Item<E>> list = new LinkedPositionalList<>();
    public FavoritesList(){}//Constructor
    
    // <editor-fold defaultstate="collapsed" desc="Nonpublic utilities.">
    /**
     * Provides shorthand notation to retrive user's element stored at Position p.
     * @param p
     * @return 
     */
    protected E value(Position<Item<E>> p){return p.getElement().getValue();}
    /**
     *  Provides shorthand notation to retrieve count of item stored at Position p.
     * @param p
     * @return 
     */
    protected int count(Position<Item<E>> p){return p.getElement().getCount();}
    /**
     * Returns Position having element equal to e (or null if not found).
     * @param e
     * @return 
     */
    protected Position<Item<E>> findPosition(E e){
        Position<Item<E>> walk = list.first();
        while(walk != null && !e.equals(value(walk)))
            walk = list.after(walk);
        return walk;
    }
    /**
     * Moves item at Position p earlier in the list based on accsess count.
     * @param p 
     */
    protected void moveUp(Position<Item<E>> p){
        int cnt = count(p);
        Position<Item<E>> walk = p;
        while(walk != list.first() && count(list.before(walk)) < cnt)
            walk = list.before(walk);
        if(walk != p)
            list.addBefore(walk, list.remove(p));
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Public Methods.">
    public int size(){return list.size();}
    public boolean isEmpty(){ return list.isEmpty();}
    /**
     * Accesses element e(possibly new), increasing its access count.
     * @param e 
     */
    public void access(E e){
        Position<Item<E>> p = findPosition(e);
        if(p == null)
            p = list.addLast(new Item<>(e));
        p.getElement().increment();
        moveUp(p);
    }
    /**
     * Removes element equal to e from the list of favorites(if found).
     * @param e 
     */
    public void remove(E e){
        Position<Item<E>> p = findPosition(e);
        if(p != null)
            list.remove(p);
    }
    /**
     * returns an iterable collection of the k most frequently accessed elements.
     * @param k
     * @return
     * @throws IllegalArgumentException 
     */
    public Iterable<E> getFavorites(int k) throws IllegalArgumentException{
        if(k < 0 || k > size())
            throw new IllegalArgumentException("Invalid k");
        PositionalList<E> result = new LinkedPositionalList<>();
        Iterator<Item<E>> iter = list.iterator();
        for(int j = 0;j < k;j++)
            result.addLast(iter.next().getValue());
        return result;
    }
    // </editor-fold>
}
