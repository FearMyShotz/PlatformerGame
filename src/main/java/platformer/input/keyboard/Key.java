package platformer.input.keyboard;

/**
 * @author Jamil B.
 */
public final class Key {

    final int keyCode;

    public Key(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }
}