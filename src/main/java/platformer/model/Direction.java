package platformer.model;

import platformer.model.animation.Animation;
import platformer.util.Identifiable;

/**
 * EIne Direction ist eine Richtung.
 */
public enum Direction implements Identifiable {
    LEFT(0, "Links"),
    RIGHT(1, "Rechts"),
    UP(2, "Hoch"),
    DOWN(3, "Herunter")

    ;

    private int id;
    private String name;

    Direction(int id, String name) {
        this.id = id;
        this.name = name;
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

    public static Direction of(String name) {
        for (Direction direction : values()) {
            if (direction.name().equalsIgnoreCase(name)) return direction;
        }
        return null;
    }
}