import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];

        backtrack(nums, new ArrayList<>(), used, result);

        return result;
    }

    private void backtrack(int[] nums, List<Integer> temp,
                           boolean[] used, List<List<Integer>> result) {

        // If permutation is complete
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (used[i]) continue;

            // Choose
            used[i] = true;
            temp.add(nums[i]);

            // Explore
            backtrack(nums, temp, used, result);

            // Backtrack
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }
}
