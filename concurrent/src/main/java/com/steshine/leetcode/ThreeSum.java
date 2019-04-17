package com.steshine.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by skychen on 2019/4/17.
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * via. https://leetcode.com/problems/3sum/
 */
public class ThreeSum {
    private int[] nums;

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 3) {
            return Collections.emptyList();
        }
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                int twoSum = nums[i] + nums[j];
                int index = index(-twoSum);
                if (i != j && index > -1 && index != i && index != j && !exist(nums[i],nums[j],nums[index],list)) {
                    list.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[index]}));
                }
            }
        }
        return list;
    }

    private int index(int num) {
        for (int i = 0; i < nums.length; i++) {
            if (num == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    private boolean exist(int i, int j, int index, List<List<Integer>> lists) {
        Set<Integer> input = new HashSet<>(Arrays.asList(i,j,index));
        return lists.stream().filter(list -> {
            Set<Integer> set = new HashSet<>(list);
            set.addAll(input);
            return set.size() == input.size();
        }).findFirst().isPresent();
    }

    public void print(List<List<Integer>> list) {
        for (List<Integer> l : list) {
            String lstr = "[" + l.stream().map(Object::toString).collect(Collectors.joining(",")) + "]";
            System.out.println(lstr);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{12,13,-10,-15,4,5,-8,11,10,3,-11,4,-10,4,-7,9,1,8,-5,-1,-9,-4,3,-14,-11,14,0,-8,-6,-2,14,-9,-4,11,-8,-14,-7,-9,4,10,9,9,-1,7,-10,7,1,6,-8,12,12,-10,-7,0,-9,-3,-1,-1,-4,8,12,-13,6,-7,13,5,-14,13,12,6,8,-2,-8,-15,-10,-3,-1,7,10,7,-4,7,4,-4,14,3,0,-10,-13,11,5,6,13,-4,6,3,-13,8,1,6,-9,-14,-11,-10,8,-5,-6,-7,9,-11,7,12,3,-4,-7,-6,14,8,-1,8,-4,-11};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(nums);
        System.out.println("-------------");
        threeSum.print(lists);
    }
}
