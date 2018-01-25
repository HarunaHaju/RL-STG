package cn.lwx.rlstg.algorithm.QLearning;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.algorithm.Common.Vector2D;
import cn.lwx.rlstg.algorithm.Controller;
import cn.lwx.rlstg.gameobjects.Bullet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Package: cn.lwx.rlstg.algorithm
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/17
 * Modified Date: 2018/1/25
 * Why & What is modified:
 * Version: 1.0.0
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class QLearning extends Controller {
    private double eGreedy;
    private double learningRate;
    private double rewardDecay;

    private HashMap<QState, QValue> QTable;//state-value pair

    private QState lastState;
    private QState nowState;

    public QLearning() {
        super(Controller.ALGORITHM_QLEARNING);
        QTable = new HashMap<>();

        lastState = new QState();
        nowState = new QState();

        eGreedy = 0.9;
        learningRate = 0.01;
        rewardDecay = 0.9;
    }

    @Override
    public int decide() {
        if (QTable.containsKey(nowState)) {
            if (Math.random() < eGreedy)
                return QTable.get(nowState).getAction();
            else
                return (int) (Math.random() * GlobalManager.ACTION_COUNT);
        } else {
            QTable.put(nowState, new QValue());
            return (int) (Math.random() * GlobalManager.ACTION_COUNT);
        }
    }

    public void learn(int action, int reward) {
        if (QTable.containsKey(lastState)) {
            if (action == -1)
                return;
            double qPredict = QTable.get(lastState).getValue(action);
            double qTarget = reward + rewardDecay * qPredict;
            QTable.get(lastState).setValue(action, qPredict + learningRate * (qTarget - qPredict));
        } else {
            QTable.put(lastState, new QValue());
        }
    }

    public void updateState(){
        //add all enemies to list and sort by distance
        ArrayList<Vector2D> enemyVectors = new ArrayList<>();
        GlobalManager.GLOBAL_MANAGER.getEnemies().forEach(enemy -> {
            enemyVectors.add(new Vector2D(GlobalManager.GLOBAL_MANAGER.getPlayer().getX() - enemy.getX(),
                    GlobalManager.GLOBAL_MANAGER.getPlayer().getY() - enemy.getY()));
        });
        Collections.sort(enemyVectors);

        //add all bullets to list and sort by distance
        ArrayList<Vector2D> bulletVectors = new ArrayList<>();
        GlobalManager.GLOBAL_MANAGER.getBullets().forEach(bullet -> {
            if(bullet.getFlag() == Bullet.PARENTS_ENEMY)
                bulletVectors.add(new Vector2D(GlobalManager.GLOBAL_MANAGER.getPlayer().getX() - bullet.getX(),
                        GlobalManager.GLOBAL_MANAGER.getPlayer().getY() - bullet.getY()));
        });
        Collections.sort(bulletVectors);

        //if list have 5 more bullets, keep shortest five one and delete others.
        if(bulletVectors.size() > 5) {
            ArrayList<Vector2D> removeList = new ArrayList<>();
            int maxDistance = bulletVectors.get(4).getDistance();
            bulletVectors.forEach(bullet->{
                if(bullet.getDistance() > maxDistance)
                    removeList.add(bullet);
            });
            bulletVectors.removeAll(removeList);
        }

        //update state
        lastState = nowState;
        nowState = new QState(bulletVectors,enemyVectors);
    }
}
