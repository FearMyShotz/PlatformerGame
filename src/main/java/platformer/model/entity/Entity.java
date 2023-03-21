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

public abstract class Entity extends GameObject implements Rendering {

    public static final transient String NAMESPACE = "entity";
    public static final float GRAVITY = 0.04f * GameWindow.SCALE;

    public boolean switchedDirection;
    public boolean jumping;
    protected boolean moving;
    public boolean movingLeft;
    public boolean movingRight;

    protected boolean died;

    protected Animation currentAnimation;

    protected final TreeMap<AnimationType, Animation> animations;

    protected Direction direction;
    private Direction lastDirection;

    protected Hitbox hitbox;

    protected int frameTick;
    protected int frameIndex;
    protected int frameDelay = 40;

    protected int spikeIndex;

    protected boolean inAir = false;
    protected float airSpeedY;
    protected final float jumpSpeed = -2.25f * GameWindow.SCALE;
    protected float fallSpeedAfterCollision = 0.5f * GameWindow.SCALE;

    protected float walkSpeed;
    
    protected boolean fallSwitch = false;

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

    public void updatePosition() {
        if (died) return;
        // if (!(movingLeft) && !(movingRight)) {
        // if (airSpeed == 0) return;
        // }

        // game.debug("InAir: " + inAir + ", airSpeedY: " + airSpeedY);
        if (!inAir) 
            // game.debug("Moving left: " + movingLeft + ", moving right: " + movingRight);
            if (movingRight == movingLeft) 
                return;

        if (jumping) jump();

        float xSpeed = 0;

        if (movingLeft && !movingRight) {
            // game.debug("Moving left and not right");d
            xSpeed = -walkSpeed;
        }

        if (movingRight && !movingLeft) {
            // game.debug("Moving right and not left");
            xSpeed = walkSpeed;
        }

        if (!LocationHelper.isEntityOnGround(this))
            inAir = true;

        if (inAir) {
            // game.debug("Is in air, air speed: " + airSpeed);
            if (fallSwitch && !(direction == Direction.RIGHT)) fallSwitch = false;

            if (fallSwitch && (LocationHelper.getDistanceToFloorRight(this) <= 3)) {
                fallSwitch = false;
                location.addY(-1);
                hitbox.y -= 1;
            }

            if (LocationHelper.movePossible(hitbox.getXF(), hitbox.getYF() + airSpeedY, hitbox.getWidthF(),
                    hitbox.getHeightF())
            // || LocationHelper.isEntityOnWall(this)
                || fallSwitch
            ) {
                hitbox.y += airSpeedY;
                location.y += airSpeedY;
                airSpeedY += GRAVITY;
                updateX(xSpeed);
            } else {
                // hitbox.y = LocationHelper.getYPosNearLimit(hitbox, airSpeedY);
                if (airSpeedY > 0) {
                    // game.debug("Resetting air position");
                    resetAirPosition();
                } else {
                    airSpeedY = fallSpeedAfterCollision;
                    // if (LocationHelper.isEntityOnGround(this)) {
                    //     game.debug("Resetting air position, reached ground after falling");
                    //     resetAirPosition();
                    // }
                }
                updateX(xSpeed);
            }
        } else {
            // game.debug("Not in air, air speed: " + airSpeed);
            updateX(xSpeed);
            // resetAirPosition();
            if (LocationHelper.isEntityOnWall(this)) {
                // game.debug("Is on wall");
                fixPosition();
            }
        }

        // moving = true;
    }

    public void jump() {
        // game.debug("inAir: " + inAir + ", airSpeedY: " + airSpeedY);
        // game.debug("On ground: " + LocationHelper.isEntityOnGround(this) + ", on wall: "
        //         + LocationHelper.isEntityOnWall(this) + ", distance to floor: "
        //         + LocationHelper.getDistanceToFloor(this));
        if (inAir) return;

        inAir = true;
        airSpeedY = jumpSpeed;
    }

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

    public void updateX(final float xSpeed) {
        if (LocationHelper.movePossible(hitbox.getXF() + xSpeed, hitbox.getYF(), hitbox.getWidthF(),
                hitbox.getHeightF())) {
            // game.debug("Location: " + location + ", hitbox: " + hitbox + ", xSpeed: " +
            // xSpeed);
            hitbox.x += xSpeed;
            location.addX(xSpeed);
            syncHitbox();
        } else {
            // hitbox.x = LocationHelper.getXPosNextToWall(hitbox, xSpeed);
            // resetAirPosition();
            if (LocationHelper.isEntityOnGround(this)) fallSwitch = false;
            if (lastDirection == Direction.RIGHT && inAir) {
                // game.debug("Direction: " + direction + ", in air: " + inAir);
                // game.debug("Collided while moving right and in air");

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

            spikeIndex++;
            if (spikeIndex <= 100) {
                location.addY(0.5f);
                hitbox.y += 0.5f;
            }

            if (spikeIndex >= 250) {
                // ((PlayState) game.getActiveState()).setGameEnded(true);

                game.debug(name + " died");
                game.debug("SPAWN LOC: "+ game.getLevelManager().getCurrentLevel().getSpawnLocation().toString());

                this.location = game.getLevelManager().getCurrentLevel().getSpawnLocation();
                this.hitbox = new Hitbox(location, (int) (32 * GameWindow.SCALE), (int) (32 * GameWindow.SCALE) - 1);
                resetAll();
            }
        }
    }

    public void resetAirPosition() {
        inAir = false;
        airSpeedY = 0;
    }

    public void resetAll() {
        died = false;
        spikeIndex = 0;
        inAir = false;
        airSpeedY = 0;
        fallSwitch = false;
        movingLeft = false;
        movingRight = false;
        direction = Direction.RIGHT;
        lastDirection = Direction.RIGHT;
        currentAnimation = animations.get(AnimationType.IDLE_RIGHT);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(final Direction lastDirection) {
        this.direction = lastDirection;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection(final Direction lastDirection) {
        this.lastDirection = lastDirection;
    }

    public void syncHitbox() {
        hitbox.x = location.x;
        hitbox.y = location.y;
    }

    public void fixPosition() {
        inAir = true;
        airSpeedY = fallSpeedAfterCollision;

        location.addY(-1);
        hitbox.y -= 1;
    }

    @Override
    public void tick() {
        updateFrameTick();
        updatePosition();
        updateAnimation();
        checkSpikes();
    }

    protected void drawHitbox(final Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    protected void drawHitboxOrigin(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int) hitbox.x, (int) hitbox.y, 1, 1);
    }

    protected void drawLocation(final Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect((int) location.x, (int) location.y, 1, 1);
    }

    public void drawDebugInfo(final Graphics g) {
        drawHitbox(g);
        drawHitboxOrigin(g);
        drawLocation(g);
    }

    @Override
    public void render(final Graphics g) {
        // System.out.println("Rendering entity: " + this + " at " + location + " with
        // animation: " + currentAnimation + " and frame: " +
        // currentAnimation.getFrame(frameIndex));

        // System.out.println("Rendering at " + location + ", pixelLocation: " +
        // getPixelLocation());
        g.drawImage(
            currentAnimation.getFrame(frameIndex).getFrameImage(),
            (int) location.getX(),
            (int) location.getY() + (int) (LocationHelper.getDistanceToFloor(this) <= 7 ? LocationHelper.getDistanceToFloor(this) : 0),
            GameWindow.ENTITY_SIZE,
            GameWindow.ENTITY_SIZE,
            null
        );
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(final Location location) {
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

    public void setHitbox(final Hitbox hitbox) {
        this.hitbox = hitbox;
    }
}