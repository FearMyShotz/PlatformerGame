package platformer.input;

import java.awt.event.KeyListener;

import platformer.PlatformerGame;
import platformer.input.keyboard.Key;
import platformer.input.keyboard.KeyAction;
import platformer.input.keyboard.layout.KeyboardLayout;
import platformer.input.keyboard.layout.implementation.DefaultKeyboardLayout;
import platformer.input.keyboard.listener.DefaultKeyListener;
import platformer.manager.Manager;

/**
 * Der InputManager ist der Manager, der für die Eingabe zuständig ist.
 * 
 * @author Jamil B.
 * @see Manager
 * @see KeyListener
 * @see KeyboardLayout
 */
public final class InputManager extends Manager {

    /**
     * Der KeyListener, der die Eingabe verarbeitet.
     */
    private final KeyListener keyListener;

    /**
     * Das Tastaturlayout, das die Tasten repräsentiert.
     */
    private final KeyboardLayout keyboardLayout;

    /**
     * Erstellt einen neuen InputManager.
     * 
     * @param game Instanz der Hauptklasse des Spiels
     */
    public InputManager(PlatformerGame game) {
        super(game);

        this.keyListener = new DefaultKeyListener();
        this.keyboardLayout = new DefaultKeyboardLayout();
    }

    /**
     * Gibt den KeyListener zurück.
     * 
     * @return der KeyListener
     */
    public KeyListener getKeyListener() {
        return keyListener;
    }

    /**
     * Gibt das Tastaturlayout zurück.
     * 
     * @return das Tastaturlayout
     */
    public KeyboardLayout getKeyboardLayout() {
        return keyboardLayout;
    }

    /**
     * Verarbeitet eine Aktion, die mit einer Taste ausgeführt wurde.
     * Gibt die Aktion an den aktuellen Zustand des Spiels weiter.
     * 
     * @see platformer.state.State#handleKeyAction(KeyAction, int)
     * @param action die Aktion
     * @param keyCode der KeyCode der Taste
     */
    public void handleKeyAction(KeyAction action, int keyCode) {
        game.getActiveState().handleKeyAction(action, keyCode);
    }

    /**
     * Gibt die Taste für einen bestimmten KeyCode zurück.
     * @return die Taste des aktuellen Tastaturlayouts
     */
    public Key getKey(int keyCode) {
        return keyboardLayout.getKey(keyCode);
    }
}