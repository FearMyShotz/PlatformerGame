package me.fearmyshotz.adventure.game;

import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.FocusEvent.Cause;

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
    }

    // Called every frame, clears the screen and draws the next frame
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}