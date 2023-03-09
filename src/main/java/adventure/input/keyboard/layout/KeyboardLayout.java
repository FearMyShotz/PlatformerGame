package adventure.input.keyboard.layout;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import adventure.input.keyboard.Key;

/**
 * @author Jamil B.
 */
public interface KeyboardLayout {

    @NotNull Key getLeftKey();

    @NotNull Key getRightKey();

    @NotNull Key getForwardKey();

    @NotNull Key getJumpKey();
    
    @NotNull Optional<Key> getAltAttackKey();

    @NotNull Key getKey(int keyCode);

}