package com.steshine.leetcode;

/**
 * Created by cp123 on 2018/7/17.
 */
public class MedianofTwoSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] sortedNums = new int[n1 + n2];
        int length = sortedNums.length;
        // 先合并数组
        int i = 0; // 总长度
        int n1p = 0; // 第一个数组轮训节点位置
        int n2p = 0; // 第二数组轮训的节点位置
        int n1f = 0; // 第一数组当前节点
        int n2f = 0; // 第二数组当前节点
        int k = 0; // 当前值
        boolean n1OK = false;
        boolean n2Ok = false;
        while (i < length) {
            if (n1p < n1) {
                n1f = nums1[n1p];
            }else {
                n1OK = true;
            }
            if (n2p < n2) {
                n2f = nums2[n2p];
            }else {
                n2Ok = true;
            }

            if (n1OK) {
                k = n2f;
                n2p++;
            } else if(n2Ok) {
                k = n1f;
                n1p ++;
            }else if( n1f < n2f){
                k = n1f;
                n1p++;
            }else {
                k = n2f;
                n2p++;
            }

            sortedNums[i] = k;
            i++;
        }
        // 计算中位数

        if (length % 2 == 1) {
            return sortedNums[length / 2];
        } else {
            return (double) (sortedNums[length / 2 -1] + sortedNums[length / 2 ]) / 2;
        }
    }

    public static void main(String[] args) {
        int a[] = new int[]{1,2};
        int b[] = new int[]{3,4};
        System.out.println(findMedianSortedArrays(b, a));
    }
}
