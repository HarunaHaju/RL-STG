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
 * Modified Date: 2018/01/24
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class GlobalManager implements StepPerFrame {
    private Player player;
    private ConcurrentLinkedQueue<Enemy> enemies;
    private ConcurrentLinkedQueue<Bullet> bullets;

    //4 action of player
    public static final int ACTION_MOVE_UP = 0;
    public static final int ACTION_MOVE_DOWN = 1;
    public static final int ACTION_MOVE_LEFT = 2;
    public static final int ACTION_MOVE_RIGHT = 3;

    private static final int MAX_ENEMY_SIZE = 5;

    private int count = 0;
    private static final int THREAD_TIME = 3;//one step() per 3 frame

    public static final GlobalManager GLOBAL_MANAGER = new GlobalManager();

    private GlobalManager(){
        player = new Player();
        enemies = new ConcurrentLinkedQueue<>();
        bullets = new ConcurrentLinkedQueue<>();
    }

    public void reset(){
        player.reset();
        enemies.clear();
        bullets.clear();
    }

    private void randomGenerateEnemy(){
        if(enemies.size() < MAX_ENEMY_SIZE){
            int newEnemyCount = (int)(Math.random() * (5 - enemies.size())) + 1;
            for (int i = 0; i < newEnemyCount; i++) {
                Enemy enemy = new Enemy();
                enemies.add(enemy);
            }
        }
    }

    private void removeDeadEnemies(){
        enemies.forEach(enemy -> {
            if(!enemy.isAlive()) {
                player.getReward(10);
                enemies.remove(enemy);
            }
        });
    }

    @Override
    public void step() {
        count ++;
        if(count == THREAD_TIME) {
            randomGenerateEnemy();
            count = 0;
        }
        player.step();
        bullets.forEach(Bullet::step);
        enemies.forEach(Enemy::step);
        if (!player.isAlive()){
            player.getReward(-1000);//get reward
            this.reset();
        } else {
            player.getReward(1);
        }
        removeDeadEnemies();
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
