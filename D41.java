// 3719. Longest Balanced Subarray I
// You are given an integer array nums.
// A subarray is called balanced if the number of distinct even numbers in the subarray is equal to the number of distinct odd numbers.
// Return the length of the longest balanced subarray.
// Example 1:
// Input: nums = [2,5,4,3]
// Output: 4
// Explanation:
// The longest balanced subarray is [2, 5, 4, 3].
// It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [5, 3]. Thus, the answer is 4.


class Solution {
    public int longestBalanced(int[] nums) {
        int res = 0;
        HashSet<Integer> odd = new HashSet<>(), even = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            odd.clear(); 
            even.clear();
            for (int j = i; j < nums.length; j++){
                int num = nums[j];
                if ((num & 1) == 1) odd.add(num);
                else even.add(num);
                if (odd.size() == even.size()){
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }
}
