package cn.lwx.rlstg.algorithm.RuleBased;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.algorithm.Common.Vector2D;
import cn.lwx.rlstg.algorithm.Controller;
import cn.lwx.rlstg.gameobjects.Bullet;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Package: cn.lwx.rlstg.algorithm.RuleBased
 * Comments: This algorithm aims to live longer
 * Author: lwx
 * Create Date: 2018/1/24
 * Modified Date: 2018/1/26
 * Why & What is modified:
 * Version: 1.2.0
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class LiveFirst extends Controller {

    private ArrayList<Vector2D> state;
    public static final int BULLET_COUNTS = 5;

    public LiveFirst() {
        super(Controller.ALGORITHM_LIVEFIRST);
        state = new ArrayList<>();
    }

    public void updateState() {
        //add all bullets to list and sort by distance
        ArrayList<Vector2D> bulletVectors = new ArrayList<>();
        GlobalManager.GLOBAL_MANAGER.getBullets().forEach(bullet -> {
            if (bullet.getFlag() == Bullet.PARENTS_ENEMY)
                bulletVectors.add(new Vector2D(GlobalManager.GLOBAL_MANAGER.getPlayer().getX()
                        + GlobalManager.GLOBAL_MANAGER.getPlayer().getWidth() / 2 + 1 - bullet.getX(),
                        GlobalManager.GLOBAL_MANAGER.getPlayer().getY()
                                + GlobalManager.GLOBAL_MANAGER.getPlayer().getHeight() / 2 + 1 - bullet.getY()));
        });
        Collections.sort(bulletVectors);

        //if list have 5 more bullets, keep shortest five one and delete others.
        if (bulletVectors.size() > BULLET_COUNTS) {
            ArrayList<Vector2D> removeList = new ArrayList<>();
            int maxDistance = bulletVectors.get(BULLET_COUNTS - 1).getDistance();
            bulletVectors.forEach(bullet -> {
                if (bullet.getDistance() > maxDistance)
                    removeList.add(bullet);
            });
            bulletVectors.removeAll(removeList);
        }

        this.state = bulletVectors;
    }

    @Override
    public int decide() {
        //prevent empty state
        if (this.state.size() == 0) {
            return (int) (Math.random() * GlobalManager.ACTION_COUNT);
        }

        //if a bullet come too close, ignore others
        if (state.get(0).getGeometricDistance() < 20) {
            if (Math.abs(state.get(0).getX()) > Math.abs(state.get(0).getY())) {
                if (state.get(0).getX() < 0) {
                    return GlobalManager.ACTION_MOVE_LEFT;
                } else {
                    return GlobalManager.ACTION_MOVE_RIGHT;
                }
            } else {
                if (state.get(0).getY() < 0) {
                    return GlobalManager.ACTION_MOVE_UP;
                } else {
                    return GlobalManager.ACTION_MOVE_DOWN;
                }
            }
        }

        //judge action by closest bullets, the target is dodging the bullet and live longer
        //dodge bullet by moving to the negative direction of bullets
        double totalWeight = 0;
        double xWeight = 0;
        double yWeight = 0;
        for (int i = 0; i < state.size(); i++) {
            totalWeight += 1.0 / state.get(i).getGeometricDistance();
        }
        for (int i = 0; i < state.size(); i++) {
            xWeight += (double)state.get(i).getX() * 1.0 / state.get(i).getGeometricDistance() / totalWeight;
            yWeight += (double)state.get(i).getY() * 1.0 / state.get(i).getGeometricDistance() / totalWeight;
        }
        if (Math.abs(xWeight) == Math.abs(yWeight))
            return GlobalManager.ACTION_DO_NOTHING;
        if (Math.abs(xWeight) > Math.abs(yWeight)) {
            if (xWeight < 0) {
                return GlobalManager.ACTION_MOVE_LEFT;
            } else {
                return GlobalManager.ACTION_MOVE_RIGHT;
            }
        } else {
            if (yWeight < 0) {
                return GlobalManager.ACTION_MOVE_UP;
            } else {
                return GlobalManager.ACTION_MOVE_DOWN;
            }
        }
    }
}
