package com.steshine.leetcode;

public class ParseInt {
    public int parseInt(String s) {
        if (s == null) {
            throw new NumberFormatException("s is null");
        }
        int i = 0;
        int len = s.length();
        int radix = 10;
        int result = 0;
        boolean negative = false;
        char first = s.charAt(0);
        if (first == '-') {
            negative = true;
        }
        while (i < len) {
            int digit = Character.digit(s.charAt(i++), radix);
            result *= radix;
            result += digit;
        }
        return negative ? -result : result;
    }

    public static void main(String[] args) {
        System.out.println(new ParseInt().parseInt("1980"));
    }
}
