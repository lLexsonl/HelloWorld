/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

//import Position.LinkedPositionalList;
//import Position.PositionalList;
import Position.Position;
//import java.util.Iterator;

/**
 *Concrete implementation of a binary tree using a node-based, linked structure.
 * @author Goodrich.
 * @param <E> Any type.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    // <editor-fold defaultstate="collapsed" desc="Node Class.">
    protected static class Node<E> implements Position<E>{
        private E element; //an element estored at this node
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        /**
         * Constructs a node with the given element and neighbors.
         * @param e
         * @param above
         * @param leftChild
         * @param rightChild 
         */
        public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild){
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
        }
        // <editor-fold defaultstate="collapsed" desc="accessor methods.">
        @Override
        public E getElement(){return element;}
        public Node<E> getParent(){return parent;}
        public Node<E> getLeft(){return left;}
        public Node<E> getRight(){return right;}
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="update methods.">
        public void setElement(E e){element = e;}
        public void setParent(Node<E> parentNode){parent = parentNode;}
        public void setLeft(Node<E> leftChild){left = leftChild;}
        public void setRight(Node<E> rightChild){right = rightChild;}
        // </editor-fold>
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Factory method.">
    /**
     * Factory fuction to create a new node storing element e.
     * @param e
     * @param parent
     * @param left
     * @param right
     * @return 
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){
        return new Node<>(e, parent, left, right);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="LinkedBinaryTree instance variables.">
    protected Node<E> root = null;
    private int size = 0;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Constructor.">
    public LinkedBinaryTree(){}
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="nonpublic utility.">
    /**
     * Validates the position and returns it as a node.
     * @param p
     * @return
     * @throws IllegalArgumentException 
     */
    protected Node<E> validate(Position<E> p)throws IllegalArgumentException{
        if(!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type.");
        Node<E> node = (Node<E>)p;
        if(node.getParent() == node)
            throw new IllegalArgumentException("p is no longer in the tree.");
        return node;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="accessors methods (not already implements in AbstractBinaryTree.">
    /**
     * Return the number of nodes in the tree.
     * @return 
     */
    @Override
    public int size(){return size;}
    /**
     * Returns the root Position of the tree(or null if tree is empty).
     * @return 
     */
    @Override
    public Position<E> root(){return root;}
    /**
     * Returns the position of p's parent(or null if p is root).
     * @param p
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Position<E> parent (Position<E> p)throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getParent();
    }
    /**
     * Returns the position of p's left child (or null if no child exists).
     * @param p
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Position<E> left(Position<E> p)throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getLeft();
    }
    /**
     * Returns the position of p's right chils(or null if no child exists).
     * @param p
     * @return
     * @throws IllegalArgumentException 
     */
    @Override
    public Position<E> right(Position<E> p)throws IllegalArgumentException{
        Node<E> node = validate(p);
        return node.getRight();
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="update methods suported by this class.">
    /**
     * Places element e at the root of an empty tree and returns its new Position.
     * @param e
     * @return
     * @throws IllegalStateException 
     */
    public Position<E> addRoot(E e)throws IllegalStateException{
        if(!isEmpty()) throw new IllegalStateException("Tree is noot empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }
    /**
     * Creates a new left child of Position p storing element e; returns its Position.
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException 
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> parent = validate(p);
        if(parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child.");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size += 1;
        return child;
    }
    /**
     * Returns a new right of Position p storing element e; returns its Position.
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException 
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> parent = validate(p);
        if(parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child.");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size += 1;
        return child;
    }
    /**
     * Replaces the element at Position p with e and returns the replaced element.
     * @param p
     * @param e
     * @return
     * @throws IllegalArgumentException 
     */
    public E set(Position<E> p, E e)throws IllegalArgumentException{
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }
    /**
     * Attaches trees t1 and t2 as left and right subtrees of external p.
     * @param p
     * @param t1
     * @param t2
     * @throws IllegalArgumentException 
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1,
            LinkedBinaryTree<E> t2) throws IllegalArgumentException{
        Node<E> node = validate(p);
        if(isInternal(p)) throw new IllegalArgumentException("p must be a leaf.");
        size += t1.size() + t2.size();
        if(!t1.isEmpty()){
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if(!t2.isEmpty()){
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }/**
     * Removes the node at Position p and replaces it with its chils, if any.
     * @param p
     * @return
     * @throws IllegalArgumentException 
     */
    public E remove(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        if(numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children.");
        Node<E> child = (node.getLeft() != null ? node.getLeft(): node.getRight());
        if(child != null)
            child.setParent(node.getParent());
        if(node == root)
            root = child;
        else{
            Node<E> parent = node.getParent();
            if(node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size -= 1;
        E temp = node.getElement();
        node.setElement(null);//help garbage collection
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);//our convention for defunct node
        return temp;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Positions and iterator Methods Comenteds.">
    /** Implementacion tomada de la quinta edicion del este libro.
    @Override
    public Iterable<Position<E>> positions(){
        PositionalList<Position<E>> positions = new LinkedPositionalList<>();
        //
        if(size != 0)
            preorderPositions(root(),positions);
        //
        return positions;
    }
    @Override
    public Iterator<E> iterator(){
        Iterable<Position<E>> positions = positions();
        PositionalList<E> elements = new LinkedPositionalList<>();
        for(Position<E> pos : positions)
            elements.addLast(pos.getElement());
        return elements.iterator();//An iterator od elements.
    }*/
    // </editor-fold>
}
