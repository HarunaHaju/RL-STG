package cn.lwx.rlstg.algorithm.QLearning;

import cn.lwx.rlstg.algorithm.Common.Vector2D;
import cn.lwx.rlstg.algorithm.QNetwork.QNetwork;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.arrayutil.NormalizedField;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Package: cn.lwx.rlstg.algorithm.QLearning
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/17
 * Modified Date: 2018/2/3
 * Why & What is modified:
 * Version: 1.2.2
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class QState {
    private ArrayList<ArrayList<Vector2D>> lists;

    private static final NormalizedField normalizerX =
            new NormalizedField(NormalizationAction.Normalize, "x", Vector2D.MAX_VALUE_X, -Vector2D.MAX_VALUE_X, 1, 0);
    private static final NormalizedField normalizerY =
            new NormalizedField(NormalizationAction.Normalize, "y", Vector2D.MAX_VALUE_Y, -Vector2D.MAX_VALUE_X, 1, 0);

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
        ArrayList<Double> normalizedData = new ArrayList<>();
        lists.forEach(list -> {
            list.forEach(vector -> {
                normalizedData.add(normalizerX.normalize((double) vector.getX()));
                normalizedData.add(normalizerY.normalize((double) vector.getY()));
            });
        });

        if (normalizedData.size()>QNetwork.INPUT_SIZE){
            ArrayList<Double> temp = new ArrayList<>();
            for (int i = QNetwork.INPUT_SIZE; i < normalizedData.size(); i++) {
                temp.add(normalizedData.get(i));
            }
            normalizedData.removeAll(temp);
        }

        for (int i = 0; i < normalizedData.size(); i++) {
            tempArray[i] = normalizedData.get(i);
        }

        return new BasicMLData(tempArray);
    }

    public ArrayList<Double> stateToArray() {
        //add all data into an ArrayList
        ArrayList<Double> normalizedData = new ArrayList<>();
        lists.forEach(list -> {
            list.forEach(vector -> {
                normalizedData.add(normalizerX.normalize((double) vector.getX()));
                normalizedData.add(normalizerY.normalize((double) vector.getY()));
            });
        });

        //filled with zero
        while (normalizedData.size() < QNetwork.INPUT_SIZE) {
            normalizedData.add(0.0);
        }

        return normalizedData;
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
