package org.kohsuke.cldojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

import static java.util.Arrays.*;

/**
 * @author Kohsuke Kawaguchi
 */
public class Sum3 {
    /**
     * Slow version: O(n^3)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        var answers = new ArrayList<List<Integer>>();
        // have (i,j,k) cover all the possible combinations of the index without duplication
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (nums[i]+nums[j]+nums[k]==0) {
                        answers.add(List.of(i,j,k));
                    }
                }
            }
        }
        return answers;
    }

    // facade to meet the poor taste of the designer of the quiz
    public List<List<Integer>> findTriplets(int[] nums) {
        var l = new ArrayList<Integer>(nums.length);
        for (int num : nums) {
            l.add(num);
        }
        return new ArrayList<>(findTriplets(l));
    }

    /**
     * Find all the unique triplets that adds up to 0.
     *
     * Better version: O(n^2)
     *
     * <pre>
     * Input: nums = [-1,0,1,2,-1,-4]
     * Output: [[-1,-1,2],[-1,0,1]]
     * </pre>
     */
    public Set<List<Integer>> findTriplets(List<Integer> _nums) {
        var nums = dedup(_nums);

        // Builds a hash set of all the nums.
        var hash = (Set<Integer>) new HashSet<>(nums);

        // use set to eliminate duplicates -- we'll turn it into
        var answers = new HashSet<List<Integer>>();

        // have (i,j,k) cover all the possible combinations of the index without duplication
        loop(nums, 0, (i,ii) -> {
            loop(nums, i+1, (j,jj) -> {
                int desired = -ii - jj;
                if (hash.contains(desired)) {
                    loop(nums, j+1, (k,kk) -> {
                        if (ii + jj + kk == 0) {
                            answers.add(sort(ii, jj, kk));
                        }
                    });
                }
            });
        });

        return answers;
    }

    /**
     * Blah blah blah
     */
    private void loop(List<Integer> l, int start, BiConsumer<Integer,Integer> f) {
        for (int i = start; i < l.size(); i++) {
            int x = l.get(i);
            f.accept(i, x);
        }
    }

    /**
     * We don't need more than three of the same digits.
     */
    private List<Integer> dedup(List<Integer> nums) {
        // count the occurence of each number
        var count = new HashMap<Integer,Integer>();

        var copy = new ArrayList<Integer>(nums.size());
        for (Integer n : nums) {
            var c = count.getOrDefault(n, 0);
            count.put(n, c +1);
            if (c<3)
                copy.add(n);
        }
        return copy;
    }

    private List<Integer> sort(int i, int j, int k) {
        var l = new ArrayList<>(asList(i,j,k));
        Collections.sort(l);
        return l;
    }
}
