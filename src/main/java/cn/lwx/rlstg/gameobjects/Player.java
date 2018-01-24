package cn.lwx.rlstg.gameobjects;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.algorithm.Controller;
import cn.lwx.rlstg.algorithm.QLearning.QLearning;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2018/01/24
 * Why & What is modified:
 * Version: 1.0.0
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Player extends CommonObjects {
    private BufferedImage image;

    private static final int THREAD_TIME = 15;//per 15 frames
    private int count = 0;

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
        judgeGetShot();
        this.move();
        count++;
        if(count == THREAD_TIME){
            count = 0;
            this.shot();
            if(GlobalManager.GLOBAL_MANAGER.getController().getFlag() == Controller.ALGORITHM_QLEARNING){
                ((QLearning)GlobalManager.GLOBAL_MANAGER.getController()).updateState();
            }
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
