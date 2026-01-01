// 1071. Greatest Common Divisor of Strings
// For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).
// Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
// Example 1:
// Input: str1 = "ABCABC", str2 = "ABC"
// Output: "ABC"
// Example 2:
// Input: str1 = "ABABAB", str2 = "ABAB"
// Output: "AB"


class Solution {
    public String gcdOfStrings(String str1, String str2) {
        
        if(!(str1+str2).equals(str2+str1))
        return "";

        int n1=str1.length();
        int n2=str2.length();

        while(n1%n2 !=0){
            int rem= n1%n2;
            n1=n2;
            n2=rem;
        }
        int gcd=n2;

        String res= str1.substring(0,gcd);
        return res;
    }
}
