import java.util.*;

class Solution {

    class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // path compression
            }
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px != py) parent[py] = px;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int n = accounts.size();
        DSU dsu = new DSU(n);

        // Map email → account index
        HashMap<String, Integer> map = new HashMap<>();

        // Step 1: Union accounts with same email
        for (int i = 0; i < n; i++) {
            List<String> acc = accounts.get(i);

            for (int j = 1; j < acc.size(); j++) {
                String email = acc.get(j);

                if (map.containsKey(email)) {
                    dsu.union(i, map.get(email));
                } else {
                    map.put(email, i);
                }
            }
        }

        // Step 2: Group emails by parent
        HashMap<Integer, TreeSet<String>> groups = new HashMap<>();

        for (String email : map.keySet()) {
            int parent = dsu.find(map.get(email));

            groups.putIfAbsent(parent, new TreeSet<>());
            groups.get(parent).add(email);
        }

        // Step 3: Build result
        List<List<String>> result = new ArrayList<>();

        for (int parent : groups.keySet()) {
            List<String> list = new ArrayList<>();

            // Add name
            list.add(accounts.get(parent).get(0));

            // Add sorted emails
            list.addAll(groups.get(parent));

            result.add(list);
        }

        return result;
    }
}
