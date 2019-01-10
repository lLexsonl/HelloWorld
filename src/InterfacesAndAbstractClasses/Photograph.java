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
public class Photograph implements Sellable{
    private String descript;
    private int price;
    private boolean color;
    
    public Photograph(String desc,int p, boolean c){
        descript = desc;
        price = p;
        color = c;
    }
    
    @Override
    public String description(){return descript;}
    @Override
    public int listPrice(){return price;}
    @Override
    public int lowestPrice(){return price/2;}
    public boolean isColor(){return color;}
}
