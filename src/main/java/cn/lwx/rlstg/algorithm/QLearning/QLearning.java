package cn.lwx.rlstg.algorithm.QLearning;

import java.util.HashMap;

/**
 * Package: cn.lwx.rlstg.algorithm
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/17
 * Modified Date: 2018/1/19
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class QLearning {
    private double eGreedy;
    private double learningRate;
    private double rewardDecay;

    private HashMap<QState, QValue> QTable;//state-value pair

    public QLearning() {
        QTable = new HashMap<>();
        eGreedy = 0.9;
        learningRate = 0.01;
        rewardDecay = 0.9;
    }

    public QLearning(double eGreedy, double learningRate, double rewardDecay) {
        QTable = new HashMap<>();
        this.eGreedy = eGreedy;
        this.learningRate = learningRate;
        this.rewardDecay = rewardDecay;
    }

    public QLearning(HashMap<QState, QValue> QTable) {
        this.QTable = QTable;
    }

    public int decide(QState state) {
        if (QTable.containsKey(state)) {
            if (Math.random() < eGreedy)
                return QTable.get(state).getAction();
            else
                return (int) (Math.random() * 4);
        } else {
            QTable.put(state, new QValue());
            return (int) (Math.random() * 4);
        }
    }

    public void learn(QState state, int action, int reward) {
        if (QTable.containsKey(state)) {
            if (action == -1)
                return;
            double qPredict = QTable.get(state).getValue(action);
            double qTarget = reward + rewardDecay * qPredict;
            QTable.get(state).setValue(action, qPredict + learningRate * (qTarget - qPredict));
        } else {
            QTable.put(state, new QValue());
        }
    }

    public void setQTable(QState state, QValue value) {
        this.QTable.put(state, value);
    }

    public HashMap<QState, QValue> getQTable() {
        return QTable;
    }

    public void setQTable(HashMap<QState, QValue> QTable) {
        this.QTable = QTable;
    }
}
