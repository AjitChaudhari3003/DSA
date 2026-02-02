// 3013. Divide an Array Into Subarrays With Minimum Cost II
// You are given a 0-indexed array of integers nums of length n, and two positive integers k and dist.
// The cost of an array is the value of its first element. For example, the cost of [1,2,3] is 1 while the cost of [3,4,1] is 3.
// You need to divide nums into k disjoint contiguous subarrays, such that the difference between the starting index of the second subarray and the starting index of the kth subarray should be less than or equal to dist. In other words, if you divide nums into the subarrays nums[0..(i1 - 1)], nums[i1..(i2 - 1)], ..., nums[ik-1..(n - 1)], then ik-1 - i1 <= dist.
// Return the minimum possible sum of the cost of these subarrays.
// Example 1:
// Input: nums = [1,3,2,6,4,2], k = 3, dist = 3
// Output: 5
// Explanation: The best possible way to divide nums into 3 subarrays is: [1,3], [2,6,4], and [2]. This choice is valid because ik-1 - i1 is 5 - 2 = 3 which is equal to dist. The total cost is nums[0] + nums[2] + nums[5] which is 1 + 2 + 2 = 5.
// It can be shown that there is no possible way to divide nums into 3 subarrays at a cost lower than 5.



class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        
        PriorityQueue<Integer> pq_left = new PriorityQueue<>((a, b)->b-a);
        PriorityQueue<Integer> pq_right = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();
        int valid_left = 0;
        long sum_left = 0;

        long res = Long.MAX_VALUE;

        for(int i=1; i<n; i++){
            
            if(i>=dist+2){
                int v = nums[i-dist-1];  
                if(v<pq_left.peek()){
                    map.merge(v, 1, Integer::sum);
                    valid_left--;
                    sum_left -= v;
                }
                else if(v==pq_left.peek()){
                    pq_left.poll();
                    valid_left--;
                    sum_left -= v;
                }
                else if(v==pq_right.peek()){
                    pq_right.poll();
                }
                else{
                    map.merge(v, 1, Integer::sum);
                }
            }


            if(i<=k-1 || nums[i]<=pq_left.peek()){
                pq_left.offer(nums[i]);
                valid_left++;
                sum_left += nums[i];
            }
            else{
                pq_right.offer(nums[i]);
            }

            if(i>k-1){
                if(valid_left<k-1){
                    int v = pq_right.poll();
                    pq_left.offer(v);
                    valid_left++;
                    sum_left += v;
                }
                else if(valid_left>k-1){
                    int v = pq_left.poll();
                    valid_left--;
                    sum_left -= v;
                    pq_right.offer(v);
                }
            }


            while(!pq_left.isEmpty() && map.getOrDefault(pq_left.peek(), 0)>0){
                int v = pq_left.poll();
                map.merge(v, -1, Integer::sum);
            }
            while(!pq_right.isEmpty() && map.getOrDefault(pq_right.peek(), 0)>0){
                int v = pq_right.poll();
                map.merge(v, -1, Integer::sum);
            }

            if(i>=dist+1){
                res = Math.min(res, sum_left);
            }
        }
        return res+nums[0];
    }
}
