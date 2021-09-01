/*Given are n cards, where n is an even, positive integer. Each card has two numbers written
on it. Each number is an integer in the range [1, n]. Every number appears exactly twice
in total.
Give an O(n) algorithm to determine if it is possible to select n
2
cards such that each
number appears exactly once on those cards.
For example, given n = 6 and cards: (1, 5),(1, 4),(2, 4),(6, 3),(3, 6),(5, 2). Then the
answer is yes, because the cards (1, 5),(2, 4),(6, 3) can be selected such that all numbers
appear exactly once.
On the other hand, given n = 6, and cards: (1, 5),(2, 6),(1, 4),(4, 5),(3, 6),(3, 2). Then
the answer is no, as there is no subset of 3 cards such that every number appears exactly
once.*/


/*
 * pickHalf.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

import java.util.Scanner;

/**
 * Given n even input numbers, this program determines if each number appears exactly once.
 *
 */

import java.util.*;

class pickHalf{

    //take input from user and run the algorithm to check if numbers can be selected such that all numbers
    //appear exactly once.
    public static void main(String args[])
    {
        int m = 1;
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        int numOfNodes = sc.nextInt();
        boolean visitedNodes[] = new boolean[numOfNodes+1];
        int coloredNodes[] = new int[numOfNodes+1];
        for(int i = 0;i<coloredNodes.length;i++){
            coloredNodes[i] = -1;
        }
        visitedNodes[1] = true;
        coloredNodes[1] = 0;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(numOfNodes+1);

        for(int i = 0; i <= numOfNodes; i++)
        {
            graph.add(new ArrayList<>());
        }

        int[] tempStr = new int[2];
        for( int i=0; i<numOfNodes; i++ ) {
            tempStr[0] = sc.nextInt();
            tempStr[1] = sc.nextInt();
            if(tempStr[0]==tempStr[1]){
                flag = true;
            }
            graph.get(tempStr[0]).add(tempStr[1]);
            graph.get(tempStr[1]).add(tempStr[0]);

        }
        if(flag){
            System.out.println("NO");
        }
        else{
            pickHalf pickHalf = new pickHalf();
            if (pickHalf.testNumbers(m,visitedNodes,coloredNodes,graph))
            {
                System.out.println("YES");
            }
            else
            {
                System.out.println("NO");
            }
        }

    }
    private boolean testNumbers(int neighbours,boolean visitedNodes[],int coloredNodes[],ArrayList<ArrayList<Integer>> graph) {
        for (int node:graph.get(neighbours)) {

            if (!visitedNodes[node]) {

                coloredNodes[node]=1-coloredNodes[neighbours];
                visitedNodes[node]=true;

                if (!testNumbers(node,visitedNodes,coloredNodes,graph))
                {
                    return false;
                }
            }
            else if (coloredNodes[node] == coloredNodes[neighbours]){
                return false;
                }
        }
        return true;
    }
}