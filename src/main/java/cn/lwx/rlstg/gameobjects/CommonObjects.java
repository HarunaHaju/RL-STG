package cn.lwx.rlstg.gameobjects;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.interfaces.StepPerFrame;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2018/2/3
 * Why & What is modified:
 * Version: 1.2.2
 * It's the only NEET thing to do. – Shionji Yuuko
 */
abstract class CommonObjects implements StepPerFrame {
    private int x;
    private int y;
    private int speed;
    private int bulletSpeed;
    private int height;
    private int width;
    private boolean isAlive;
    private int flag;
    private int action = -1;

    public final static int PLAYER = 0;
    public final static int ENEMY = 1;

    CommonObjects(int x, int y, int speed, int bulletSpeed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.bulletSpeed = bulletSpeed;
        this.width = 0;
        this.height = 0;
        this.isAlive = true;
    }

    public void move() {
        switch (action) {
            case GlobalManager.ACTION_MOVE_UP:
                moveUp();
                break;
            case GlobalManager.ACTION_MOVE_DOWN:
                moveDown();
                break;
            case GlobalManager.ACTION_MOVE_LEFT:
                moveLeft();
                break;
            case GlobalManager.ACTION_MOVE_RIGHT:
                moveRight();
                break;
            case GlobalManager.ACTION_DO_NOTHING:
                break;
            default:
                break;
        }
    }

    public void judgeAlive() {
        //judge get crashed
        if(this.flag == CommonObjects.PLAYER){
            GlobalManager.GLOBAL_MANAGER.getEnemies().forEach(enemy -> {
                if (enemy.getX() + enemy.getWidth() >= this.x&&
                        enemy.getX() <= this.x + this.getWidth()&&
                        enemy.getY() + enemy.getHeight() >= this.y&&
                        enemy.getY() <= this.y + this.getHeight()) {
                    isAlive = false;
                }
            });
        }

        GlobalManager.GLOBAL_MANAGER.getBullets().forEach(bullet -> {
            switch (this.flag) {
                case PLAYER:
                    if (bullet.getFlag() == Bullet.PARENTS_PLAYER)
                        return;
                    if (bullet.getX() + bullet.getWidth() >= this.x + this.getWidth() / 4 &&
                            bullet.getX() <= this.x + this.getWidth() / 4 * 3 &&
                            bullet.getY() + bullet.getHeight() >= this.y + this.getHeight() / 4 &&
                            bullet.getY() <= this.y + this.getHeight() / 4 * 3) {
                        GlobalManager.GLOBAL_MANAGER.getBullets().remove(bullet);
                        isAlive = false;
                    }
                case ENEMY:
                    if (bullet.getFlag() == Bullet.PARENTS_ENEMY)
                        return;
                    if (bullet.getX() + bullet.getWidth() >= this.x &&
                            bullet.getX() <= this.x + this.getWidth() &&
                            bullet.getY() + bullet.getHeight() >= this.y &&
                            bullet.getY() <= this.y + this.getHeight()) {
                        GlobalManager.GLOBAL_MANAGER.getBullets().remove(bullet);
                        isAlive = false;
                    }
                    break;
            }
        });
    }

    public void moveUp() {
        if (height == 0)
            return;
        this.setY(y - speed > 0 ? y - speed : 0);
    }

    public void moveDown() {
        if (height == 0)
            return;
        switch (this.getFlag()) {
            case CommonObjects.PLAYER:
                this.setY(y + speed < 700 - height ? y + speed : 700 - height);
                break;
            case CommonObjects.ENEMY:
                int yTemp = y + speed;
                //if enemy come to the bottom, player fail
                if (yTemp < 600 - height) {
                    this.setY(yTemp);
                } else {
                    GlobalManager.GLOBAL_MANAGER.getPlayer().setAlive(false);
                }
                break;
        }

    }

    public void moveLeft() {
        if (width == 0)
            return;
        this.setX(x - speed > 0 ? x - speed : 0);
    }

    public void moveRight() {
        if (width == 0)
            return;
        this.setX(x + speed < 480 - width ? x + speed : 480 - width);
    }

    public abstract void shot();

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

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
