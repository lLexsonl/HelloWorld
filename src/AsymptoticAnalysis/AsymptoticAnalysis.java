/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AsymptoticAnalysis;

import java.util.Arrays;

/**
 *
 * @author MIPC
 */
public class AsymptoticAnalysis {
    /** Returns true if there is no element common to all three arrays.
    Devuelve verdadero si no hay ningún elemento común a las tres matrices
     * @param groupA.
     * @param groupB
     * @param groupC
     * @return */
    public static boolean disjoint1(int[] groupA, int[] groupB, int[] groupC){
        for(int a : groupA)
            for(int b : groupB)
                for(int c : groupC)
                    if((a == b) && (b == c))
                        return false;
        return true;
    }
    /**Returns true if there is no element common to all three arrays.
    Devuelve verdadero si no hay ningún elemento común a las tres matrices
     * @param groupA.
     * @param groupB
     * @param groupC
     * @return */
    public static boolean disjoint2(int[] groupA, int[] groupB, int[] groupC){
        for(int a : groupA)
            for(int b : groupB)
                if(a == b)
                    for(int c : groupC)
                        if(a == c)
                            return false;
        return true;
    }
    /**Returns true if there are no duplicate elements in the array.
    Devuelve verdadero si no hay elementos duplicados en la matriz
     * @param data.
     * @return */
    public static boolean unique1(int[] data){
        int n = data.length;
        for(int j = 0;j < n - 1;j++)
            for(int k = j + 1;k < n;k++)
                if(data[j] == data[k])
                    return false;
        return true;
    }
    /** Returns true if there are no duplicate elemnts in the array.
    Devuelve true si no hay elementos duplicados en la matri
     * @param data
     * @return */
    public static boolean unique2(int[] data){
        int n = data.length;
        int[] temp = Arrays.copyOf(data, n);
        Arrays.sort(temp);
        for(int j = 0;j < n - 1;j++)
            if(temp[j] == temp[j+1])
                return false;
        return true;
    }
    /**Returns an array a such that, for all j, a[j] equals yhe average of x[0,..., x[j]
     * Devuelve una matriz a tal que, para todos j, a [j] es igual al promedio de x [0], ... , x [j].
     * @param x.
     * @return */
    public static double[] prefixAverage(double[] x){
        int n = x.length;
        double[] a = new double[n];
        for(int j = 0; j < n;j++){
            double total = 0;
            for(int i = 0; i <= j; i++)
                total += x[i];
            a[j] = total / (j + 1);
        }
        return a;
    }
    /** Returns an array a such that, for all j, a[j] equals the average of x[0],...,x[j].
     * @param x
     * @return 
     */
    public static double[] prefixAverage2(double[] x){
        int n = x.length;
        double[] a = new double[n];
        double total = 0;
        for(int j = 0; j< n; j++){
            total += x[j];
            a[j] = total/(j + 1);
        }
        return a;
    }
    /** Returns index j such that data[j] == val, or -1 if no such element.
     * @param data
     * @param val
     * @return 
     */
    public static int arrayFind(int[] data, int val){
        int n = data.length;
        int j = 0;
        while(j < n){
            if(data[j] == val)
                return j;
            j +=1;
        }
        return -1;
    }
}
