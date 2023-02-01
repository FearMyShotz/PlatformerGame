package me.fearmyshotz.adventure.model.animation.implementation;

import me.fearmyshotz.adventure.model.ResourceKey;
import me.fearmyshotz.adventure.model.animation.Animation;

public class PlayerAnimations {
    
    public static final String PREFIX = "player";

    public static final ResourceKey<Animation> IDLE = new ResourceKey<Animation>("player_idle");
    public static final ResourceKey<Animation> WALK = new ResourceKey<Animation>("player_walk");
    public static final ResourceKey<Animation> JUMP = new ResourceKey<Animation>("player_jump");
    public static final ResourceKey<Animation> DEATH = new ResourceKey<Animation>("player_death");
}