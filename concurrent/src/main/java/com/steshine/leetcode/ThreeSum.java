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

    /**
     * 无脑循环的思路，结果正确,但是参与比较的太多了
     *
     * @param nums
     * @return
     */
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
                if (i != j && index > -1 && index != i && index != j && !exist(nums[i], nums[j], nums[index], list)) {
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
        Set<Integer> input = new HashSet<>(Arrays.asList(i, j, index));
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


    /**
     * 尝试另一种思路
     * 因为最终需要结果为0的，所以先按照正负分组，这样都是同组内不用再比较了
     *
     * @param nums
     * @return
     */
    private List<String> hold = new ArrayList<>();

    public List<List<Integer>> threeSumV2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> sortedList = new ArrayList<>();
        if (nums.length < 3) {
            return Collections.emptyList();
        }
        for (int i : nums) {
            sortedList.add(i);
        }
        Collections.sort(sortedList);
        int left = 0;
        int right = sortedList.size() - 1;
        for (int i = 0; i < sortedList.size(); i++) {
            while (left < right) {
                int needSum = - sortedList.get(i); // 需要找到两个数组合结果是needSum的bu
                if(i != left && i != right){
                    Integer leftValue = sortedList.get(left);
                    Integer rightValue = sortedList.get(right);
                    int tmp = leftValue + rightValue;
                    if (needSum == tmp && !exist(sortedList.get(i), leftValue, rightValue)) {
                        list.add(Arrays.asList(leftValue, rightValue, sortedList.get(i)));
                        record(sortedList.get(i), leftValue, rightValue);
                    }else if(tmp < needSum){
                        left++;
                    }else {
                        right --;
                    }
                }else if(i == left){
                    left++;
                }else if(i == right){
                    right --;
                }
            }
            // 归零
            left = 0;
            right = sortedList.size() - 1;
        }
        return list;
    }

    private boolean exist(int sum, int i, int j) {
        List<Integer> integers = Arrays.asList(sum, i, j);
        Collections.sort(integers);
        //System.out.println(integers.stream().map(a -> a.toString()).collect(Collectors.joining()));
        return hold.contains(integers.toString());
    }

    private void record(int sum, int i, int j) {
        List<Integer> integers = Arrays.asList(sum, i, j);
        Collections.sort(integers);
        hold.add(integers.toString());
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        ThreeSum threeSum = new ThreeSum();
        long start = System.currentTimeMillis();
        List<List<Integer>> lists = threeSum.threeSumV2(nums);

        threeSum.print(lists);
        System.out.println("-------------：size:=" + lists.size() + "cost" + (System.currentTimeMillis() - start));
    }
}
