/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

/**
 *Solving the Josephus Problem using a Queue.
 * @author Goodrich
 */
public class Josephus {/**
 * Computes the winner of the Josephus problem using circular queue.
 * @param <E>
 * @param queue
 * @param k
 * @return 
 */
    public static <E> E Josephus(CircularQueue<E> queue, int k){
        if(queue.isEmpty()) return null;
        while(queue.size() > 1){
            for(int i = 0; i < k - 1;i++)
                queue.rotate();
            E e = queue.dequeue();
            System.out.println("      " + e + " is out");
        }
        return queue.dequeue();
    }
    /**
     * Builds a circular queue from an array of objects.
     * @param <E>
     * @param a
     * @return 
     */
    public static <E> CircularQueue<E> buildQueue(E a[]){
        CircularQueue<E> queue = new LinkedCircularQueue<>();
        for (E a1 : a) {
            queue.enqueue(a1);
        }
        return queue;
    }/**
     * Tester method.
     * @param args 
     */
    public static void main(String[] args){
        String[] a1 = {"Alice","Bob","Cindy","Doug","Ed","Fred"};
        String[] a2 = {"Gene","Hope","Irene","jack","Kim","Lance"};
        String[] a3 = {"Mike","Roberto"};
        System.out.println("First winner is " + Josephus(buildQueue(a1), 3));
        System.out.println("Second winner is " + Josephus(buildQueue(a2), 10));
        System.out.println("Third winner is " + Josephus(buildQueue(a3), 7));
    }
}
