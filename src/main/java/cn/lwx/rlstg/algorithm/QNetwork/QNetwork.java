package cn.lwx.rlstg.algorithm.QNetwork;


import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.algorithm.Common.Vector2D;
import cn.lwx.rlstg.algorithm.Controller;
import cn.lwx.rlstg.algorithm.QLearning.QState;
import cn.lwx.rlstg.gameobjects.Bullet;
import org.encog.Encog;
import org.encog.engine.network.activation.ActivationReLU;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.prg.extension.FunctionFactory;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Package: cn.lwx.rlstg.algorithm.QNetwork
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/28
 * Modified Date: 2018/2/1
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class QNetwork extends Controller {
    //hyper parameters
    private static final double GAMMA = 0.9;//discount factor for target Q
    private static final double INITIAL_EPSILON = 0.5;//starting value of epsilon
    private static final double FINAL_EPSILON = 0.01;//final value of epsilon
    private static final int REPLAY_SIZE = 100;//experience replay buffer size
    private static final int MAX_ENEMY_COUNT = 4;
    private static final int MAX_BULLET_COUNT = 4;

    public static final int INPUT_SIZE = MAX_ENEMY_COUNT * 2 + MAX_BULLET_COUNT * 2;

    private BasicNetwork qNetwork;
    private double epsilon;
    private QState nowState;
    private boolean isTrainingDone;

    public QNetwork() {
        super(Controller.ALGORITHM_QNETWORK);
        qNetwork = new BasicNetwork();
        //add layer
        //parameter 1 means activation function, parameter 2 means bias, parameter 3 means neuron size
        //input layer have 16 neurons
        qNetwork.addLayer(new BasicLayer(null, true, INPUT_SIZE));
        qNetwork.addLayer(new BasicLayer(new ActivationReLU(), true, 8));
        qNetwork.addLayer(new BasicLayer(new ActivationReLU(), true, 6));
        qNetwork.addLayer(new BasicLayer(new ActivationReLU(), false, GlobalManager.ACTION_COUNT));

        //finalizeStructure method is called to inform the network that no more layers are to be added
        qNetwork.getStructure().finalizeStructure();
        qNetwork.reset();

        epsilon = INITIAL_EPSILON;
        isTrainingDone = false;
        nowState = new QState();
    }

    @Override
    public int decide() {
        return isTrainingDone?action():egreedyAction();
    }

    public void train() {
        //todo:add state -> set method

        //init train set
        MLDataSet trainingSet = new BasicMLDataSet();

        //init train method
        Backpropagation train = new Backpropagation(qNetwork, trainingSet);
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
        nowState = new QState(bulletVectors,enemyVectors);

        //add state to training set

        //check for train
    }

    private int action() {
        return getActionFromData(qNetwork.compute(stateToData(nowState)));
    }

    private int egreedyAction() {
        if (Math.random() < epsilon) {
            return getActionFromData(qNetwork.compute(stateToData(nowState)));
        } else {
            return (int) (Math.random() * GlobalManager.ACTION_COUNT);
        }
    }

    public void checkReplay() {

    }

    private MLData stateToData(QState state) {
        return state.stateToData();
    }

    private int getActionFromData(MLData data) {
        int index = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.getData(index) < data.getData(i)) {
                index = i;
            }
        }
        return index;
    }
}
