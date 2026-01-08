// 1458. Max Dot Product of Two Subsequences
// Given two arrays nums1 and nums2.
// Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.
// A subsequence of a array is a new array which is formed from the original array by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a subsequence of [1,2,3,4,5] while [1,5,3] is not).
// Example 1:
// Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
// Output: 18
// Explanation: Take subsequence [2,-2] from nums1 and subsequence [3,-6] from nums2.
// Their dot product is (2*3 + (-2)*(-6)) = 18.


import java.util.*;

class Solution {
    int[] nums1, nums2;
    int[][] memo;
    int n, m;
    final int NEG_INF = (int) -1e9;

    int dp(int i, int j) {
        if (i == n || j == m)
            return NEG_INF;

        if (memo[i][j] != Integer.MIN_VALUE)
            return memo[i][j];

        int take = nums1[i] * nums2[j];

        int res = Math.max(
            Math.max(
                take + dp(i + 1, j + 1), 
                take                    
            ),
            Math.max(
                dp(i + 1, j),           
                dp(i, j + 1)            
            )
        );

        return memo[i][j] = res;
    }

    public int maxDotProduct(int[] a, int[] b) {
        nums1 = a;
        nums2 = b;
        n = nums1.length;
        m = nums2.length;

        memo = new int[n][m];
        for (int i = 0; i < n; i++)
            Arrays.fill(memo[i], Integer.MIN_VALUE);

        return dp(0, 0);
    }
}
