package cn.lwx.rlstg.gameobjects;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.interfaces.StepPerFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/24
 * Modified Date: 2018/01/24
 * Why & What is modified:
 * Version: 1.0.0
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Bullet implements StepPerFrame {
    private BufferedImage image;
    private int x;
    private int y;
    private int speed;
    private int height;
    private int width;
    private int flag;
    private int offsetX;
    private int offsetY;

    public static final int PARENTS_PLAYER = 1;
    public static final int PARENTS_ENEMY = 2;

    public Bullet(int x,int y,int speed,int flag) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.flag = flag;
        try {
            image = ImageIO.read(Bullet.class.getResource("/img/bullet.png"));
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bullet(int x,int y,int speed,int flag,int offsetX,int offsetY) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.flag = flag;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
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
        switch (this.flag){
            case PARENTS_PLAYER:
                y -= speed;
                break;
            case PARENTS_ENEMY:
                y += offsetY;
                x += offsetX;
                break;
            default:
                break;
        }
        if (y + height < 0 || x + width<0||x-width>480||y-height>700){
            GlobalManager.GLOBAL_MANAGER.getBullets().remove(this);
        }
    }

    public BufferedImage getImage() {
        if(image.getHeight()>0&&image.getWidth()>0)
            return image;
        else
            return null;
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

    public int getFlag() {
        return flag;
    }
}
