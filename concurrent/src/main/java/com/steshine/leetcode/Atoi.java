package com.steshine.leetcode;

/**
 * Created by skychen on 2018/8/7.
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 */
public class Atoi {
    public int myAtoi(String str) {
        int result = 0;
        int length = str.length();
        boolean negative = false;
        boolean stamp = false;
        if (length == 0) {
            return 0;
        }
        for (int i = 0; i < length; i++) {
            int c = str.charAt(i) - 48;
            if (stamp == false && c == -3) {
                negative = true;
                stamp = true;
                continue;
            }
            if( stamp == false && c == -5){
                negative = false;
                stamp = true;
                continue;
            }
            if(stamp == false && c == -16){// blank char
                continue;
            }
            if(c >= 0 && c <= 9){
                if(negative){
                    c = -c;
                }
                stamp = true;
                if (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE / 10 && c > 7)) {
                    return Integer.MAX_VALUE;
                }
                if (result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE / 10 && c < -8)){
                    return Integer.MIN_VALUE;
                }
                result = result * 10 + c;
            }else {
                break;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Atoi().myAtoi("+0 123"));
        System.out.println(8 >>> 2);
        Integer.parseInt("123");
    }
}
