/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import Map.AbstractSortedMap;
import Position.Position;
import PriorityQueue.Entry;
import Tree.LinkedBinaryTree.Node;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Implementing ADT TreeMap.
 * @author Goodrich
 * @param <K>
 * @param <V>
 */
public class TreeMap <K,V> extends AbstractSortedMap<K,V>{
    //<editor-fold defaultstate="collapsed" desc="BalancedBinaryTree class">
    protected static class BalanceableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K,V>>{
        //<editor-fold defaultstate="collapsed" desc="BSTNode class">
        protected static  class BSTNode<E> extends Node<E>{
            private int aux = 0;
            BSTNode(E e, Node<E> parent, Node<E> leftChild, Node<E> rightChild){
                super(e, parent, leftChild, rightChild);
            }
            public int getAux(){return aux;}
            public void setAux(int value){
                aux = value;
            }
        }
        //</editor-fold>
        //positional-based methods related to aux field
        /**
         * Returns 
         * @param p
         * @return 
         */
        public int getAux(Position<Entry<K,V>> p){
            return ((BSTNode<Entry<K,V>>) p).getAux();
        }
        /**
         * 
         * @param p
         * @param value 
         */
        public void setAux(Position<Entry<K,V>> p, int value){
            ((BSTNode<Entry<K,V>>) p).setAux(value);
        }
        @Override
        protected Node<Entry<K,V>> createNode(Entry<K,V> e, Node<Entry<K,V>> parent,
                                                Node<Entry<K,V>> left, Node<Entry<K,V>> right){
            return new BSTNode<>(e, parent, left, right);
        }
        /**
         * Relinks a parent node with its oriented child node.
         * @param parent
         * @param child
         * @param makeLeftChild 
         */
        private void relink(Node<Entry<K,V>> parent, Node<Entry<K,V>> child,
                boolean makeLeftChild){
            child.setParent(parent);
            if(makeLeftChild)
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        /**
         * Rotates position p above its parent. Switches between these
         * consfigurations, depending on wheter p is a or p is b.
         * @param p 
         */
        public void rotate(Position<Entry<K,V>> p){
            Node<Entry<K,V>> x = validate(p);
            Node<Entry<K,V>> y = x.getParent();
            Node<Entry<K,V>> z = y.getParent();
            if(z == null){
                root = x;
                x.setParent(null);
            }else
                relink(z, x, y == z.getLeft());
            if(x == y.getLeft()){
                relink(y ,x.getRight(), true);
                relink(x, y, false);
            }else{
                relink(y, x.getLeft(),false);
                relink(x, y, true);
            }
        }
        /**
         * Returns the position that becomes the root of the restructured subtree.
         * @param x
         * @return 
         */
        public Position<Entry<K,V>> restructure(Position<Entry<K,V>> x){
            Position<Entry<K,V>> y = parent(x);
            Position<Entry<K,V>> z = parent(y);
            if((x == right(y)) == (y == right(z))){
                rotate(y);
                return y;
            }else{
                rotate(x);
                rotate(x);
                return x;
            }
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Atributes and Constructors">
    protected BalanceableBinaryTree<K,V> tree = new BalanceableBinaryTree<>();
    /**
     * Constructs an empty map using the natural ordering of keys.
     */
    public TreeMap(){
        super();                //the AbstractSortedMap constructor
        tree.addRoot(null);     //create a sentinel leaf as root
    }
    /**
     * Constructs an empty map using the given comparator to oreder keys.
     * @param comp 
     */
    public TreeMap(Comparator<K> comp){
        super(comp);                //the AbstractSortedMap constructor
        tree.addRoot(null);         //create a sentinel leaf as root
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Public Methods Overrides Map methods">
    /**
     * Returns the number of entries in the map.
     * @return 
     */
    @Override
    public int size(){
        return (tree.size() - 1)/2;}        //only internal nodes have entries
    /**
     * Returns the value associated with the specified key (or else null).
     * @param key
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public V get(K key)throws IllegalArgumentException{
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        rebalanceAccess(p);
        if(isExternal(p)) return null;
        return p.getElement().getValue();
    }
    /**
     * Associates the given value with the given key, returning any overriden value.
     * @param key
     * @param value
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public V put(K key, V value) throws IllegalArgumentException{
        checkKey(key);
        Entry<K,V> newEntry = new MapEntry<> (key, value);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if(isExternal(p)){
            expandExternal(p, newEntry);
            rebalanceInsert(p);
            return null;
        }else{
            V old = p.getElement().getValue();
            set(p, newEntry);
            rebalanceAccess(p);
            return old;
        }
    }
    /**
     * Removes the entry having key (if any) and returns its associated value.
     * @param key
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public V remove(K key)throws IllegalArgumentException{
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if(isExternal(p)){
            rebalanceAccess(p);
            return null;
        }else{
            V old = p.getElement().getValue();
            if(isInternal(left(p)) && isInternal(right(p))){
                Position<Entry<K,V>> repacement = treeMax(left(p));
                p = repacement;
            }
            Position<Entry<K,V>> leaf = (isExternal(left(p))?left(p) : right(p));
            Position<Entry<K,V>> sib = sibling(leaf);
            remove(leaf.getElement().getKey());
            remove(p.getElement().getKey());
            rebalanceDelete(sib);
            return old;
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Private utilities">
    /**
     * Utility used when inserting a new entry at a leaf of the tree
     * @param p
     * @param entry 
     */
    private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry){
        tree.set(p ,entry);
        tree.addLeft(p, null);
        tree.addRight(p,null);
    }
    /**
     * Returns the position in p's subtree having given key(or else the terminal leaf).
     * @param p
     * @param key
     * @return 
     */
    private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key){
        if(isExternal(p))
            return p;
        int comp = compare(key,p.getElement().getKey());
        if(comp == 0)
            return p;
        else if(comp < 0)
            return treeSearch(left(p), key);
        else
            return treeSearch(right(p), key);
        
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Protected utilities">
    protected Position<Entry<K,V>> root(){return tree.root();}
    protected Position<Entry<K,V>> parent(Position<Entry<K,V>> p){return tree.parent(p);}
    protected Position<Entry<K,V>> left(Position<Entry<K,V>> p){return tree.left(p);}
    protected Position<Entry<K,V>> right(Position<Entry<K,V>> p){return tree.right(p);}
    protected Position<Entry<K,V>> sibling(Position<Entry<K,V>> p){ return tree.sibling(p);}
    
    protected boolean isRoot(Position<Entry<K,V>> p){ return tree.isRoot(p);}
    protected boolean isExternal(Position<Entry<K,V>> p){ return tree.isExternal(p);}
    protected boolean isInternal(Position<Entry<K,V>> p){ return tree.isInternal(p);}
    protected void set(Position<Entry<K,V>> p, Entry<K,V> e){ tree.set(p, e);}
    protected Entry<K,V> remove(Position<Entry<K,V>> p){ return tree.remove(p);}
    protected void rotate(Position<Entry<K,V>> p){ tree.rotate(p);}
    protected Position<Entry<K,V>> restructure(Position<Entry<K,V>> x){ return tree.restructure(x);}
    
    /**
     * Returns position with the minimal key in the subtree rooted at Position p.
     * @param p
     * @return 
     */
    protected Position<Entry<K,V>> treeMin(Position<Entry<K,V>> p){
        Position<Entry<K,V>> walk = p;
        while(isInternal(walk))
            walk = left(walk);
        return parent(walk);
    }
    /**
     * Returns the position with the maximun key in subtree rooted at Position p.
     * @param p
     * @return 
     */
    protected Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p){
        Position<Entry<K,V>> walk = p;
        while(isInternal(walk))
            walk = right(walk);
        return parent(walk);
    }
    protected void rebalanceInsert(Position<Entry<K,V>> p){}
    protected void rebalanceDelete(Position<Entry<K,V>> p){}
    protected void rebalanceAccess(Position<Entry<K,V>> p){}
    
    //remainder of class is for debug purposes only
    protected void dump(){
        dumpRecurse(root(), 0);
    }
    private void dumpRecurse(Position<Entry<K,V>> p, int depth){
        String indent = (depth == 0 ? "" : String.format("%" + (2*depth) + "s",""));
        if(isExternal(p))
            System.out.println(indent + "leaf");
        else{
            System.out.println(indent + p.getElement());
            dumpRecurse(left(p), depth + 1);
            dumpRecurse(right(p), depth + 1);
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Public methods Override AbstractSortedMap Methods">
    /**
     * Returns the entry having the least key (or null if map is empty).
     * @return entry with least key (or null if map is empty)
     */
    @Override
    public Entry<K,V> firstEntry(){
        if(isEmpty()) return null;
        return treeMin(root()).getElement();
    }
    /**
     * Returns the entry having the greatest key (or null if map is empty).
     * @return 
     */
    @Override
    public Entry<K,V> lastEntry(){
        if(isEmpty()) return null;
        return treeMax(root()).getElement();
    }
    /**
     * Returns the entry with least key greater than or equal to given key(or null if no such key exist).
     * @param key
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Entry<K,V> ceilingEntry(K key) throws IllegalArgumentException{
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if(isInternal(p)) return p.getElement();
        while(!isRoot(p)){
            if(p == left(parent(p)))
                return parent(p).getElement();
            else
                p = parent(p);
        }
        return null;
    }
    /**
     * Returns the entry with greatest key less than or equal to given key(if any).
     * @param key
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Entry<K,V> floorEntry(K key)throws IllegalArgumentException{
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if(isInternal(p)) return p.getElement();
        while(!isRoot(p)){
            if(p == right(parent(p)))
                return parent(p).getElement();
            else
                p = parent(p);
        }
        return null;
    }
    /**
     * Returns the entry with greatest key strictly less than given key(if any).
     * @param key
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Entry<K,V> lowerEntry(K key)throws IllegalArgumentException{
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if(isInternal(p) && isInternal(left(p)))
            return treeMax(left(p)).getElement();
        while(!isRoot(p)){
            if(p == right(parent(p)))
                return parent(p).getElement();
            else
                p = parent(p);
        }
        return null;
    }
    /**
     * Returns the entry with least key strictly greater than given key(or null if such key exist).
     * @param key
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Entry<K,V> higherEntry(K key) throws IllegalArgumentException{
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(root(), key);
        if(isInternal(p) && isExternal(right(p)))
            return treeMin(right(p)).getElement();
        while(!isRoot(p)){
            if(p == left(parent(p)))
                return parent(p).getElement();
            else
                p = parent(p);
        }
        return null;
    }
    /**
     * Returns an iterable collection of all key-value entries of the map.
     * @return 
     */
    @Override
    public Iterable<Entry<K,V>> entrySet(){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
        for(Position<Entry<K,V>> p : tree.inorder())
            if(isInternal(p)) buffer.add(p.getElement());
        return buffer;
    }
    /**
     * Reurns an iterable of entries with keys in range [fromKey, toKey].
     * @param fromKey
     * @param toKey
     * @return 
     */
    @Override
    public Iterable<Entry<K,V>> subMap(K fromKey, K toKey) throws IllegalArgumentException{
        checkKey(fromKey);
        checkKey(toKey);
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(size());
        if(compare(fromKey, toKey) < 0)
            subMapRecurse(fromKey, toKey, root(), buffer);
        return buffer;
    }
    private void subMapRecurse(K fromKey, K toKey, Position<Entry<K,V>>p,
                                ArrayList<Entry<K,V>> buffer){
        if(isInternal(p)){
            if(compare(p.getElement(), fromKey) < 0)
                //p's key is less than fromkey, so any relevant entries are to the right
                subMapRecurse(fromKey, toKey,right(p), buffer);
            else{
                subMapRecurse(fromKey, toKey, left(p), buffer);
                if(compare(p.getElement(), toKey) < 0){
                    buffer.add(p.getElement());
                    subMapRecurse(fromKey, toKey, right(p), buffer);
                }
            }
        }
    }
}   //</editor-fold>
