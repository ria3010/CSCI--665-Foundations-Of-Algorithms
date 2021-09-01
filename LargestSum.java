/*Given is a sequence of integers, a1, a2, . . . , an. Give an O(n^2) algorithm that finds the
largest possible sum of elements in an increasing subsequence of a1, a2, . . . , an.*/
import java.util.Scanner;

public class LargestSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter the number of elements : ");
        int numOfElements = sc.nextInt();
        int [] arr = new int[numOfElements];
        //System.out.println("Enter the elements of the array:");

        for(int i=0; i<numOfElements; i++ ) {
            arr[i] = sc.nextInt();
        }
        int[] copyOfarr = arr.clone();
        largestSumOfIncreasingSub(arr,copyOfarr);
    }

    private static void largestSumOfIncreasingSub(int[] arr,int[] copyOfarr) {
        for(int i = 1 ;i<arr.length;i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    if (copyOfarr[j] + arr[i] > copyOfarr[i]) {
                        copyOfarr[i] = arr[i]+copyOfarr[j];
                    }
                }
            }
        }
        int max = copyOfarr[0];
        for (int i =0;i< copyOfarr.length;i++){
            if(copyOfarr[i]>max){
                max = copyOfarr[i];
            }
        }
        System.out.println(max);

    }
}
