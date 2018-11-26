package com.steshine.leetcode;

/**
 * Created by skychen on 2018/8/7.
 * 是否是回文数
 *  Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        int tmp = x;
        // 特殊情况
        if (x < 10 && x > 0) {
            return true;
        }
        if( x < 0){
            return false;
        }
        int rev = 0;
        // 反转 比较
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
