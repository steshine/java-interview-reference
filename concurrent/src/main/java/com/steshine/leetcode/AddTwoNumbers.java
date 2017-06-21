package com.steshine.leetcode;

import java.util.List;

/**
 * Created by cp123 on 2017/6/21.
 */
public class AddTwoNumbers {

    private static ListNode l1 = null;
    private static ListNode l2 = null;
    private static ListNode l3 = null;

    static {
        l1 = new ListNode(1);
        l1.next = new ListNode(8);

        l2 = new ListNode(0);

    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode tmp = result;
        while (l1 != null || l2 != null ) {
            int sum = 0;
            if(l1 != null){
                sum += l1.val;
            }
            if(l2 != null){
                sum += l2.val;
            }
                sum += tmp.val;

            int high = sum / 10;
            if (high > 0) {
                tmp.val = sum % 10;
                tmp.next = new ListNode(1);
            }else {
                tmp.val = sum;
                tmp.next =  new ListNode(0);
            }
            if(l1 == null || l2 == null || (l1.next == null && l2.next == null)){//todo 优化一下这个地方的逻辑
                tmp.next = null;
                break;
            }
            l1 = l1.next;
            l2 = l2.next;
            tmp = tmp.next;

        }
        return result;
    }

    public static void main(String[] args) {
        ListNode l3 = new AddTwoNumbers().addTwoNumbers(l1,l2);
        while (l3 != null){
            System.out.print(l3.val +" ,");
            l3 = l3.next;
        }
    }



    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
