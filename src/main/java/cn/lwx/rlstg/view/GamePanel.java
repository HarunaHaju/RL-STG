package cn.lwx.rlstg.view;

import cn.lwx.rlstg.interfaces.StepPerFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Package: cn.lwx.rlstg.view
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/19
 * Modified Date: 2017/12/19
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
class GamePanel extends JPanel implements StepPerFrame {

    GamePanel(){
        this.setUpUI();
    }
    
    void resetAll(){

    }

    private void setUpUI(){
        this.setSize(480,700);
        this.setBackground(Color.blue);
    }

    @Override
    public void step() {

    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }
}
