package cn.lwx.rlstg.gameobjects;

import cn.lwx.rlstg.interfaces.StepPerFrame;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2017/12/18
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
abstract class CommonObjects implements StepPerFrame {
    private double hp;
    private int x;
    private int y;
    private double speed;
    private double damage;

    public CommonObjects(double hp, int x, int y, double speed, double damage) {
        this.hp = hp;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
    }

    public double getHp() {
        return hp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDamage() {
        return damage;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
