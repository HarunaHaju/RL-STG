package cn.lwx.rlstg.algorithm.MCTS;

import cn.lwx.rlstg.GlobalManager;

import java.util.*;

/**
 * Package: cn.lwx.rlstg.algorithm.MCTS
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/24
 * Modified Date: 2018/3/04
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class TreeNode {
    //hyper parameters
    private static final double EPSILON = 1e-6;
    public static final int MAX_DEPTH = 5;
    private static final double UCB_CONST = 3;

    private TreeNode parentNode;
    private TreeNode[] children;

    private int index;
    private int depth;
    private int visitCount;
    private double winCount;
    private int bpReward;//used in backPropagation(), 0 means lose, 1 means win

    private MState state;
    private boolean canExpand;

    public TreeNode(TreeNode parent, int index) {
        parentNode = parent;
        visitCount = 0;
        depth = parent.getDepth() + 1;
        this.index = index;
        canExpand = true;
        winCount = 0;
        bpReward = -1;
    }

    public TreeNode() {
        parentNode = null;
        visitCount = 0;
        depth = 0;
        index = -1;
        canExpand = true;
        winCount = 0;
        bpReward = -1;
    }

    public void expand() {
        children = new TreeNode[GlobalManager.ACTION_COUNT];
        for (int i = 0; i < GlobalManager.ACTION_COUNT; i++) {
            children[i] = new TreeNode(this, i);
        }
    }

    public TreeNode randomSimulation(int action) {
        if (this.canExpand) {
            if (this.children == null) {
                this.expand();
            }
            if (this.children[action].getVisitCount() > 0) {
                if(this.children[action].canExpand) {
                    this.children[action].winCount++;
                    this.children[action].bpReward = 1;
                } else {
                    this.children[action].bpReward = 0;
                }
                this.children[action].visitCount++;
            } else {
                MState nextState = MTool.getSimulateResult(this.state, action);
                if (nextState != null) {
                    this.children[action].setState(nextState);
                    this.children[action].winCount++;
                    this.children[action].bpReward = 1;
                } else {
                    this.children[action].setCanExpand(false);
                    this.children[action].bpReward = 0;
                }
                this.children[action].visitCount++;
            }
        }
        return this.children[action];
    }

    public void backPropagation() {
        TreeNode cur = this.getParentNode();
        while (cur != null) {
            cur.visitCount++;
            cur.winCount += this.bpReward;
            cur = cur.getParentNode();
        }
    }

    public int selectAction() {
        int bestIndex = -1;
        double maxValue = Double.MIN_VALUE;
        for (TreeNode node : children) {
            if (node.getUCB() >= maxValue) {
                maxValue = node.getUCB();
                bestIndex = node.getIndex();
            }
        }
        return bestIndex;
    }

    public double getUCB() {
        if (this.isRoot())
            return 0;
        return winCount / (visitCount + EPSILON)
                + UCB_CONST * Math.sqrt(Math.log(MTool.getIterationCount()) / (visitCount + EPSILON));
    }

    private TreeNode getParentNode() {
        return parentNode;
    }

    public TreeNode[] getChildren() {
        return children;
    }

    public int getVisitCount() {
        return visitCount;
    }

    private int getIndex() {
        return index;
    }

    public int getDepth() {
        return depth;
    }

    public double getWinCount() {
        return winCount;
    }

    public boolean isCanExpand() {
        return canExpand;
    }

    public void setCanExpand(boolean canExpand) {
        this.canExpand = canExpand;
    }

    private boolean isLeaf() {
        return children == null;
    }

    private boolean isRoot() {
        return index == -1;
    }

    public MState getState() {
        return state;
    }

    public void setState(MState state) {
        this.state = state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depth, index, winCount, visitCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;
        TreeNode node = (TreeNode) o;
        return winCount == node.getWinCount() && depth == node.getDepth() && index == node.getIndex();
    }
}
