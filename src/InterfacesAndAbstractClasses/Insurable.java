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
public interface Insurable extends Sellable, Transportable{
    public int insuredValue();
}
