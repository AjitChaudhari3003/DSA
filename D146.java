3120. Count the Number of Special Characters I
Easy
Topics
premium lock icon
Companies
You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.

Return the number of special letters in word.

 

Example 1:

Input: word = "aaAbcBC"

Output: 3

Explanation:

The special characters in word are 'a', 'b', and 'c'.


  class Solution {
    public int numberOfSpecialChars(String word) {
        
        HashSet<Character> set = new HashSet<>();

        for(char ch : word.toCharArray()) {
            set.add(ch);
        }

        int count = 0;

        for(char ch = 'a'; ch <= 'z'; ch++) {

            if(set.contains(ch) && set.contains((char)(ch - 'a' + 'A'))) {
                count++;
            }
        }

        return count;
    }
}
