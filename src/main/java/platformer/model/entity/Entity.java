package platformer.model.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.TreeMap;

import platformer.model.Direction;
import platformer.model.GameObject;
import platformer.model.animation.Animation;
import platformer.model.animation.type.AnimationType;
import platformer.tick.Rendering;
import platformer.util.Hitbox;
import platformer.util.Location;
import platformer.util.LocationHelper;
import platformer.window.GameWindow;

/**
 * Die Entity-Klasse ist die Oberklasse aller Entities. Sie stellt die grundlegenden Methoden und Variablen zur Verfügung.
 * 
 * @author Jamil B.
 * @see GameObject
 * @see Rendering
 */
public abstract class Entity extends GameObject implements Rendering {

    /**
     * Der Namespace der Entity, welcher für den {@link ResourceKey} verwendet wird.
     */
    public static final transient String NAMESPACE = "entity";

    /**
     * Die Schwerkraft, die auf die Entity wirkt. Multipliziert mit {@link GameWindow#SCALE}.
     */
    public static final float GRAVITY = 0.04f * GameWindow.SCALE;

    /**
     * Ein Wahrheitswert, der angibt, ob die Taste für den Bewegungsrichtungswechsel gedrückt wurde.
     */
    public boolean switchedDirection;

    /**
     * Ein Wahrheitswert, der angibt, ob die Entity springt.
     */
    public boolean jumping;

    /**
     * Ein Wahrheitswert, der angibt, ob die Entity sich bewegt.
     */
    protected boolean moving;

    /**
     * Ein Wahrheitswert, der angibt, ob die Entity sich nach links bewegt.
     */
    public boolean movingLeft;

    /**
     * Ein Wahrheitswert, der angibt, ob die Entity sich nach rechts bewegt.
     */
    public boolean movingRight;

    /**
     * Ein Wahrheitswert, der angibt, ob die Entity stirbt.
     */
    protected boolean died;

    /**
     * Die aktuelle Animation der Entity.
     */
    protected Animation currentAnimation;

    /**
     * Die Animations-Map der Entity.
     */
    protected final TreeMap<AnimationType, Animation> animations;

    /**
     * Die aktuelle Richtung der Entity.
     */
    protected Direction direction;

    /**
     * Die letzte Richtung der Entity.
     */
    private Direction lastDirection;

    /**
     * Die aktuelle Hitbox der Entity.
     */
    protected Hitbox hitbox;

    /**
     * Die aktuelle Anzahl an Ticks der Animation.
     */
    protected int frameTick;

    /**
     * Der aktuelle Frame-Index der Animation.
     */
    protected int frameIndex;

    /**
     * Die aktuelle Anzahl an Ticks, die zwischen den Frames vergehen.
     */
    protected int frameDelay = 40;

    /**
     * Die Anzahl an Ticks, die vergangen sind, seitdem die Entity in Stacheln gefallen ist.
     */
    protected int spikeTick;

    /**
     * Ein Wahrheitswert, der angibt, ob die Entity in der Luft ist.
     */
    protected boolean inAir = false;

    /**
     * Die aktuelle Y-Geschwindigkeit der Entity.
     */
    protected float airSpeedY;

    /**
     * Die Sprunggeschwindigkeit der Entity. Multipliziert mit {@link GameWindow#SCALE}.
     */
    protected final float jumpSpeed = -2.25f * GameWindow.SCALE;

    /**
     * Die Geschwindigkeit, mit der die Entity nach einer Kollision abprallt. Multipliziert mit {@link GameWindow#SCALE}.
     */
    protected float fallSpeedAfterCollision = 0.5f * GameWindow.SCALE;

    /**
     * Die aktuelle X-Geschwindigkeit der Entity.
     */
    protected float walkSpeed;
    
    /**
     * Ein Wahrheitswert, welcher für das Fallen einen Sonderfall aktivieren kann.
     */
    protected boolean fallSwitch = false;

    /**
     * Erstellt eine neue Entity.
     * 
     * @param id Die ID der Entity.
     * @param name Der Name der Entity.
     * @param key Der ResourceKey der Entity.
     */
    public Entity(final int id, final String name, final String key) {
        super(id, name, NAMESPACE, key);

        this.animations = new TreeMap<AnimationType, Animation>();
        this.direction = Direction.RIGHT;
        this.lastDirection = Direction.RIGHT;
        this.moving = false;
        this.died = false;
        this.location = new Location(0, 0);
        this.hitbox = new Hitbox(location, 32, 32);
        this.walkSpeed = (float) GameWindow.SCALE - 0.2f;
    }

    /**
     * Aktualisiert die verstrichenen Ticks der aktuellen Animation.
     */
    protected void updateFrameTick() {
        frameTick++;

        if (frameTick >= frameDelay) {
            frameTick = 0;
            if (currentAnimation.getFrameAmount() != 1) frameIndex++;
            if (frameIndex >= currentAnimation.getFrameAmount()) {
                frameIndex = 0;
            }
        } else {
            frameTick++;
        }
    }

    /**
     * Aktualisiert die Position der Entity.
     */
    public void updatePosition() {
        if (died) return;
        if (!inAir)
            if (movingRight == movingLeft) 
                return;

        if (jumping) jump();

        float xSpeed = 0;

        if (movingLeft && !movingRight) {
            xSpeed = -walkSpeed;
        }

        if (movingRight && !movingLeft) {
            xSpeed = walkSpeed;
        }

        if (!LocationHelper.isEntityOnGround(this))
            inAir = true;

        if (inAir) {
            if (fallSwitch && !(direction == Direction.RIGHT)) fallSwitch = false;

            if (fallSwitch && (LocationHelper.getDistanceToFloorRight(this) <= 3)) {
                fallSwitch = false;
                location.addY(-1);
                hitbox.y -= 1;
            }

            if (LocationHelper.movePossible(hitbox.getXF(), hitbox.getYF() + airSpeedY, hitbox.getWidthF(),
                    hitbox.getHeightF())
                || fallSwitch
            ) {
                hitbox.y += airSpeedY;
                location.y += airSpeedY;
                airSpeedY += GRAVITY;
                updateX(xSpeed);
            } else {
                if (airSpeedY > 0) {
                    resetAirPosition();
                } else {
                    airSpeedY = fallSpeedAfterCollision;
                }
                updateX(xSpeed);
            }
        } else {
            updateX(xSpeed);
            if (LocationHelper.isEntityOnWall(this)) {
                fixPosition();
            }
        }
    }

    /**
     * Aktualisiert die Animation der Entity.
     */
    public void updateAnimation() {
        if (died) return;

        if (movingLeft && !movingRight) {
            currentAnimation = animations.get(AnimationType.WALK_LEFT);
            return;
        } else if (movingRight && !movingLeft) {
            currentAnimation = animations.get(AnimationType.WALK_RIGHT);
            return;
        }
        if (movingLeft == movingRight) {
            currentAnimation = direction == Direction.LEFT ? animations.get(AnimationType.IDLE_LEFT)
                    : animations.get(AnimationType.IDLE_RIGHT);
        }
    }

    /**
     * Aktualisiert die X-Position der Entity.
     * 
     * @param xSpeed Die X-Geschwindigkeit der Entity.
     */
    public void updateX(final float xSpeed) {
        if (LocationHelper.movePossible(hitbox.getXF() + xSpeed, hitbox.getYF(), hitbox.getWidthF(), hitbox.getHeightF())) {
            hitbox.x += xSpeed;
            location.addX(xSpeed);
            syncHitbox();
        } else {
            if (LocationHelper.isEntityOnGround(this)) fallSwitch = false;
            if (lastDirection == Direction.RIGHT && inAir) {
                if (LocationHelper.getDistanceToFloor(this) > 1) {
                    fallSwitch = true;
                    inAir = true;
                }
            }
            // Behebt einen Bug, bei dem der Spieler nach einem Sprung nicht mehr auf der
            // Ebene landet
            if (LocationHelper.getDistanceToSurface(this) > 0) {
                location.addY(-LocationHelper.getDistanceToSurface(this));
                hitbox.y -= LocationHelper.getDistanceToSurface(this);
            }
        }
    }

    /**
     * Versucht, die Position der Entity zu beheben.
     */
    public void fixPosition() {
        inAir = true;
        airSpeedY = fallSpeedAfterCollision;

        location.addY(-1);
        hitbox.y -= 1;
    }

    /**
     * Lässt die Entity springen.
     */
    public void jump() {
        if (inAir) return;

        inAir = true;
        airSpeedY = jumpSpeed;
    }

    /**
     * Prüft, ob die Entity auf einem Spike steht.
     */
    public void checkSpikes() {
        if (LocationHelper.isEntityOnSpike(this) || died) {
            if (!died) {
                died = true;

                this.frameIndex = 0;
                this.currentAnimation = switch (direction) {
                    case LEFT -> animations.get(AnimationType.DEATH_LEFT);
                    case RIGHT -> animations.get(AnimationType.DEATH_RIGHT);
                    default -> null;
                };
            }

            spikeTick++;
            if (spikeTick <= 100) {
                location.addY(0.5f);
                hitbox.y += 0.5f;
            }

            if (spikeTick >= 250) {
                // ((PlayState) game.getActiveState()).setGameEnded(true);

                game.debug(name + " died");
                game.debug("SPAWN LOC: "+ game.getLevelManager().getCurrentLevel().getSpawnLocation().toString());

                this.location = game.getLevelManager().getCurrentLevel().getSpawnLocation();
                this.hitbox = new Hitbox(location, (int) (32 * GameWindow.SCALE), (int) (32 * GameWindow.SCALE) - 1);
                resetAll();
            }
        }
    }

    /**
     * Setzt die Position der Entity in der Luft zurück.
     */
    public void resetAirPosition() {
        inAir = false;
        airSpeedY = 0;
    }

    /**
     * Setzt alle wichtigen Variablen der Entity zurück.
     */
    public void resetAll() {
        died = false;
        spikeTick = 0;
        inAir = false;
        airSpeedY = 0;
        fallSwitch = false;
        movingLeft = false;
        movingRight = false;
        direction = Direction.RIGHT;
        lastDirection = Direction.RIGHT;
        currentAnimation = animations.get(AnimationType.IDLE_RIGHT);
    }

    /**
     * Gibt die Richtung der Entity zurück.
     * 
     * @return Die Richtung der Entity.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Setzt die Richtung der Entity.
     * 
     * @param lastDirection Die neue Richtung der Entity.
     */
    public void setDirection(final Direction lastDirection) {
        this.direction = lastDirection;
    }

    /**
     * Gibt die letzte Richtung der Entity zurück.
     * 
     * @return Die letzte Richtung der Entity.
     */
    public Direction getLastDirection() {
        return lastDirection;
    }

    /**
     * Setzt die letzte Richtung der Entity.
     * 
     * @param lastDirection Die neue letzte Richtung der Entity.
     */
    public void setLastDirection(final Direction lastDirection) {
        this.lastDirection = lastDirection;
    }

    /**
     * Setzt die Attribute der Hitbox auf die der Location.
     */
    public void syncHitbox() {
        hitbox.x = location.x;
        hitbox.y = location.y;
    }

    /**
     * Führt die Spiellogik der Entity aus.
     */
    @Override
    public void tick() {
        updateFrameTick();
        updatePosition();
        updateAnimation();
        checkSpikes();
    }

    /**
     * Zeichnet die Hitbox der Entity. Für Debugging.
     */
    protected void drawHitbox(final Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    /**
     * Zeichnet den Ursprung der Hitbox der Entity. Für Debugging.
     */
    protected void drawHitboxOrigin(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int) hitbox.x, (int) hitbox.y, 1, 1);
    }

    /**
     * Zeichnet die Position der Entity. Für Debugging.
     */
    protected void drawLocation(final Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect((int) location.x, (int) location.y, 1, 1);
    }

    /**
     * Zeichnet Debug-Informationen der Entity.
     */
    public void drawDebugInfo(final Graphics g) {
        drawHitbox(g);
        drawHitboxOrigin(g);
        drawLocation(g);
    }

    /**
     * Zeichnet die Entity und behebt die Position, falls nötig.
     */
    @Override
    public void render(final Graphics g) {
        g.drawImage(
            currentAnimation.getFrame(frameIndex).getFrameImage(),
            (int) location.getX(),
            (int) location.getY() + (int) (LocationHelper.getDistanceToFloor(this) <= 7 ? LocationHelper.getDistanceToFloor(this) : 0),
            GameWindow.ENTITY_SIZE,
            GameWindow.ENTITY_SIZE,
            null
        );
    }

    /**
     * Gibt die Location der Entity zurück.
     * 
     * @return Die Location der Entity.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Setzt die Location der Entity.
     * 
     * @param location Die neue Location der Entity.
     */
    public void setLocation(final Location location) {
        this.location = location;
    }

    /**
     * Gibt die Hitbox der Entity zurück.
     * 
     * @return Die Hitbox der Entity.
     */
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * Setzt die Hitbox der Entity.
     * 
     * @param hitbox Die neue Hitbox der Entity.
     */
    public void setHitbox(final Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * Gibt die Entity als String zurück.
     */
    @Override
    public String toString() {
        return "Entity [moving=" + moving + ", currentAnimation=" + currentAnimation + ", frameTick=" + frameTick
                + ", frameIndex=" + frameIndex + ", frameDelay=" + frameDelay + ", walkSpeed=" + walkSpeed + "]";
    }
}