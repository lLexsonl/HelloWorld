/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

/**
 *
 * @author MIPC
 * @param <E> any type.
 */
public class ArrayDeque<E> implements Deque<E> {
    private static final int CAPACITY = 1000;
    private E[] data;
    private int f;
    private int sz;
    private int rear;
    //Constructors
    public ArrayDeque(){this(CAPACITY);}
    public ArrayDeque(int capacity){
        data = (E[]) new Object[capacity];
        f = sz = 0;
        rear = 1;
    }
    @Override
    public int size(){return sz;}
    @Override
    public boolean isEmpty(){return (sz == 0);}
    @Override
    public E first(){
        if(isEmpty()) return null;
        return data[(f + 1 + data.length)%data.length];
    }
    @Override
    public E last(){
        if(isEmpty())return null;
        return data[(rear - 1)%data.length];
    }
    @Override
    public void addLast(E e)throws IllegalStateException{
        if(sz == data.length) throw new IllegalStateException("Deque is full");
        data[rear] = e;
        rear = (rear + 1)%data.length;
        sz += 1;
    }
    @Override
    public void addFirst(E e)throws IllegalStateException{
        if(sz == data.length)throw new IllegalStateException("Deque is full");
        data[f] = e;
        f = (f - 1 + data.length)%data.length;
        sz += 1;
    }
    @Override
    public E removeFirst(){
        if(isEmpty()) return null;
        E answer = data[f];
        data[f] = null;
        f = (f + 1 + data.length)%data.length;
        sz -= 1;
        return answer;
    }
    @Override
    public E removeLast(){
        if(isEmpty()) return null;
        E answer = data[rear];
        data[rear] = null;
        rear = (rear - 1)%data.length;
        sz -= 1;
        return answer;
    }
}
