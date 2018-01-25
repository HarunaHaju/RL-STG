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
 * Modified Date: 2018/1/25
 * Why & What is modified:
 * Version: 1.0.0
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class LiveFirst extends Controller {

    private ArrayList<Vector2D> state;

    public LiveFirst(){
        super(Controller.ALGORITHM_LIVEFIRST);
        state = new ArrayList<>();
    }

    public void updateState(){
        //add all bullets to list and sort by distance
        ArrayList<Vector2D> bulletVectors = new ArrayList<>();
        GlobalManager.GLOBAL_MANAGER.getBullets().forEach(bullet -> {
            if(bullet.getFlag() == Bullet.PARENTS_ENEMY)
                bulletVectors.add(new Vector2D(GlobalManager.GLOBAL_MANAGER.getPlayer().getX() - bullet.getX(),
                        GlobalManager.GLOBAL_MANAGER.getPlayer().getY() - bullet.getY()));
        });
        Collections.sort(bulletVectors);

        //if list have 10 more bullets, keep shortest five one and delete others.
        if(bulletVectors.size() > 3) {
            ArrayList<Vector2D> removeList = new ArrayList<>();
            int maxDistance = bulletVectors.get(2).getDistance();
            bulletVectors.forEach(bullet->{
                if(bullet.getDistance() > maxDistance)
                    removeList.add(bullet);
            });
            bulletVectors.removeAll(removeList);
        }

        this.state = bulletVectors;
    }

    @Override
    public int decide() {
        //prevent empty state
        if (this.state.size() == 0){
            return (int) (Math.random() * GlobalManager.ACTION_COUNT);
        }
        return 0;
    }
}
