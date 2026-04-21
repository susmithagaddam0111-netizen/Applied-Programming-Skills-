class Solution {

    int index = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] preorder, int min, int max) {

        // If all elements used or value out of valid range
        if (index >= preorder.length || preorder[index] < min || preorder[index] > max) {
            return null;
        }

        // Create node
        int val = preorder[index++];
        TreeNode root = new TreeNode(val);

        // Build left subtree (values < root)
        root.left = build(preorder, min, val);

        // Build right subtree (values > root)
        root.right = build(preorder, val, max);

        return root;
    }
}
