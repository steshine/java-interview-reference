package com.steshine.leetcode;


import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BinaryTree {
    static class TreeNode {
        private int index;
        private Object value;
        private TreeNode root;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int index) {
            this.index = index;
        }
    }

    /**
     * 构造一刻树
     * @return
     */
    private TreeNode init() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        // root
        node4.root = null;
        node4.left = node2;
        node4.right = node5;
        // 1 floor
        node2.root = node4;
        node2.left = node1;
        node2.right = node3;

        node5.root = node4;
        node5.right = node6;
        // 2 floor
        node1.root = node3;
        node3.root = node2;


        node6.root = node5;
        node6.right = node7;

        node7.root = node6;
        return node4;
    }

    /**
     * 先序遍历
     *
     * @param node
     */
    public void firstForeach(TreeNode node) {
        if (node != null) {
            System.out.println(node.index);
        }
        if (node.left != null) {
            firstForeach(node.left);
        }
        if (node.right != null) {
            firstForeach(node.right);
        }
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public void lastForeach(TreeNode node) {

        if (node.left != null) {
            lastForeach(node.left);
        }
        if (node.right != null) {
            lastForeach(node.right);
        }
        if (node != null) {
            System.out.println(node.index);
        }
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public void middleForeach(TreeNode node) {

        if (node.left != null) {
            middleForeach(node.left);
        }
        if (node != null) {
            System.out.println(node.index);
        }
        if (node.right != null) {
            middleForeach(node.right);
        }
    }

    /**
     * 最大叶子数
     *
     * @param node
     * @return
     */
    public int maxLeafWidth(TreeNode node) {
        BlockingQueue<TreeNode> queue = new ArrayBlockingQueue<>(16);
        int maxLeafCount = 0;
        // 根节点入队列
        queue.add(node);
        while (queue.size() > 0) {
            TreeNode treeNode = queue.poll();
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
            maxLeafCount = queue.size() > maxLeafCount ? queue.size() : maxLeafCount;
        }
        return maxLeafCount;
    }

    public int maxLeafHeight(TreeNode node) {
        BlockingQueue<TreeNode> queue = new ArrayBlockingQueue<>(16);
        int maxLeafHeight = 0;
        // 根节点入队列
        queue.add(node);
        while (queue.size() > 0) {
            // 清空队列 ,遍历当前层全部节点
            List<TreeNode> tmp = new ArrayList<>();
            for (TreeNode treeNode : queue.toArray(new TreeNode[]{})) {
                if (treeNode.left != null) {
                    tmp.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    tmp.add(treeNode.right);
                }
            }
            queue.clear();
            for (TreeNode treeNode : tmp) {
                queue.add(treeNode);
            }
            maxLeafHeight++;
        }
        return maxLeafHeight;
    }

    public TreeNode binarySearch(TreeNode node, int key) {
        if (node != null) {
            System.out.println("当前查找节点：" + node.index);
            if (node.index == key) {
                System.out.println("找到" + node.index);
                return node;
            }
            if (node.index > key) {
                return binarySearch(node.left, key);
            }
            if (node.index < key) {
                return binarySearch(node.right, key);
            }
        }
        throw new IndexOutOfBoundsException("not found this key = " + key);
    }

    public TreeNode depthFirstSearch(TreeNode node, int key) {
        Stack<TreeNode> container = new Stack<>();
        if (node != null) {
            container.push(node);
            while (!container.isEmpty()) {
                TreeNode treeNode = container.pop();
                System.out.println("当前查找节点：" + treeNode.index);
                if (treeNode.index == key) {
                    System.out.println("找到" + treeNode.index);
                    return treeNode;
                }
                if (treeNode.right != null) {
                    container.push(treeNode.right);
                }
                if (treeNode.left != null) {
                    container.push(treeNode.left);
                }

            }
        }
        throw new IndexOutOfBoundsException("not found this key = " + key);
    }

    public TreeNode breadthFirstSearch(TreeNode node, int key) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (node != null) {
            queue.add(node);
            while (!queue.isEmpty()) {
                TreeNode treeNode = queue.poll();
                System.out.println("当前查找节点：" + treeNode.index);
                if (treeNode.index == key) {
                    System.out.println("找到" + treeNode.index);
                    return treeNode;
                }

                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }

            }
        }
        throw new IndexOutOfBoundsException("not found this key = " + key);
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        System.out.println("先序----------------");
        TreeNode node = binaryTree.init();
        binaryTree.firstForeach(node);
        System.out.println("后序----------------");
        binaryTree.lastForeach(node);
        System.out.println("中序----------------");
        binaryTree.middleForeach(node);
        System.out.println("最大宽度----------------");
        System.out.println(binaryTree.maxLeafWidth(node));
        System.out.println("最大高度----------------");
        System.out.println(binaryTree.maxLeafHeight(node));
        System.out.println("二分查找----------------");
        System.out.println(binaryTree.binarySearch(node, 7).index);
        System.out.println("DFS查找----------------");
        System.out.println(binaryTree.depthFirstSearch(node, 7).index);
        System.out.println("BFS查找----------------");
        System.out.println(binaryTree.breadthFirstSearch(node, 7).index);
    }
}
