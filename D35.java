3640. Trionic Array II
Hard
Topics
premium lock icon
Companies
You are given an integer array nums of length n.

A trionic subarray is a contiguous subarray nums[l...r] (with 0 <= l < r < n) for which there exist indices l < p < q < r such that:

nums[l...p] is strictly increasing,
nums[p...q] is strictly decreasing,
nums[q...r] is strictly increasing.
Return the maximum sum of any trionic subarray in nums.

 

Example 1:

Input: nums = [0,-2,-1,-3,0,2,-1]

Output: -4

Explanation:

Pick l = 1, p = 2, q = 3, r = 5:

nums[l...p] = nums[1...2] = [-2, -1] is strictly increasing (-2 < -1).
nums[p...q] = nums[2...3] = [-1, -3] is strictly decreasing (-1 > -3)
nums[q...r] = nums[3...5] = [-3, 0, 2] is strictly increasing (-3 < 0 < 2).
Sum = (-2) + (-1) + (-3) + 0 + 2 = -4.



class Solution {
    long NEG = -100000000000000L;

    long f(int index, int status, int n, int[] nums) {
        if (index == n) {
            return status == 3 ? 0 : NEG;
        }

        long take = NEG;
        long notTake = NEG;

        if (status == 0) {
            notTake = f(index + 1, 0, n, nums);
        }

        if (status == 3) {
            take = nums[index];
        }

        if (index + 1 < n) {
            if (status == 0 && nums[index + 1] > nums[index]) {
                take = Math.max(take, nums[index] + f(index + 1, 1, n, nums));
            } 
            else if (status == 1) {
                if (nums[index + 1] > nums[index]) {
                    take = Math.max(take, nums[index] + f(index + 1, 1, n, nums));
                } else if (nums[index + 1] < nums[index]) {
                    take = Math.max(take, nums[index] + f(index + 1, 2, n, nums));
                }
            } 
            else if (status == 2) {
                if (nums[index + 1] < nums[index]) {
                    take = Math.max(take, nums[index] + f(index + 1, 2, n, nums));
                } else if (nums[index + 1] > nums[index]) {
                    take = Math.max(take, nums[index] + f(index + 1, 3, n, nums));
                }
            } 
            else if (status == 3 && nums[index + 1] > nums[index]) {
                take = Math.max(take, nums[index] + f(index + 1, 3, n, nums));
            }
        }

        return Math.max(take, notTake);
    }

    public long maxSumTrionic(int[] nums) {
        return f(0, 0, nums.length, nums);
    }
}
