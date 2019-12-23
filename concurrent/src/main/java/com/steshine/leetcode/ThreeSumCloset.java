package com.steshine.leetcode;

import java.util.Arrays;

/**
 * Created by skychen on 2019/4/30.
 */
public class ThreeSumCloset {
    public int threeSumClosest(int[] nums, int target) {
        final int VALUE = 1;
        int offset;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int left = 1,right = nums.length - 1; int sum = VALUE - nums[i];
            while (left < right){

            }
        }
        return VALUE;
    }
}
