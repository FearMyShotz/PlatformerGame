package me.fearmyshotz.adventure.model.animation;

import java.util.ArrayList;
import java.util.List;

import me.fearmyshotz.adventure.model.animation.type.AnimationType;
import me.fearmyshotz.adventure.model.spritesheet.Spritesheet;
import me.fearmyshotz.adventure.model.spritesheet.SpritesheetType;
import me.fearmyshotz.adventure.util.Initializable;

public class AnimationLoader implements Initializable<String> {

    private Spritesheet spritesheet;

    private SpritesheetType type;

    private List<Animation> loadedAnimations = new ArrayList<>();

    public AnimationLoader(SpritesheetType sType, String fileName, Spritesheet sheet) {
        this.type = sType;
        this.spritesheet = sheet;
        initialize(fileName);
    }

    @Override
    public void initialize(String fileName) {
        // TODO Animation laden
        switch (type) {
            case ENTITY -> {
                if (fileName.equals("player_left")) {
                    Animation walkLeft = new Animation(0, "player_walk_left", "Nach links laufender Spieler", spritesheet);
                    walkLeft.setType(AnimationType.WALK_LEFT);

                    
                }
                if (fileName.equals("player_right")) {

                }
            }
            case TILE -> throw new UnsupportedOperationException("Unimplemented case: " + type);
            case GUI -> throw new UnsupportedOperationException("Unimplemented case: " + type);
            case ITEM -> throw new UnsupportedOperationException("Unimplemented case: " + type);
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        }
    }

    public SpritesheetType getType() {
        return type;
    }

    public Spritesheet getSpritesheet() {
        return spritesheet;
    }

    public List<Animation> getLoadedAnimations() {
        return loadedAnimations;
    }
}