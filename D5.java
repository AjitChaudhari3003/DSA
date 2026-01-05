// 1975. Maximum Matrix Sum
// You are given an n x n integer matrix. You can do the following operation any number of times:
// Choose any two adjacent elements of matrix and multiply each of them by -1.
// Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.
// Example 1:
// Input: matrix = [[1,-1],[-1,1]]
// Output: 4
// Explanation: We can follow the following steps to reach sum equals 4:
// - Multiply the 2 elements in the first row by -1.
// - Multiply the 2 elements in the first column by -1.

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long sum=0;
        int minAbs=Integer.MAX_VALUE, negOdd=0;
        for (int [] row: matrix){
            for(int x: row){
                minAbs=Math.min(minAbs, Math.abs(x));
                if (x<0){
                    sum-=x;
                    negOdd=1-negOdd;
                }
                else sum+=x;
            }
        }
        return sum-2*negOdd*minAbs;
    }
}
