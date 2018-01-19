package cn.lwx.rlstg.gameobjects;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.algorithm.QLearning.QLearning;
import cn.lwx.rlstg.algorithm.QLearning.QState;
import cn.lwx.rlstg.algorithm.QLearning.Vector2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2018/01/19
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Player extends CommonObjects {
    private BufferedImage image;

    private QLearning learning;
    private QState lastState;
    private QState nowState;

    private static final int THREAD_TIME = 15;//per 15 frames
    private int count = 0;

    public Player() {
        super(220, 640, 3, 5);
        lastState = new QState();
        nowState = new QState();
        learning = new QLearning();
        this.setFlag(CommonObjects.PLAYER);
        try {
            this.image = ImageIO.read(Player.class.getResource("/img/plane.png"));
            this.setWidth(image.getWidth());
            this.setHeight(image.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player(int x, int y, int speed, int bulletSpeed) {
        super(x, y, speed, bulletSpeed);
        learning = new QLearning();
        this.setFlag(CommonObjects.PLAYER);
        try {
            image = ImageIO.read(Player.class.getResource("/img/plane.png"));
            this.setWidth(image.getWidth());
            this.setHeight(image.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        this.setX(220);
        this.setY(640);
        this.setAlive(true);
    }

    private void updateState(){
        ArrayList<Vector2D> enemyVector = new ArrayList<>();
        GlobalManager.GLOBAL_MANAGER.getEnemies().forEach(enemy -> {
            enemyVector.add(new Vector2D(getX() - enemy.getX(), getY() - enemy.getY()));
        });
        Collections.sort(enemyVector);

        ArrayList<Vector2D> bulletVector = new ArrayList<>();
        GlobalManager.GLOBAL_MANAGER.getBullets().forEach(bullet -> {
            if(bullet.getFlag() == Bullet.PARENTS_ENEMY)
                bulletVector.add(new Vector2D(getX() - bullet.getX(), getY() - bullet.getY()));
        });
        Collections.sort(bulletVector);
        if(bulletVector.size() > 5) {
            ArrayList<Vector2D> removeList = new ArrayList<>();
            int maxDistance = bulletVector.get(4).getDistance();
            bulletVector.forEach(bullet->{
                if(bullet.getDistance() > maxDistance)
                    removeList.add(bullet);
            });
            bulletVector.removeAll(removeList);
        }

        nowState = new QState(bulletVector,enemyVector);
    }

    @Override
    public void shot() {
        Bullet bullet = new Bullet(this.getX() + this.getWidth() / 2, this.getY()
                , this.getBulletSpeed(), Bullet.PARENTS_PLAYER);
        GlobalManager.GLOBAL_MANAGER.getBullets().add(bullet);
    }

    @Override
    public void step() {
        judgeGetShot();
        this.move();
        count++;
        if(count == THREAD_TIME){
            count = 0;
            this.shot();
            lastState = nowState;
            updateState();
            this.setAction(learning.decide(nowState));
        }
        updateState();
    }

    public void getReward(int reward) {
        learning.learn(lastState,getAction(),reward);
    }

    public BufferedImage getImage() {
        if (image.getHeight() > 0 && image.getWidth() > 0)
            return image;
        else
            return null;
    }

    public QLearning getLearning() {
        return learning;
    }

    public void setLearning(QLearning learning) {
        this.learning = learning;
    }
}
