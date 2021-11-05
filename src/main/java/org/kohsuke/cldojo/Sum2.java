package org.kohsuke.cldojo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Kohsuke Kawaguchi
 */
public class Sum2 {
    /**
     * DONE!
     * O(n^2)
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i=0; i<nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i==j) {
                    continue;
                }
                if (nums[i]+nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    // O(n)
    public int[] twoSumHash(int[] nums, int target) {
        var hash = new HashSet<Integer>();
        for (int num : nums) {
            hash.add(num);
        }

        for (int i=0; i<nums.length; i++) {
            int desired = target-nums[i];
            // do we have 'desired' in 'nums'?
            // list -> O(n)
            // tree -> O(log(n))
            // hash -> O(1)
            if (hash.contains(desired)) {
                // find the index of 'desired' that doesn't collide with i
                for (int j=0; j<nums.length; j++) {
                    if (nums[j]==desired && i!=j) {
                        return new int[]{i, j};
                    }
                }
            }
        }

        return null;
    }

    public int[] twoSumTree(int[] nums, int target) {
        int[] sorted = Arrays.copyOf(nums,nums.length);
        Arrays.sort(sorted);

        for (int i=0; i<nums.length; i++) {
            int desired = target-nums[i];
            int p = Arrays.binarySearch(sorted,desired);
            if (p>=0) {
                // we found a pair [i,p] except their indices are messed up
                // ...
            }
        }

        return null;
    }
}
