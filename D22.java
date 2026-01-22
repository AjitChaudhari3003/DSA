// 3507. Minimum Pair Removal to Sort Array I
// Given an array nums, you can perform the following operation any number of times:
// Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
// Replace the pair with their sum.
// Return the minimum number of operations needed to make the array non-decreasing.
// An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it exists).
// Example 1:
// Input: nums = [5,2,3,1]
// Output: 2
// Explanation:
// The pair (3,1) has the minimum sum of 4. After replacement, nums = [5,2,4].
// The pair (2,4) has the minimum sum of 6. After replacement, nums = [5,6].
// The array nums became non-decreasing in two operations.


class Solution {
    public int minimumPairRemoval(int[] nums) {
        List <Integer> list = new ArrayList<>();
        for(int i:nums)
        {
            list.add(i);
        }

        int count = 0;

        // Keep merging until the list becomes sorted
        while(!sorted(list))
        {
            int idx = 0;
            int minSum = Integer.MAX_VALUE;

            // Find adjacent pair with the smallest sum
            for(int i=0;i<list.size()-1;i++)
            {
                int sum = list.get(i)+list.get(i+1);
                if(sum<minSum)
                {
                    minSum = sum;
                    idx = i;
                }
            }

            // Merge the chosen pair
            int merged = list.get(idx)+list.get(idx+1);
            list.set(idx,merged);
            list.remove(idx+1);
            count++;
        }
        return count;
    }

    // Check if list is sorted
    private boolean sorted(List<Integer> list)
    {
        for(int i=1;i<list.size();i++)
        {
            if(list.get(i)<list.get(i-1))
            {
                return false;
            }
        }
        return true;
    }
}
