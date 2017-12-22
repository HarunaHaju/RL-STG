package cn.lwx.rlstg.view;

import cn.lwx.rlstg.GlobalManager;
import com.sun.org.apache.bcel.internal.generic.SWITCH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Package: cn.lwx.rlstg.view
 * Comments:
 * Author: lwx
 * Create Date: 2017/12/18
 * Modified Date: 2017/12/22
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class MainFrame extends JFrame implements Runnable {

    private GamePanel panel_game = null;

    private boolean isRunning;
    private Thread thread;

    public MainFrame(){
        thread = new Thread(this);
        isRunning = true;
        this.setUpUI();
        thread.start();
    }

    private void setUpUI(){
        panel_game = new GamePanel();

        JPanel panel_button = new JPanel();
        JButton button_start = new JButton("Start");
        button_start.addActionListener(e->this.methodStart());

        JButton button_pause = new JButton("Pause");
        button_pause.addActionListener(e->this.methodPause());

        JButton button_restart = new JButton("Restart");
        button_restart.addActionListener(e->this.methodRestart());

        panel_button.setSize(480,70);
        panel_button.setBackground(Color.black);
        panel_button.add(button_start);
        panel_button.add(button_pause);
        panel_button.add(button_restart);

        this.setSize(480, 770);
        this.setTitle("RL_STG by lwx");

        this.setFocusable(true);
        this.addKeyListener(this.panel_game);

        this.setLayout(new BorderLayout());
        this.add(panel_game,BorderLayout.CENTER);
        this.add(panel_button,BorderLayout.SOUTH);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width / 2 - 216, height / 2 - 307);
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void methodStart(){
        if (!isRunning){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    private void methodPause(){
        if (isRunning){
            isRunning = false;
        }else{
            methodStart();
        }
    }

    private void methodRestart(){
        panel_game.resetAll();
        this.repaint();
        methodStart();
    }

    @Override
    public void run() {
        while (isRunning){
            this.repaint();
            this.panel_game.repaint();
            this.panel_game.step();
            try {
                Thread.sleep(1000/60);
            } catch (Exception e) {
                isRunning = false;
                e.printStackTrace();
            }
        }
    }
}
