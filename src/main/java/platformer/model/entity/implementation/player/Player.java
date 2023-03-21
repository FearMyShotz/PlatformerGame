package platformer.model.entity.implementation.player;

import java.awt.Graphics;
import java.util.Collection;

import platformer.PlatformerGame;
import platformer.model.animation.Animation;
import platformer.model.animation.type.AnimationType;
import platformer.model.entity.Entity;
import platformer.model.entity.implementation.type.GeneralEntityType;

/**
 * Die Klasse {@link Player} erweitert die Klasse {@link Entity} und stellt einen Spieler dar.
 * 
 * @author Jamil B.
 * @see Entity
 */
public class Player extends Entity {

    /**
     * Der Namespace des Spielers, welcher für den {@link ResourceKey} verwendet wird.
     */
    public static final transient String NAMESPACE = "player";

    /**
     * Die ID des Spielers, die für die Erstellung des Spielers verwendet wird.
     */
    public static final transient int SPAWN_ID = 44;

    /**
     * Erstellt einen neuen Spieler.
     * 
     * @param id Die ID des Spielers.
     * @param name Der Name des Spielers.
     * @param key Der Schlüssel des Spielers.
     */
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

    /**
     * Stellt den Spieler dar.
     * Ruft die Methode {@link #render(Graphics)} der Oberklasse {@link Entity} auf.
     */
    @Override
    public void render(Graphics g) {
        super.render(g);

        // if (AdventureGame.getInstance().isDebug()) super.drawDebugInfo(g);
    }

    /**
     * Aktualisiert den Spieler.
     * Ruft die Methode {@link #tick()} der Oberklasse {@link Entity} auf.
     */
    @Override
    public void tick() {
        super.tick();
    }
}