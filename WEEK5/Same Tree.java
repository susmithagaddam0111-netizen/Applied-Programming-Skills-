class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        // Case 1: both nodes are null
        if (p == null && q == null) {
            return true;
        }

        // Case 2: one is null OR values are different
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        // Case 3: check left and right subtrees
        return isSameTree(p.left, q.left) &&
               isSameTree(p.right, q.right);
    }
}
