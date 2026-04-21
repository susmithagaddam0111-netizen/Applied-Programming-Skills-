import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[numCourses];
        for(int[] p : prerequisites) {
            int a = p[0], b = p[1];
            graph[b].add(a);  
            indegree[a]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
            }
        }
        int count = 0;
        while(!q.isEmpty()) {
            int curr = q.poll();
            count++;

            for(int next : graph[curr]) {
                indegree[next]--;
                if(indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        return count == numCourses;
    }
}
