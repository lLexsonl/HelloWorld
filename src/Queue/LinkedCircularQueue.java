/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

import LinkedList.CircularyLinkedList;

/**
 *
 * @author MIPC
 * @param <E>
 */
public class LinkedCircularQueue<E> implements CircularQueue<E>{
private CircularyLinkedList<E> list = new CircularyLinkedList<>();
    public LinkedCircularQueue(){}
    @Override
    public int size(){return list.size();}
    @Override
    public boolean isEmpty(){return list.isEmpty();}
    @Override
    public void enqueue(E element){list.addLast(element);}
    @Override
    public E first(){return list.first();}
    @Override
    public E dequeue(){return list.removeFirst();}
    @Override
    public void rotate(){
        list.rotate();
    }
}
