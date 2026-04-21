class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Base case
        if (root == null || root == p || root == q) {
            return root;
        }

        // Search in left subtree
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // Search in right subtree
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both sides return non-null → current node is LCA
        if (left != null && right != null) {
            return root;
        }

        // Otherwise return the non-null side
        return (left != null) ? left : right;
    }
}
