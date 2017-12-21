package cn.lwx.rlstg.gameobjects;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2017/12/21
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Player extends CommonObjects {

    public Player() {
        super(100,100,50,5,10);
    }

    public Player(double hp, int x, int y, int speed, double damage) {
        super(hp,x,y,speed,damage);
    }

    public void reset(){
        this.setHp(100);
        this.setX(50);
        this.setY(50);
        this.setSpeed(5);
        this.setDamage(10);
    }

    @Override
    public void step() {

    }
}
