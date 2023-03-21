package platformer.input.keyboard.layout;

import java.util.Optional;

import platformer.input.keyboard.Key;

/**
 * Ein Interface, das die Tasten eines Tastaturlayouts beschreibt. Es wird von der Klasse {@link InputManager} verwendet.
 * 
 * Es enthält die Methoden {@link #getLeftKey()}, {@link #getRightKey()}, {@link #getForwardKey()}, {@link #getSwitchKey()}, {@link #getJumpKey()} und {@link #getKey(int)},
 * welche die entsprechenden Tasten zurückgeben.
 * 
 * @author Jamil B.
 * @see platformer.input.InputManager
 * @see Key
 */
public interface KeyboardLayout {

    /**
     * Gibt die Taste zurück, die für die Bewegung nach links verwendet wird.
     * @return die Taste für die Bewegung nach links
     */
    Key getLeftKey();

    /**
     * Gibt die Taste zurück, die für die Bewegung nach rechts verwendet wird.
     * @return die Taste für die Bewegung nach rechts
     */
    Key getRightKey();

    /**
     * Gibt die Taste zurück, die für die Bewegung nach vorne verwendet wird.
     * @return die Taste für die Bewegung nach vorne
     */
    Key getForwardKey();

    /**
     * Gibt die Taste zurück, die für einen Bewegungswechsel verwendet wird.
     * @return die Taste für einen Bewegungswechsel
     */
    Key getSwitchKey();

    /**
     * Gibt die Taste zurück, die für den Sprung verwendet wird.
     * @return die Taste für den Sprung
     */
    Key getJumpKey();
    
    /**
     * Gibt die Taste zurück, die für den alternativen Angriff verwendet wird.
     * 
     * 
     * @see Optional
     * @return die Taste für den alternativen Angriff
     */
    Optional<Key> getAltAttackKey();

    Key getKey(int keyCode);

}