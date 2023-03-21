package platformer.model.animation.loader.implementation;

import java.util.Set;
import java.util.HashSet;
import java.util.function.Function;

import platformer.model.animation.Animation;
import platformer.model.animation.type.AnimationType;
import platformer.model.entity.implementation.type.GeneralEntityType;
import platformer.model.spritesheet.Spritesheet;
import platformer.model.spritesheet.loader.SpritesheetLoader;

/**
 * Eine spezialisierte {@link SpritesheetLoader}-Implementierung, die für das Laden von Entity-Animationen verwendet wird.
 * 
 * @author Jamil B.
 * @see Animation
 * @see SpritesheetLoader
 */
public class EntityAnimationLoader extends SpritesheetLoader<Animation> {

    /**
     * Die Animationen, die geladen werden sollen.
     */
    Animation idle, walk, hit, jump, attack, death;

    /**
     * Erstellt einen neuen EntityAnimationLoader.
     * 
     * @param sheet Das Spritesheet, die für das Laden der Animationen verwendet werden soll.
     * @param fileName Der Name der Datei, die für das Laden der Animationen verwendet werden soll.
     */
    public EntityAnimationLoader(Spritesheet sheet, String fileName) {
        super(new HashSet<Animation>(), sheet, fileName);
    }

    /**
     * Lädt die Animationen, die für die Entity geladen werden sollen.
     * 
     * Führt @see {@link #load(String[])} aus.
     * 
     * @return Die Animationen, die für die Entity geladen werden sollen.
     */
    @Override
    public HashSet<Animation> load() {
        return switch (fileName) {
            case "player_left_32x32.png": case "player_right_32x32.png":
            case "archer_left_32x32.png": case "archer_right_32x32.png":
            case "knight_left_32x32.png": case "knight_right_32x32.png":
            case "mage_left_32x32.png": case "mage_right_32x32.png":
                yield load(fileName.split("_"));

            default: yield null;
        };
    }

    /**
     * Lädt die Animationen, die für die Entity geladen werden sollen.
     * 
     * Ruft die entsprechende Builder-Methode für jede Animation auf.
     * 
     * @param entityOptions Die Optionen, die für das Laden der Animationen verwendet werden sollen.
     * @return Die Animationen, die für die Entity geladen werden sollen.
     */
    public HashSet<Animation> load(String[] entityOptions) {
        String entityName = entityOptions[0], direction = entityOptions[1];

        Function<String, String> key = (animationName) -> entityName + "_" + animationName + "_" + direction;
        Function<String, AnimationType> type = (animationName) -> AnimationType.of(animationName + "_" + direction);

        // System.out.println("Loading animations for " + entityName + "...");

        GeneralEntityType entityType = entityName == "player" ? GeneralEntityType.PLAYER : GeneralEntityType.NPC;

        idle = new Animation.Builder()
                    .setId(0)
                    .setKey(key.apply("idle"))
                    .setType(type.apply("idle"))
                    .setEntityType(entityType)
                    .setSpritesheet(spritesheet)
                    .setFrameAmount(4)
                .build();

        walk = new Animation.Builder()
                    .setId(1)
                    .setKey(key.apply("walk"))
                    .setType(type.apply("walk"))
                    .setEntityType(entityType)
                    .setSpritesheet(spritesheet)
                    .setFrameAmount(4)
                .build();

        hit = new Animation.Builder()
                    .setId(2)
                    .setKey(key.apply("hit"))
                    .setType(type.apply("hit"))
                    .setEntityType(entityType)
                    .setSpritesheet(spritesheet)
                    .setFrameAmount(2)
                .build();
        
        jump = new Animation.Builder()
                    .setId(3)
                    .setKey(key.apply("jump"))
                    .setType(type.apply("jump"))
                    .setEntityType(entityType)
                    .setSpritesheet(spritesheet)
                    .setFrameAmount(4)
                .build();

        attack = new Animation.Builder()
                    .setId(4)
                    .setKey(key.apply("attack"))
                    .setType(type.apply("attack"))
                    .setEntityType(entityType)
                    .setSpritesheet(spritesheet)
                    .setFrameAmount(4)
                .build();

        death = new Animation.Builder()
                    .setId(5)
                    .setKey(key.apply("death"))
                    .setType(type.apply("death"))
                    .setEntityType(entityType)
                    .setSpritesheet(spritesheet)
                    .setFrameAmount(1)
                .build();

        return new HashSet<Animation>(Set.of(idle, walk, hit, jump, attack, death));
    }
}