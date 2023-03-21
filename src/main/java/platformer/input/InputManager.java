package platformer.input;

import java.awt.event.KeyListener;

import platformer.PlatformerGame;
import platformer.input.keyboard.Key;
import platformer.input.keyboard.KeyAction;
import platformer.input.keyboard.layout.KeyboardLayout;
import platformer.input.keyboard.layout.implementation.DefaultKeyboardLayout;
import platformer.input.keyboard.listener.DefaultKeyListener;
import platformer.manager.Manager;

public class InputManager extends Manager {

    private final KeyListener keyListener;
    private final KeyboardLayout keyboardLayout;

    public InputManager(PlatformerGame game) {
        super(game);

        this.keyListener = new DefaultKeyListener();
        this.keyboardLayout = new DefaultKeyboardLayout();
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public KeyboardLayout getKeyboardLayout() {
        return keyboardLayout;
    }

    public void handleKeyAction(KeyAction action, int keyCode) {
        // game.debug("Received key action: " + action + " with key code: " + keyCode);

        game.getActiveState().handleKeyAction(action, keyCode);
    }

    public Key getKey(int keyCode) {
        return keyboardLayout.getKey(keyCode);
    }
    
}