package cn.lwx.rlstg.gameobjects;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2017/12/20
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Enemy extends CommonObjects {

    public Enemy(){
        super(100,50,50,5,10);
    }

    public Enemy(double hp, int x, int y, double speed, double damage) {
        super(hp, x, y, speed, damage);
    }

    @Override
    public void step() {

    }
}
