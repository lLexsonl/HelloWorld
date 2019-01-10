/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack;

/**
 *
 * @author MIPC
 * @param <E>
 */
public class ArrayStack <E> implements Stack<E>{
    public static final int CAPACITY = 1000;
    private E[] data;
    private int t = -1;
    public ArrayStack(){this(CAPACITY);}
    public ArrayStack(int capacity){
        data = (E[]) new Object[capacity];
    }
    @Override
    public int size(){return (t + 1);}
    @Override
    public boolean isEmpty(){return (t == -1);}
    @Override
    public void push(E e)throws IllegalStateException{
        if(size() == data.length)throw new IllegalStateException("Stack is full");
        data[++t] = e;
    }
    @Override
    public E top(){
        if(isEmpty()) return null;
        return data[t];
    }
    @Override
    public E pop(){
        if(isEmpty()) return null;
        E answer = data[t];
        data[t] = null;
        t -= 1;
        return answer;
    }
    public static <E> void reverse(E[] a){
        Stack<E> buffer = new ArrayStack<>(a.length);
        for (E a1 : a)
            buffer.push(a1);
        for(int i = 0; i < a.length;i++)
            a[i] = buffer.pop();
    }
}
