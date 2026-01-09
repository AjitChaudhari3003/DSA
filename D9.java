// 865. Smallest Subtree with all the Deepest Nodes
// Given the root of a binary tree, the depth of each node is the shortest distance to the root.
// Return the smallest subtree such that it contains all the deepest nodes in the original tree.
// A node is called the deepest if it has the largest depth possible among any node in the entire tree.
// The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.
// Example 1:
// Input: root = [3,5,1,6,2,0,8,null,null,7,4]
// Output: [2,7,4]
// Explanation: We return the node with value 2, colored in yellow in the diagram.
// The nodes coloured in blue are the deepest nodes of the tree.
// Notice that nodes 5, 3 and 2 contain the deepest nodes in the tree but node 2 is the smallest subtree among them, so we return it.


class Solution {
    class Result {
        int height;
        TreeNode node;
        Result(int h, TreeNode n) {
            height = h;
            node = n;
        }
    }

    private Result dfs(TreeNode node) {
        if (node == null) return new Result(0, null);
        
        Result left = dfs(node.left);
        Result right = dfs(node.right);
        
        if (left.height > right.height) {
            return new Result(left.height + 1, left.node);
        } else if (right.height > left.height) {
            return new Result(right.height + 1, right.node);
        } else {
            return new Result(left.height + 1, node);
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }
}
