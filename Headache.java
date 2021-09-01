/*You are working at an amusement park on a day when a large company reserves the entire
park for its employees. You are working at a ride fed by two lines. You already feel a
headache coming on, and your goal is to get everybody in the two lines onto the ride with
minimum aggravation of your headache. Here are the characteristics of your problem:
1. There are three types of employees: (E)xecutives, (V)eterans, and (N)ew Hires.
2. The two lines are filled with a mix of the three different types of employees. Line 1
has m people in it. Line 2 has n people in it.
3. The ride fits two people at a time. Standard procedure is to take the person at the
front of each line and put them together on the ride. However...
4. E’s and N’s don’t want to sit together. Matching an E with an N causes grumbling
that incurs 5 units of headache for you. (Any other pairing causes no problem.)
5. You can choose to fill a ride by taking two people from the front of the same line.
However, this causes grumbling from the other line that incurs 3 units of headache to
you, unless the other line is empty. If the two people you take from the front of the
same line happen to be an E and an N, you would also incur an additional 5 units of
headache (see above).
6. You can also choose to fill a ride with only one person. But then everybody remaining
complains about the inefficiency, incurring 4 units of headache, unless there is nobody
left at that point in either line (this is the last person to ride).
For example, suppose Line 1 = {E, E, N}, and Line 2 = {N, N, V, E}, where the lines
are listed from the back to the front. You could start by pairing V, E from line 2 at a cost
of 3. Then pair N from the front of each line at no cost. Then match E, E from line 1 at a
cost of 3. Finally, N from line 2 rides alone at no cost (because there is nobody left). Thus
the total cost is 6. However, this is not optimal. You could do better by letting N from
line 1 ride alone to start, at a cost of 4. Then pair the front of each line twice (E, E and
E, V ) at no cost. Finally, only N, N remains in line 2, and can ride at no cost (since the
other line is empty). Thus the total cost is 4. No other solution yields a total less than 4.
Give an O(mn) dynamic programming solution to this problem, that computes the
minimum units of headache you will incur getting all of the company employees on the
ride.*/


/*
 * Headache.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 *
 * This program computes the minimum units of headache you will incur getting all of the company employees on the ride
 *
 */

import java.util.Scanner;
import java.util.stream.IntStream;


public class Headache {
    static final int MAX_VALUE = 10000;
    /**
     * The main function which takes in the input of the number of lines
     * and the number of people and calls the calculate the headache incurred
     * @param args command line args (ignored)
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter the line 1 elements : ");
        int m = sc.nextInt();
        //System.out.println("Enter the line 2 elements : ");
        int n = sc.nextInt();
        String mArray[] = new String[m];
        String nArray[] = new String[n];
        //System.out.println("Enter the people in line m : ");
        for (int i = 0; i < m; i++) {
            mArray[i] = sc.next();
        }
        //System.out.println("Enter the people in line n : ");
        for (int i = 0; i < n; i++) {
            nArray[i] = sc.next();
        }
        int[][] arr = new int[n + 1][m + 1];
        System.out.println(calculateHeadache(arr, mArray, nArray));
    }
    /**
     * This function calculates the headache incurred when one person or two people are taken into consideration
     *
     * @param arr 2-Dimnesional array
     * @param mArray array that stores line m
     * @param nArray array that stores line n
     */
    private static int calculateHeadache(int[][] arr, String[] mArray, String[] nArray) {
        int output = 0;
        //base cases for the line n
        arr[0][0] = 0;
        arr[0][1] = 0;
        arr[1][0] = 0;
        //base cases
        for (int i = 2; i <=nArray.length; i++) {
            int val = MAX_VALUE;
            int val0 = MAX_VALUE;
            int min = 0;
            //one person incurs a cost of 4
            val = arr[i-1][0] + 4;
            // E and N are placed together-incurs a cost of 5
            if ((nArray[i-2].equals("N") && nArray[i-1].equals("E")) || (nArray[i-2].equals("E") && mArray[i-1].equals("N"))) {
                 val0 = arr[i - 2][0] + 5;
            }
            else{
                 val0 = arr[i - 2][0] + 0;
            }
            //find min value from base cases
            if (val < val0){
                min = val;
            }
            else {
                min = val0;
            }
            arr[i][0] = min;

        }
        //base cases for the line m
        for (int j = 2; j <= mArray.length; j++) {
            int val = MAX_VALUE;
            int val0 = MAX_VALUE;
            int min = 0;
             val = arr[0][j-1] + 4;
            if ((mArray[j-2].equals("N") && mArray[j-1].equals("E")) || (mArray[j-2].equals("E") && mArray[j-1].equals("N"))) {
                val0 = arr[0][j - 2] + 5;
            }
            else{
                val0 = arr[0][j-2] + 0;
            }
            if (val < val0){
                min = val;
            }
            else {
                min = val0;
            }
            arr[0][j] = min;
        }
        //Recurrence of the algorithm

        for (int i = 1; i <=nArray.length; i++) {
                        for (int j = 1; j <= mArray.length; j++) {
                            int val1= MAX_VALUE;
                            int val2= MAX_VALUE;
                            int val3 = MAX_VALUE;
                            int val4 = MAX_VALUE;
                            int val5 = MAX_VALUE;

                            val1 = arr[i - 1][j] + 4;
                val2 = arr[i][j - 1] + 4;
                if ((nArray[i-1].equals("N") && mArray[j-1].equals("E")) || (nArray[i-1].equals("E") && mArray[j-1].equals("N"))) {
                    val3 = arr[i - 1][j - 1] + 5;
                } else {
                    val3 = arr[i - 1][j - 1] + 0;
                }
                if (i >= 2) {
                    if ((nArray[i-1].equals("N") && mArray[j-1].equals("E")) || (nArray[i-1].equals("E") && mArray[j-1].equals("N"))) {
                        val4 = arr[i - 2][j] + 3 + 5;
                    } else {
                        val4 = arr[i - 2][j] + 3;
                    }
                }
                if (j >= 2) {
                    if ((nArray[i-1].equals("N") && mArray[j-1].equals("E")) || (nArray[i-1].equals("E") && mArray[j-1].equals("N"))) {
                        val5 = arr[i][j - 2] + 3 + 5;
                    } else {
                        val5 = arr[i][j - 2] + 3;
                    }
                }
                arr[i][j] = findMin(val1,val2,val3,val4,val5);
                 output = arr[i][j];

            }
        }

            return output;
    }

    /**
     * find the minimum value out of the five cases
     *
     * @param val1 value1
     * @param val2 value2
     * @param val3 value3
     * @param val4 value4
     * @param val5 value5
     *
     */
    private static int findMin(int val1, int val2, int val3, int val4, int val5) {
        int min = IntStream.of(val1,val2,val3,val4,val5).min().orElse(-1);
        return min;
    }

}
