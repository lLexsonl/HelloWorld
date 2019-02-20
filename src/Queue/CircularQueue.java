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
public interface CircularQueue<E> extends Queue<E>{
    /**
     * Rotates the front element of the queue to back of the queue.
     * This does nothing if the queue is empty.
     */
    void rotate();
}
