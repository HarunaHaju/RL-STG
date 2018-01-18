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
 * Modified Date: 2018/01/18
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Enemy extends CommonObjects {
    private BufferedImage image;

    private int shotCount = 0;
    private static final int SHOT_TIME = 60;

    public Enemy(){
        super(100,(int)(Math.random()*300),50,3,5,10);
        this.setFlag(CommonObjects.ENEMY);
        try {
            image = ImageIO.read(Enemy.class.getResource("/img/enemy.png"));
            this.setWidth(image.getWidth());
            this.setHeight(image.getHeight());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Enemy(double hp, int x, int y, int speed,int bulletSpeed, double damage) {
        super(hp, x, y, speed,bulletSpeed, damage);
        this.setFlag(CommonObjects.ENEMY);
        try {
            image = ImageIO.read(Enemy.class.getResource("/img/enemy.png"));
            this.setWidth(image.getWidth());
            this.setHeight(image.getHeight());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void shot(){
        if(Math.random()<0.95) {
            Bullet bullet = new Bullet(this.getX() + this.getWidth() / 2, this.getY()+ this.getHeight()
                    , this.getBulletSpeed(), this.getDamage(), Bullet.PARENTS_ENEMY, 0, this.getBulletSpeed());
            GlobalManager.GLOBAL_MANAGER.getBullets().add(bullet);
        }else{
            for (int i = 0;i<10;i++){
                double rad = 2 * Math.PI * ((double)(i*(150-40)/10+40) / 360);
                int offsetX = (int) (Math.cos(rad) * this.getBulletSpeed());
                int offsetY = (int) (Math.sin(rad) * this.getBulletSpeed());
                Bullet bullet = new Bullet(this.getX() + this.getWidth() / 2, this.getY()+ this.getHeight()
                        , this.getBulletSpeed(), this.getDamage(), Bullet.PARENTS_ENEMY, offsetX, offsetY);
                GlobalManager.GLOBAL_MANAGER.getBullets().add(bullet);
            }
        }
    }

    @Override
    public void step() {
        judgeGetShot();
        ++shotCount;
        if (shotCount == SHOT_TIME) {
            shot();
            shotCount = 0;
        }
    }

    public BufferedImage getImage() {
        if(image.getHeight()>0&&image.getWidth()>0)
            return image;
        else
            return null;
    }
}
