import java.util.*;

class Solution {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        // Step 1: Assign unique group to -1 items
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        // Graphs
        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();

        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];

        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) groupGraph.add(new ArrayList<>());

        // Step 2: Build graphs
        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {

                // Item dependency
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                // Group dependency
                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        // Step 3: Topo sort groups
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);
        if (groupOrder.size() == 0) return new int[0];

        // Step 4: Topo sort items
        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        if (itemOrder.size() == 0) return new int[0];

        // Step 5: Group items based on group order
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int item : itemOrder) {
            map.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        List<Integer> result = new ArrayList<>();

        for (int g : groupOrder) {
            if (map.containsKey(g)) {
                result.addAll(map.get(g));
            }
        }

        // Convert to array
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    // Topological Sort (Kahn’s Algorithm)
    private List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int size) {

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result.add(curr);

            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return result.size() == size ? result : new ArrayList<>();
    }
}
