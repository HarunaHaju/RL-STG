package cn.lwx.rlstg.algorithm.MCTS;

import cn.lwx.rlstg.algorithm.Common.Vector2D;

import java.util.ArrayList;

/**
 * Package: cn.lwx.rlstg.algorithm.MCTS
 * Comments:
 * Author: lwx
 * Create Date: 2018/2/10
 * Modified Date: 2018/2/10
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class MState {
    private ArrayList<Vector2D> bullets;

    public MState(ArrayList<Vector2D> bulletDeltaVectors) {
        bullets = new ArrayList<>();
        bullets = bulletDeltaVectors;
    }

    public MState() {
        bullets = new ArrayList<>();
    }

    public ArrayList<Vector2D> getLists() {
        return bullets;
    }

    @Override
    public int hashCode() {
        return bullets != null ? bullets.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass())
            return false;
        if (this == o)
            return true;
        MState state = (MState) o;
        return (bullets.equals(state.getLists()));
    }
}
