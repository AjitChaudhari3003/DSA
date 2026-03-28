// 2573. Find the String with LCP
// We define the lcp matrix of any 0-indexed string word of n lowercase English letters as an n x n grid such that:
// lcp[i][j] is equal to the length of the longest common prefix between the substrings word[i,n-1] and word[j,n-1].
// Given an n x n matrix lcp, return the alphabetically smallest string word that corresponds to lcp. If there is no such string, return an empty string.
// A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b. For example, "aabd" is lexicographically smaller than "aaca" because the first position they differ is at the third letter, and 'b' comes before 'c'.
// Example 1:
// Input: lcp = [[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]
// Output: "abab"
// Explanation: lcp corresponds to any 4 letter string with two alternating letters. The lexicographically smallest of them is "abab".


class Solution {

    class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }

        int findPar(int x) {
            if(parent[x] == x) return x;
            return parent[x] = findPar(parent[x]);
        }

        void unite(int x, int y) {
            int px = findPar(x);
            int py = findPar(y);

            if(px == py) return;

            if(rank[px] < rank[py]) parent[px] = py;
            else if(rank[px] > rank[py]) parent[py] = px;
            else {
                parent[px] = py;
                rank[py]++;
            }
        }
    }

    void compute(String word, int[][] dp) {
        int n = word.length();
        for(int i = n - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(word.charAt(i) == word.charAt(j)) {
                    if(i + 1 < n && j + 1 < n)
                        dp[i][j] = 1 + dp[i + 1][j + 1];
                    else
                        dp[i][j] = 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
    }

    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        DSU dsu = new DSU(n);

        for(int i = 0; i < n; i++) {
            if(lcp[i][i] != n - i) return "";
        }

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if(lcp[i][j] > 0)
                    dsu.unite(i, j);
            }
        }

        char[] grp = new char[n];
        char[] word = new char[n];
        char c = 'a';

        for(int i = 0; i < n; i++) {
            int p = dsu.findPar(i);
            if(grp[p] == 0) {
                if(c > 'z') return "";
                grp[p] = c++;
            }
            word[i] = grp[p];
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(lcp[i][j] == 0 && word[i] == word[j])
                    return "";
            }
        }

        int[][] dp = new int[n][n];
        compute(new String(word), dp);

        if(java.util.Arrays.deepEquals(dp, lcp))
            return new String(word);

        return "";
    }
}
