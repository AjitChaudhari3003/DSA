// 1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
// Given a m x n matrix mat and an integer threshold, return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
// Example 1:
// Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
// Output: 2
// Explanation: The maximum side length of square with sum less than 4 is 2 as shown.


class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int maxSide = Math.min(m,n);

        int[][] pref = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pref[i][j] =
                    mat[i-1][j-1]
                  + pref[i-1][j]
                  + pref[i][j-1]
                  - pref[i-1][j-1];
            }
        }

        while (maxSide > 0) {
            for (int i = 0; i + maxSide <= m; i++) {
                for (int j = 0; j + maxSide <= n; j++) {
                    if (helper(pref, threshold, i, j, maxSide))
                        return maxSide;
                }
            }
            maxSide--;
        }

        return 0;
    }

    private boolean helper(int[][] pref, int t, int x, int y, int side) {
        int x2 = x + side;
        int y2 = y + side;

        int sum =
            pref[x2][y2]
        - pref[x][y2]
        - pref[x2][y]
        + pref[x][y];

        return sum <= t;
    }

}
