// 2839. Check if Strings Can be Made Equal With Operations I
// You are given two strings s1 and s2, both of length 4, consisting of lowercase English letters.
// You can apply the following operation on any of the two strings any number of times:
// Choose any two indices i and j such that j - i = 2, then swap the two characters at those indices in the string.
// Return true if you can make the strings s1 and s2 equal, and false otherwise.
// Example 1:
// Input: s1 = "abcd", s2 = "cdab"
// Output: true
// Explanation: We can do the following operations on s1:
// - Choose the indices i = 0, j = 2. The resulting string is s1 = "cbad".
// - Choose the indices i = 1, j = 3. The resulting string is s1 = "cdab" = s2.


class Solution {
    public boolean canBeEqual(String s1, String s2) {
        char[] even1 = new char[2], odd1 = new char[2];
        char[] even2 = new char[2], odd2 = new char[2];
        
        int e = 0, o = 0;
        
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0) {
                even1[e] = s1.charAt(i);
                even2[e] = s2.charAt(i);
                e++;
            } else {
                odd1[o] = s1.charAt(i);
                odd2[o] = s2.charAt(i);
                o++;
            }
        }
        
        java.util.Arrays.sort(even1);
        java.util.Arrays.sort(even2);
        java.util.Arrays.sort(odd1);
        java.util.Arrays.sort(odd2);
        
        return java.util.Arrays.equals(even1, even2) && 
               java.util.Arrays.equals(odd1, odd2);
    }
}
