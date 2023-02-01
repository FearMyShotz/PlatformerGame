package me.fearmyshotz.adventure.manager.implementation;

import java.util.HashMap;
import java.util.Map;

import me.fearmyshotz.adventure.AdventureGame;
import me.fearmyshotz.adventure.manager.Manager;
import me.fearmyshotz.adventure.model.ResourceKey;
import me.fearmyshotz.adventure.model.animation.Animation;
import me.fearmyshotz.adventure.model.animation.AnimationLoader;
import me.fearmyshotz.adventure.model.spritesheet.Spritesheet;
import me.fearmyshotz.adventure.model.spritesheet.SpritesheetType;
import me.fearmyshotz.adventure.util.Initializable;
import me.fearmyshotz.adventure.util.Pair;

public class AnimationManager extends Manager implements Initializable<AdventureGame> {

    private Map<ResourceKey<Animation>, Animation> animations = new HashMap<>();

    public AnimationManager(AdventureGame game) {
        super(game);
    }

    @Override
    public void initialize(AdventureGame game) {
        initAnimation("player_left");
        initAnimation("player_right");

        // TODO: Alle Animationen hinzufügen
    }

    public void initAnimation(String fileName) {
        SpritesheetType type = getType(fileName);

        if (type == null) return;        

        Spritesheet spritesheet = new Spritesheet(fileName, getAssetRatio(fileName));

        AnimationLoader loader = new AnimationLoader(type, fileName, spritesheet);

        loader.getLoadedAnimations().forEach(this::registerAnimation);
    }

    public Pair<Integer, Integer> getAssetRatio(String fileName) {
        return switch (fileName) {
            case "player_right" -> new Pair<>(32, 32);
            case "player_left" -> new Pair<>(32, 32);
            case "enemy" -> new Pair<>(32, 32);
            case "item" -> new Pair<>(1, 1);
            case "block" -> new Pair<>(1, 1);
            case "gui" -> new Pair<>(1, 1);
            default -> null;
        };
    }

    public SpritesheetType getType(String fileName) {
        return switch (fileName) {
            case "player_right" -> SpritesheetType.ENTITY;
            case "player_left" -> SpritesheetType.ENTITY;
            case "enemy" -> SpritesheetType.ENTITY;
            case "item" -> SpritesheetType.ITEM;
            case "block" -> SpritesheetType.TILE;
            case "gui" -> SpritesheetType.GUI;
            default -> null;
        };
    }

    @SuppressWarnings("unchecked")
    public void registerAnimation(Animation animation) {
        animations.put((ResourceKey<Animation>) animation.getKey(), animation);
    }
}