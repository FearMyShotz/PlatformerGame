package platformer.input.keyboard.layout;

import java.util.Optional;

import platformer.input.keyboard.Key;

/**
 * @author Jamil B.
 */
public interface KeyboardLayout {

    Key getLeftKey();

    Key getRightKey();

    Key getForwardKey();

    Key getBackwardKey();

    Key getJumpKey();
    
    Optional<Key> getAltAttackKey();

    Key getKey(int keyCode);

}