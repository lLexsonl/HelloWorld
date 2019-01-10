/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generics;

/**
 *
 * @author MIPC
 * @param <A>
 * @param <B>
 * A diferencia del estilo clasico podemos hacer las siguientes asignaciones sin ningun problema
 * String stock = bid.getFirst();
 * double price = bid.getSecond();
 */
public class Pair<A,B> {
    A first;
    B second;
    public Pair(A a, B b){
        first = a;
        second = b;
    }
    public A getFirst() { return first;}
    public B getSecond() { return second;}
}
