package adventure.model.entity;

import java.awt.Graphics;

import adventure.model.GameObject;
import adventure.model.animation.Animation;
import adventure.tick.Rendering;
import adventure.util.Hitbox;
import adventure.util.Location;
import adventure.window.GameWindow;

public abstract class Entity extends GameObject implements Rendering {

    public static final transient String NAMESPACE = "entity";

    protected boolean moving;

    protected Animation currentAnimation;

    protected Hitbox hitbox;

    protected int frameTick;
    protected int frameIndex;
    protected int frameDelay = 40;

    protected float walkSpeed;

    public Entity(int id, String name, String key) {
        super(id, name, NAMESPACE, key);

        this.moving = false;
        this.hitbox = new Hitbox();
        this.location = new Location(0, 0);
        this.walkSpeed = 1.0f;
    }

    protected void updateFrameTick() {
        frameTick++;

        if (frameTick >= frameDelay) {
            frameTick = 0;
            frameIndex++;
            if (frameIndex >= currentAnimation.getFrameAmount()) {
                frameIndex = 0;
            }
        } else {
            frameTick++;
        }
    }

    @Override
    public void tick() {
        updateFrameTick();
    }

    @Override
    public void render(Graphics graphics) {
        // System.out.println("Rendering entity: " + this + " at " + location + " with animation: " + currentAnimation + " and frame: " + currentAnimation.getFrame(frameIndex));
        
        // System.out.println("Rendering at " + location + ", pixelLocation: " + getPixelLocation());
        graphics.drawImage(
            currentAnimation.getFrame(frameIndex).getFrameImage(),
            location.getX() * GameWindow.TILE_SIZE,
            location.getY() * GameWindow.TILE_SIZE - GameWindow.ENTITY_SIZE_INCREMENT,
            GameWindow.ENTITY_SIZE + GameWindow.ENTITY_SIZE_INCREMENT,
            GameWindow.ENTITY_SIZE + GameWindow.ENTITY_SIZE_INCREMENT,
            null
        );
    }

    public void keyPress(int keyCode) {
        
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Entity [moving=" + moving + ", currentAnimation=" + currentAnimation + ", frameTick=" + frameTick
                + ", frameIndex=" + frameIndex + ", frameDelay=" + frameDelay + ", walkSpeed=" + walkSpeed + "]";
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    } 
}