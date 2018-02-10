package cn.lwx.rlstg.gameobjects;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.algorithm.Controller;
import cn.lwx.rlstg.algorithm.QLearning.QLearning;
import cn.lwx.rlstg.algorithm.QNetwork.QNetwork;
import cn.lwx.rlstg.algorithm.RuleBased.LiveFirst;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2018/2/10
 * Why & What is modified:
 * Version: 1.2.2
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Player extends CommonObjects {
    private BufferedImage image;

    private static final int SHOT_SPAN = 15;//shot 1 time per 15 frames
    private int shotCount = 0;

    public static final int MOVE_SPAN = 5;//move may change per 5 frames
    private int moveCount = 0;

    public Player() {
        super(220, 640, 3, 5);
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

    @Override
    public void shot() {
        Bullet bullet = new Bullet(this.getX() + this.getWidth() / 2, this.getY()
                , this.getBulletSpeed(), Bullet.PARENTS_PLAYER);
        GlobalManager.GLOBAL_MANAGER.getBullets().add(bullet);
    }

    @Override
    public void step() {
        judgeAlive();
        this.move();
        shotCount++;
        moveCount++;
        if(shotCount == SHOT_SPAN){
            shotCount = 0;
            this.shot();
        }
        if(moveCount == MOVE_SPAN){
            moveCount = 0;
            //update state
            switch (GlobalManager.GLOBAL_MANAGER.getController().getFlag()){
                case Controller.ALGORITHM_QLEARNING:
                    ((QLearning)GlobalManager.GLOBAL_MANAGER.getController()).updateState();
                    break;
                case Controller.ALGORITHM_LIVEFIRST:
                    ((LiveFirst)GlobalManager.GLOBAL_MANAGER.getController()).updateState();
                    break;
                case Controller.ALGORITHM_QNETWORK:
                    ((QNetwork)GlobalManager.GLOBAL_MANAGER.getController()).updateState(this.getAction());
                    break;
                default:
                    break;
            }

            //get action by algorithms
            this.setAction(GlobalManager.GLOBAL_MANAGER.getController().decide());
        }
    }

    public BufferedImage getImage() {
        if (image.getHeight() > 0 && image.getWidth() > 0)
            return image;
        else
            return null;
    }
}
