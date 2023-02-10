package me.fearmyshotz.adventure.input.keyboard.layout.implementation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import me.fearmyshotz.adventure.input.keyboard.layout.Key;
import me.fearmyshotz.adventure.input.keyboard.layout.KeyboardLayout;

public class ConfiguredKeyboardLayout extends KeyboardLayout {

    private final @Nullable File configFile;

    public ConfiguredKeyboardLayout(File configFile) {
        this.configFile = configFile;
    }

    public void setKeys() {
        try {
            FileReader reader = new FileReader(configFile);

            reader.read();
            // ! Read the file and set the keys

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Key getLeftKey() {
        
        return null;
    }

    @Override
    public Key getRightKey() {
        
        return null;
    }

    @Override
    public Key getForwardKey() {
        
        return null;
    }

    @Override
    public Key getJumpKey() {
        
        return null;
    }

    @Override
    public Optional<Key> getAltAttackKey() {
        
        return Optional.empty();
    }
    
}