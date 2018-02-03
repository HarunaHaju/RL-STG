package cn.lwx.rlstg.algorithm.Random;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.algorithm.Controller;

/**
 * Package: cn.lwx.rlstg.algorithm.Random
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/23
 * Modified Date: 2018/2/3
 * Why & What is modified:
 * Version: 1.2.0
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Random extends Controller {
    public Random() {
        super(Controller.ALGORITHM_RANDOM);
    }

    @Override
    public int decide() {
        return (int) (Math.random() * GlobalManager.ACTION_COUNT);
    }
}
