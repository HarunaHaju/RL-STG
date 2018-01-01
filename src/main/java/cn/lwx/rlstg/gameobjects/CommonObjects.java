package cn.lwx.rlstg.gameobjects;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.interfaces.StepPerFrame;

import java.awt.image.BufferedImage;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2018/01/01
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
abstract class CommonObjects implements StepPerFrame {
    private double hp;
    private int x;
    private int y;
    private int speed;
    private double damage;
    private int height;
    private int width;
    private boolean isAlive;

    CommonObjects(double hp, int x, int y, int speed, double damage) {
        this.hp = hp;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.width = 0;
        this.height = 0;
        this.isAlive = true;
    }

    public void judgeGetShot(){
        GlobalManager.GLOBAL_MANAGER.getBullets().forEach(bullet -> {
            if(bullet.getX() + bullet.getWidth() >= this.x &&
                    bullet.getX() <= this.x + this.getWidth()&&
                    bullet.getY() + bullet.getHeight() >= this.y&&
                    bullet.getY() <= this.y + this.getHeight())
                isAlive = false;
        });
    }

    public void moveUp(){
        if(height == 0)
            return;
        this.setY(y-speed>0?y-speed:0);
    }

    public void moveDown(){
        if(height == 0)
            return;
        this.setY(y+speed<700 - height?y+speed:700 - height);
    }

    public void moveLeft(){
        if(width == 0)
            return;
        this.setX(x - speed>0?x - speed:0);
    }

    public void moveRight(){
        if(width == 0)
            return;
        this.setX(x + speed<480 - width?x + speed:480 - width);
    }

    public abstract void shot();

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
