/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generics;

/**
 *
 * @author MIPC
 */
public class Portafolio <T>{
    T[] data;
    public Portafolio(int capacity){
        data = (T[]) new Object[capacity];
    }
    public T get(int index){return data[index];}
    public void set(int index, T element){data[index] = element;}
}
