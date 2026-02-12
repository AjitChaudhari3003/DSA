// 3713. Longest Balanced Substring I
// You are given a string s consisting of lowercase English letters.
// A substring of s is called balanced if all distinct characters in the substring appear the same number of times.
// Return the length of the longest balanced substring of s.
// Example 1:
// Input: s = "abbac"
// Output: 4
// Explanation:
// The longest balanced substring is "abba" because both distinct characters 'a' and 'b' each appear exactly 2 times


class Solution {
    public int longestBalanced(String s) {
        int max = 0;
        int n = s.length();
        
        for(int i = 0; i < n; i++){
            int freq[] = new int[26];
            
            for(int j = i; j < n; j++){
                char ch = s.charAt(j);
                freq[ch - 'a']++;
                
                if(issamefreq(freq)){
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }
    
    public static boolean issamefreq(int arr[]){
        int value = 0;
        
        for(int a : arr){
            if(a == 0) continue;
            
            if(value == 0) 
                value = a;
            else if(value != a) 
                return false;
        }
        return true;
    }
}
