package platformer.input.keyboard.layout.implementation;

import java.awt.event.KeyEvent;
import java.util.Optional;

import platformer.input.keyboard.Key;
import platformer.input.keyboard.layout.KeyboardLayout;

/**
 * Das standardmäßige Tastaturlayout. Es verwendet die Tasten A, D, W, S, Leertaste und Shift.
 * Es implementiert das {@link KeyboardLayout}-Interface.
 * 
 * @author Jamil B.
 * @see KeyboardLayout
 * @see Key
 */
public final class DefaultKeyboardLayout implements KeyboardLayout {

    @Override
    public Key getLeftKey() { return new Key(KeyEvent.VK_A); }

    @Override
    public Key getRightKey() { return new Key(KeyEvent.VK_D); }

    @Override
    public Key getForwardKey() { return new Key(KeyEvent.VK_W); }

    @Override
    public Key getSwitchKey() { return new Key(KeyEvent.VK_S); }

    @Override
    public Key getJumpKey() { return new Key(KeyEvent.VK_SPACE); }

    @Override
    public Optional<Key> getAltAttackKey() { return Optional.of(new Key(KeyEvent.VK_SHIFT)); }

    @Override
    public Key getKey(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A:
                return getLeftKey();
            case KeyEvent.VK_D:
                return getRightKey();
            case KeyEvent.VK_W:
                return getForwardKey();
            case KeyEvent.VK_SPACE:
                return getJumpKey();
            case KeyEvent.VK_SHIFT:
                return getAltAttackKey().get();
            default:
                return null;
        }
    }

}