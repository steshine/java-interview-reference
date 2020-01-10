package com.steshine.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graphic {
    public static class GraphicNode {
        private int index;
        private String value;
        private GraphicNode[] neighbor; // 相邻节点

        public GraphicNode(int index, String value) {
            this.index = index;
            this.value = value;
        }

        public void neighbor(GraphicNode... neighbor) {
            this.neighbor = neighbor;
        }
    }

    private GraphicNode init() {
        GraphicNode node0 = new GraphicNode(0, "惠大园");
        GraphicNode node1 = new GraphicNode(1, "南口");
        GraphicNode node2 = new GraphicNode(2, "6里桥");
        GraphicNode node3 = new GraphicNode(3, "小泥巴");
        GraphicNode node4 = new GraphicNode(4, "水库");
        GraphicNode node5 = new GraphicNode(5, "河北");
        node0.neighbor(node1, node2);
        node1.neighbor(node3);
        node2.neighbor(node4);
        node3.neighbor(node5);
        node4.neighbor(node5);
        return node0;
    }

    public GraphicNode breadthFirstSearch(GraphicNode node, int index) {
        Queue<GraphicNode> s = new LinkedList<>();
        List<GraphicNode> searched = new LinkedList<>();
        if (node != null) {
            s.add(node);
        }
        while (!s.isEmpty()) {
            GraphicNode poll = s.poll();
            if (poll.index == index) {
                return poll;
            } else {
                for (GraphicNode neighbor : poll.neighbor) {
                    if (!searched.contains(neighbor)) {
                        s.add(neighbor);
                    }
                }
            }
            searched.add(poll);
        }
        throw new IndexOutOfBoundsException("index :" + index + " not found");
    }

    /**
     * 卡恩于1962年提出的算法。
     * 简单来说就是，假设L是存放结果的列表，先找到那些入度为零的节点，把这些节点放到L中，因为这些节点没有任何的父节点。
     * 然后把与这些节点相连的边从图中去掉，再寻找图中的入度为零的节点。
     * 对于新找到的这些入度为零的节点来说，他们的父节点已经都在L中了，所以也可以放入L。
     * 重复上述操作，直到找不到入度为零的节点。如果此时L中的元素个数和节点总数相同，说明排序完成；
     * 如果L中的元素个数和节点总数不同，说明原图中存在环，无法进行拓扑排序。
     *
     * @param node
     * @param targetIndex
     * @return
     */
    public List<GraphicNode> shortestPath(GraphicNode node, int targetIndex) {
        Queue<GraphicNode> s = new LinkedList<>();
        Queue<GraphicNode> searched = new LinkedList<>();
        if (node != null) {
            s.add(node);
        }
        while (!s.isEmpty()) {
            GraphicNode firstNode = s.poll();
            s.add(firstNode);
            System.out.println("当前比较节点" + firstNode.index);
            if (firstNode.neighbor != null && firstNode.neighbor.length > 0) {
                for (GraphicNode neighbor : firstNode.neighbor) {
                    if (!searched.contains(neighbor)) {
                        if (targetIndex == neighbor.index) {
                            s.add(firstNode);
                            s.add(neighbor);
                        } else {
                            s.add(neighbor);
                        }
                        searched.add(neighbor);
                    }

                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Graphic graphic = new Graphic();
        GraphicNode graphicNode = graphic.init();
        System.out.println("最短路径-------");
        GraphicNode shortestPath = graphic.breadthFirstSearch(graphicNode, 5);
        System.out.println(shortestPath.index);
    }

}
