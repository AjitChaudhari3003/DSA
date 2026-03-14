// 1415. The k-th Lexicographical String of All Happy Strings of Length n
// A happy string is a string that:
// consists only of letters of the set ['a', 'b', 'c'].
// s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
// For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
// Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
// Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
// Example 1:
// Input: n = 1, k = 3
// Output: "c"
// Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".

class Solution {

    public String getHappyString(int n, int k) {

        int total = 3 * (1<<(n-1));
        if(k>total) return "";

        k--;
        StringBuilder res=new StringBuilder();
        char last='\0';

        for(int pos=0;pos<n;pos++){

            int branch=1<<(n-pos-1);

            List<Character> choices=new ArrayList<>();
            for(char c:new char[]{'a','b','c'})
                if(c!=last) choices.add(c);

            int idx=k/branch;
            res.append(choices.get(idx));
            last=choices.get(idx);
            k%=branch;
        }

        return res.toString();
    }
}
