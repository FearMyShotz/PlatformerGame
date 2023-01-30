package me.fearmyshotz.adventure.input.keyboard.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Jamil B.
 */
public class DefaultKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}