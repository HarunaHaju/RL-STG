package cn.lwx.rlstg.algorithm.MCTS;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.algorithm.Common.Vector2D;
import cn.lwx.rlstg.algorithm.Controller;
import cn.lwx.rlstg.gameobjects.Player;

import java.util.ArrayList;

/**
 * Package: cn.lwx.rlstg.algorithm.MCTS
 * Comments:
 * Author: lwx
 * Create Date: 2018/2/10
 * Modified Date: 2018/3/04
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class MTool {
    private static final int SPEED = GlobalManager.GLOBAL_MANAGER.getPlayer().getSpeed();
    private static final int BULLET_SPEED = GlobalManager.GLOBAL_MANAGER.getEnemies().peek().getBulletSpeed();

    private static int iterationCount = 0;

    //return next state, null means lose
    public static MState getSimulateResult(MState state, int action) {
        ArrayList<Vector2D> bulletRoute = new ArrayList<>();
        Vector2D playerPos = new Vector2D();
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

        //add positions
        for (int i = 0; i < Player.MOVE_SPAN; i++) {
            int posX = state.getPlayerPosition().getX() + i * deltaX;
            int posY = state.getPlayerPosition().getY() + i * deltaY;

            if (posX <= 0) {
                posX = 0;
            } else if (posX >= 700) {
                posX = 700;
            }
            if (posY <= 0) {
                posY = 0;
            } else if (posY > 480) {
                posY = 480;
            }

            playerPos = new Vector2D(posX, posY);
            for (int j = 0; j < state.getBullets().size(); j++) {
                if (judgeGetShot(playerPos, new Vector2D(state.getBullets().get(j).getX()
                        , state.getBullets().get(j).getY() + i * BULLET_SPEED)))
                    return null;
            }
        }

        for (int j = 0; j < state.getBullets().size(); j++) {
            bulletRoute.add(new Vector2D(state.getBullets().get(j).getX()
                    , state.getBullets().get(j).getY() + (Player.MOVE_SPAN - 1) * BULLET_SPEED));
        }

        return new MState(bulletRoute, playerPos);
    }

    public static boolean judgeGetShot(Vector2D player, Vector2D bullet) {
        if (GlobalManager.GLOBAL_MANAGER.getBullets().size() <= 0)
            return false;
        boolean isShot = false;
        //the judging condition is same as CommonObjects.java
        if (bullet.getX() + GlobalManager.GLOBAL_MANAGER.getBullets().peek().getWidth()
                >= player.getX() + GlobalManager.GLOBAL_MANAGER.getPlayer().getWidth() / 4 &&
                bullet.getX() <= player.getX() + GlobalManager.GLOBAL_MANAGER.getPlayer().getWidth() / 4 * 3 &&
                bullet.getY() + GlobalManager.GLOBAL_MANAGER.getBullets().peek().getHeight()
                        >= player.getY() + GlobalManager.GLOBAL_MANAGER.getPlayer().getHeight() / 4 &&
                bullet.getY() <= player.getY() + GlobalManager.GLOBAL_MANAGER.getPlayer().getHeight() / 4 * 3) {
            isShot = true;
        }

        return isShot;
    }

    public static void randomExpand(TreeNode root) {
        iterationCount++;
        if (root.getChildren() == null) {
            root.expand();
        }
        TreeNode cur = root;
        int action = 0;
        while (cur != null && cur.getDepth() < TreeNode.MAX_DEPTH && cur.isCanExpand()) {
            action = (int)(Math.random() * GlobalManager.ACTION_COUNT);
            cur = cur.randomSimulation(action);
        }
        if(cur != null)
            cur.backPropagation();
    }

    public static int getIterationCount() {
        return iterationCount;
    }

    public static void setIterationCount(int iterationCount) {
        MTool.iterationCount = iterationCount;
    }
}
