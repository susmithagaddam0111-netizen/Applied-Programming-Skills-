class Solution {
    public int findJudge(int n, int[][] trust) {

        int[] degree = new int[n + 1];

        for (int[] t : trust) {
            int a = t[0];
            int b = t[1];

            degree[a]--; // a trusts someone
            degree[b]++; // b is trusted
        }

        // Judge will have degree = n - 1
        for (int i = 1; i <= n; i++) {
            if (degree[i] == n - 1) {
                return i;
            }
        }

        return -1;
    }
}
