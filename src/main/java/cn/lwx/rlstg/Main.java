package cn.lwx.rlstg;

import cn.lwx.rlstg.view.MainFrame;
import cn.lwx.rlstg.view.SelectiveFrame;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Package: cn.lwx.rlstg
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2018/1/23
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.FINE,e.getMessage());
            e.printStackTrace();
        }
        SelectiveFrame selectiveFrame = new SelectiveFrame();
    }
}
