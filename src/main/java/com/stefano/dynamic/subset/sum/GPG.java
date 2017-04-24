package com.stefano.dynamic.subset.sum;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Author stefanofranz
 */
public class GPG {


    public static List<List<Long>> parse(InputStream in) {
        Scanner scanner = new Scanner(in);
        int numberOfCases = scanner.nextInt();
        List<List<Long>> lists = new ArrayList<List<Long>>(numberOfCases);
        for (int i = 0; i < numberOfCases; i++) {
            int numberOfElements = scanner.nextInt();
            List<Long> elements = new ArrayList<Long>(numberOfElements);
            for (int j = 0; j < numberOfElements; j++) {
                elements.add(scanner.nextLong());
            }
            lists.add(elements);
        }
        return lists;
    }

    public static void main(String[] args) {

        String s = "1\n" +
                "4\n" +
                "1 6 5 11\n";


        List<List<Long>> arraysToPartition = parse(new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8)));

        for (List<Long> longList : arraysToPartition) {
            int[] longs = longList.stream().mapToInt(l -> l.intValue()).toArray();
            findMin(longs, longs.length);
        }
    }

    private static long partitionArray(List<Long> arrayToPartition) {
        Collections.sort(arrayToPartition);


        return 0;
    }

    // Function to find the minimum sum
    static long findMinRec(int arr[], int divisionPoint, int sumCalculated, int sumTotal) {
        // If we have reached last element.  Sum of one
        // subset is sumCalculated, sum of other subset is
        // sumTotal-sumCalculated.  Return absolute difference
        // of two sums.
        if (divisionPoint == 0)
            return Math.abs((sumTotal - sumCalculated) - sumCalculated);


        // For every item arr[divisionPoint], we have two choices
        // (1) We do not include it first set
        // (2) We include it in first set
        // We return minimum of two choices
        long partion1 = findMinRec(arr, divisionPoint - 1, sumCalculated + arr[divisionPoint - 1], sumTotal);
        long partion2 = findMinRec(arr, divisionPoint - 1, sumCalculated, sumTotal);
        long min = Math.min(partion1, partion2);
        System.out.println(printPartition(arr, divisionPoint) +" -> " + min);
        return min;
    }

    // Returns minimum possible difference between sums
    // of two subsets
    static int findMin(int arr[], int n) {
        // Compute total sum of elements
        int sumTotal = 0;
        for (int i = 0; i < n; i++)
            sumTotal += arr[i];

        // Compute result using recursive function
        return (int) findMinRec(arr, n, 0, sumTotal);
    }

    static String printPartition(int[] arr, int divisionPoint){

        int[] leftArray = new int[divisionPoint];
        int[] rightArray = new int[arr.length - divisionPoint];



        System.arraycopy(arr, 0, leftArray, 0, divisionPoint);
        System.arraycopy(arr, divisionPoint, rightArray, 0, arr.length - divisionPoint);

        return "{left: " + Arrays.toString(leftArray) + ", right: " + Arrays.toString(rightArray) +"}";


    }


}
