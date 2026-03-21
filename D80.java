// 3643. Flip Square Submatrix Vertically
// You are given an m x n integer matrix grid, and three integers x, y, and k.
// The integers x and y represent the row and column indices of the top-left corner of a square submatrix and the integer k represents the size (side length) of the square submatrix.
// Your task is to flip the submatrix by reversing the order of its rows vertically.
// Return the updated matrix.
// Example 1:
// Input: grid = [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]], x = 1, y = 0, k = 3
// Output: [[1,2,3,4],[13,14,15,8],[9,10,11,12],[5,6,7,16]]
// Explanation:
// The diagram above shows the grid before and after the transformation.


class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int top = x;
        int bottom = x + k - 1;

        while (top < bottom) {
            for (int j = y; j < y + k; j++) {
                int temp = grid[top][j];
                grid[top][j] = grid[bottom][j];
                grid[bottom][j] = temp;
            }
            top++;
            bottom--;
        }

        return grid;
    }
}
