import java.util.*;

class Solution {

    class Node {
        TreeNode node;
        int row, col;

        Node(TreeNode n, int r, int c) {
            node = n;
            row = r;
            col = c;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        // Map: column → list of [row, value]
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 0, 0));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            TreeNode node = curr.node;
            int row = curr.row;
            int col = curr.col;

            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }

            map.get(col).add(new int[]{row, node.val});

            if (node.left != null) {
                queue.offer(new Node(node.left, row + 1, col - 1));
            }
            if (node.right != null) {
                queue.offer(new Node(node.right, row + 1, col + 1));
            }
        }

        List<List<Integer>> result = new ArrayList<>();

        // Process columns in sorted order
        for (List<int[]> list : map.values()) {

            // Sort by row first, then value
            Collections.sort(list, (a, b) -> {
                if (a[0] == b[0]) return a[1] - b[1];
                return a[0] - b[0];
            });

            List<Integer> temp = new ArrayList<>();
            for (int[] arr : list) {
                temp.add(arr[1]);
            }

            result.add(temp);
        }

        return result;
    }
}
