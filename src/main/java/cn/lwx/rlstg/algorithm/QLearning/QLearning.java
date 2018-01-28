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
 * Modified Date: 2018/1/28
 * Why & What is modified:
 * Version: 1.1.0
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class QLearning extends Controller {
    private static final double E_GREEDY = 0.9;
    private static final double LEARNING_RATE = 0.01;
    private static final double REWARD_DECAY = 0.9;
    private static final int MAX_ENEMY_COUNT = 2;
    private static final int MAX_BULLET_COUNT = 2;

    private HashMap<QState, QValue> QTable;//state-value pair

    private QState lastState;
    private QState nowState;

    public QLearning() {
        super(Controller.ALGORITHM_QLEARNING);
        QTable = new HashMap<>();

        lastState = new QState();
        nowState = new QState();
    }

    @Override
    public int decide() {
        if (QTable.containsKey(nowState)) {
            if (Math.random() < E_GREEDY)
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
            double qTarget = reward + REWARD_DECAY * qPredict;
            QTable.get(lastState).setValue(action, qPredict + LEARNING_RATE * (qTarget - qPredict));
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

        //if list have 2 more enemies, keep shortest two and delete others.
        if(enemyVectors.size() > MAX_ENEMY_COUNT) {
            ArrayList<Vector2D> removeList = new ArrayList<>();
            int maxDistance = enemyVectors.get(MAX_ENEMY_COUNT - 1).getDistance();
            enemyVectors.forEach(bullet->{
                if(bullet.getDistance() > maxDistance)
                    removeList.add(bullet);
            });
            enemyVectors.removeAll(removeList);
        }

        //add all bullets to list and sort by distance
        ArrayList<Vector2D> bulletVectors = new ArrayList<>();
        GlobalManager.GLOBAL_MANAGER.getBullets().forEach(bullet -> {
            if(bullet.getFlag() == Bullet.PARENTS_ENEMY)
                bulletVectors.add(new Vector2D(GlobalManager.GLOBAL_MANAGER.getPlayer().getX() - bullet.getX(),
                        GlobalManager.GLOBAL_MANAGER.getPlayer().getY() - bullet.getY()));
        });
        Collections.sort(bulletVectors);

        //if list have 2 more bullets, keep shortest two and delete others.
        if(bulletVectors.size() > MAX_BULLET_COUNT) {
            ArrayList<Vector2D> removeList = new ArrayList<>();
            int maxDistance = bulletVectors.get(MAX_BULLET_COUNT - 1).getDistance();
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
