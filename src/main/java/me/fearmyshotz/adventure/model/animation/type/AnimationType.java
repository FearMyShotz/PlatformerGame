package me.fearmyshotz.adventure.model.animation.type;

import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import me.fearmyshotz.adventure.util.Identifiable;

public enum AnimationType implements Identifiable {

    IDLE(0, "Idle", false),

    WALK(1, "Gehen", true),
    WALK_LEFT(2, "Nach links gehen", true, Directonal.LEFT),
    WALK_RIGHT(3, "Nach rechts gehen", true, Directonal.RIGHT),

    JUMP(4, "Springen", true),
    JUMP_LEFT(5, "Nach links springen", true, Directonal.LEFT),
    JUMP_RIGHT(6, "Nach rechts springen", true, Directonal.RIGHT),


    ATTACK(7, "Angreifen", true),
    ATTACK_LEFT(8, "Nach links angreifen", true, Directonal.LEFT),
    ATTACK_RIGHT(9, "Nach rechts angreifen", true, Directonal.RIGHT),

    HIT(10, "Getroffen", true),
    HIT_LEFT(11, "Von rechts getroffen", true, Directonal.LEFT),
    HIT_RIGHT(12, "Von links getroffen", true, Directonal.RIGHT),

    DEATH(13, "Sterben", true),

    ;

    // Testet ob direction richtig angegeben wurde
    private final Predicate<Directonal[]> testValid = d -> d == null || d.length == 1;

    private int id;
    private String name;
    private boolean isDirectional;
    private @Nullable Directonal direction;

    AnimationType(int id, String name, boolean directional, Directonal... direction) {
        this.id = id;
        this.name = name;
        this.isDirectional = directional;

        if (!testValid.test(direction)) throw new IllegalArgumentException("Direction must be null or have a length of 1");
        
        this.direction = direction[0];
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isDirectional() {
        return isDirectional && direction != null;
    }

    static enum Directonal {
        LEFT(0, "Left"),
        RIGHT(1, "Right"),

        ;

        private int id;
        private String name;

        Directonal(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}