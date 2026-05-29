// 3300. Minimum Element After Replacement With Digit Sum
// You are given an integer array nums.
// You replace each element in nums with the sum of its digits.
// Return the minimum element in nums after all replacements.
// Example 1:
// Input: nums = [10,12,13,14]
// Output: 1
// Explanation:nums becomes [1, 3, 4, 5] after all replacements, with minimum element 1.


class Solution {
    public int minElement(int[] nums) {
        int minVal = Integer.MAX_VALUE;
        
        for (int num : nums) {
            int currentSum = 0;
            
            while (num > 0) {
                currentSum += num % 10;
                num /= 10;
            }
            
            minVal = Math.min(minVal, currentSum);
        }
        
        return minVal;
    }
}
