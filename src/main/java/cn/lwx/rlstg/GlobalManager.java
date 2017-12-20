package cn.lwx.rlstg;

import cn.lwx.rlstg.gameobjects.Enemy;
import cn.lwx.rlstg.interfaces.StepPerFrame;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: cn.lwx.rlstg
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/20
 * Modified Date: 2017/12/20
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class GlobalManager implements StepPerFrame {
    private int killScore;
    private int liveScore;

    private List<Enemy> enemyList;

    public static final GlobalManager GLOBAL_MANAGER = new GlobalManager();

    private GlobalManager(){
        killScore = 0;
        liveScore = 0;
        enemyList = new ArrayList<>();
    }

    public void reset(){
        killScore = 0;
        liveScore = 0;
        enemyList.clear();
    }

    public int getKillScore() {
        return killScore;
    }

    public void setKillScore(int killScore) {
        this.killScore = killScore;
    }

    public int getLiveScore() {
        return liveScore;
    }

    public void setLiveScore(int liveScore) {
        this.liveScore = liveScore;
    }

    @Override
    public void step() {
        liveScore++;
    }
}
