package cn.lwx.rlstg.algorithm.RuleBased;

import cn.lwx.rlstg.algorithm.Controller;

/**
 * Package: cn.lwx.rlstg.algorithm.RuleBased
 * Comments: This algorithm aims to live longer
 * Author: lwx
 * Create Date: 2018/1/24
 * Modified Date: 2018/1/25
 * Why & What is modified:
 * Version: 1.0.0
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class LiveFirst extends Controller {
    public LiveFirst(){
        super(Controller.ALGORITHM_LIVEFIRST);
    }

    @Override
    public int decide() {
        return 0;
    }
}
