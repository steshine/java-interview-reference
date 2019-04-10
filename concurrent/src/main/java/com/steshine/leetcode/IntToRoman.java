package com.steshine.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by skychen on 2019/3/21.
 */
public class IntToRoman {
    static Map<Integer, String> mapping = new HashMap<>();
    static Integer keyArray[];

    static {
        mapping.put(1, "I");
        mapping.put(4, "IV");
        mapping.put(5, "V");
        mapping.put(9, "IX");
        mapping.put(10, "X");
        mapping.put(40, "XL");
        mapping.put(50, "L");
        mapping.put(90, "XC");
        mapping.put(100, "C");
        mapping.put(400, "CD");
        mapping.put(500, "D");
        mapping.put(900, "CM");
        mapping.put(1000, "M");
        keyArray = new Integer[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    }

    public String intToRoman(int num) {
        if (num == 0) {
            return "";
        }
        if (mapping.containsKey(num)) {
            return mapping.get(num);
        }
        // 找到最大的公约数
        int max = 0;
        for (int i = keyArray.length - 1; i >= 0; i--) {
            if (keyArray[i] < num) {
                max = keyArray[i];
                break;
            }
        }
        int n = num / max;
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            s.append(mapping.get(max));
        }
        return s.append(intToRoman(num % max)).toString();
    }

    public static void main(String[] args) {
        System.out.println(new IntToRoman().intToRoman(1994));
    }
}
