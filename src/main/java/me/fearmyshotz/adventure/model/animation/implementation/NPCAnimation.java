package me.fearmyshotz.adventure.model.animation.implementation;

import me.fearmyshotz.adventure.model.animation.Animation;
import me.fearmyshotz.adventure.model.animation.type.AnimationType;
import me.fearmyshotz.adventure.model.spritesheet.Spritesheet;

public class NPCAnimation extends Animation {

    public NPCAnimation(int id, String key, String description, Spritesheet spritesheet, AnimationType type) {
        super(id, key, description, spritesheet, type);
    }
    
}