class Solution {
    public int minCostClimbingStairs(int[] cost) {

        int prev1 = 0; // cost to reach previous step
        int prev2 = 0; // cost to reach step before previous

        for (int c : cost) {
            int curr = c + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = curr;
        }

        // Final step: min of last two
        return Math.min(prev1, prev2);
    }
}
