package cn.lwx.rlstg;

import cn.lwx.rlstg.gameobjects.Bullet;
import cn.lwx.rlstg.gameobjects.Enemy;
import cn.lwx.rlstg.gameobjects.Player;
import cn.lwx.rlstg.interfaces.StepPerFrame;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Package: cn.lwx.rlstg
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/20
 * Modified Date: 2018/01/01
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class GlobalManager implements StepPerFrame {
    private int killScore;
    private int liveScore;

    private Player player;
    private ConcurrentLinkedQueue<Enemy> enemies;
    private ConcurrentLinkedQueue<Bullet> bullets;

    public static final GlobalManager GLOBAL_MANAGER = new GlobalManager();

    private GlobalManager(){
        killScore = 0;
        liveScore = 0;
        player = new Player();
        enemies = new ConcurrentLinkedQueue<>();
        bullets = new ConcurrentLinkedQueue<>();
    }

    public void reset(){
        killScore = 0;
        liveScore = 0;
        player.reset();
    }

    private void randomGenerateEnemy(){
        if(enemies.size()<10){
            int newEnemyCount = (int)(Math.random() * (10 - enemies.size()));
            for (int i = 0; i < newEnemyCount; i++) {
                Enemy enemy = new Enemy();
                enemies.add(enemy);
            }
        }
    }

    private void removeDeadEnemies(){
        enemies.forEach(enemy -> {
            if(!enemy.isAlive())
                enemies.remove(enemy);
        });
    }

    @Override
    public void step() {
        randomGenerateEnemy();
        liveScore++;
        player.step();
        bullets.forEach(Bullet::step);
        enemies.forEach(Enemy::step);
        removeDeadEnemies();
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ConcurrentLinkedQueue<Enemy> getEnemies() {
        return enemies;
    }

    public ConcurrentLinkedQueue<Bullet> getBullets() {
        return bullets;
    }
}
