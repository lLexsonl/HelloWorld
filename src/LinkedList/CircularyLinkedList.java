/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedList;

/**
 *
 * @author MIPC
 */
public class CircularyLinkedList<E> {
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
    private Node<E> tail = null;
    private int size = 0;
    public CircularyLinkedList(){}
    public int size(){return size;}
    public boolean isEmpty(){ return size == 0;}
    public E first(){
        if(isEmpty()) return null;
        return tail.getNext().getElement();
    }
    public E last(){
        if(isEmpty()) return null;
        return tail.getElement();
    }
    public void rotate(){
        if(tail != null)
            tail= tail.getNext();
    }
    public void addFirst(E e){
        if(size == 0){
            tail = new Node<>(e,null);
            tail.setNext(tail);
        }else{
            Node<E> newest = new Node<>(e,tail.getNext());
            tail.setNext(newest);
        }
        size += 1;
    }
    public void addLast(E e){
        addFirst(e);
        tail = tail.getNext();
    }
    public E removeFirst(){
        if(isEmpty()) return null;
        Node<E> head = tail.getNext();
        if(head == tail) tail = null;
        else tail.setNext(head.getNext());
        size -= 1;
        return head.getElement();
    }
}
