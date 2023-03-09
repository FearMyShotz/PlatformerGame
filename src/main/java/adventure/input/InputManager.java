package adventure.input;

import java.awt.event.KeyListener;

import adventure.AdventureGame;
import adventure.input.keyboard.Key;
import adventure.input.keyboard.KeyAction;
import adventure.input.keyboard.layout.KeyboardLayout;
import adventure.input.keyboard.layout.implementation.DefaultKeyboardLayout;
import adventure.input.keyboard.listener.DefaultKeyListener;
import adventure.manager.Manager;

public class InputManager extends Manager {

    private final KeyListener keyListener;
    private final KeyboardLayout keyboardLayout;

    public InputManager(AdventureGame game) {
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
        game.getActiveState().handleKeyAction(action, keyCode);
    }

    public Key getKey(int keyCode) {
        return keyboardLayout.getKey(keyCode);
    }
    
}