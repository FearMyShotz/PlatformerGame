package me.fearmyshotz.adventure.manager.implementation;

import java.util.HashMap;
import java.util.Map;

import me.fearmyshotz.adventure.AdventureGame;
import me.fearmyshotz.adventure.manager.Manager;
import me.fearmyshotz.adventure.model.ResourceKey;
import me.fearmyshotz.adventure.model.animation.Animation;
import me.fearmyshotz.adventure.model.animation.loader.AnimationLoader;
import me.fearmyshotz.adventure.model.spritesheet.Spritesheet;
import me.fearmyshotz.adventure.model.spritesheet.SpritesheetType;
import me.fearmyshotz.adventure.util.Pair;

public class AnimationManager extends Manager {

    private Map<ResourceKey<Animation>, Animation> animations = new HashMap<>();

    public AnimationManager(AdventureGame game) {
        super(game);
    }

    @Override
    public void initialize(AdventureGame game) {
        super.initialize(game);

        initAnimations();

        // try {
        //     ZipFile zipFile = new ZipFile(new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()));

        //     zipFile.stream().filter(entry -> entry.getName().endsWith(".png")).forEach(entry -> {
        //         String fileName = entry.getName().substring(entry.getName().lastIndexOf("/") + 1);

        //         game.log("Loading animation: " + fileName);

        //         initAnimation(fileName);
        //     });
        // } catch (IOException | URISyntaxException e) {
        //     e.printStackTrace();
        // }
    }

    private void initAnimations() {
        initAnimation("player_left_32x32.png");
        initAnimation("player_right_32x32.png");
        initAnimation("knight_left_32x32.png");
        initAnimation("knight_right_32x32.png");
        initAnimation("archer_left_32x32.png");
        initAnimation("archer_right_32x32.png");
        initAnimation("mage_left_32x32.png");
        initAnimation("mage_right_32x32.png");
    }

    public void initAnimation(String fileName) {
        SpritesheetType type = getType(fileName);

        if (type == null) return;        

        Spritesheet spritesheet = new Spritesheet(type, fileName, getAssetRatio(fileName));

        AnimationLoader loader = new AnimationLoader(spritesheet, fileName);

        loader.getLoadedAnimations().forEach(this::registerAnimation);
    }

    public Pair<Integer, Integer> getAssetRatio(String fileName) {
        return switch (fileName.substring(fileName.length() - 4)) {
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