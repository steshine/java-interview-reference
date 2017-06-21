package com.steshine.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cp123 on 2017/6/21.
 *
 *   Given nums = [2, 7, 11, 15], target = 9,
 *   Because nums[0] + nums[1] = 2 + 7 = 9,
 *   return [0, 1].
 */
public class TwoSum {

    private static int [] nums = new int[]{3,2,4};
    private static int target = 6;


    public int[] twoSum(int[] nums, int target) {
        int size = nums.length;
        for(int i = 0;i < size ;i++){
            int tmp = nums[i];
            for(int j = 0;j < size ;j++){
                if(i == j){
                    continue;
                }
                int sum = tmp + nums[j];
                if(sum == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0,0};
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    public static void main(String[] args) {
        int [] a = new TwoSum().twoSum2(nums,target);
        System.out.println(a[0]+","+a[1]);
    }
}
