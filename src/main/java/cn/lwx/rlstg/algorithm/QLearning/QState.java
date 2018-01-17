package cn.lwx.rlstg.algorithm.QLearning;

import java.util.ArrayList;

/**
 * Package: cn.lwx.rlstg.algorithm.QLearning
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/17
 * Modified Date: 2018/1/17
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class QState {
    private ArrayList<Vector2D> bulletDeltaVectors;
    private ArrayList<Vector2D> enemyDeltaVectors;

    public QState(ArrayList<Vector2D> bulletDeltaVectors, ArrayList<Vector2D> enemyDeltaVectors) {
        this.bulletDeltaVectors = bulletDeltaVectors;
        this.enemyDeltaVectors = enemyDeltaVectors;
    }

    public ArrayList<Vector2D> getBulletDeltaVectors() {
        return bulletDeltaVectors;
    }

    public void setBulletDeltaVectors(ArrayList<Vector2D> bulletDeltaVectors) {
        this.bulletDeltaVectors = bulletDeltaVectors;
    }

    public ArrayList<Vector2D> getEnemyDeltaVectors() {
        return enemyDeltaVectors;
    }

    public void setEnemyDeltaVectors(ArrayList<Vector2D> enemyDeltaVectors) {
        this.enemyDeltaVectors = enemyDeltaVectors;
    }
}
