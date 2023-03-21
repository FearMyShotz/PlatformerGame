package platformer.model.animation.implementation;

import platformer.model.animation.Animation;
import platformer.model.animation.type.AnimationType;
import platformer.model.spritesheet.Spritesheet;

public class TileAnimation extends Animation {

    public TileAnimation(int id, String key, Spritesheet spritesheet, AnimationType type, int frames) {
        super(id, key, spritesheet, type, frames);
    }
    
}
