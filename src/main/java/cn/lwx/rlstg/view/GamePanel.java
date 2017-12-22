package cn.lwx.rlstg.view;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.interfaces.StepPerFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Package: cn.lwx.rlstg.view
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/19
 * Modified Date: 2017/12/21
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
class GamePanel extends JPanel implements StepPerFrame,KeyListener {

    private Font font;

    GamePanel(){
        this.setUpUI();
        font = new Font(Font.SANS_SERIF, Font.BOLD, 25);
    }
    
    void resetAll(){
        GlobalManager.GLOBAL_MANAGER.reset();
    }

    private void setUpUI(){
        this.setSize(480,700);
        this.setBackground(Color.red);
    }

    @Override
    public void step() {
        GlobalManager.GLOBAL_MANAGER.step();
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.drawImage(GlobalManager.GLOBAL_MANAGER.getPlayer().getImage(),
                GlobalManager.GLOBAL_MANAGER.getPlayer().getX()
                ,GlobalManager.GLOBAL_MANAGER.getPlayer().getY(),null);

        graphics.drawImage(GlobalManager.GLOBAL_MANAGER.getEnemy().getImage(),
                GlobalManager.GLOBAL_MANAGER.getEnemy().getX()
                ,GlobalManager.GLOBAL_MANAGER.getEnemy().getY(),null);

        graphics.setFont(this.font);
        graphics.drawString("KillScore:"+GlobalManager.GLOBAL_MANAGER.getKillScore(),0,20);
        graphics.drawString("LiveScore:"+GlobalManager.GLOBAL_MANAGER.getLiveScore(),0,40);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                GlobalManager.GLOBAL_MANAGER.getPlayer().moveUp();
                this.repaint();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                GlobalManager.GLOBAL_MANAGER.getPlayer().moveDown();
                this.repaint();
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                GlobalManager.GLOBAL_MANAGER.getPlayer().moveLeft();
                this.repaint();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                GlobalManager.GLOBAL_MANAGER.getPlayer().moveRight();
                this.repaint();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
