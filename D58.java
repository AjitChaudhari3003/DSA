// 3666. Minimum Operations to Equalize Binary String
// You are given a binary string s, and an integer k.
// In one operation, you must choose exactly k different indices and flip each '0' to '1' and each '1' to '0'.
// Return the minimum number of operations required to make all characters in the string equal to '1'. If it is not possible, return -1.
// Example 1:
// Input: s = "110", k = 1
// Output: 1
// Explanation:
// There is one '0' in s.
// Since k = 1, we can flip it directly in one operation.

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int zero = 0;

        for (char c : s.toCharArray())
            if (c == '0') zero++;

        if (zero == 0) return 0;

        if (n == k) {
            if (zero == n) return 1;
            if (zero == 0) return 0;
            return -1;
        }

        int one = n - zero;
        int base = n - k;

        long ans = Long.MAX_VALUE;

        if ((k % 2) == (zero % 2)) {
            long m = Math.max(
                (zero + k - 1) / k,
                (one + base - 1) / base
            );

            if (m % 2 == 0) m++;

            ans = Math.min(ans, m);
        }

        if (zero % 2 == 0) {
            long m = Math.max(
                (zero + k - 1) / k,
                (zero + base - 1) / base
            );

            if (m % 2 == 1) m++;

            ans = Math.min(ans, m);
        }

        return ans == Long.MAX_VALUE ? -1 : (int)ans;
    }
}
