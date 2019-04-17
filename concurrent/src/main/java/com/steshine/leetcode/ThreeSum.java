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
        List<Integer> singleHold = new ArrayList<>();
        if (nums.length < 3) {
            return Collections.emptyList();
        }
        List<Integer> positiveList = new ArrayList<>();
        List<Integer> negativeList = new ArrayList<>();
        List<Integer> zeroList = new ArrayList<>();
        // 先分组
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                positiveList.add(nums[i]);
            } else if (nums[i] < 0) {
                negativeList.add(nums[i]);
            } else {
                zeroList.add(nums[i]);
            }
        }
        // 判断不满足条件的数据
        if (zeroList.size() < 3 && (positiveList.size() < 0 || negativeList.size() < 0)) {
            return list;
        }
        for (int i = 0; i < negativeList.size(); i++) {
            for (int j = 0; j < positiveList.size(); j++) {
                int left = negativeList.get(i);
                int right = positiveList.get(j);
                int sum = left + right;
                if(sum > 0){
                    negativeList.remove(i);
                    if(negativeList.contains(-sum) && !exist(-sum,left,right)){
                        list.add(Arrays.asList(left, right, -sum));
                        record(left, right, -sum);
                    }
                    negativeList.add(i,left);
                }else if(sum < 0 ){
                    positiveList.remove(j);
                    if(positiveList.contains(-sum) && !exist(-sum,left,right)){
                        list.add(Arrays.asList(left, right, -sum));
                        record(left, right, -sum);
                    }
                    positiveList.add(j,right);
                }else if(zeroList.size() > 0 && !singleHold.contains(positiveList.get(j))) {
                    list.add(Arrays.asList(left, 0,right));
                    singleHold.add(positiveList.get(j));
                }
            }
        }
        if (zeroList.size() > 0) {
            if (zeroList.size() > 2) {
                list.add(Arrays.asList(0, 0, 0));
            }
        }

        return list;
    }

    private boolean exist(int sum,int i ,int j){
        List<Integer> integers = Arrays.asList(sum, i, j);
        Collections.sort(integers);
        System.out.println(integers.stream().map(a -> a.toString()).collect(Collectors.joining()));
        return hold.contains(integers.toString());
    }

    private void record(int sum,int i ,int j){
        List<Integer> integers = Arrays.asList(sum, i, j);
        Collections.sort(integers);
        hold.add(integers.toString());
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,-2,1};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSumV2(nums);
        System.out.println("-------------");
        threeSum.print(lists);
    }
}
