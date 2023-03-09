package adventure.model.animation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import adventure.AdventureGame;
import adventure.manager.AssetManager;
import adventure.model.ResourceKey;
import adventure.model.animation.loader.AnimationLoader;
import adventure.model.animation.type.AnimationType;
import adventure.model.entity.implementation.type.GeneralEntityType;
import adventure.model.spritesheet.Spritesheet;
import adventure.model.spritesheet.SpritesheetType;
import adventure.util.Pair;

public class AnimationManager extends AssetManager<Animation> {

    private Function<String, String> actualFileName = (fileName) -> fileName.substring(0, fileName.length() - 10);

    // private final TreeMap<ResourceKey<Animation>, Animation> loadedAnimations;

    private final TreeMap<GeneralEntityType, TreeMap<AnimationType, Animation>> mappedAnimations;

    private AnimationLoader loader;

    public AnimationManager(AdventureGame game) {
        super(game);

        // this.loadedAnimations = new TreeMap<ResourceKey<Animation>, Animation>();
        this.mappedAnimations = new TreeMap<GeneralEntityType, TreeMap<AnimationType, Animation>>();
        initialize(game);
    }

    @Override
    public void initialize(AdventureGame game) {
        initAnimations();
        // printLoadedAnimations();

        for (Map.Entry<ResourceKey<Animation>, Animation> entry : assets.entrySet()) {
            ResourceKey<Animation> key = entry.getKey();
            // System.out.println("Current key: " + key.getAccessor());
            Animation animation = entry.getValue();

            // System.out.println("Current animation: " + animation);

            GeneralEntityType type = GeneralEntityType.of(animation);
            
            if (type == null) continue;

            // System.out.println("Current type: " + type.name());

            if (mappedAnimations.containsKey(type)) {
                mappedAnimations.get(type).put(AnimationType.of(key.getKey()), animation);
            } else {
                TreeMap<AnimationType, Animation> animations = new TreeMap<AnimationType, Animation>();

                animations.put(AnimationType.of(key.getKey()), animation);
                mappedAnimations.put(type, animations);
            }
        }

        System.out.println("Player animations: " + mappedAnimations.get(GeneralEntityType.PLAYER).size());

        // printMappedAnimations();
    }

    private void initAnimations() {
        initAnimation("archer_left_32x32.png");
        initAnimation("archer_right_32x32.png");
        initAnimation("knight_left_32x32.png");
        initAnimation("knight_right_32x32.png");
        initAnimation("mage_left_32x32.png");
        initAnimation("mage_right_32x32.png");
        initAnimation("player_right_32x32.png");
        initAnimation("player_left_32x32.png");
    }

    public void initAnimation(String fileName) {
        Spritesheet spritesheet = new Spritesheet(SpritesheetType.ENTITY, fileName, getAssetRatio(fileName));

        this.loader = new AnimationLoader(spritesheet, fileName);

        loader.getLoadedAnimations().forEach(this::registerAnimation);
    }

    public Pair<Integer, Integer> getAssetRatio(String fileName) {
        return switch (actualFileName.apply(fileName)) {
            case "archer_left": case "archer_right":
            case "knight_left": case "knight_right":
            case "mage_left": case "mage_right":
            case "player_right": case "player_left" :
                yield new Pair<>(32, 32);
            default: yield null;
        };
    }

    public SpritesheetType getType(String fileName) {
        return switch (actualFileName.apply(fileName)) {
            case "player_right": case "player_left":
            case "archer_left": case "archer_right":
            case "knight_left": case "knight_right":
            case "mage_left": case "mage_right":
                yield SpritesheetType.ENTITY;
            default: yield null;
        };
    }

    @SuppressWarnings("unchecked")
    public void registerAnimation(Animation animation) {
        assets.put((ResourceKey<Animation>) animation.getKey(), animation);
    }

    public void printLoadedAnimations() {
        assets.forEach((key, animation) -> System.out.println(key + " -> " + animation));
    }

    public void printMappedAnimations() {
        System.out.println("Mapped animations:");
        mappedAnimations.forEach((key, animations) -> {
            System.out.println(key + " -> ");
            animations.forEach((type, animation) -> System.out.println(type + " -> " + animation));
        });
    }



    public List<Animation> getAnimations(GeneralEntityType type) {
        return new ArrayList<Animation>(mappedAnimations.get(type).values());
    }

    public TreeMap<AnimationType, Animation> getAnimationsMap(GeneralEntityType type) {
        return mappedAnimations.get(type);
    }

    public Collection<Animation> getAnimationsSet(GeneralEntityType type) {
        return mappedAnimations.get(type).values();
    }

    public Animation getAnimation(GeneralEntityType type, AnimationType animationType) {
        return mappedAnimations.get(type).get(animationType);
    }

    public TreeMap<GeneralEntityType, TreeMap<AnimationType, Animation>> getMappedAnimations() {
        return mappedAnimations;
    }
}