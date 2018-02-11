package cn.lwx.rlstg.algorithm.MCTS;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.algorithm.Common.Vector2D;
import cn.lwx.rlstg.gameobjects.Player;

import java.util.ArrayList;

/**
 * Package: cn.lwx.rlstg.algorithm.MCTS
 * Comments:
 * Author: lwx
 * Create Date: 2018/2/10
 * Modified Date: 2018/2/11
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class MTool {
    private static final int SPEED = GlobalManager.GLOBAL_MANAGER.getPlayer().getSpeed();

    //return next state, null means lose
    public static MState getSimulateResult(MState state, int action) {
        ArrayList<Vector2D> route = new ArrayList<>();//the route of player;
        int deltaX = 0;
        int deltaY = 0;
        switch (action) {
            case GlobalManager.ACTION_DO_NOTHING:
                break;
            case GlobalManager.ACTION_MOVE_DOWN:
                deltaY = SPEED;
                break;
            case GlobalManager.ACTION_MOVE_LEFT:
                deltaX = -SPEED;
                break;
            case GlobalManager.ACTION_MOVE_RIGHT:
                deltaX = SPEED;
                break;
            case GlobalManager.ACTION_MOVE_UP:
                deltaY = -SPEED;
                break;
        }
        for (int i = 0; i < Player.MOVE_SPAN; i++) {
            route.add(new Vector2D(GlobalManager.GLOBAL_MANAGER.getPlayer().getX() + i * deltaX
                    , GlobalManager.GLOBAL_MANAGER.getPlayer().getY() + i * deltaY));
        }

        return null;
    }

    public static boolean judgeGetShot(Vector2D player, ArrayList<Vector2D> bullets) {
        boolean isShot = false;

        for (int i = 0; i < bullets.size(); i++) {

        }

        return isShot;
    }
}
