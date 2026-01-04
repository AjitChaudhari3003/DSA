//1390. Four Divisors
//Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors. If there is no such integer in the array, return 0.
//Example 1:
//Input: nums = [21,4,7]
//Output: 32
//Explanation: 
//21 has 4 divisors: 1, 3, 7, 21
//4 has 3 divisors: 1, 2, 4
//7 has 2 divisors: 1, 7
//The answer is the sum of divisors of 21 only.
//Example 2:
//Input: nums = [21,21]
//Output: 64

class Solution {
    private int factors(int n) {
        int sum = 0, c = 0;
        for(int i = 2; i*i <= n; i++) {
            if(n % i == 0) {
                int j = n / i;
                if(j == i || c > 0) return 0;
                sum += i + j;
                c++;
            }
        }
        if(c == 0) return 0;
        return 1 + sum + n;
    }
    public int sumFourDivisors(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += factors(nums[i]);
        }
        return sum;
    }
}
