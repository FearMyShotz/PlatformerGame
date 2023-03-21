package platformer.model.animation.implementation;

import platformer.model.animation.Animation;
import platformer.model.animation.type.AnimationType;
import platformer.model.spritesheet.Spritesheet;

/**
 * Eine spezialisierte Animation, die für Tiles verwendet wird.
 * 
 * Wird derzeit nicht verwendet. (Stand: 21.03.2023)
 * 
 * @author Jamil B.
 */
public class TileAnimation extends Animation {

    public TileAnimation(int id, String key, Spritesheet spritesheet, AnimationType type, int frames) {
        super(id, key, spritesheet, type, frames);
    }
}
