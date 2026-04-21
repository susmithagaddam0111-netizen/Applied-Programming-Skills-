import java.util.*;

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        dfs(root, targetSum, path, result);
        return result;
    }

    private void dfs(TreeNode node, int sum, List<Integer> path, List<List<Integer>> result) {

        if (node == null) return;

        // Add current node to path
        path.add(node.val);

        // If it's a leaf node and sum matches
        if (node.left == null && node.right == null && sum == node.val) {
            result.add(new ArrayList<>(path)); // store copy
        } else {
            // Continue searching
            dfs(node.left, sum - node.val, path, result);
            dfs(node.right, sum - node.val, path, result);
        }

        // Backtrack (remove last element)
        path.remove(path.size() - 1);
    }
}
