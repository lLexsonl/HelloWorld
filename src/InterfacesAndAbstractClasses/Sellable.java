/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacesAndAbstractClasses;

/**
 *
 * @author MIPC
 */
/**Interface for objects that can be sold*/
public interface Sellable {
    /**Returns a descrition of the object
     * @return .*/
    public String description();
    /**Returns the lidt price in cents
     * @return .*/
    public int listPrice();
    /**Returns the lowest price in cents we will accept
     * @return .*/
    public int lowestPrice();
}
