// 3070. Count Submatrices with Top-Left Element and Sum Less Than k
// You are given a 0-indexed integer matrix grid and an integer k.
// Return the number of submatrices that contain the top-left element of the grid, and have a sum less than or equal to k.
// Example 1:
// Input: grid = [[7,6,3],[6,6,1]], k = 18
// Output: 4
// Explanation: There are only 4 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 18.


class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;

        int[] prefix = new int[m];
        int ans = 0;

        for(int i = 0; i < n; i++){
            int rowSum = 0;
            for(int j = 0; j < m; j++){
                rowSum += grid[i][j];
                prefix[j] += rowSum;

                if(prefix[j] <= k) ans++;
            }
        }
        return ans;
    }
}
