package me.fearmyshotz.adventure.model.animation.loader.implementation;

import java.util.Set;
import java.util.TreeSet;

import me.fearmyshotz.adventure.model.animation.Animation;
import me.fearmyshotz.adventure.model.spritesheet.Spritesheet;
import me.fearmyshotz.adventure.util.SpritesheetLoader;

public class ItemAnimationLoader extends SpritesheetLoader<Animation> {

    public ItemAnimationLoader(Spritesheet sheet, String fileName) {
        super(new TreeSet<Animation>(), sheet, fileName);
    }

    @Override
    public Set<Animation> load() {
        
        return null;
    }
}