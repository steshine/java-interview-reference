package com.steshine.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by skychen on 2019/4/10.
 */
public class RomanToInt {
    static Map<String, Integer> mapping = new HashMap<>();
    static String keyArray[];

    static {
        mapping.put("I", 1);
        mapping.put("IV", 4);
        mapping.put("V", 5);
        mapping.put("IX", 9);
        mapping.put("X", 10);
        mapping.put("XL", 40);
        mapping.put("L", 50);
        mapping.put("XC", 90);
        mapping.put("C", 100);
        mapping.put("CD", 400);
        mapping.put("D", 500);
        mapping.put("CM", 900);
        mapping.put("M", 1000);
        keyArray = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    }

    public int romanToInt(String s) {
        if (s == null || s == "") {
            return 0;
        }
        if (mapping.containsKey(s)) {
            return mapping.get(s);
        }
        for (int i = 0; i < keyArray.length; i++) {
            if(s.startsWith(keyArray[i])){
                return mapping.get(keyArray[i]) + romanToInt(s.substring(keyArray[i].length(),s.length()));
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new RomanToInt().romanToInt("LVIII"));
    }
}
