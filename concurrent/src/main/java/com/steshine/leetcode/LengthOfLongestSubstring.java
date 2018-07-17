package com.steshine.leetcode;

import java.util.*;

/**
 * Created by cp123 on 2017/6/22.
 * <p>
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * <p>
 * 翻译：
 * 给定一个字符串，找到最长的连续不重复子字符串长度
 * <p>
 * 这个写法可以，但是有一个问题，时间复杂度不够 尝试优化一个版本
 */
public class LengthOfLongestSubstring {
    public int max_substring(String s) {
        if (s == "" || s == null) {
            return 0;
        }
        String[] array = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            array[i] = s.substring(i, i + 1);
        }
        if (array.length == 1)
            return 1;
        int length = s.length();
        int begin = 0;    //记录最长子串的起点  以便输出最长串
        int end = 0;    //记录最长子串的终点  以便输出最长串
        int maxlen = 0;        //最长不重复字符串长度
        int j = 0;          //记录当前不重复字符串的起点
        int i = 0;          //记录当前不重复字符串的终点
        Map<String, Integer> carry = new HashMap<>();
        int k = 0;

        while (j < length) {
            if (!carry.containsKey(array[j])) {
                carry.put(array[j], j);
            } else {
                j--;
                if (j - i + 1 > maxlen) {
                    maxlen = j - i + 1;   /*更新结果取较大者*/
                    begin = i;        /*  并记录当前较大子串的起点*/
                    end = j;         /*  并记录当前较大子串的终点*/
                }
                i ++;
                j = i -1 ;
                carry.clear();
            }
            j++;
        }
        if (!carry.isEmpty() && carry.size() > maxlen) {
            maxlen = carry.size();
        }
        return maxlen;
    }



    public static void main(String[] args) {
        String source = "dvdf";
        int length = new LengthOfLongestSubstring().max_substring(source);
        System.out.println(length);
    }
}
