// 3548. Equal Sum Grid Partition II
// You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make either one horizontal or one vertical cut on the grid such that:
// Each of the two resulting sections formed by the cut is non-empty.
// The sum of elements in both sections is equal, or can be made equal by discounting at most one single cell in total (from either section).
// If a cell is discounted, the rest of the section must remain connected.
// Return true if such a partition exists; otherwise, return false.
// Note: A section is connected if every cell in it can be reached from any other cell by moving up, down, left, or right through other cells in the section.
// Example 1:
// Input: grid = [[1,4],[2,3]]
// Output: true
// Explanation:A horizontal cut after the first row gives sums 1 + 4 = 5 and 2 + 3 = 5, which are equal. Thus, the answer is true.

import java.util.*;

class Solution {
    private boolean canRemove(int r1, int c1, int r2, int c2, int i, int j) {
        int rows = r2 - r1 + 1;
        int cols = c2 - c1 + 1;

        if (rows * cols == 1) return false;

        if (rows == 1) {
            return (j == c1 || j == c2);
        }

        if (cols == 1) {
            return (i == r1 || i == r2);
        }

        return true;
    }

    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        long[] prefRow = new long[n];
        long[] prefCol = new long[m];

        Map<Long, List<int[]>> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long val = 0;
            for (int j = 0; j < m; j++) {
                val += grid[i][j];
                long cellVal = (long) grid[i][j];
                mp.putIfAbsent(cellVal, new ArrayList<>());
                mp.get(cellVal).add(new int[]{i, j});
            }
            prefRow[i] = val + (i > 0 ? prefRow[i - 1] : 0);
        }

        for (int j = 0; j < m; j++) {
            long val = 0;
            for (int i = 0; i < n; i++) {
                val += grid[i][j];
            }
            prefCol[j] = val + (j > 0 ? prefCol[j - 1] : 0);
        }

        long total = prefRow[n - 1];

        for (int i = 0; i < n - 1; i++) {
            long top = prefRow[i];
            long bottom = total - top;

            if (top == bottom) return true;

            long diff = Math.abs(top - bottom);

            if (!mp.containsKey(diff)) continue;

            if (top > bottom) {
                for (int[] p : mp.get(diff)) {
                    int x = p[0], y = p[1];
                    if (x <= i && canRemove(0, 0, i, m - 1, x, y)) {
                        return true;
                    }
                }
            } else {
                for (int[] p : mp.get(diff)) {
                    int x = p[0], y = p[1];
                    if (x > i && canRemove(i + 1, 0, n - 1, m - 1, x, y)) {
                        return true;
                    }
                }
            }
        }

        for (int j = 0; j < m - 1; j++) {
            long left = prefCol[j];
            long right = total - left;

            if (left == right) return true;

            long diff = Math.abs(left - right);

            if (!mp.containsKey(diff)) continue;

            if (left > right) {
                for (int[] p : mp.get(diff)) {
                    int x = p[0], y = p[1];
                    if (y <= j && canRemove(0, 0, n - 1, j, x, y)) {
                        return true;
                    }
                }
            } else {
                for (int[] p : mp.get(diff)) {
                    int x = p[0], y = p[1];
                    if (y > j && canRemove(0, j + 1, n - 1, m - 1, x, y)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
