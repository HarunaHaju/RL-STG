package cn.lwx.rlstg.algorithm.QLearning;

import cn.lwx.rlstg.algorithm.Common.Vector2D;
import cn.lwx.rlstg.algorithm.QNetwork.QNetwork;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Package: cn.lwx.rlstg.algorithm.QLearning
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/17
 * Modified Date: 2018/1/31
 * Why & What is modified:
 * Version: 1.1.0
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class QState {
    private ArrayList<ArrayList<Vector2D>> lists;

    public QState(ArrayList<Vector2D> bulletDeltaVectors, ArrayList<Vector2D> enemyDeltaVectors) {
        lists = new ArrayList<>();
        lists.add(bulletDeltaVectors);
        lists.add(enemyDeltaVectors);
    }

    public QState() {
        lists = new ArrayList<>();
    }

    public ArrayList<ArrayList<Vector2D>> getLists() {
        return lists;
    }

    public MLData stateToData() {
        double[] tempArray = new double[QNetwork.INPUT_SIZE];
        for (int i = 0; i < QNetwork.INPUT_SIZE; i++) {
            tempArray[i] = 0;
        }

        //add all data into an ArrayList
        ArrayList<Integer> unNormalizedData = new ArrayList<>();
        lists.forEach(list -> {
            list.forEach(vector -> {
                unNormalizedData.add(vector.getX());
                unNormalizedData.add(vector.getY());
            });
        });

        for (int i = 0; i < unNormalizedData.size(); i++) {
            tempArray[i] = unNormalizedData.get(i);
        }

        return new BasicMLData(tempArray);
    }

    @Override
    public int hashCode() {
        return lists != null ? lists.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass())
            return false;
        if (this == o)
            return true;
        QState state = (QState) o;
        return (lists.equals(state.getLists()));
    }
}
