package platformer.input.keyboard.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import platformer.PlatformerGame;
import platformer.input.keyboard.KeyAction;

/**
 * @author Jamil B.
 */
public class DefaultKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("Key pressed: " + e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);

        PlatformerGame.getInstance().getInputManager().handleKeyAction(KeyAction.PRESS, e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println("Key released: " + e.getKeyCode());

        PlatformerGame.getInstance().getInputManager().handleKeyAction(KeyAction.RELEASE, e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


}