package platformer.model.animation.loader.implementation;

import java.util.HashSet;

import platformer.model.animation.Animation;
import platformer.model.spritesheet.Spritesheet;
import platformer.model.spritesheet.loader.SpritesheetLoader;

public class ItemAnimationLoader extends SpritesheetLoader<Animation> {

    public ItemAnimationLoader(Spritesheet sheet, String fileName) {
        super(new HashSet<Animation>(), sheet, fileName);
    }

    @Override
    public HashSet<Animation> load() {
        // Nicht implementiert
        return null;
    }
}