package cn.lwx.rlstg.algorithm.QLearning;

import javax.annotation.Nonnull;

/**
 * Package: cn.lwx.rlstg.algorithm.QLearning
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/17
 * Modified Date: 2018/1/19
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Vector2D implements Comparable<Vector2D>{
    private int x;
    private int y;

    public Vector2D() {
        x = 0;
        y = 0;
    }

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getDistance() {
        return (int) Math.pow(this.getX(),2) + (int) Math.pow(this.getY(),2);
    }

    @Override
    public int compareTo(@Nonnull Vector2D vec) {
        //find for closest vector
        if(vec.getDistance() > this.getDistance())
            return -1;
        else
            return 1;
    }
}
