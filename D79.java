// 3567. Minimum Absolute Difference in Sliding Submatrix
// You are given an m x n integer matrix grid and an integer k.
// For every contiguous k x k submatrix of grid, compute the minimum absolute difference between any two distinct values within that submatrix.
// Return a 2D array ans of size (m - k + 1) x (n - k + 1), where ans[i][j] is the minimum absolute difference in the submatrix whose top-left corner is (i, j) in grid.
// Note: If all elements in the submatrix have the same value, the answer will be 0.
// A submatrix (x1, y1, x2, y2) is a matrix that is formed by choosing all cells matrix[x][y] where x1 <= x <= x2 and y1 <= y <= y2.
// Example 1:
// Input: grid = [[1,8],[3,-2]], k = 2
// Output: [[2]]
// Explanation:
// There is only one possible k x k submatrix: [[1, 8], [3, -2]].
// Distinct values in the submatrix are [1, 8, 3, -2].
// The minimum absolute difference in the submatrix is |1 - 3| = 2. Thus, the answer is [[2]].


class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] ans = new int[m - k + 1][n - k + 1];

        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {

                List<Integer> temp = new ArrayList<>();

                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        temp.add(grid[x][y]);
                    }
                }

                if (k == 1) {
                    ans[i][j] = 0;
                    continue;
                }

                Collections.sort(temp);

                List<Integer> unique = new ArrayList<>();
                for (int num : temp) {
                    if (unique.isEmpty() || unique.get(unique.size() - 1) != num) {
                        unique.add(num);
                    }
                }

                if (unique.size() <= 1) {
                    ans[i][j] = 0;
                    continue;
                }

                int mini = Integer.MAX_VALUE;
                for (int p = 1; p < unique.size(); p++) {
                    mini = Math.min(mini, Math.abs(unique.get(p) - unique.get(p - 1)));
                }

                ans[i][j] = mini;
            }
        }

        return ans;
    }
}
