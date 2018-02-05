package cn.lwx.rlstg.algorithm;

/**
 * Package: cn.lwx.rlstg.algorithm
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/23
 * Modified Date: 2018/2/5
 * Why & What is modified:
 * Version: 1.2.2
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public abstract class Controller {
    public static final int ALGORITHM_QLEARNING = 0;
    public static final int ALGORITHM_RANDOM = 1;
    public static final int ALGORITHM_LIVEFIRST = 2;
    public static final int ALGORITHM_QNETWORK = 3;
    public static final int ALGORITHM_MCTS = 4;

    private int flag;

    public Controller(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public String getAlgorithmName(){
        switch (flag){
            case ALGORITHM_QLEARNING:
                return "Q-Learning";
            case ALGORITHM_RANDOM:
                return "Random";
            case ALGORITHM_LIVEFIRST:
                return "Live First (Rule Based)";
            case ALGORITHM_QNETWORK:
                return "Q-Network";
            default:
                return "null";
        }
    }

    public abstract int decide();
}
