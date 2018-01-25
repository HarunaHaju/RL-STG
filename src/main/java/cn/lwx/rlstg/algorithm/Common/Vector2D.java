package cn.lwx.rlstg.algorithm.Common;

/**
 * Package: cn.lwx.rlstg.algorithm.QLearning
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/17
 * Modified Date: 2018/1/25
 * Why & What is modified:
 * Version: 1.0.0
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

    public double getGeometricDistance(){
        return Math.sqrt(Math.pow(this.getX(),2) + Math.pow(this.getY(),2));
    }

    @Override
    public int compareTo(Vector2D vec) {
        //find for closest vector
        if(vec.getDistance() > this.getDistance())
            return -1;
        else
            return 1;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(x).hashCode() + Integer.valueOf(y).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || o.getClass() != this.getClass())
            return false;
        if(this == o)
            return true;
        Vector2D vec = (Vector2D) o;
        return(x==vec.getX() && y==vec.getY());
    }
}
