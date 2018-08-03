package com.steshine.leetcode;

/**
 * Created by skychen on 2018/8/1.
 * ZigZag Conversion
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 */
public class Zigzag {
    public String convert(String s, int numRows) {
        int length = s.length();
        int containerNum = numRows;
        if(numRows == 1){
            return s;
        }
        if(length >= numRows){
            containerNum =  numRows * (length / numRows);
        }else {
            containerNum = length * numRows;
        }

        char container[][] = new char[containerNum][numRows];
        int x = 0;
        int y = numRows - 1;
        boolean downward = true;
        boolean upperRightward = false;

        for (int i = 0; i < length; i++) {
            System.out.println(x+","+y+"->"+s.charAt(i));
            container[x][y] = s.charAt(i);
            if (downward) {
                y--;
            } else if (upperRightward) {
                x++;
                y++;
            }
            if (y == 0) {
                downward = false;
                upperRightward = true;
            }
            if (y == numRows -1) {
                downward = true;
                upperRightward = false;
            }
        }
        return concatContainer(container,numRows);
    }

    private String concatContainer(char container[][],int containerNum ) {
        String source = "";
        for (int i = containerNum -1 ; i >= 0; i--) {//循环最外面的数组
            for (int j = 0; j < container.length ; j++) {//循环里面的数组
                if(container[j][i] > 0){
                    source = source.concat(String.valueOf(container[j][i]));
                }

            }
        }
        return source;
    }

    public static void main(String[] args) {
        System.out.println(new Zigzag().convert("A", 3));
    }
}
