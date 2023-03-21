package platformer.model.animation.type;

import java.util.stream.Stream;

import platformer.model.Direction;
import platformer.model.ResourceKey;
import platformer.model.animation.Animation;
import platformer.util.Identifiable;

/**
 * Eine Aufzählung unterschiedlicher Animationstypen, die für Entities verwendet werden können.
 * 
 * @author Jamil B.
 * @see Identifiable
 */
public enum AnimationType implements Identifiable {

    IDLE_LEFT(1, "Nach links sehen", Direction.LEFT),
    IDLE_RIGHT(2, "Nach rechts sehen", Direction.RIGHT),

    WALK_LEFT(3, "Nach links gehen", Direction.LEFT),
    WALK_RIGHT(4, "Nach rechts gehen", Direction.RIGHT),

    HIT_LEFT(5, "Von rechts getroffen", Direction.LEFT),
    HIT_RIGHT(6, "Von links getroffen", Direction.RIGHT),

    JUMP_LEFT(7, "Nach links springen", Direction.LEFT),
    JUMP_RIGHT(8, "Nach rechts springen", Direction.RIGHT),

    ATTACK_LEFT(9, "Nach links angreifen", Direction.LEFT),
    ATTACK_RIGHT(10, "Nach rechts angreifen", Direction.RIGHT),

    DEATH_LEFT(11, "Nach links fallen", Direction.LEFT),
    DEATH_RIGHT(12, "Nach rechts fallen", Direction.RIGHT),

    ;

    private int id;
    private String name;
    private Direction direction;

    AnimationType(int id, String name, Direction direction) {
        this.id = id;
        this.name = name;
        this.direction = direction;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ResourceKey<Animation> getIdentifier() {
        return new ResourceKey.Builder<Animation>(Animation.NAMESPACE, name().toLowerCase()).build();
    }

    /**
     * Gibt die Richtung zurück, in die der Animationstyp zeigt.
     * 
     * @return Die Richtung, in die der Animationstyp zeigt.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Gibt den Animationstyp zurück, der den angegebenen Namen enthält.
     * 
     * @param name Der Name, der den Animationstypen enthält.
     * @return Der Animationstyp, der den angegebenen Namen enthält.
     */
    public static AnimationType of(String name) {
        return Stream.of(values())
            .filter(type -> name.contains(type.name().toLowerCase()))
            .findFirst()
            .orElse(null);
    }
}