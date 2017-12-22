package cn.lwx.rlstg.gameobjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Package: cn.lwx.rlstg.gameobjects
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2017/12/22
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Player extends CommonObjects {

    private BufferedImage image;

    public Player() {
        super(100,240,200,3,10);
        try {
            image = ImageIO.read(Player.class.getResource("/img/plane.png"));
            this.setWidth(image.getWidth());
            this.setHeight(image.getHeight());
            this.setCenterX(this.getX() + this.getWidth()/2);
            this.setCenterY(this.getY() + this.getHeight()/2);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Player(double hp, int x, int y, int speed, double damage) {
        super(hp,x,y,speed,damage);
        try {
            image = ImageIO.read(Player.class.getResource("/img/plane.png"));
            this.setWidth(image.getWidth());
            this.setHeight(image.getHeight());
            this.setCenterX(this.getX() + this.getWidth()/2);
            this.setCenterY(this.getY() + this.getHeight()/2);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void reset(){
        this.setHp(100);
        this.setX(50);
        this.setY(50);
        this.setSpeed(5);
        this.setDamage(10);
        this.setCenterX(this.getX() + this.getWidth()/2);
        this.setCenterY(this.getY() + this.getHeight()/2);
    }

    public BufferedImage getImage() {
        if(image.getHeight()>0&&image.getWidth()>0)
            return image;
        else
            return null;
    }

    @Override
    public void step() {

    }
}
