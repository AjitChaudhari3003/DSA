// 693. Binary Number with Alternating Bits
// Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
// Example 1:
// Input: n = 5
// Output: true
// Explanation: The binary representation of 5 is: 101


class Solution {
    public boolean hasAlternatingBits(int n) {
        int val= n&1;

        while(n>0){
            n =n>>1;
            if(val==(n&1))
            return false;
            val=n&1;
        }
        return true;
    }
}
