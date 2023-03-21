package platformer.model.entity.implementation.player;

import java.awt.Graphics;
import java.util.Collection;

import platformer.PlatformerGame;
import platformer.model.animation.Animation;
import platformer.model.animation.type.AnimationType;
import platformer.model.entity.Entity;
import platformer.model.entity.implementation.type.GeneralEntityType;

public class Player extends Entity {

    public static final transient String NAMESPACE = "player";
    public static final transient int SPAWN_ID = 44;

    public Player(int id, String name, String key) {
        super(id, name, key);

        Collection<Animation> loadedAnimations = PlatformerGame.getInstance().getAnimationManager().getAnimationsSet(GeneralEntityType.PLAYER);

        game.debug("Loaded animations: " + loadedAnimations.size());

        for (Animation animation : loadedAnimations) {
            animations.put(animation.getType(), animation);
        }

        this.currentAnimation = animations.get(AnimationType.IDLE_RIGHT);

        // System.out.println("Loaded player animations: ");
        // int i = 0;
        // for (Animation animation : animations.values()) {
        //     System.out.println("Index: " + i++ + ", ID: " + animation.getId() + ": " + animation);
        // }

    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        // if (AdventureGame.getInstance().isDebug()) super.drawDebugInfo(g);
    }

    @Override
    public void tick() {
        super.tick();
    }
    
}