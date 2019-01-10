/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedList;

/**
 *
 * @author MIPC
 * @param <E>
 */
public class SinglyLinkedList<E> implements Cloneable{
    private static class Node<E>{
        private E element;
        private Node<E> next;
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
        public E getElement(){return element;}
        public Node<E> getNext(){return next;}
        public void setNext(Node<E> n){next = n;}
    }
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size;
    public SinglyLinkedList(){}
    public int size(){return size;}
    public boolean isEmpty(){return size == 0;}
    public E first(){
        if(isEmpty()) return null;
        return head.getElement();
    }
    public E last(){
        if(isEmpty()) return null;
        return tail.getElement();
    }
    public void addFirt(E e){
        head = new Node<>(e, head);
        if(size == 0)
            tail = head;
        size += 1;
    }
    public void addLast(E e){
        Node<E> newest = new Node<> (e,null);
        if(isEmpty())
            head = newest;
        else
            tail.setNext(newest);
        tail = newest;
        size += 1;
    }
    public E removeFirst(){
        if(isEmpty()) return null;
        E anwer = head.getElement();
        head = head.getNext();
        size -= 1;
        if(size == 0)
            tail = null;
        return anwer;
    }
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        SinglyLinkedList other = (SinglyLinkedList) o;
        if(size != other.size) return false;
        Node walkA = head;
        Node walkB = other.head;
        while(walkA != null){
            if(!walkA.getElement().equals(walkB.getElement())) return false;
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }
        return true;
    }
    @Override
    public SinglyLinkedList<E> clone() throws CloneNotSupportedException{
        SinglyLinkedList<E> other = (SinglyLinkedList) super.clone();
        if(size > 0){
            other.head = new Node<>(head.getElement(), null);
            Node<E> walk = head.getNext();
            Node<E> otherTail = other.head;
            while(walk != null){
                Node<E> newest = new Node<> (walk.getElement(),null);
                otherTail.setNext(newest);
                otherTail = newest;
                walk = walk.getNext();
            }
        }
        return other;
    }
    /**
     *  A robust implementation of the hashCode method for the SinglyLinkedList class.
     * @return 
     */
    @Override
    public int hashCode(){
        int h = 0;
        for(Node walk = head; walk != null; walk = walk.getNext()){
            h ^= walk.getElement().hashCode();
            h = (h<<5) | (h>>>27);
        }
        return h;
    }
}
