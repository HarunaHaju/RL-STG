package cn.lwx.rlstg.gameobjects;

import cn.lwx.rlstg.GlobalManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2018/2/3
 * Why & What is modified:
 * Version: 1.2.2
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Enemy extends CommonObjects {
    private BufferedImage image;

    private int shotCount = 0;
    private static final int THREAD_TIME = 80;

    private int action = -1;

    public Enemy() {
        super(50, 50, 1, 3);
        this.setFlag(CommonObjects.ENEMY);
        try {
            image = ImageIO.read(Enemy.class.getResource("/img/enemy.png"));
            this.setWidth(image.getWidth());
            this.setHeight(image.getHeight());
            this.setX((int) (Math.random() * (480 - getWidth())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Enemy(int x, int y, int speed, int bulletSpeed) {
        super(x, y, speed, bulletSpeed);
        this.setFlag(CommonObjects.ENEMY);
        try {
            image = ImageIO.read(Enemy.class.getResource("/img/enemy.png"));
            this.setWidth(image.getWidth());
            this.setHeight(image.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shot() {
        Bullet bullet = new Bullet(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight()
                , this.getBulletSpeed(), Bullet.PARENTS_ENEMY, 0, this.getBulletSpeed());
        GlobalManager.GLOBAL_MANAGER.getBullets().add(bullet);
    }

    @Override
    public void step() {
        judgeAlive();
        this.move();
        ++shotCount;
        if (shotCount == THREAD_TIME) {
            this.setAction((int) (Math.random() * 5));
            shot();
            shotCount = 0;
        }
    }

    public BufferedImage getImage() {
        if (image.getHeight() > 0 && image.getWidth() > 0)
            return image;
        else
            return null;
    }
}
