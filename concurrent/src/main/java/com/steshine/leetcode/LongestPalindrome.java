package com.steshine.leetcode;

/**
 * Created by skychen on 2018/7/23.
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 */

public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int maxLength = 0;
        int start = 0;// 2. start position
        int end = 0;// 3. end position
        StringBuffer queue = new StringBuffer();// 1.a container that recode substring
        String[] arrays = s.split("");

        for (int i = 0; i < arrays.length; i++) {
            for (int j = i; j < arrays.length; j++) {
                queue.append(arrays[j]);
                if (isPalindrome(queue)) {
                    if (maxLength < queue.length()) {
                        maxLength = queue.length();
                        start = i;
                        end = j;
                        if(start == 0 && end == arrays.length){
                            break;
                        }
                    }
                }
            }
            queue = new StringBuffer();
        }

        return subString(arrays, start, end);
    }

    private boolean isPalindrome(StringBuffer queue) {
        if (queue.length() < 2) {
            return false;
        }
        int times = queue.length() / 2;
        int head = 0;
        int tail = queue.length();
        int i = times;
        int evenCounter = 0;
        while (i > 0) {
            if (queue.substring(head, head + 1).equals(queue.substring(tail - 1, tail))) {
                evenCounter++;
            }else {
                break;
            }
            i--;
            head++;
            tail--;
        }
        return evenCounter > 0 && evenCounter == times;
    }

    private String subString(String[] array, int start, int end) {
        StringBuffer sb = new StringBuffer();
        for (int i = start; i <= end; i++) {
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindrome().longestPalindrome("ccc"));
    }
}
