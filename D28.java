// 3651. Minimum Cost Path with Teleportations
// You are given a m x n 2D integer array grid and an integer k. You start at the top-left cell (0, 0) and your goal is to reach the bottom‐right cell (m - 1, n - 1).
// There are two types of moves available:
// Normal move: You can move right or down from your current cell (i, j), i.e. you can move to (i, j + 1) (right) or (i + 1, j) (down). The cost is the value of the destination cell.
// Teleportation: You can teleport from any cell (i, j), to any cell (x, y) such that grid[x][y] <= grid[i][j]; the cost of this move is 0. You may teleport at most k times.
// Return the minimum total cost to reach cell (m - 1, n - 1) from (0, 0).
// Example 1:
// Input: grid = [[1,3,3],[2,5,4],[4,3,5]], k = 2
// Output: 7
// Explanation:
// Initially we are at (0, 0) and cost is 0.
// Current Position	Move	New Position	Total Cost
// (0, 0)	Move Down	(1, 0)	0 + 2 = 2
// (1, 0)	Move Right	(1, 1)	2 + 5 = 7
// (1, 1)	Teleport to (2, 2)	(2, 2)	7 + 0 = 7
// The minimum cost to reach bottom-right cell is 7.


class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        if (k > 0 && grid[0][0] >= grid[m - 1][n - 1]) {
            return 0;
        }

        int mx = 0;
        for (int[] row : grid) {
            for (int x : row) {
                mx = Math.max(mx, x);
            }
        }

        int[] sufMinF = new int[mx + 2];
        Arrays.fill(sufMinF, Integer.MAX_VALUE);
        int[] minF = new int[mx + 1];
        int[] f = new int[n + 1];

        for (int t = 0; t <= k; t++) {
            Arrays.fill(minF, Integer.MAX_VALUE);
            Arrays.fill(f, Integer.MAX_VALUE / 2);
            f[1] = -grid[0][0];
            for (int[] row : grid) {
                for (int j = 0; j < n; j++) {
                    int x = row[j];
                    f[j + 1] = Math.min(Math.min(f[j], f[j + 1]) + x, sufMinF[x]);
                    minF[x] = Math.min(minF[x], f[j + 1]);
                }
            }
            boolean done = true;
            for (int i = mx; i >= 0; i--) {
                int mn = Math.min(sufMinF[i + 1], minF[i]);
                if (mn < sufMinF[i]) {
                    sufMinF[i] = mn;
                    done = false;
                }
            }
            if (done) {
                break;
            }
        }

        return f[n];
    }
}
