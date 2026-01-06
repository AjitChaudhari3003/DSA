// 1161. Maximum Level Sum of a Binary Tree
// Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
// Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
// Example 1:
// Input: root = [1,7,0,7,-8,null,null]
// Output: 2
// Explanation: 
// Level 1 sum = 1.
// Level 2 sum = 7 + 0 = 7.
// Level 3 sum = 7 + -8 = -1.
// So we return the level with the maximum sum which is level 2.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxLevelSum(TreeNode root) {
        Queue <TreeNode> q = new LinkedList<>();

        q.add(root);
        int lvl=1, ans=1;
        long mx= Long.MIN_VALUE;

        while(!q.isEmpty()){
            int sz=q.size();
            long s=0;

            for(int i=0;i<sz;i++){
                TreeNode n=q.poll();
                s +=n.val;
                if(n.left !=null) q.add(n.left);
                if(n.right !=null) q.add(n.right);
            }

            if(s>mx){
                mx=s;
                ans= lvl;
            }
            lvl++;
        }
        return ans;
    }
}
