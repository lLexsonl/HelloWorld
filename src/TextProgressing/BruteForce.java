/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextProgressing;

import Hash.ChainHashMap;
import Map.Map;
import java.util.Arrays;

/**
 * The brute-force algorithmic desing pattern
 * @author Goodrich, Tamassia, Goldwasser
 */
public class BruteForce {
    //<editor-fold defaultstate="collapsed" desc="Find a pattern in a text using a brute force algorithm">
    /**
     * Returns the lowest index at which substring pattern begins in text (or else - 1).
     * @param text
     * @param pattern string that we want to find
     * @return the index of the pattern
     */
    public static int findBrute(char[] text, char[] pattern){
        int n = text.length;
        int m = pattern.length;
        for(int i = 0; i <= n - m; i++){                    //try every starting index within text
            int k = 0;                                      //k is index into pattern
            while(k < m && text[i + k] == pattern[k])       //kth character of pattern matches
                k++;
            if(k == m)                                      //if we reach the end of the pattern,
                return i;                                   //substring text [i...i+m-1] is a match
        }
        return -1;                                          //search failed
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Find a pattern in a text using a Boyer More algorithm">
    /**
     * Returns the lowest index at which substring pattern begins in text(or else -1).
     * @param text
     * @param pattern
     * @return 
     */
    public static int findBoyerMoore(char[] text, char[] pattern){
        int n = text.length;
        int m = pattern.length;
        if(m == 0)return 0;                                         //trivial search for empty string
        Map<Character, Integer> last = new ChainHashMap<>();        //the 'last' map
        for(int i = 0; i < n; i++)
            last.put(text[i], -1);                                  //set - 1 as default for all text characters
        for(int k = 0; k < m; k++)
            last.put(pattern[k], k);                                //rightmost ocurrence in the pattern is last
        //start with the end of the pattern aligned at index m-1 of the text
        int i = m - 1;                                              //an index into the text
        int k = m - 1;                                              //an index into the pattern
        while(i < n){
            if(text[i] == pattern[k]){                              //a matching caracter
                if(k == 0)return i;                                 //entire pattern has been found
                i--;                                                //otherwise, examine previous
                k--;                                                //characters of text/pattern
            }else{
                i += m - Math.min(k, 1 + last.get(text[i]));        //case analysis for the jump step
                k = m - 1;                                          //restart at end of pattern
            }
        }
        return -1;                                                  //pattern has never found
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="An implementation of the KMP pattern-matching algorithm">
    /**
     * Returns the lowest index at which substring pattern begins in the text (or else -1).
     * @param text
     * @param pattern
     * @return 
     */
    public static int findKMP(char[] text, char[] pattern){
        int n = text.length;
        int m = pattern.length;
        if(m == 0)return 0;                                 //trivial search for empty string
        int[] fail = computeFailKMP(pattern);               //computed by private utility
        int j = 0;                                          //index into text
        int k = 0;                                          //index into pattern
        while(j < n){
            if(text[j] == pattern[k]){                      //pattern[0...k] matched thus far
                if(k == m - 1)return j - m + 1;             //match is complete
                j++;                                        //otherwise, try to entend match
                k++;
            }else if(k > 0)
                k = fail[k - 1];                            //reuse suffix of P[0..k-1]
            else
                j++;
        }
        return -1;                                          //reached end without match
    }
    /**
     * An implementation of the computeFailKMP utility in support of the KMP pattern-matching algorithm.
     * @param pattern
     * @return 
     */
    private static int[] computeFailKMP(char[] pattern){
        int m = pattern.length;
        int[] fail = new int[m];                            //by default, all overlaps are zero
        int j = 1;
        int k = 0;
        while(j < m){                                       //compute fail[j] during this pass, if nonzero
            if(pattern[j] == pattern[k]){                   //k + 1 characters match thus far
                fail[j] = k + 1;
                j++;
                k++;
            }else if(k > 0)                                 //k follows a matching prefix
                k = fail[k - 1];
            else                                            //no match found starting at j
                j++;
        }
        return fail;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Main">
    public static void main(String[] args){
        /*char[] word = {'a', 'r', 'b', 't', 'e', 'c', 'o', 'c', 'o','e','r','v','e', 'r'};
        char[] pattern = {'c', 'o', 'c', 'o'};
        char[] pattern1 = {'v', 'e', 'r'};
        if(findBoyerMoore(word, pattern) >= 0)
            System.out.println(String.format("La palabra %s está contenida en el texto %s.", Arrays.toString(pattern),Arrays.toString(word)));
        else
            System.out.println(String.format("La palabra %s NO está contenida en el texto %s.", Arrays.toString(pattern), Arrays.toString(word)));
        */
        System.out.println(findBrute("ababa".toCharArray(), "aba".toCharArray()));
    }
    //</editor-fold>
}
