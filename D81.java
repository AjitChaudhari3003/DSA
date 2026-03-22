// 1886. Determine Whether Matrix Can Be Obtained By Rotation
// Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.
// Example 1:
// Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
// Output: true
// Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.
// Example 2:
// Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
// Output: false
// Explanation: It is impossible to make mat equal to target by rotating mat.


class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        if(compare(mat, target))return true;
        int count = 3;
        while(count > 0){
            mat = rotate(mat);
            if(compare(mat, target))return true;
            count--;
        }
        return false;
    }

    private boolean compare(int[][] mat, int[][] target){
        int n = mat.length;
        for(int i=0;i<n;i++)for(int j=0;j<n;j++)if(mat[i][j]!=target[i][j])return false;
        return true;
    }

    private int[][]rotate(int[][]mat){
        int n = mat.length;
        int[][]res = new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                res[n-1-j][i] = mat[i][j];
            }
        }

        return res;
    }
}
