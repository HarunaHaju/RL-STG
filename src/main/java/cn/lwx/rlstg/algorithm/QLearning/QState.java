package cn.lwx.rlstg.algorithm.QLearning;

import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<ArrayList<Vector2D>> lists;

    public QState(ArrayList<Vector2D> bulletDeltaVectors, ArrayList<Vector2D> enemyDeltaVectors) {
        this.bulletDeltaVectors = bulletDeltaVectors;
        this.enemyDeltaVectors = enemyDeltaVectors;
        lists = new ArrayList<>();
        lists.add(this.bulletDeltaVectors);
        lists.add(this.enemyDeltaVectors);
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

    @Override
    public int hashCode() {
        return lists != null ? lists.hashCode():0;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != this.getClass())
            return false;
        if(this == o)
            return true;
        QState state = (QState) o;
        return(bulletDeltaVectors.equals(state.getBulletDeltaVectors())
                &&enemyDeltaVectors.equals(state.getEnemyDeltaVectors()));
    }
}
