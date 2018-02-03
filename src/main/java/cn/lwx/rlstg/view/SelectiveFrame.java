package cn.lwx.rlstg.view;

import javax.swing.*;
import java.awt.*;

/**
 * Package: cn.lwx.rlstg.view
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/23
 * Modified Date: 2018/2/3
 * Why & What is modified:
 * Version: 1.2.1
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class SelectiveFrame extends JFrame {
    private JPanel combo_panel;
    private JComboBox<String> combo_box;
    private JPanel panel_button;
    private JButton button_confirm;
    private JButton button_cancel;

    public SelectiveFrame() {
        setupUI();
    }

    private void setupUI() {
        this.setSize(400, 140);

        JLabel label1  = new JLabel("Which algorithm do you want to use?");
        label1.setFont(new Font("Helvetica",Font.PLAIN, 20));
        this.add(label1,BorderLayout.NORTH);

        combo_box = new JComboBox<>();
        combo_box.setFont(new Font("Helvetica",Font.PLAIN, 16));
        combo_box.addItem("Q-Learning");
        combo_box.addItem("Random");
        combo_box.addItem("Live First (Rule Based)");
        combo_box.addItem("Q-Network");

        JLabel label2 = new JLabel("Algorithm:");
        label2.setFont(new Font("Helvetica",Font.PLAIN, 16));

        combo_panel = new JPanel();
        combo_panel.add(label2,BorderLayout.CENTER);
        combo_panel.add(combo_box);
        this.add(combo_panel,BorderLayout.CENTER);

        button_confirm = new JButton("Confirm");
        button_confirm.addActionListener(e -> methodConfirm());

        button_cancel = new JButton("Cancel");
        button_cancel.addActionListener(e -> methodCancel());

        panel_button = new JPanel();
        panel_button.add(button_confirm);
        panel_button.add(button_cancel);
        this.add(panel_button, BorderLayout.SOUTH);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width / 2 - 200, height / 2 - 70);
        this.setTitle("RL_STG by lwx");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void methodConfirm() {
        MainFrame mainFrame = new MainFrame(combo_box.getSelectedIndex());
        this.dispose();
    }

    private void methodCancel() {
        System.exit(0);
    }
}
