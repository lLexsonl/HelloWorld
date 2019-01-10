/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author MIPC
 * @param <E>
 */
public class ArrayList<E> implements List<E>{
    //Instance variables
    public static final int CAPACITY = 16;
    private E[] data;
    private int size;
    //Constructors
    public ArrayList(){this(CAPACITY);}
    public ArrayList(int capacity){
        data = (E[]) new Object[capacity];
    }
    @Override
    public int size(){return size;}
    @Override
    public boolean isEmpty(){return (size == 0);}
    @Override
    public E get(int i)throws IndexOutOfBoundsException{
        checkIndex(i,size);
        return data[i];
    }
    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException{
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }
    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException{
        checkIndex(i, size + 1);
        if(size == data.length)
            resize(2 * data.length);
        for(int k = size - 1;k >= i; k--)
            data[k + 1] = data[k];
        data[i] = e;
        size += 1;
    }
    @Override
    public E remove(int i) throws IndexOutOfBoundsException{
        checkIndex(i, size);
        E temp = data[i];
        for(int k = i; k < size - 1;k++)
            data[k] = data[k + 1];
        data[size - 1] = null;
        size -= 1;
        return temp;
    }
    /**
     * Cheks whether the given index is in the range[0, n - 1].
     * @param i
     * @param n
     * @throws IndexOutOfBoundsException 
     */
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
        if(i < 0 || i > n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }
    /**
     * Resizes internal array to given capacity >= size.
     * @param capacity 
     */
    protected void resize(int capacity){
        E[] temp = (E[]) new Object[capacity];
        for(int k = 0;k < size;k++)
            temp[k] = data[k];
        data = temp;
    }
    private class ArrayIterator implements Iterator<E>{
        private int j = 0;
        private boolean removable = false;
        
        @Override
        public boolean hasNext(){return j < size;}
        @Override
        public E next()throws NoSuchElementException{
            if(j == size) throw new NoSuchElementException("No next element");
            removable = true;
            return data[j + 1];
        }
        @Override
        public void remove() throws IllegalStateException{
            if(!removable)throw new IllegalStateException("nothing to remove");
            ArrayList.this.remove(j - 1);
            j -= 1;
            removable = false;
        }
    }
    public Iterator<E> iterator(){
        return new ArrayIterator();
    }
}
