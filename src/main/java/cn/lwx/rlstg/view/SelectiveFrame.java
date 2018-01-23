package cn.lwx.rlstg.view;

import javax.swing.*;
import java.awt.*;

/**
 * Package: cn.lwx.rlstg.view
 * Comments:
 * Author: lwx
 * Create Date: 2018/1/23
 * Modified Date: 2018/1/23
 * Why & What is modified:
 * Version: 0.0.1beta
 * It's the only NEET thing to do. – Shionji Yuuko
 */
public class SelectiveFrame extends JFrame {
    private JPanel comboPanel;
    private JComboBox<String> comboBox;
    private JPanel buttonPanel;
    private JButton buttonConfirm;
    private JButton buttonCancel;

    public SelectiveFrame() {
        setupUI();
    }

    private void setupUI() {
        this.setSize(400, 140);

        JLabel label1  = new JLabel("Which algorithm do you want to use?");
        label1.setFont(new Font("Helvetica",Font.PLAIN, 20));
        this.add(label1,BorderLayout.NORTH);

        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Helvetica",Font.PLAIN, 16));
        comboBox.addItem("Q-Learning");
        comboBox.addItem("Random");

        JLabel label2 = new JLabel("Algorithm:");
        label2.setFont(new Font("Helvetica",Font.PLAIN, 16));

        comboPanel = new JPanel();
        comboPanel.add(label2,BorderLayout.CENTER);
        comboPanel.add(comboBox);
        this.add(comboPanel,BorderLayout.CENTER);

        buttonConfirm = new JButton("Confirm");
        buttonConfirm.addActionListener(e -> methodConfirm());

        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(e -> methodCancel());

        buttonPanel = new JPanel();
        buttonPanel.add(buttonConfirm);
        buttonPanel.add(buttonCancel);
        this.add(buttonPanel, BorderLayout.SOUTH);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation(width / 2 - 200, height / 2 - 70);
        this.setTitle("RL_STG by lwx");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void methodConfirm() {
        MainFrame mainFrame = new MainFrame(comboBox.getSelectedIndex());
        this.dispose();
    }

    private void methodCancel() {
        System.exit(0);
    }
}
