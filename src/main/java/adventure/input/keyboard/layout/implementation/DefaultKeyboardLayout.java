package adventure.input.keyboard.layout.implementation;

import java.awt.event.KeyEvent;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import adventure.input.keyboard.Key;
import adventure.input.keyboard.layout.KeyboardLayout;

public final class DefaultKeyboardLayout implements KeyboardLayout {

    @Override
    public @NotNull Key getLeftKey() { return new Key(KeyEvent.VK_A); }

    @Override
    public @NotNull Key getRightKey() { return new Key(KeyEvent.VK_D); }

    @Override
    public @NotNull Key getForwardKey() { return new Key(KeyEvent.VK_W); }

    @Override
    public @NotNull Key getJumpKey() { return new Key(KeyEvent.VK_SPACE); }

    @Override
    public @NotNull Optional<Key> getAltAttackKey() { return Optional.of(new Key(KeyEvent.VK_SHIFT)); }

    @Override
    public @NotNull Key getKey(int keyCode) {
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