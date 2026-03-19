// 3212. Count Submatrices With Equal Frequency of X and Y
// Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or '.', return the number of submatrices that contain:
// grid[0][0]
// an equal frequency of 'X' and 'Y'.
// at least one 'X'.
// Example 1:
// Input: grid = [["X","Y","."],["Y",".","."]]
// Output: 3
// Explanation:
// Example 2:
// Input: grid = [["X","X"],["X","Y"]]
// Output: 0
// Explanation:
// No submatrix has an equal frequency of 'X' and 'Y'.


class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;

        int[][] px = new int[m][n];
        int[][] py = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                px[i][j] = (grid[i][j] == 'X') ? 1 : 0;
                py[i][j] = (grid[i][j] == 'Y') ? 1 : 0;

                if (i > 0) {
                    px[i][j] += px[i - 1][j];
                    py[i][j] += py[i - 1][j];
                }
                if (j > 0) {
                    px[i][j] += px[i][j - 1];
                    py[i][j] += py[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    px[i][j] -= px[i - 1][j - 1];
                    py[i][j] -= py[i - 1][j - 1];
                }

                if (px[i][j] == py[i][j] && px[i][j] > 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
