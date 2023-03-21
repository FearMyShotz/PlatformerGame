package platformer.model.animation.implementation;

import platformer.model.animation.Animation;
import platformer.model.animation.type.AnimationType;
import platformer.model.spritesheet.Spritesheet;

/**
 * Eine spezialisierte Animation, die für Spieler verwendet wird.
 * 
 * @author Jamil B.
 */
public class PlayerAnimation extends Animation {

    public PlayerAnimation(int id, String key, Spritesheet spritesheet, AnimationType type, int frames) {
        super(id, key, spritesheet, type, frames);
    }    
}