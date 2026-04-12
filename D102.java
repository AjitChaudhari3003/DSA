// 1320. Minimum Distance to Type a Word Using Two Fingers
// You have a keyboard layout as shown above in the X-Y plane, where each English uppercase letter is located at some coordinate.
// For example, the letter 'A' is located at coordinate (0, 0), the letter 'B' is located at coordinate (0, 1), the letter 'P' is located at coordinate (2, 3) and the letter 'Z' is located at coordinate (4, 1).
// Given the string word, return the minimum total distance to type such string using only two fingers.
// The distance between coordinates (x1, y1) and (x2, y2) is |x1 - x2| + |y1 - y2|.
// Note that the initial positions of your two fingers are considered free so do not count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.
// Example 1:
// Input: word = "CAKE"
// Output: 3
// Explanation: Using two fingers, one optimal way to type "CAKE" is: 
// Finger 1 on letter 'C' -> cost = 0 
// Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2 
// Finger 2 on letter 'K' -> cost = 0 
// Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1 
// Total distance = 3

class Solution {
    int[][][] dp;

    int dist(int a, int b){
        if(a == 26 || b == 26) return 0;

        int r1 = a / 6, c1 = a % 6;
        int r2 = b / 6, c2 = b % 6;

        return Math.abs(r1-r2) + Math.abs(c1-c2);
    }

    int solve(int i, int f1, int f2, String word){
        if(i == word.length()) return 0;

        if(dp[i][f1][f2] != -1)
            return dp[i][f1][f2];

        int cur = word.charAt(i) - 'A';

        int move1 = dist(f1, cur) +
                    solve(i+1, cur, f2, word);

        int move2 = dist(f2, cur) +
                    solve(i+1, f1, cur, word);

        return dp[i][f1][f2] = Math.min(move1, move2);
    }

    public int minimumDistance(String word) {
        dp = new int[301][27][27];

        for(int i=0;i<301;i++)
            for(int j=0;j<27;j++)
                Arrays.fill(dp[i][j], -1);

        return solve(0, 26, 26, word);
    }
}
