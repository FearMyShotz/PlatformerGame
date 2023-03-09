package adventure.input.keyboard.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Jamil B.
 */
public class DefaultKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);

        // AdventureGame
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


}