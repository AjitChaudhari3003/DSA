1758. Minimum Changes To Make Alternating Binary String
You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0' to '1' or vice versa.
The string is called alternating if no two adjacent characters are equal. For example, the string "010" is alternating, while the string "0100" is not.
Return the minimum number of operations needed to make s alternating.
Example 1:
Input: s = "0100"
Output: 1
Explanation: If you change the last character to '1', s will be "0101", which is alternating.

  class Solution {
    public int minOperations(String s) {
        return Math.min(check(s, true), check(s, false));
    }
    
    private int check(String s, boolean flag) {
        int count = 0;
        int n = s.length();
        //check first char
        if(flag) { 
            if(s.charAt(0) != '1') count++;
        } else {  
            if(s.charAt(0) != '0') count++;
        }
        //check rest
        for(int i = 1; i < n; i++) {
            flag = !flag;
    
            if(flag) { 
                if(s.charAt(i) != '1') count++;
            } else {  
                if(s.charAt(i) != '0') count++;
            }
        }
        
        return count;
    }
}
