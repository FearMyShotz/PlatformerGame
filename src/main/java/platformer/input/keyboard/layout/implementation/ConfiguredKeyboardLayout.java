package platformer.input.keyboard.layout.implementation;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import platformer.input.keyboard.Key;
import platformer.input.keyboard.layout.KeyboardLayout;

/**
 * Ein Tastaturlayout, das aus einer Konfigurationsdatei gelesen wird.
 * Es implementiert das {@link KeyboardLayout}-Interface.
 * 
 * Noch nicht implementiert. (Stand: 21.03.2023)
 * 
 * @author Jamil B.
 * @see KeyboardLayout
 * @see Key
 */
public class ConfiguredKeyboardLayout implements KeyboardLayout {

    private final File configFile;

    public ConfiguredKeyboardLayout(File configFile) {
        this.configFile = configFile;
    }

    public void setKeys() {
        try {
            FileReader reader = new FileReader(configFile);

            reader.read();
            // Noch nicht implementiert

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

    public Key getSwitchKey() {
        
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

    @Override
    public Key getKey(int keyCode) {
        
        return null;
    }
    
}