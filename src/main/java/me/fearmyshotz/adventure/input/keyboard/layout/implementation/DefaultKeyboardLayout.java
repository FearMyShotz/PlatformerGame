package me.fearmyshotz.adventure.input.keyboard.layout.implementation;

import java.awt.event.KeyEvent;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import me.fearmyshotz.adventure.input.keyboard.layout.Key;
import me.fearmyshotz.adventure.input.keyboard.layout.KeyboardLayout;

public final class DefaultKeyboardLayout extends KeyboardLayout {

    public DefaultKeyboardLayout() {
        left = new Key(KeyEvent.VK_A);
        right = new Key(KeyEvent.VK_D);
        forward = new Key(KeyEvent.VK_W);
        jump = new Key(KeyEvent.VK_SPACE);
        altAttack = new Key(KeyEvent.VK_SHIFT);
    }

    @Override
    public @NotNull Key getLeftKey() { return left; }

    @Override
    public @NotNull Key getRightKey() { return right; }

    @Override
    public @NotNull Key getForwardKey() { return forward; }

    @Override
    public @NotNull Key getJumpKey() { return jump; }

    @Override
    public @NotNull Optional<Key> getAltAttackKey() { return Optional.of(altAttack); }

}