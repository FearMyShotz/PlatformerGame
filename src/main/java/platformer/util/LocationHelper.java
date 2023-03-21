package platformer.util;

import platformer.PlatformerGame;
import platformer.model.entity.Entity;
import platformer.model.tile.Tile;
import platformer.window.GameWindow;

public class LocationHelper {
    
    private static final int MAX_X = PlatformerGame.getInstance().getLevelManager().getCurrentLevel().getMap().getWidth() * GameWindow.TILE_SIZE;

    public static boolean isLocationInBounds(Location location, int width, int height) {
        return location.getX() >= 0 && location.getX() <= width && location.getY() >= 0 && location.getY() <= height;
    }

    public static float getXPosNextToWall(Hitbox hitbox, float xSpeed) {
        return getXPosNextToWall(hitbox.getXF(), hitbox.getYF(), hitbox.getWidthF(), hitbox.getHeightF(), xSpeed);
    }

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

    public static float getYPosNearLimit(Hitbox hitbox, float airSpeed) {
        return getYPosNearLimit(hitbox.getYF(), hitbox.getHeightF(), airSpeed);
    }

    private static float getYPosNearLimit(float y, float height, float airSpeed) {
        int currentTile = (int) (y / GameWindow.TILE_SIZE);

        if (airSpeed > 0) {
            int tileY = currentTile * GameWindow.TILE_SIZE;
            int offsetY = (int) (GameWindow.TILE_SIZE - height);

            return tileY + offsetY - 1;
        } else { return currentTile * GameWindow.TILE_SIZE; }
    }

    public static boolean movePossible(float x, float y, float width, float height) {
        return (
            !isTileSolid(x, y) &&
            !isTileSolid(x + width, y) &&
            !isTileSolid(x, y + height) &&
            !isTileSolid(x + width, y + height)
        );
    }

    public static boolean movePossible(Hitbox hitbox) {
        return movePossible(hitbox.getXF(), hitbox.getYF(), hitbox.getWidthF(), hitbox.getHeightF());
    }

    public static boolean isEntityOnGround(Entity entity) {
        Hitbox h = entity.getHitbox();
        return (
            isTileSolid(h.getXF(), h.getYF() + h.getHeightF() + 1) &&
            isTileSolid(h.getXF() + h.getWidthF(), h.getYF() + h.getHeightF() + 1)
        );
    }

    public static boolean isEntityOnWall(Entity entity) {
        // AdventureGame.getInstance().debug("Checking if entity is on wall");
        Hitbox h = entity.getHitbox();
        return (
            isTileSolid(h.getXF() - 1, h.getYF()) ||
            isTileSolid(h.getXF() + h.getWidthF() + 1, h.getYF())
        );
    }

    public static float getDistanceToFloor(Entity entity) {
        Hitbox h = entity.getHitbox();

        float distanceOrigin = 0;

        while (!isTileSolid(h.getXF(), h.getYF() + h.getHeightF() + distanceOrigin)) {
            distanceOrigin++;
        }

        return distanceOrigin;
    }

    public static float getDistanceToFloorRight(Entity entity) {
        Hitbox h = entity.getHitbox();

        float distance = 0;

        while (!isTileSolid(h.getXF() + h.getWidthF() + distance, h.getYF() + h.getHeightF())) {
            distance++;
        }

        return distance;
    }

    public static float getDistanceToSurface(Entity entity) {
        Hitbox h = entity.getHitbox();

        float distance = 0;

        // get the distance to the surface (maybe the entity is inside the surface)
        while (isTileSolid(h.getXF(), h.getYF() + h.getHeightF() - distance)) {
            distance++;
        }

        // AdventureGame.getInstance().debug("Returning istance to surface: " + distance);

        return distance;
    }

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

    public static boolean isTileSolid(float x, float y) {
        // AdventureGame.getInstance().debug("Checking tile at " + x + ", " + y);

        // AdventureGame.getInstance().debug("MAX_X: " + MAX_X);

        if (x < 0 || x >= MAX_X) return true; 
        if (y < 0 || y >= GameWindow.HEIGHT) return true; 

        return getTile(x, y).hasCollision();
    }

    public static boolean isEntityOnSpike(Entity entity) {
        Hitbox h = entity.getHitbox();
        return (
            getTile(h.getXF(), h.getYF() + h.getHeightF() + 5).getId() == 23 ||
            getTile(h.getXF() + h.getWidthF(), h.getYF() + h.getHeightF() + 5).getId() == 23
        );
    }

    private static Tile getTile(float x, float y) {
        return PlatformerGame.getInstance().getLevelManager().getCurrentLevel().getMap().getTile((int) (x / GameWindow.TILE_SIZE), (int) (y / GameWindow.TILE_SIZE));
    }

}