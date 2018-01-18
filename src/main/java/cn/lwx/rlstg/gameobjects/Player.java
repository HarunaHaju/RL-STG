package cn.lwx.rlstg.gameobjects;

import cn.lwx.rlstg.GlobalManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

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
public class Player extends CommonObjects {

    private BufferedImage image;

    public Player() {
        super(100,240,200,3,5,10);
        this.setFlag(CommonObjects.PLAYER);
        try {
            this.image = ImageIO.read(Player.class.getResource("/img/plane.png"));
            this.setWidth(image.getWidth());
            this.setHeight(image.getHeight());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Player(double hp, int x, int y, int speed,int bulletSpeed, double damage) {
        super(hp,x,y,speed,bulletSpeed,damage);
        this.setFlag(CommonObjects.PLAYER);
        try {
            image = ImageIO.read(Player.class.getResource("/img/plane.png"));
            this.setWidth(image.getWidth());
            this.setHeight(image.getHeight());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void reset(){
        this.setHp(100);
        this.setX(240);
        this.setY(200);
        this.setSpeed(5);
        this.setDamage(10);
    }

    @Override
    public void shot(){
        Bullet bullet = new Bullet(this.getX() + this.getWidth()/2,this.getY()
                ,this.getBulletSpeed(),this.getDamage(),Bullet.PARENTS_PLAYER);
        GlobalManager.GLOBAL_MANAGER.getBullets().add(bullet);
    }

    @Override
    public void step() {
        judgeGetShot();
    }

    public BufferedImage getImage() {
        if(image.getHeight()>0&&image.getWidth()>0)
            return image;
        else
            return null;
    }
}
