package cn.lwx.rlstg;

import cn.lwx.rlstg.algorithm.Controller;
import cn.lwx.rlstg.algorithm.QLearning.QLearning;
import cn.lwx.rlstg.algorithm.RuleBased.LiveFirst;
import cn.lwx.rlstg.gameobjects.Bullet;
import cn.lwx.rlstg.gameobjects.Enemy;
import cn.lwx.rlstg.gameobjects.Player;
import cn.lwx.rlstg.interfaces.StepPerFrame;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Package: cn.lwx.rlstg
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/20
 * Modified Date: 2018/1/25
 * Why & What is modified:
 * Version: 1.1.0
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class GlobalManager implements StepPerFrame {
    private Player player;
    private ConcurrentLinkedQueue<Enemy> enemies;
    private ConcurrentLinkedQueue<Bullet> bullets;

    //5 action of player
    public static final int ACTION_COUNT = 5;
    public static final int ACTION_MOVE_UP = 0;
    public static final int ACTION_MOVE_DOWN = 1;
    public static final int ACTION_MOVE_LEFT = 2;
    public static final int ACTION_MOVE_RIGHT = 3;
    public static final int ACTION_DO_NOTHING = 4;

    private int score;
    private int bestScore;

    private Controller controller;

    private static final int MAX_ENEMY_SIZE = 5;

    private int count = 0;
    private static final int THREAD_TIME = 3;//one step() per 3 frame

    public static GlobalManager GLOBAL_MANAGER;

    public GlobalManager(int flag){
        switch (flag){
            case Controller.ALGORITHM_QLEARNING:
                controller = new QLearning();
                break;
            case Controller.ALGORITHM_RANDOM:
                controller = new cn.lwx.rlstg.algorithm.Random.Random();
                break;
            case Controller.ALGORITHM_LIVEFIRST:
                controller = new LiveFirst();
                break;
            default:
                break;
        }
        score = 0;
        bestScore = 0;
        player = new Player();
        enemies = new ConcurrentLinkedQueue<>();
        bullets = new ConcurrentLinkedQueue<>();
    }

    public void reset(){
        if (score >= bestScore){
            bestScore = score;
        }
        score = 0;
        player.reset();
        enemies.clear();
        bullets.clear();
    }

    private void randomGenerateEnemy(){
        if(enemies.size() < MAX_ENEMY_SIZE){
            int newEnemyCount = (int)(Math.random() * (MAX_ENEMY_SIZE - enemies.size())) + 1;
            for (int i = 0; i < newEnemyCount; i++) {
                Enemy enemy = new Enemy();
                enemies.add(enemy);
            }
        }
    }

    private void removeDeadEnemies(){
        enemies.forEach(enemy -> {
            if(!enemy.isAlive()) {
                if (controller.getFlag() == Controller.ALGORITHM_QLEARNING){
                    ((QLearning)(controller)).learn(player.getAction(),10);
                }
                score ++;
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
            if (controller.getFlag() == Controller.ALGORITHM_QLEARNING){
                ((QLearning)(controller)).learn(player.getAction(),-1000);
            }
            this.reset();
        } else {
            if (controller.getFlag() == Controller.ALGORITHM_QLEARNING){
                ((QLearning)(controller)).learn(player.getAction(),1);
            }
        }
        removeDeadEnemies();
    }

    public Player getPlayer() {
        return player;
    }

    public ConcurrentLinkedQueue<Enemy> getEnemies() {
        return enemies;
    }

    public ConcurrentLinkedQueue<Bullet> getBullets() {
        return bullets;
    }

    public Controller getController() {
        return controller;
    }

    public int getScore() {
        return score;
    }

    public int getBestScore() {
        return bestScore;
    }
}
