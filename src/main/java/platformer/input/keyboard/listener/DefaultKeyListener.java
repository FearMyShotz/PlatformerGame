package platformer.input.keyboard.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import platformer.PlatformerGame;
import platformer.input.keyboard.KeyAction;

/**
 * Der DefaultKeyListener ist der KeyListener, der standardmäßig verwendet wird.
 * 
 * @author Jamil B.
 * @see KeyListener
 */
public class DefaultKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);

        PlatformerGame.getInstance().getInputManager().handleKeyAction(KeyAction.PRESS, e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PlatformerGame.getInstance().getInputManager().handleKeyAction(KeyAction.RELEASE, e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}