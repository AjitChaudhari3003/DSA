// 3653. XOR After Range Multiplication Queries I
// You are given an integer array nums of length n and a 2D integer array queries of size q, where queries[i] = [li, ri, ki, vi].
// For each query, you must apply the following operations in order:
// Set idx = li.
// While idx <= ri:
// Update: nums[idx] = (nums[idx] * vi) % (109 + 7)
// Set idx += ki.
// Return the bitwise XOR of all elements in nums after processing all queries.
// Example 1:
// Input: nums = [1,1,1], queries = [[0,2,1,4]]
// Output: 4
// Explanation:
// A single query [0, 2, 1, 4] multiplies every element from index 0 through index 2 by 4.
// The array changes from [1, 1, 1] to [4, 4, 4].
// The XOR of all elements is 4 ^ 4 ^ 4 = 4.


class Solution {
    final int mod = 1000000007;

    public int xorAfterQueries(int[] nums, int[][] queries) {

        // Process each query
        for (int[] t : queries) {
            int l = t[0];
            int r = t[1];
            int k = t[2];
            int v = t[3];

            int idx = l;

            // Apply operation at step k
            while (idx <= r) {
                long temp = nums[idx];
                nums[idx] = (int)((temp * v) % mod);
                idx += k;
            }
        }

        // Compute XOR of final array
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }

        return ans;
    }
}
