// 3637. Trionic Array I
// You are given an integer array nums of length n.
// An array is trionic if there exist indices 0 < p < q < n − 1 such that:
// nums[0...p] is strictly increasing,
// nums[p...q] is strictly decreasing,
// nums[q...n − 1] is strictly increasing.
// Return true if nums is trionic, otherwise return false.
// Example 1:
// Input: nums = [1,3,5,4,2,6]
// Output: true
// Explanation:
// Pick p = 2, q = 4:
// nums[0...2] = [1, 3, 5] is strictly increasing (1 < 3 < 5).
// nums[2...4] = [5, 4, 2] is strictly decreasing (5 > 4 > 2).
// nums[4...5] = [2, 6] is strictly increasing (2 < 6).


class Solution {
    public boolean isTrionic(int[] nums) {
        boolean c1 = false; // increasing
        boolean c2 = false; // decreasing
        boolean c3 = false; // increasing again

        for (int i = 1; i < nums.length; i++) {
            int prev = nums[i - 1];
            int curr = nums[i];

            // Phase 1: increasing
            if (!c1) {
                if (curr > prev) {
                    c1 = true; //condition 1 satisfied
                } else {
                    return false; // must increase in the start
                }
            }

            // Phase 2: decreasing
            else if (c1 && !c2) {
                if (curr > prev) {
                    continue;  // still increasing (consider in phase1)
                } else if (curr < prev) {
                    c2 = true; // decreasing started (condition 2 satisfied)
                } else {
                    return false; // no flat values allowed
                }
            }

            // Phase 3 start: increasing again
            else if (c1 && c2 && !c3) {
                if (curr < prev) {
                    continue; // still decreasing (consider in phase2)
                } else if (curr == prev) {
                    return false; // no flat values allowed
                } else {
                    c3 = true; // second increasing started (condition 3 satisfied)
                }
            }

            // Phase 3 continuation: must strictly increase
            else if (c1 && c2 && c3) {
                if (curr <= prev) {
                    return false; // cannot go down or stay flat
                }
            }
        }

        return c1 && c2 && c3;
    }
}
