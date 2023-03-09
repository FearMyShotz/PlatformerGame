package adventure.model.animation.loader.implementation;

import java.util.Set;
import java.util.TreeSet;

import adventure.model.animation.Animation;
import adventure.model.spritesheet.Spritesheet;
import adventure.model.spritesheet.loader.SpritesheetLoader;

public class ItemAnimationLoader extends SpritesheetLoader<Animation> {

    public ItemAnimationLoader(Spritesheet sheet, String fileName) {
        super(new TreeSet<Animation>(), sheet, fileName);
    }

    @Override
    public Set<Animation> load() {
        
        return null;
    }
}