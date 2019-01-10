/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Progression;

/**
 *
 * @author MIPC
 */
public class GeometricProgression extends Progression{
    protected long base;
    public GeometricProgression(){this(2,1);}
    public GeometricProgression(long b){this(b,1);}
    public GeometricProgression(long b, long start){
        super(start);
        base = b;
    }
    @Override
    protected void advance(){
        current *= base;
    }
}
