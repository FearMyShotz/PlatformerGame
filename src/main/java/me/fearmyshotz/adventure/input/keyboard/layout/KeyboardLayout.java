package me.fearmyshotz.adventure.input.keyboard.layout;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

/**
 * @author Jamil B.
 */
public abstract class KeyboardLayout {

    protected @NotNull Key left, right, forward, jump, altAttack;

    protected abstract @NotNull Key getLeftKey();

    protected abstract @NotNull Key getRightKey();

    protected abstract @NotNull Key getForwardKey();

    protected abstract @NotNull Key getJumpKey();
    
    protected abstract @NotNull Optional<Key> getAltAttackKey();
}