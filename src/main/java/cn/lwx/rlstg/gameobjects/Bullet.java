package cn.lwx.rlstg.gameobjects;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.interfaces.StepPerFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/24
 * Modified Date: 2017/12/25
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Bullet implements StepPerFrame {
    private BufferedImage image;
    private int x;
    private int y;
    private int speed;
    private double damage;
    private int height;
    private int width;
    private int centerX;
    private int centerY;
    private int state;

    public static final int PARENTS_PLAYER = 1;
    public static final int PARENTS_ENEMY = 2;

    public Bullet(int x,int y,int speed,double damage,int state) {
        this.x = x;
        this.y = y;
        this.centerY = y;
        this.speed = speed;
        this.damage = damage;
        this.state = state;
        try {
            image = ImageIO.read(Bullet.class.getResource("/img/bullet.png"));
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void step() {
        switch (this.state){
            case PARENTS_PLAYER:
                y -= speed;
                centerY -= speed;
                if (centerY < 0){
                    System.out.println(centerY);
                    GlobalManager.GLOBAL_MANAGER.getBullets().remove(this);
                    break;
                }
                break;
            case PARENTS_ENEMY:
                break;
            default:
                break;
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
}
