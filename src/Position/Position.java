/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Position;

/**
 *
 * @author MIPC
 * @param <E>
 */
public interface Position <E> {
    /**
     * Returns the element stored at this position.
     * @return the stored element.
     * @throws IllegalStateException if podition no lonher valid.
     */
    E getElement() throws IllegalStateException;
}
