package cn.lwx.rlstg.algorithm.QNetwork;


import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.algorithm.Controller;
import cn.lwx.rlstg.algorithm.QLearning.QState;
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

/**
 * Package: cn.lwx.rlstg.algorithm.QNetwork
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/28
 * Modified Date: 2018/1/31
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

    public static final int INPUT_SIZE = 16;

    private BasicNetwork qNetwork;
    private double epsilon;
    private QState nowState;

    public QNetwork() {
        super(Controller.ALGORITHM_QNETWORK);
        qNetwork = new BasicNetwork();
        //add layer
        //parameter 1 means activation function, parameter 2 means bias, parameter 3 means neuron size
        //input layer have 16 neurons
        qNetwork.addLayer(new BasicLayer(null, true, INPUT_SIZE));
        qNetwork.addLayer(new BasicLayer(new ActivationReLU(), true, 8));
        qNetwork.addLayer(new BasicLayer(new ActivationReLU(), true, 6));
        qNetwork.addLayer(new BasicLayer(new ActivationReLU(), false, 5));

        //finalizeStructure method is called to inform the network that no more layers are to be added
        qNetwork.getStructure().finalizeStructure();
        qNetwork.reset();

        epsilon = INITIAL_EPSILON;
        nowState = new QState();
    }

    @Override
    public int decide() {
        return 0;
    }

    public void train() {
        //todo:add state -> set method

        //init train set
        MLDataSet trainingSet = new BasicMLDataSet();

        //init train method
        Backpropagation train = new Backpropagation(qNetwork, trainingSet);
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
