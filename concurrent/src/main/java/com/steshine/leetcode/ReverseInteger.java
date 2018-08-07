package com.steshine.leetcode;

/**
 * Created by skychen on 2018/8/3.
 */
public class ReverseInteger {
    /**
     * 无脑写法 依赖字符串转整型
     * @param x
     * @return
     */
    public int reverse(int x) {
        int reverse = 0;
        StringBuffer sb = new StringBuffer();
        String source = String.valueOf(x);
        if (x < 0) {
            sb.append(source.substring(0, 1));
            source = source.substring(1, source.length());
        }
        for (int i = source.length() - 1; i >= 0; i--) {
            sb.append(source.charAt(i));
        }
        try {
            reverse = Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
        }
        return reverse;
    }

    /**
     * 借鉴上面的字符串反转， 每次讲拿到的数字插入到反转数字的最后 rev *10 + pop 为插入过程
     * @param x
     * @return
     */
    public int reverse2(int x){
        int rev = 0;
        while (x != 0){
            int pop = x % 10;
            x = x /10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev =  rev * 10 + pop;
        }
        return rev;
    }

    /**
     *   12 = 10 * 1 + 2 反转即为  21 = 2 * 10 + 1
     * @param x
     * @return
     */
    public int reverse3(int x){
        int rev = 0;
        while (x != 0){
            int tail = x % 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && tail > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && tail < -8)) return 0;
            if(x < 0){
                tail = -tail;
                rev =  rev + tail * (int)Math.pow(10,String.valueOf(x).length() -1);
            }else {
                rev =  rev + tail * (int)Math.pow(10,String.valueOf(x).length() -2);
            }
            x = x /10;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInteger().reverse3(-2147483412));
    }
}
