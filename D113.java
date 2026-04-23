// 2615. Sum of Distances
// You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.
// Return the array arr.
// Example 1:
// Input: nums = [1,3,1,1,2]
// Output: [5,0,3,4,0]
// Explanation: 
// When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5. 
// When i = 1, arr[1] = 0 because there is no other index with value 3.
// When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3. 
// When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4. 
// When i = 4, arr[4] = 0 because there is no other index with value 2.


import java.util.*;

class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        Map<Long, long[]> occr = new HashMap<>();

        for (int i = 0; i < n; i++) {
            long key = nums[i];
            occr.putIfAbsent(key, new long[]{0, 0});
            occr.get(key)[0] += i;
            occr.get(key)[1] += 1;
        }

        Map<Long, long[]> occl = new HashMap<>();
        long[] ans = new long[n];

        for (int i = 0; i < n; i++) {
            long key = nums[i];

            occl.putIfAbsent(key, new long[]{0, 0});
            occl.get(key)[0] += i;
            occl.get(key)[1] += 1;

            occr.get(key)[0] -= i;
            occr.get(key)[1] -= 1;

            ans[i] += i * occl.get(key)[1] - occl.get(key)[0];
            ans[i] += occr.get(key)[0] - i * occr.get(key)[1];
        }

        return ans;
    }
}
