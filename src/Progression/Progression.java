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
public abstract class Progression {
    protected long current;
    
    public Progression(){this(0);}
    public Progression(long start){
        current = start;
    }
    public long nextValue(){
        long answer = current;
        advance();
        return answer;
    }
    public void printProgression(int n){
        System.out.print(nextValue());
        for(int j = 1; j < n;j++)
            System.out.print(" " + nextValue());
        System.out.println();
    }
    protected abstract void advance();
}
