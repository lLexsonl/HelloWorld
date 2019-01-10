/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedList;

import Queue.Deque;

/**
 *
 * @author MIPC
 * @param <E>
 */
public class DoublyLinkedList<E> implements Deque<E>{
    private static class Node<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }
        public E getElement(){return element;}
        public Node<E> getPrev(){return prev;}
        public Node<E> getNext(){return next;}
        public void setPrev(Node<E> p){prev = p;}
        public void setNext(Node<E> n){next = n;}
    }
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;
    
    public DoublyLinkedList(){
        header = new Node<>(null, null,null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }
    @Override
    public int size(){return size;}
    @Override
    public boolean isEmpty(){ return size == 0;}
    @Override
    public E first(){
        if(isEmpty()) return null;
        return header.getNext().getElement();
    }
    @Override
    public E last(){
        if(isEmpty()) return null;
        return trailer.getPrev().getElement();
    }
    @Override
    public void addFirst(E e){
        addBetween(e, header, header.getNext());
    }
    @Override
    public void addLast(E e){
        addBetween(e,trailer.getPrev(),trailer);
    }
    @Override
    public E removeFirst(){
        if(isEmpty()) return null;
        return remove(header.getNext());
    }
    @Override
    public E removeLast(){
        if(isEmpty()) return null;
        return remove(trailer.getPrev());
    }
    private void addBetween(E e, Node<E> predecessor, Node<E> succesor){
        Node<E> newest = new Node<>(e, predecessor, succesor);
        predecessor.setNext(newest);
        succesor.setPrev(newest);
        size += 1;
    }
    private E remove(Node<E> node){
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size -= 1;
        return node.getElement();
    }
}
