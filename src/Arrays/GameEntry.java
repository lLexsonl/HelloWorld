/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arrays;

/**
 *
 * @author MIPC
 */
public class GameEntry {
    private String name;
    private int score;
    
    public GameEntry(String n, int s){
        name = n;
        score = s;
    }
    public String getName(){return name;}
    public int getScore(){return score;}
    @Override
    public String toString(){
        return "(" + name + ", " + score + ")";
    }
}
