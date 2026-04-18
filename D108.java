3783. Mirror Distance of an Integer
Easy
Topics
premium lock icon
Companies
You are given an integer n.

Define its mirror distance as: abs(n - reverse(n)) where reverse(n) is the integer formed by reversing the digits of n.

Return an integer denoting the mirror distance of n.

abs(x) denotes the absolute value of x.

 

Example 1:

Input: n = 25

Output: 27

Explanation:

reverse(25) = 52.
Thus, the answer is abs(25 - 52) = 27.



class Solution {
    static public int mirrorDistance(int n) {
        int rev=0;
        for(int x=n; x>0; x/=10){
            rev=10*rev+x%10;
        }
        return Math.abs(rev-n);
    }
}

  
