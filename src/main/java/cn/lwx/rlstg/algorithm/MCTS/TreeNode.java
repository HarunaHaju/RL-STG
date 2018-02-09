package cn.lwx.rlstg.algorithm.MCTS;

import cn.lwx.rlstg.GlobalManager;

import java.util.*;

/**
 * Package: cn.lwx.rlstg.algorithm.MCTS
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/24
 * Modified Date: 2018/2/9
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class TreeNode {
    private static final double EPSILON = 1e-6;
    private static final double UCB_CONST = 0.15;

    private TreeNode parentNode;
    private TreeNode[] children;

    private int index;
    private int depth;

    private int visitCount;
    private double totalValue;

    public TreeNode(TreeNode parent, int index) {
        parentNode = parent;
        visitCount = 0;
        totalValue = 0;
        depth = parent.getDepth() + 1;
        this.index = index;
    }

    public TreeNode() {
        parentNode = null;
        visitCount = 0;
        totalValue = 0;
        depth = 0;
        index = -1;
    }

    private void expand() {
        children = new TreeNode[GlobalManager.ACTION_COUNT];
        for (int i = 0; i < GlobalManager.ACTION_COUNT; i++) {
            children[i] = new TreeNode(this, i);
        }
    }

    private void randomSimulation() {

    }

    private void backPropagation() {
        TreeNode cur = this;
        while (cur != null) {
            cur.visitCount++;
            cur = cur.getParentNode();
        }
    }

    private int selectAction() {
        int bestIndex = -1;
        double maxValue = Double.MIN_VALUE;
        for (TreeNode node : children) {
            if(node.getUCB() > maxValue){
                maxValue = node.getUCB();
                bestIndex = node.getIndex();
            }
        }
        return bestIndex;
    }

    private double getUCB() {
        if (this.isRoot())
            return 0;
        return totalValue / (visitCount + EPSILON)
                + UCB_CONST * Math.sqrt(Math.log(parentNode.getVisitCount()) / (visitCount + EPSILON));
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public TreeNode[] getChildren() {
        return children;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public int getIndex() {
        return index;
    }

    public int getDepth() {
        return depth;
    }

    private boolean isLeaf() {
        return children == null;
    }

    private boolean isRoot() {
        return index == -1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depth, index, totalValue, visitCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;
        TreeNode node = (TreeNode) o;
        return totalValue == node.getTotalValue() && depth == node.getDepth() && index == node.getIndex();
    }

//    public void selectAction() {
//        List<TreeNode> visited = new LinkedList<>();
//        TreeNode cur = this;
//        visited.add(this);
//        while (!cur.isLeaf()) {
//            cur = cur.select();
//            visited.add(cur);
//        }
//        cur.expand();
//        TreeNode newNode = cur.select();
//        visited.add(newNode);
//        double value = Math.random();
//        for (TreeNode node : visited) {
//            node.updateStats(value);
//        }
//    }
}
