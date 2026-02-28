// 1680. Concatenation of Consecutive Binary Numbers
// Given an integer n, return the decimal value of the binary string formed by concatenating the binary representations of 1 to n in order, modulo 109 + 7.
// Example 1:
// Input: n = 1
// Output: 1
// Explanation: "1" in binary corresponds to the decimal value 1.


class Solution {
    public int concatenatedBinary(int n) {
        int shifter = 1;
        int val = 1;
        long answer = 0;
        int mod = 1_000_000_007;
        
        for(int a = 1; a <= n; a++){
            if(val * 2 == a){
                shifter++;
                val = a;
            }
            answer = ((answer << shifter) | a) % mod;
        }
        
        return (int) answer;
    }
}
