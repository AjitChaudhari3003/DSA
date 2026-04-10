// 3740. Minimum Distance Between Three Equal Elements I
// You are given an integer array nums.
// A tuple (i, j, k) of 3 distinct indices is good if nums[i] == nums[j] == nums[k].
// The distance of a good tuple is abs(i - j) + abs(j - k) + abs(k - i), where abs(x) denotes the absolute value of x.
// Return an integer denoting the minimum possible distance of a good tuple. If no good tuples exist, return -1.
// Example 1:
// Input: nums = [1,2,1,1,3]
// Output: 6
// Explanation:
// The minimum distance is achieved by the good tuple (0, 2, 3).
// (0, 2, 3) is a good tuple because nums[0] == nums[2] == nums[3] == 1. Its distance is abs(0 - 2) + abs(2 - 3) + abs(3 - 0) = 2 + 1 + 3 = 6.


class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> mp = new HashMap<>();
        int n = nums.length;

        // Store indices for each value
        for (int i = 0; i < n; i++) {
            mp.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int ans = Integer.MAX_VALUE;

        // Process each value
        for (List<Integer> indices : mp.values()) {
            if (indices.size() < 3) continue;

            // Try consecutive triplets
            for (int i = 0; i + 2 < indices.size(); i++) {
                int dist = 2 * (indices.get(i + 2) - indices.get(i));
                ans = Math.min(ans, dist);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
