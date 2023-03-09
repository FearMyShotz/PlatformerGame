package adventure.input.keyboard;

import org.jetbrains.annotations.NotNull;

/**
 * @author Jamil B.
 */
public final class Key {

    final @NotNull int keyCode;

    public Key(int keyCode) {
        this.keyCode = keyCode;
    }

    public @NotNull int getKeyCode() {
        return keyCode;
    }
}