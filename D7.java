// 1339. Maximum Product of Splitted Binary Tree
// Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
// Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.
// Note that you need to maximize the answer before taking the mod and not after taking it.
// Example 1:
// Input: root = [1,2,3,4,5,6]
// Output: 110
// Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)


class Solution {
    long totalSum = 0;
    long maxProd = 0;
    static final int MOD = 1_000_000_007;

    private void dfsTotal(TreeNode root) {
        if (root == null) return;
        totalSum += root.val;
        dfsTotal(root.left);
        dfsTotal(root.right);
    }

    private long dfs(TreeNode root) {
        if (root == null) return 0;

        long left = dfs(root.left);
        long right = dfs(root.right);

        long subSum = left + right + root.val;
        maxProd = Math.max(maxProd, subSum * (totalSum - subSum));

        return subSum;
    }

    public int maxProduct(TreeNode root) {
        dfsTotal(root);
        dfs(root);
        return (int)(maxProd % MOD);
    }
}
