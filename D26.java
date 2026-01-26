// 1200. Minimum Absolute Difference
// Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
// Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
// a, b are from arr
// a < b
// b - a equals to the minimum absolute difference of any two elements in arr
// Example 1:
// Input: arr = [4,2,1,3]
// Output: [[1,2],[2,3],[3,4]]
// Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.



class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        if(n<1) return new ArrayList<>(0);
        int minDiff = 1234567;
        for(int i=0;i<n-1;i++){
            int diff = Math.abs(arr[i]-arr[i+1]);
            if(diff<minDiff){
                minDiff = diff;
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<n-1;i++){
            if((arr[i+1]-arr[i])==minDiff){
                List<Integer> pairs = new ArrayList<>();
                pairs.add(arr[i]);
                pairs.add(arr[i+1]);
                res.add(pairs);
            }
        }
        return res;
    }
}
