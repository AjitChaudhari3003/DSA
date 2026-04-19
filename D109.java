// 1855. Maximum Distance Between a Pair of Values
// You are given two non-increasing 0-indexed integer arrays nums1​​​​​​ and nums2​​​​​​.
// A pair of indices (i, j), where 0 <= i < nums1.length and 0 <= j < nums2.length, is valid if both i <= j and nums1[i] <= nums2[j]. The distance of the pair is j - i​​​​.
// Return the maximum distance of any valid pair (i, j). If there are no valid pairs, return 0.
// An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i < arr.length.
// Example 1:
// Input: nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
// Output: 2
// Explanation: The valid pairs are (0,0), (2,2), (2,3), (2,4), (3,3), (3,4), and (4,4).
// The maximum distance is 2 with pair (2,4)


class Solution {
    public int maxDistance(int[] A, int[] B) {
        int i, j;

        for (i = 0, j = 0; i < A.length && j < B.length; j++)
            if (A[i] > B[j])
                i++;

        return Math.max(0, j - i - 1);
    }
}
