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
    private Vector2D playerPosition;

    public MState(ArrayList<Vector2D> bulletDeltaVectors, Vector2D pos) {
        bullets = new ArrayList<>();
        bullets = bulletDeltaVectors;
        playerPosition = pos;
    }

    public MState() {
        bullets = new ArrayList<>();
        playerPosition = new Vector2D();
    }

    public ArrayList<Vector2D> getBullets() {
        return bullets;
    }

    public ArrayList<Vector2D> getLists() {
        return bullets;
    }

    public Vector2D getPlayerPosition() {
        return playerPosition;
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
