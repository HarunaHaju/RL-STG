package cn.lwx.rlstg.algorithm;

/**
 * Package: cn.lwx.rlstg.algorithm
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/23
 * Modified Date: 2018/1/24
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public abstract class Controller {
    public static final int ALGORITHM_QLEARNING = 0;
    public static final int ALGORITHM_RANDOM = 1;

    private int flag;
    public Controller(int flag){
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }
}
