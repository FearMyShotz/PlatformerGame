package platformer.model.animation.loader.implementation;

import java.util.HashSet;

import platformer.model.animation.Animation;
import platformer.model.spritesheet.Spritesheet;
import platformer.model.spritesheet.loader.SpritesheetLoader;

/**
 * Eine spezialisierte {@link SpritesheetLoader}-Implementierung, die für das Laden von Item-Animationen verwendet wird.
 * 
 * Wird derzeit nicht verwendet. (Stand: 21.03.2023)
 * 
 * @author Jamil B.
 * @see Animation
 * @see SpritesheetLoader
 */
public class ItemAnimationLoader extends SpritesheetLoader<Animation> {

    /**
     * Erstellt einen neuen ItemAnimationLoader.
     * 
     * @param sheet Das Spritesheet, die für das Laden der Animationen verwendet werden soll.
     * @param fileName Der Name der Datei, die für das Laden der Animationen verwendet werden soll.
     */
    public ItemAnimationLoader(Spritesheet sheet, String fileName) {
        super(new HashSet<Animation>(), sheet, fileName);
    }

    /**
     * Lädt die Animationen, die für das Item geladen werden sollen.
     * 
     * Derzeit nicht implementiert.
     * 
     * @return Die Animationen, die für das Item geladen werden sollen.
     */
    @Override
    public HashSet<Animation> load() {
        // Nicht implementiert
        return null;
    }
}