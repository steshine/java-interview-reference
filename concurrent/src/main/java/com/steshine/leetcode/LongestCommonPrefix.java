package com.steshine.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by skychen on 2019/4/10.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String longest = "";
        if (strs == null || strs.length == 0) {
            return longest;
        }
        // 之前的策略是先找最短,但是其实拿第一个就行
        String shortest = strs[0];//Arrays.stream(strs).sorted(Comparator.comparingInt(String::length)).findFirst().get();
        for (int j = shortest.length(); j > 0; j--) {
            longest = shortest.substring(0, j);
            int counter = 0;
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].startsWith(longest)) {
                    counter++;
                }
            }
            if (counter == strs.length) {
                return longest;
            } else {
                counter = 0;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String[] strs = new String []{"dog","racecar","car"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));
    }
}
