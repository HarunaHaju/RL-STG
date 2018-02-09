package cn.lwx.rlstg.algorithm.MCTS;

import cn.lwx.rlstg.GlobalManager;

import java.util.LinkedList;
import java.util.List;

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

    private TreeNode[] children;
    private double nVisits, totalValue;

    private void randomSimulation(){

    }

    public void selectAction() {
        List<TreeNode> visited = new LinkedList<>();
        TreeNode cur = this;
        visited.add(this);
        while (!cur.isLeaf()) {
            cur = cur.select();
            visited.add(cur);
        }
        cur.expand();
        TreeNode newNode = cur.select();
        visited.add(newNode);
        double value = Math.random();
        for (TreeNode node : visited) {
            node.updateStats(value);
        }
    }

    private void expand() {
        children = new TreeNode[GlobalManager.ACTION_COUNT];
        for (int i=0; i<GlobalManager.ACTION_COUNT; i++) {
            children[i] = new TreeNode();
        }
    }

    private TreeNode select() {
        TreeNode selected = null;
        double bestValue = Double.MIN_VALUE;
        for (TreeNode child : children) {
            double uctValue = child.totalValue / (child.nVisits + EPSILON) +
                    Math.sqrt(Math.log(nVisits+1) / (child.nVisits + EPSILON)) +
                    Math.random() * EPSILON;
            // small random number to break ties randomly in unexpanded nodes
            if (uctValue > bestValue) {
                selected = child;
                bestValue = uctValue;
            }
        }
        return selected;
    }

    public boolean isLeaf() {
        return children == null;
    }

    public void updateStats(double value) {
        nVisits++;
        totalValue += value;
    }
}
