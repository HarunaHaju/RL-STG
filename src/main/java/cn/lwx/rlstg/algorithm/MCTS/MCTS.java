package cn.lwx.rlstg.algorithm.MCTS;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.algorithm.Common.Vector2D;
import cn.lwx.rlstg.algorithm.Controller;
import cn.lwx.rlstg.gameobjects.Bullet;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Package: cn.lwx.rlstg.algorithm.MCTS
 * Comments:
 * Author: lwx
 * Create Date: 2018/2/5
 * Modified Date: 2018/2/11
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class MCTS extends Controller {
    //hyper parameters
    private static final int TIME_LIMIT = 14;

    private MState state;

    public MCTS() {
        super(Controller.ALGORITHM_MCTS);
    }

    @Override
    public int decide() {
        MTool.setIterationCount(0);
        long startTime = System.currentTimeMillis();
        long timer = System.currentTimeMillis() - startTime;
        TreeNode root = new TreeNode();
        root.setState(state);
        while (timer <= TIME_LIMIT) {
            MTool.randomExpand(root);
            timer = System.currentTimeMillis() - startTime;
        }
        return root.selectAction();
    }

    public void updateState() {
        //add all bullets to list and sort by distance
        ArrayList<Vector2D> bulletVectors = new ArrayList<>();
        GlobalManager.GLOBAL_MANAGER.getBullets().forEach(bullet -> {
            if (bullet.getFlag() == Bullet.PARENTS_ENEMY)
                bulletVectors.add(new Vector2D(GlobalManager.GLOBAL_MANAGER.getPlayer().getX() - bullet.getX(),
                        GlobalManager.GLOBAL_MANAGER.getPlayer().getY() - bullet.getY()));
        });
        Collections.sort(bulletVectors);

        //update state
        state = new MState(bulletVectors
                , new Vector2D(GlobalManager.GLOBAL_MANAGER.getPlayer().getX(),
                GlobalManager.GLOBAL_MANAGER.getPlayer().getY()));
    }
}
