package me.fearmyshotz.adventure.game;

import javax.swing.JFrame;

public class GameWindow {
    
    private JFrame frame;

    public GameWindow(GamePanel panel) {
        frame = new JFrame("Adventure Game");

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        // frame.add(new GamePanel());
        // frame.pack();
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }

}