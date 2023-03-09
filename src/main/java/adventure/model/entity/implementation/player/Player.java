package adventure.model.entity.implementation.player;

import java.awt.Graphics;
import java.util.Collection;
import java.util.TreeMap;

import adventure.AdventureGame;
import adventure.model.Direction;
import adventure.model.animation.Animation;
import adventure.model.animation.type.AnimationType;
import adventure.model.entity.Entity;
import adventure.model.entity.implementation.type.GeneralEntityType;

public class Player extends Entity {

    public static final transient String NAMESPACE = "player";
    public static final transient int SPAWN_ID = 44;

    private final TreeMap<AnimationType, Animation> animations;

    private boolean movingLeft;
    private boolean movingRight;

    public Player(int id, String name, String key) {
        super(id, name, key);

        this.animations = new TreeMap<AnimationType, Animation>();

        Collection<Animation> loadedAnimations = AdventureGame.getInstance().getAnimationManager().getAnimationsSet(GeneralEntityType.PLAYER);

        System.out.println("Loaded animations: " + loadedAnimations.size());
        loadedAnimations.forEach(System.out::println);

        for (Animation animation : loadedAnimations) {
            animations.put(animation.getType(), animation);
        }

        this.currentAnimation = animations.get(AnimationType.IDLE_RIGHT);

        System.out.println("Loaded player animations: ");
        int i = 0;
        for (Animation animation : animations.values()) {
            System.out.println("Index: " + i++ + ", ID: " + animation.getId() + ": " + animation);
        }

    }

    public void setMoving(Direction direction, boolean moving) {
        switch (direction) {
            case LEFT:
                movingLeft = moving;
                break;
            case RIGHT:
                movingRight = moving;
                break;
            default:
                break;
        }
    }

    public boolean isMoving(Direction direction) {
        return switch (direction) {
            case LEFT -> movingLeft;
            case RIGHT -> movingRight;
            default -> false;
        };
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
    }

    @Override
    public void tick() {
        super.tick();
    }
    
}