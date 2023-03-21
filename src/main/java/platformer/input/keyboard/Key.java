package platformer.input.keyboard;

/**
 * Eine simple Klasse, die eine Taste der Tastatur repräsentiert.
 * 
 * @author Jamil B.
 */
public final class Key {

    /**
     * Der KeyCode der Taste.
     */
    final int keyCode;

    /**
     * Erstellt eine neue Taste mit dem angegebenen KeyCode.
     * 
     * @param keyCode der KeyCode der Taste
     */
    public Key(int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * Gibt den KeyCode der Taste zurück.
     * 
     * @return der KeyCode der Taste
     */
    public int getKeyCode() {
        return keyCode;
    }
}