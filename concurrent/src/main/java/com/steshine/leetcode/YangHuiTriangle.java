package com.steshine.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 杨辉三角形的简化版本，输出类似图形
 */
public class YangHuiTriangle {
    public void trianglePrint(int n) {
        List<String> container = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int length = 2 * (n -1)  - i;
            StringBuffer sb = new StringBuffer();
            boolean single = i % 2 == 0;
            int j = 0;
            while (j <= length) {
                if (j % 2 == (single ? 0 : 1) && j >= i) {
                    sb.append("*");
                } else {
                    sb.append(" ");
                }
                j++;
            }
            container.add(sb.toString());
        }
        Collections.reverse(container);
        for (String i : container) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        new YangHuiTriangle().trianglePrint(5);
    }
}
