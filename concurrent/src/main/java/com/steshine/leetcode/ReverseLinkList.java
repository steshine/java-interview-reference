package com.steshine.leetcode;


public class ReverseLinkList {
    static class ListNode {
        private ListNode next;
        private int value;

        public ListNode(int value) {
            this.value = value;
        }
    }

    private ListNode init() {
        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return head;
    }

    //递归方式实现
    public ListNode reverseList01(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode newHead = reverseList01(head.next);  // 得到链表最后的元素
        head.next.next = head; // head此时是倒数第二个，利用他，出栈过程是从后向前移动head指针，按位置倒转就可以了
        head.next = null;
        return newHead;
    }

    /**
     * 循环方式
     * @param head
     * @return
     */
    public ListNode reverseList02(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            ListNode temp = cur.next; // 保存当前节点的指针
            cur.next = pre; // 反转当前节点指针指向
            pre = cur; // pre后移一位
            cur = temp; // cur 后移一位
        }
        head.next = null; // 头指next置空
        return pre;
    }


    public void print(ListNode head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ReverseLinkList reverseLinkList = new ReverseLinkList();
        ListNode head = reverseLinkList.init();
        ListNode listNode = reverseLinkList.reverseList02(head);
        reverseLinkList.print(listNode);

    }
}
