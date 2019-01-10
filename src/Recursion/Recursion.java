/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursion;

import java.util.Arrays;

/**
 *
 * @author MIPC
 */
public class Recursion {
    public static void main(String[] args){
        /*System.out.println(String.format("%d! = %d", 5,factorial(5)));
        drawRuler(1, 3);*/
        int[] max = {-5,0,-1,-2,3,7,5,9,1,2,4,-4};
        System.out.print(String.format("El valor maximo dentro de la matriz= %s es = %d.", Arrays.toString(max),findMax(max)));
        Arrays.sort(max);
        int /*finda = -2, findab = -5,*/ findac = 3;
        System.out.println();
        System.out.print(String.format("El elemento %d se encuentra en la posición %d de la matiz %s.",findac,binarySearchIndx(max, findac), Arrays.toString(max)));
    }
    // <editor-fold defaultstate="collapsed" desc="Factorial">
     /**
     * Factorial.
     * @param n
     * @return
     * @throws IllegalArgumentException 
     */
    public static int factorial(int n) throws IllegalArgumentException{
        if(n < 0)
            throw new IllegalArgumentException();
        else if (n == 0)
            return 1;
        else
            return n * factorial(n - 1);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="English ruler">
    /**
     * Draws an English ruler for the given number of inches and major tick length.
     * @param nInches
     * @param majorLength
     */
    public static void drawRuler(int nInches, int majorLength){
        drawLine(majorLength,0);
        for(int j = 1; j <= nInches; j++){
            drawInterval(majorLength - 1);
            drawLine(majorLength, j);
        }
    }
    private static void drawInterval(int centralLength){
        if(centralLength >= 1){
            drawInterval(centralLength - 1);
            drawLine(centralLength);
            drawInterval(centralLength - 1);
        }
    }
    private static void drawLine(int tickLength, int tickLabel){
        for(int j = 0; j < tickLength; j++)
            System.out.print("-");
        if(tickLabel >= 0)
            System.out.print(" " + tickLabel);
        System.out.print("\n");
    }
    private static void drawLine(int tickLength){
        drawLine(tickLength, -1);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Linear Sum">
    /**
     * Returns the sum of the first n integers of the given array. use time o(n) and space O(n).
     * @param data
     * @param n
     * @return 
     */
    public static int linearSum(int[] data, int n){
        if(n == 0)
            return 0;
        else
            return linearSum(data, n - 1) + data[n - 1];
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Reverse Array">
    /**
     * Reverse the contents of subarray data[low] through data[high] inclusive.
     * @param data
     * @param low
     * @param high 
     */
    public static void reverseArray(int[] data, int low, int high){
        if(low < high){
            int temp = data[low];
            data[low] = data[high];
            data[high] = temp;
            reverseArray(data, low + 1, high - 1);
        }
    }
    /**
     * Reverse the contents of the given array. Form iterative.
     * @param data 
     */
    public static void reverseIterative(int[] data){
        int low = 0, high = data.length - 1;
        while(low < high){
            int temp = data[low];
            data[low + 1] = data[high];
            data[high - 1] = temp;
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Power">
    /**
     * Computes the value of x raised to the nth power, for nonnegstive integer n. time O(n)
     * @param x
     * @param n
     * @return 
     */
    public static double powerOn(double x, int n){
        if(n == 0)
            return 1;
        else
            return x * powerOn(x, n - 1);
    }
    /**
     * Computes the value of the x raise to the nth power, for nonegative integer n. time O(logn).
     * @param x
     * @param n
     * @return 
     */
    public static double power(double x, int n){
        if(n == 0)
            return 1;
        else{
            double partial = power(x, n/2);
            double result = partial * partial;
            if(n%2 == 1)
                result *= x;
            return result;
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Binary Sum">
    /**
     * Returns the sum of subarray data[low] through data[high] inclusive.
     * Use time O(n) but the space is O(logn).
     * @param data
     * @param low
     * @param high
     * @return 
     */
    public static int binarySum(int[] data, int low, int high){
        if(low > high)
            return 0;
        else if(low == high)
            return data[low];
        else{
            int mid = (low + high) / 2;
            return binarySum(data, low, mid) + binarySum(data, mid + 1, high);
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Binary Search">
    /**
     * Returns true if the target value is found in the indicated portion of the data array.
     * This search only considers the array portion from dat{low] to data[high] inclusive.
     * @param data
     * @param target
     * @param low
     * @param high
     * @return 
     */
    private static boolean binarySearch(int data[], int target, int low, int high){
        if(low > high)
            return false;
        else{
            int mid = (low + high)/2;
            if(target == data[mid])
                return true;
            else if( target < data[mid])
                return binarySearch(data, target, low, mid - 1);
            else
                return binarySearch(data, target, mid + 1, high);
        }
    }
    /**
     *  Returns true if the target value is found in the data array.
     * @param data
     * @param target
     * @return 
     */
    public static boolean binarySearchIterative(int[] data, int target){
        int low = 0;
        int high = data.length - 1;
        while(low <= high){
            int mid = (low + high)/2;
            if(target == data[mid])
                return true;
            else if(target < data[mid])
                high = mid - 1;
            else
                low = mid + 1;
        }
        return false;
        
    }
    /**
     * Call to binary search private.
     * @param data
     * @param target
     * @return 
     */
    public static boolean binarySearch(int[] data, int target){
        return binarySearch(data, target, 0, data.length - 1);
    }
    /**
     * Return the index the target and returns -1 if target is not found.
     * @param data
     * @param target
     * @param low
     * @param high
     * @return 
     */
    private static int binarySearchIndx(int data[], int target, int low, int high){
        if(low > high)
            return -1;
        else{
            int mid = (low + high)/2;
            if(target == data[mid])
                return mid;
            else if( target < data[mid])
                return binarySearchIndx(data, target, low, mid - 1);
            else
                return binarySearchIndx(data, target, mid + 1, high);
        }
    }
    public static int binarySearchIndx(int[] data, int target){
        return binarySearchIndx(data, target, 0, data.length - 1);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Fibonacci">
    /**
     * Returns array containing the pair of Fibonacci numbers, F(n) and F(n - 1).
     * @param n
     * @return 
     */
    public static long[] fibonacci(int n){
        if(n <= 1){
            long[] anwer = {n, 0};
            return anwer;
        }else{
            long[] temp = fibonacci(n - 1);
            long[] anwer = {temp[0] + temp[1], temp[0]};
            return anwer;
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Find Max Array">
    /**
     * Describe a recursive algorithm for ﬁnding the maximum element in an array, A, of n elements.
     * What is your running time and space usage? time O(n)
     * @param data
     * @param currentMax
     * @param high
     * @return 
     */
    private static int findMax(int[] data,int currentMax, int high){
        if(high == 0)
            return currentMax;
        else{
            if(currentMax < data[high - 1])
                currentMax = data[high - 1];
            currentMax = findMax(data,currentMax,high- 1);
            return currentMax;
        }
    }
    public static int findMax(int[] data){
        if(data.length > 0){
            int currentMax = data[data.length-1];
            return findMax(data, currentMax, data.length - 1);
        }
        return 0;
    }
    // </editor-fold>
}
