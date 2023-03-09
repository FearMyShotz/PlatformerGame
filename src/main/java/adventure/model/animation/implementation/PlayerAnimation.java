package adventure.model.animation.implementation;

import adventure.model.animation.Animation;
import adventure.model.animation.type.AnimationType;
import adventure.model.spritesheet.Spritesheet;

public class PlayerAnimation extends Animation {
    
    public static final String PREFIX = "player";

    public PlayerAnimation(int id, String key, Spritesheet spritesheet, AnimationType type, int frames) {
        super(id, key, spritesheet, type, frames);
    }

    
}