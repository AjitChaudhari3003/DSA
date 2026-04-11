// 3741. Minimum Distance Between Three Equal Elements II
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
// Example 2:
// Input: nums = [1,1,2,3,2,1,2]
// Output: 8
// Explanation:
// The minimum distance is achieved by the good tuple (2, 4, 6).
// (2, 4, 6) is a good tuple because nums[2] == nums[4] == nums[6] == 2. Its distance is abs(2 - 4) + abs(4 - 6) + abs(6 - 2) = 2 + 2 + 4 = 8.


class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map =new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int val = nums[i];
            if(!map.containsKey(val)){
                List<Integer> list =new ArrayList<>();
                list.add(i);
                map.put(val,list);
            }
            else{
                map.get(val).add(i);
            }
        }
        int result = Integer.MAX_VALUE;
        for(int key: map.keySet()){
            List<Integer> list = map.get(key);
            if(list.size()<3)continue;
            for(int i=0; i<=list.size()-3; i++){
                int sum = Math.abs(list.get(i+2)-list.get(i+1))+Math.abs(list.get(i+1)-list.get(i))+Math.abs(list.get(i)-list.get(i+2));
                result = Math.min(result,sum);
            }
        }
        return result==Integer.MAX_VALUE?-1:result;
    }
}
