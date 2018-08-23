package com.steshine.leetcode;

/**
 * Created by skychen on 2018/8/7.
 * 是否是回文数
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        int tmp = x;
        if (x < 10 && x > 0) {
            return true;
        }
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            rev = rev * 10 + pop;
        }
        return rev == tmp;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromeNumber().isPalindrome(121));
    }

}
