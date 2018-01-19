package cn.lwx.rlstg.algorithm.QLearning;

import java.util.ArrayList;

/**
 * Package: cn.lwx.rlstg.algorithm.QLearning
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/17
 * Modified Date: 2018/1/19
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class QState {
    private ArrayList<ArrayList<Vector2D>> lists;

    public QState(ArrayList<Vector2D> bulletDeltaVectors, ArrayList<Vector2D> enemyDeltaVectors) {
        lists = new ArrayList<>();
        lists.add(bulletDeltaVectors);
        lists.add(enemyDeltaVectors);
    }

    public QState(){
        lists = new ArrayList<>();
    }

    public ArrayList<ArrayList<Vector2D>> getLists() {
        return lists;
    }

    @Override
    public int hashCode() {
        return lists != null ? lists.hashCode():0;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || o.getClass() != this.getClass())
            return false;
        if(this == o)
            return true;
        QState state = (QState) o;
        return(lists.equals(state.getLists()));
    }
}
