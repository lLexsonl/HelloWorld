/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FirstExercises;

/**
 *
 * @author MIPC
 */
public class Counter {
    private int count;
    //Contructores
    public Counter(){}
    public Counter(int initial){
        this.count = initial;
    }
    //methods
    public int getCount(){
        return this.count;
    }
    public void increment(){
        this.count += 1;
    }
    public void incremente(int delta){
        this.count += delta;
    }
    public void reset(){
        this.count = 0;
    }
    
    /**
     * Retornos:
     * Metodo que saca el valor absoluto de un numero.
     * @return double.
     * @param value es el valor que se recive como argumento del metodo abs.
     */
    public double abs(double value){
        if(value < 0)
            return -value;
        return value;
    }
}
