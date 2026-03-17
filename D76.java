// 1727. Largest Submatrix With Rearrangements
// You are given a binary matrix matrix of size m x n, and you are allowed to rearrange the columns of the matrix in any order.
// Return the area of the largest submatrix within matrix where every element of the submatrix is 1 after reordering the columns optimally.
// Example 1:
// Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
// Output: 4
// Explanation: You can rearrange the columns as shown above.
// The largest submatrix of 1s, in bold, has an area of 4.

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = 0;

        // Build heights in-place
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }

        for (int i = 0; i < m; i++) {
            Integer[] row = new Integer[n];
            for (int j = 0; j < n; j++) {
                row[j] = matrix[i][j];
            }

            Arrays.sort(row, Collections.reverseOrder());

            for (int width = 1; width <= n; width++) {
                int h = row[width - 1];
                ans = Math.max(ans, h * width);
            }
        }

        return ans;
    }
}
