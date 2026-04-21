import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        backtrack(nums, 0, current, result);

        return result;
    }

    private void backtrack(int[] nums, int start,
                           List<Integer> current,
                           List<List<Integer>> result) {

        // Add current subset
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {

            // Include element
            current.add(nums[i]);

            // Move to next
            backtrack(nums, i + 1, current, result);

            // Backtrack (remove element)
            current.remove(current.size() - 1);
        }
    }
}
