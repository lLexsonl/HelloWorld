/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextProgressing;

/**
 * 
 * @author Goodrich, Tamassia
 */
public class DynamicProgramming {
    /**
     * Dynamic programming algorithm for the matrix chain product problem.
     * @param d
     * @return 
     */
    public static int[][] matrixChain(int[] d){
        int n = d.length - 1;
        int[][] N = new int[n][n];
        for(int b = 1; b < n; b++)
            for(int i = 0; i < n - b; i++){
                int j = i + b;
                N[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++)
                    N[i][j] = Math.min(N[i][j], N[i][k] + N[k + 1][j] + d[i] * d[k + 1] * d[j + 1]);
            }
        return N;
    }
    /**
     * Dynamic programming algorithm for the LCS problem.
     * @param X 
     * @param Y
     * @return 
     */
    public static int[][] LCS(char[] X, char[] Y){
        int n = X.length;
        int m = Y.length;
        int[][] L = new int[n + 1][m + 1];
        for(int j = 0; j < n; j++)
            for(int k = 0; k < m; k++)
                if(X[j] == Y[k])                        //align this match
                    L[j + 1][k + 1] = L[j][k] + 1;
                else                                    //choose to ignore character
                    L[j + 1][k + 1] = Math.max(L[j][k + 1], L[j + 1][k]);
        return L;
    }
    /**
     * Reconstructing the longest common subsequence.
     * @param X
     * @param Y
     * @param L
     * @return 
     */
    public static char[] reconstructLCS(char[] X, char[] Y, int[][] L){
        StringBuilder solution = new StringBuilder();
        int j = X.length;
        int k = Y.length;
        while(L[j][k] > 0){
            if(X[j - 1] == Y[j - 1]){                           //common characters remain
                solution.append(X[j - 1]);
                j--;
                k--;
            }else if(L[j - 1][k] >= L[j][k - 1])
                j--;
            else
                k--;
        }
        //return left-to-right version, as char array
        return solution.reverse().toString().toCharArray();        
    }
}
