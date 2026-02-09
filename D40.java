// 1382. Balance a Binary Search Tree
// Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is more than one answer, return any of them.
// A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
// Example 1:
// Input: root = [1,null,2,null,3,null,4,null,null]
// Output: [2,1,3,null,null,null,4]
// Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.

class Solution {
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<Integer> arr=new ArrayList<>();
        inorder(root,arr);;
        root=bst(arr,0,arr.size()-1);
        return root;
    }
    void inorder(TreeNode root,ArrayList<Integer> arr){
        if(root==null){
            return;
        }
        inorder(root.left,arr);
        arr.add(root.val);
        inorder(root.right,arr);
    }
    TreeNode bst(ArrayList<Integer> arr,int s,int e){
        if(s>e) return null;
        int mid=(s+e)/2;

        TreeNode root=new TreeNode(arr.get(mid));
        root.left=bst(arr,s,mid-1);
        root.right=bst(arr,mid+1,e);

        return root;
    }
}
