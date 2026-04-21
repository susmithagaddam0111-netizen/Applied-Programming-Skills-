import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        // Graph: node -> [neighbors by color]
        List<Integer>[] redGraph = new ArrayList[n];
        List<Integer>[] blueGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            redGraph[i] = new ArrayList<>();
            blueGraph[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) {
            redGraph[e[0]].add(e[1]);
        }

        for (int[] e : blueEdges) {
            blueGraph[e[0]].add(e[1]);
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);

        // visited[node][color] → 0 = red, 1 = blue
        boolean[][] visited = new boolean[n][2];

        Queue<int[]> queue = new LinkedList<>();

        // Start from node 0 with both colors
        queue.offer(new int[]{0, 0}); // red
        queue.offer(new int[]{0, 1}); // blue

        visited[0][0] = true;
        visited[0][1] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0];
                int color = curr[1];

                if (result[node] == -1) {
                    result[node] = steps;
                }

                // Alternate color
                if (color == 0) { // last was red → go blue
                    for (int nei : blueGraph[node]) {
                        if (!visited[nei][1]) {
                            visited[nei][1] = true;
                            queue.offer(new int[]{nei, 1});
                        }
                    }
                } else { // last was blue → go red
                    for (int nei : redGraph[node]) {
                        if (!visited[nei][0]) {
                            visited[nei][0] = true;
                            queue.offer(new int[]{nei, 0});
                        }
                    }
                }
            }

            steps++;
        }

        return result;
    }
}
