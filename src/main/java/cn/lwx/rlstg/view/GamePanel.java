package cn.lwx.rlstg.view;

import cn.lwx.rlstg.GlobalManager;
import cn.lwx.rlstg.interfaces.StepPerFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Package: cn.lwx.rlstg.view
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/19
 * Modified Date: 2017/12/20
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
class GamePanel extends JPanel implements StepPerFrame {

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
        graphics.setFont(this.font);

        graphics.drawString("KillScore:"+GlobalManager.GLOBAL_MANAGER.getKillScore(),0,20);
        graphics.drawString("LiveScore:"+GlobalManager.GLOBAL_MANAGER.getLiveScore(),0,40);
    }
}
