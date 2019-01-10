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
public class Scoreboard {
    private int numEntries = 0;
    private GameEntry[] board;
    
    public Scoreboard(int capacity){
        board = new GameEntry[capacity];
    }
    public void add(GameEntry e){
        int newScore = e.getScore();
        
        if(numEntries < board.length || newScore > board[numEntries - 1].getScore()){
            if(numEntries < board.length)
                numEntries += 1;
            int j = numEntries -1;
            while(j > 0 && board[j - 1].getScore() < newScore){
                board[j] = board[j - 1];
                j -= 1;
            }
            board[j] = e;
        }
    }
    public GameEntry remove(int i) throws IndexOutOfBoundsException{
        if(i < 0 || i >= numEntries)
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        GameEntry temp = board[i];
        for(int j = i; j < numEntries - 1; j++)
            board[j] = board[j + 1];
        board[numEntries - 1] = null;
        numEntries -= 1;
        return temp;
    }
}
