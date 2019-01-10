/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

/**
 *
 * @author MIPC
 * @param <E> any type
 */
public class ArrayQueue<E> implements Queue<E> {
    private static final int CAPACITY = 1000;
    private E[] data;
    private int f = 0;
    private int sz = 0;
    //Constructors
    public ArrayQueue(){this(CAPACITY);}
    public ArrayQueue(int capacity){
        data = (E[]) new Object[capacity];
    }
    /**
     * Returns the number of elements in the queue.
     * @return 
     */
    @Override
    public int size(){return sz;}
    /**
     *Test whetther the queue is empty.
     * @return
     */
    @Override
    public boolean isEmpty(){return (sz == 0);}
    /**
     *Inserts an element at the rear of the queue.
     * @param e
     * @throws IllegalStateException
     */
    @Override
    public void enqueue(E e)throws IllegalStateException{
        if(sz == data.length) throw new IllegalStateException("Queue is full");
        int avail = (f + sz)%data.length;
        data[avail] = e;
        sz += 1;
    }

    /**
     *Return, but does not remove, the first element of the queue(null if empty).
     * @return
     */
    @Override
    public E first(){
        if(isEmpty()) return null;
        return data[f];
    }
    /**
     *Removes and returns the first element of the queue(null if empty).
     * @return
     */
    @Override
    public E dequeue(){
        if(isEmpty()) return null;
        E answer = data[f];
        data[f] = null;
        f = (f + 1)%data.length;
        sz -= 1;
        return answer;
    }
}
