/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

/**
 *
 * @author user GiitHub: N3ll
 * @param <T> any type
 */
public class CircularArrayDeque<T> implements Deque<T>{
    private T[] deque;
    private int front;
    private int rear;
    private int count;
    private static int DEFAULT_CAPACITY = 25;
    
    public CircularArrayDeque(int capacity){
        deque = (T[]) new Object[capacity];
        front = count = 0;
        rear = 1;
    }
    
    public CircularArrayDeque(){ this(DEFAULT_CAPACITY);}
    
    @Override
    public void addFirst(T newEntry){
        ensureCapacity();
        deque[front] = newEntry;
        front = (front - 1 + deque.length) % deque.length;
        System.out.println("front " + front);
        count++;
    }
    
    @Override
    public void addLast(T newEntry){
        ensureCapacity();
        deque[rear] = newEntry;
        rear = (rear + 1) % deque.length;
        System.out.println("back " + rear);
        count++;
    }
    
    @Override
    public T removeFirst(){
        T result = null;
        if(isEmpty())
            return result;
        result = deque[front];
        deque[front] = null;
        front = (front + 1 + deque.length) % deque.length;
        count--;
        return result;
    }
    
    @Override
    public T removeLast(){
        T result = null;
        if(isEmpty())
            return result;
        result = deque[rear];
        deque[rear] = null;
        rear = (rear - 1) % deque.length;
        count--;
        return result;
    }
    
    @Override
    public T first(){
        T result = null;
        if(isEmpty())
            return result;
        result = deque[(front + 1 + deque.length) % deque.length];
        return result;
    }
    
    @Override
    public T last(){
        T result = null;
        if(isEmpty())
            return result;
        result = deque[(rear - 1) % deque.length];
        return result;
    }
    
    @Override
    public boolean isEmpty(){
        return count == 0;
    }
    
    public void ensureCapacity(){
        if(size() == deque.length){
            T[] larger = (T[]) (new Object[deque.length * 2]);
            for (int i = 0; i < count; i++) {
                larger[i] = deque[front];
                front = (front + 1) % deque.length;
            }
            front = 0;
            rear = count;
            deque = larger;
        }
    }
    
    @Override
    public int size(){
        return count;
    }
    
}
