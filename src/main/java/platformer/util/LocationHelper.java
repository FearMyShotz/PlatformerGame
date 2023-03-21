package platformer.util;

import platformer.PlatformerGame;
import platformer.model.entity.Entity;
import platformer.model.tile.Tile;
import platformer.window.GameWindow;

/**
 * Eine Hilfsklasse für {@link Location}s.
 * 
 * @author Jamil B.
 */
public class LocationHelper {
    
    /**
     * Die maximale X-Koordinate, die eine {@link Location} haben kann.
     */
    private static final int MAX_X = PlatformerGame.getInstance().getLevelManager().getCurrentLevel().getMap().getWidth() * GameWindow.TILE_SIZE;

    /**
     * Prüft, ob eine {@link Location} innerhalb der gegebeben Grenzen liegt.
     * 
     * @param location die Location
     * @param width die Breite
     * @param height die Höhe
     * @return true, wenn die Location innerhalb der Grenzen liegt, sonst false
     */
    public static boolean isLocationInBounds(Location location, int width, int height) {
        return location.getX() >= 0 && location.getX() <= width && location.getY() >= 0 && location.getY() <= height;
    }

    /**
     * Gibt die X-Koordinate einer Hitbox zur nächsten Wand zurück.
     * 
     * @param hitbox die Hitbox
     * @param xSpeed die Geschwindigkeit in X-Richtung
     * @return die X-Koordinate zur nächsten Wand
     */
    public static float getXPosNextToWall(Hitbox hitbox, float xSpeed) {
        return getXPosNextToWall(hitbox.getXF(), hitbox.getYF(), hitbox.getWidthF(), hitbox.getHeightF(), xSpeed);
    }

    /**
     * Gibt die X-Koordinate einer Position zur nächsten Wand zurück.
     * 
     * @param location die Position
     * @param xSpeed die Geschwindigkeit in X-Richtung
     * @return die X-Koordinate zur nächsten Wand
     */
    private static float getXPosNextToWall(float x, float y, float width, float height, float xSpeed) {
        int currentTile = (int) (x / GameWindow.TILE_SIZE);

        if (xSpeed > 0) {
            int tileX = currentTile * GameWindow.TILE_SIZE;
            // int offsetX = (int) (GameWindow.TILE_SIZE - width);

            return tileX;
        } else {
            // AdventureGame.getInstance().debug("Current tile: " + currentTile * GameWindow.TILE_SIZE);
            return currentTile * GameWindow.TILE_SIZE;
        }
    }

    /**
     * Gibt die Y-Koordinate einer Hitbox zum Limit zurück.
     * 
     * @param hitbox die Hitbox
     * @param ySpeed die Geschwindigkeit in Y-Richtung
     * @return die Y-Koordinate zum Limit
     */
    public static float getYPosNearLimit(Hitbox hitbox, float airSpeed) {
        return getYPosNearLimit(hitbox.getYF(), hitbox.getHeightF(), airSpeed);
    }

    /**
     * Gibt die Y-Koordinate einer Position zum Limit zurück.
     * 
     * @param location die Position
     * @param ySpeed die Geschwindigkeit in Y-Richtung
     * @return die Y-Koordinate zum Limit
     */
    private static float getYPosNearLimit(float y, float height, float airSpeed) {
        int currentTile = (int) (y / GameWindow.TILE_SIZE);

        if (airSpeed > 0) {
            int tileY = currentTile * GameWindow.TILE_SIZE;
            int offsetY = (int) (GameWindow.TILE_SIZE - height);

            return tileY + offsetY - 1;
        } else { return currentTile * GameWindow.TILE_SIZE; }
    }

    /**
     * Prüft, ob eine Bewegung an eine bestimmte Position möglich ist.
     * 
     * @param x die X-Koordinate der Position
     * @param y die Y-Koordinate der Position
     * @param width die Breite der Hitbox
     * @param height die Höhe der Hitbox
     * @return true, wenn die Bewegung möglich ist, sonst false
     */
    public static boolean movePossible(float x, float y, float width, float height) {
        return (
            !isTileSolid(x, y) &&
            !isTileSolid(x + width, y) &&
            !isTileSolid(x, y + height) &&
            !isTileSolid(x + width, y + height)
        );
    }

    /**
     * Prüft, ob eine Bewegung an eine bestimmte Position möglich ist.
     * 
     * @param hitbox die Hitbox
     * @return true, wenn die Bewegung möglich ist, sonst false
     */
    public static boolean movePossible(Hitbox hitbox) {
        return movePossible(hitbox.getXF(), hitbox.getYF(), hitbox.getWidthF(), hitbox.getHeightF());
    }

    /**
     * Prüft, ob eine {@link Entity} auf dem Boden steht.
     * 
     * @param entity die Entity
     * @return true, wenn die Entity auf dem Boden steht, sonst false
     */
    public static boolean isEntityOnGround(Entity entity) {
        Hitbox h = entity.getHitbox();
        return (
            isTileSolid(h.getXF(), h.getYF() + h.getHeightF() + 1) &&
            isTileSolid(h.getXF() + h.getWidthF(), h.getYF() + h.getHeightF() + 1)
        );
    }

    /**
     * Prüft, ob eine {@link Entity} an einer Wand steht.
     * 
     * @param entity die Entity
     * @return true, wenn die Entity an einer Wand steht, sonst false
     */
    public static boolean isEntityOnWall(Entity entity) {
        Hitbox h = entity.getHitbox();
        return (
            isTileSolid(h.getXF() - 1, h.getYF()) ||
            isTileSolid(h.getXF() + h.getWidthF() + 1, h.getYF())
        );
    }

    /**
     * Gibt die Distanz einer @{link Entity} zum Boden zurück.
     * 
     * @param entity die Entity
     * @return die Distanz zum Boden
     */
    public static float getDistanceToFloor(Entity entity) {
        Hitbox h = entity.getHitbox();

        float distanceOrigin = 0;

        while (!isTileSolid(h.getXF(), h.getYF() + h.getHeightF() + distanceOrigin)) {
            distanceOrigin++;
        }

        return distanceOrigin;
    }

    /**
     * Gibt die Distanz einer @{link Entity} zum Boden, von der rechten Grenze zurück.
     * 
     * @param entity die Entity
     * @return die Distanz zum Boden rechts
     */
    public static float getDistanceToFloorRight(Entity entity) {
        Hitbox h = entity.getHitbox();

        float distance = 0;

        while (!isTileSolid(h.getXF() + h.getWidthF() + distance, h.getYF() + h.getHeightF())) {
            distance++;
        }

        return distance;
    }

    /**
     * Gibt die Distanz einer @{link Entity} zur Oberfläche zurück.
     * 
     * @param entity die Entity
     * @return die Distanz zur Oberfläche
     */
    public static float getDistanceToSurface(Entity entity) {
        Hitbox h = entity.getHitbox();

        float distance = 0;

        while (isTileSolid(h.getXF(), h.getYF() + h.getHeightF() - distance)) {
            distance++;
        }

        return distance;
    }

    /**
     * Gibt die Distanz einer @{link Entity} zur Wand zurück.
     * 
     * @param entity die Entity
     * @param xSpeed die Geschwindigkeit in X-Richtung
     * @return die Distanz zur Wand
     */
    public static float getDistanceToWall(Entity entity, float xSpeed) {
        Hitbox h = entity.getHitbox();

        float distance = 0;

        if (xSpeed > 0) {

            while (!isTileSolid(h.getXF() + h.getWidthF() + distance, h.getYF())) {
                distance++;
            }

            return distance;
        } else {

            while (!isTileSolid(h.getXF() - distance, h.getYF())) {
                distance++;
            }

            return distance;
        }
    }

    /**
     * Prüft, ob eine {@link Tile} eine Kollision hat.
     */
    public static boolean isTileSolid(float x, float y) {
        if (x < 0 || x >= MAX_X) return true; 
        if (y < 0 || y >= GameWindow.HEIGHT) return true; 

        return getTile(x, y).hasCollision();
    }

    /**
     * Prüft, ob eine {@link Entity} auf einem Spike steht.
     * 
     * @param entity die Entity
     * @return true, wenn die Entity auf einem Spike steht, sonst false
     */
    public static boolean isEntityOnSpike(Entity entity) {
        Hitbox h = entity.getHitbox();
        return (
            getTile(h.getXF(), h.getYF() + h.getHeightF() + 5).getId() == 23 ||
            getTile(h.getXF() + h.getWidthF(), h.getYF() + h.getHeightF() + 5).getId() == 23
        );
    }

    /**
     * Gibt die {@link Tile} an einer bestimmten Position zurück.
     * 
     * @param x die X-Koordinate der Position
     * @param y die Y-Koordinate der Position
     * @return die Tile
     */
    private static Tile getTile(float x, float y) {
        return PlatformerGame.getInstance().getLevelManager().getCurrentLevel().getMap().getTile((int) (x / GameWindow.TILE_SIZE), (int) (y / GameWindow.TILE_SIZE));
    }
}