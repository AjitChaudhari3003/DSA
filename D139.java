// 2540. Minimum Common Value
// Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common to both arrays. If there is no common integer amongst nums1 and nums2, return -1.
// Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.
// Example 1:
// Input: nums1 = [1,2,3], nums2 = [2,4]
// Output: 2
// Explanation: The smallest element common to both arrays is 2, so we return 2.


class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int p1=0, p2=0;

        while(p1<nums1.length && p2<nums2.length){
            if(nums1[p1]==nums2[p2])
            return nums1[p1];
            else if(nums1[p1]<nums2[p2])
            p1++;
            else
            p2++;
        }
        return -1;
    }
}
