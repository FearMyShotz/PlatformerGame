package adventure.model.animation.type;

import java.util.stream.Stream;

import adventure.model.Direction;
import adventure.model.ResourceKey;
import adventure.model.animation.Animation;
import adventure.util.Identifiable;

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

    public Direction getDirection() {
        return direction;
    }

    public static AnimationType of(String name) {
        return Stream.of(values())
            .filter(type -> name.contains(type.name().toLowerCase()))
            .findFirst()
            .orElse(null);
    }
}