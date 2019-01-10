/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PriorityQueue;

import java.util.Comparator;

/**
 * A DefaultComparator class that implements a comparator 
 * based upon the natural ordering of its element type.
 * @author MIPC
 * @param <E> Any type.
 */
public class DefaultComparator<E> implements Comparator<E> {
    @Override
    public int compare(E a, E b)throws ClassCastException{
        return ((Comparable<E>)a).compareTo(b);
    }
}
