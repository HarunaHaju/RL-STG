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
 * Modified Date: 2018/01/23
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class MainFrame extends JFrame implements Runnable {

    private GamePanel panel_game = null;

    private boolean isRunning;
    private Thread thread;

    private static final int THREAD_TIME = 16;

    public MainFrame(int flag){
        switch (flag){
            case 0:
                break;
            case 1:
                break;
            default:
                break;
        }
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
        long timer;
        while (isRunning){
            timer = System.currentTimeMillis();
            this.repaint();
            this.panel_game.repaint();
            this.panel_game.step();
            timer = System.currentTimeMillis() - timer;
            if(THREAD_TIME - timer > 0L) {
                try {
                    Thread.sleep(THREAD_TIME - timer);
                } catch (Exception e) {
                    isRunning = false;
                    e.printStackTrace();
                }
            }
        }
    }
}
