// 1356. Sort Integers by The Number of 1 Bits
// You are given an integer array arr. Sort the integers in the array in ascending order by the number of 1's in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.
// Return the array after sorting it.
// Example 1:
// Input: arr = [0,1,2,3,4,5,6,7,8]
// Output: [0,1,2,4,8,3,5,6,7]
// Explantion: [0] is the only integer with 0 bits.
// [1,2,4,8] all have 1 bit.
// [3,5,6] have 2 bits.
// [7] has 3 bits.
// The sorted array by bits is [0,1,2,4,8,3,5,6,7]


class Pair {
    int num;
    int count;

    Pair(int num, int count) {
        this.num = num;
        this.count = count;
    }
}
class Solution {
    public int[] sortByBits(int[] arr) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.count==b.count?a.num-b.num:a.count-b.count);
        for(int i=0;i<arr.length;i++){
            int setbit = Integer.bitCount(arr[i]);
            pq.add(new Pair(arr[i],setbit));
        }
        int k=0;
        int[] ans =  new int[arr.length];
        while(!pq.isEmpty()){
            Pair p =pq.poll();
            ans[k++]=p.num;
        }
        return ans;
    }
}
