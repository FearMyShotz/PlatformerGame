package me.fearmyshotz.adventure.game;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.FocusEvent.Cause;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import me.fearmyshotz.adventure.input.keyboard.listener.DefaultKeyListener;
import me.fearmyshotz.adventure.input.mouse.listener.DefaultMouseListener;

public class GamePanel extends JPanel {

    private final KeyListener keyListener;
    private final DefaultMouseListener mouseListener;

    public GamePanel() {
        this.keyListener = new DefaultKeyListener();
        this.mouseListener = new DefaultMouseListener();

        this.addKeyListener(keyListener);

        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        this.setFocusable(true);
        this.requestFocus(Cause.ACTIVATION);

        setScreenSize(1280, 800);
    }

    // Called every frame, clears the screen and draws the next frame
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void setScreenSize(int width, int height) {
        Dimension screenSize = new Dimension(width, height);

        this.setPreferredSize(screenSize);

        this.setMinimumSize(screenSize);
        this.setMaximumSize(screenSize);
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public DefaultMouseListener getMouseListener() {
        return mouseListener;
    }
    
}