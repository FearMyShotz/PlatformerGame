package platformer.model.animation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import platformer.PlatformerGame;
import platformer.manager.AssetManager;
import platformer.model.ResourceKey;
import platformer.model.animation.loader.AnimationLoader;
import platformer.model.animation.type.AnimationType;
import platformer.model.entity.implementation.type.GeneralEntityType;
import platformer.model.spritesheet.Spritesheet;
import platformer.model.spritesheet.SpritesheetType;
import platformer.util.Pair;

/**
 * Der AnimationManager verwaltet alle Animationen des Spiels.
 * 
 * @author Jamil B.
 * @see AssetManager
 */
public class AnimationManager extends AssetManager<Animation> {

    /**
     * Funktion, die den Dateinamen der Animationen validiert.
     */
    private Function<String, String> actualFileName = (fileName) -> fileName.substring(0, fileName.length() - 10);

    /**
     * Eine {@link TreeMap}, die alle geladenen Animationen enthält und über den {@link GeneralEntityType} und den {@link AnimationType} die Animationen speichert.
     */
    private final TreeMap<GeneralEntityType, TreeMap<AnimationType, Animation>> mappedAnimations;

    /**
     * Erstellt einen neuen AnimationManager.
     * 
     * @param game die Spielinstanz, in der dieser Manager verwendet wird.
     */
    public AnimationManager(PlatformerGame game) {
        super(game);

        this.mappedAnimations = new TreeMap<GeneralEntityType, TreeMap<AnimationType, Animation>>();
        initialize(game);
    }

    /**
     * Initialisiert den AnimationManager. Lädt alle Animationen und speichert sie in der {@link #mappedAnimations} {@link TreeMap}.
     * 
     * @param game die Spielinstanz, in der dieser Manager verwendet wird.
     */
    @Override
    public void initialize(PlatformerGame game) {
        if (loader != null) return;

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

    /**
     * Initialisiert alle Animationen.
     */
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

    /**
     * Initialisiert eine Animation.
     * 
     * @param fileName der Dateiname der Animation.
     */
    public void initAnimation(String fileName) {
        Spritesheet spritesheet = new Spritesheet(SpritesheetType.ENTITY, fileName, getAssetRatio(fileName));

        this.loader = new AnimationLoader(spritesheet, fileName);

        loader.getLoadedAssets().forEach(this::registerAnimation);
    }

    /**
     * Gibt die Größe der Animation als ein {@link Pair} zurück.
     * 
     * @param fileName der Dateiname der Animation.
     * @return die Größe der Animation.
     */
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

    /**
     * Gibt den {@link SpritesheetType} der Animation zurück.
     * 
     * @param fileName der Dateiname der Animation.
     * @return der {@link SpritesheetType} der Animation.
     */
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

    /**
     * Registriert eine Animation.
     * 
     * @param animation die Animation.
     */
    @SuppressWarnings("unchecked")
    public void registerAnimation(Animation animation) {
        assets.put((ResourceKey<Animation>) animation.getKey(), animation);
    }

    /**
     * Gibt alle geladenen Animationen aus, indem über die {@link #assets} {@link TreeMap} iteriert wird.
     */
    public void printLoadedAnimations() {
        assets.forEach((key, animation) -> System.out.println(key + " -> " + animation));
    }

    /**
     * Gibt alle geladenen Animationen aus, indem über die {@link #mappedAnimations} {@link TreeMap} iteriert wird.
     */
    public void printMappedAnimations() {
        System.out.println("Mapped animations:");
        mappedAnimations.forEach((key, animations) -> {
            System.out.println(key + " -> ");
            animations.forEach((type, animation) -> System.out.println(type + " -> " + animation));
        });
    }

    /**
     * Gibt alle geladenen Animationen für einen bestimmten {@link GeneralEntityType} in einer {@link List} zurück.
     * 
     * @param type der {@link GeneralEntityType}, für den die Animationen gebraucht werden.
     */
    public List<Animation> getAnimations(GeneralEntityType type) {
        return new ArrayList<Animation>(mappedAnimations.get(type).values());
    }

    /**
     * Gibt alle geladenen Animationen für einen bestimmten {@link GeneralEntityType} in einer {@link TreeMap} zurück.
     * 
     * @param type der {@link GeneralEntityType}, für den die Animationen gebraucht werden.
     */
    public TreeMap<AnimationType, Animation> getAnimationsMap(GeneralEntityType type) {
        return mappedAnimations.get(type);
    }

    /**
     * Gibt alle geladenen Animationen für einen bestimmten {@link GeneralEntityType} in einer {@link Collection} zurück.
     * 
     * @param type der {@link GeneralEntityType}, für den die Animationen gebraucht werden.
     */
    public Collection<Animation> getAnimationsSet(GeneralEntityType type) {
        return mappedAnimations.get(type).values();
    }

    /**
     * Gibt eine bestimmte Animation für einen bestimmten {@link GeneralEntityType} und einen bestimmten {@link AnimationType} zurück.
     * 
     * @param type der {@link GeneralEntityType}, für den die Animation gebraucht wird.
     * @param animationType der {@link AnimationType}, für den die Animation gebraucht wird.
     */
    public Animation getAnimation(GeneralEntityType type, AnimationType animationType) {
        return mappedAnimations.get(type).get(animationType);
    }

    /**
     * Gibt die {@link #mappedAnimations} {@link TreeMap} zurück.
     */
    public TreeMap<GeneralEntityType, TreeMap<AnimationType, Animation>> getMappedAnimations() {
        return mappedAnimations;
    }
}