package com.steshine.leetcode;

/**
 * 最长公共子串
 * 使用动态规划
 */
public class LongestCommonSubstring {
    public String longestCommonSubstring(String a, String b) {
        int al = a.length();
        int bl = b.length();
        int max = 0;
        int maxI = 0;
        int[][] cell = new int[al][bl];
        for (int i = 0; i < al; i++) {
            for (int j = 0; j < bl; j++) {
                int tmp = 0;
                if (a.charAt(i) == b.charAt(j)) {
                    if (i > 0 && j > 0)
                        tmp = cell[i][j] = cell[i - 1][j - 1] + 1;
                } else {
                    cell[i][j] = 0;
                }
                if (tmp > max) {
                    max = tmp;
                    maxI = i;
                }
            }
        }
        return a.substring(maxI - max +1, maxI+1);
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubstring().longestCommonSubstring("fist88", "hist999"));
    }
}
