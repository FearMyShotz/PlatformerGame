package platformer.model.tile.type;

import platformer.model.ResourceKey;
import platformer.model.tile.Tile;
import platformer.util.Identifiable;
import platformer.util.Pair;

/**
 * Eine Aufzählung, welche alle möglichen Typen von Tiles enthält.
 * 
 * @author Jamil B.
 * @see Identifiable
 * @see Tile
 */
public enum TileType implements Identifiable {
    
    AIR(00, "Luft", false, 9, 5),

    GRASS_TOP(Integer.valueOf("01"), "Gras", true, 1, 0),
    GRASS_TOP_LEFT(Integer.valueOf("02"), "Gras (Links)", true, 0, 0),
    GRASS_TOP_RIGHT(Integer.valueOf("03"), "Gras (Rechts)", true, 2, 0),

    GRASS_SINGLE_TOP(Integer.valueOf("04"), "Gras (Einzeln, Oben)", true, 3, 0),
    GRASS_SINGLE(Integer.valueOf("33"), "Gras (Einzeln)", true, 3, 3),

    GRASS_BOTTOM_LEFT(Integer.valueOf("05"), "Gras (Einzeln, Links)", true, 0, 3),
    GRASS_BOTTOM_RIGHT(Integer.valueOf("06"), "Gras (Einzeln, Rechts)", true, 2, 3),

    DIRT(Integer.valueOf("07"), "Erde", true, 1, 1),
    DIRT_SINGLE(Integer.valueOf("08"), "Erde (Einzeln)", true, 3, 1),
    DIRT_SINGLE_LEFT(Integer.valueOf("09"), "Erde (Einzeln, Links)", true, 0, 1),
    DIRT_SINGLE_RIGHT(Integer.valueOf("10"), "Erde (Einzeln, Rechts)", true, 2, 1),

    DIRT_BOTTOM(11, "Erde (Unten)", true, 1, 2),
    DIRT_BOTTOM_SINGLE(12, "Erde (Unten, Einzeln)", true, 3, 2),
    DIRT_BOTTOM_LEFT(13, "Erde (Unten, Links)", true, 0, 2),
    DIRT_BOTTOM_RIGHT(14, "Erde (Unten, Rechts)", true, 2, 2),

    LOG(15, "Baumstamm", true, 7, 0),
    LOG_SINGLE(16, "Baumstamm (Einzeln)", true, 9, 0),
    LOG_LEFT(17, "Baumstamm (Links)", true, 6, 0),
    LOG_RIGHT(18, "Baumstamm (Rechts)", true, 8, 0),

    LADDER(19, "Leiter", false, 9, 2),
    LADDER_SINGLE(20, "Leiter (Einzeln)", false, 8, 1),
    LADDER_TOP(21, "Leiter (Oben)", false, 9, 1),
    LADDER_BOTTOM(22, "Leiter (Unten)", false, 9, 3),

    SPIKES(23, "Stacheln", true, 6, 1),

    CHEST_SILVER(24, "Silberne Truhe", false, 6, 2),
    CHEST_SILVER_OPEN(25, "Silberne Truhe (Geöffnet)", false, 7, 2),

    CHEST_GOLD(26, "Goldene Truhe", false, 6, 3),
    CHEST_GOLD_OPEN(27, "Goldene Truhe (Geöffnet)", false, 7, 3),

    KEY_SILVER(28, "Silberner Schlüssel", false, 8, 2),

    KEY_GOLD(29, "Goldener Schlüssel", false, 8, 3),

    COIN(30, "Goldmünze", false, 0, 4),

    STAR(31, "Stern", false, 6, 4),

    HEART(32, "Lebenspunkt", false, 0, 5),

    BOX(34, "Kiste", true, 7, 1),

    ;

    private int id;
    private String name;

    private boolean collision;

    private Pair<Integer, Integer> textureCoords;

    TileType(int id, String name, boolean collision, int x, int y) {
        this.id = id;
        this.name = name;
        this.collision = collision;
        this.textureCoords = new Pair<Integer, Integer>(x, y);
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
    public ResourceKey<Tile> getIdentifier() {
        return new ResourceKey<Tile>(Tile.NAMESPACE, name().toLowerCase());
    }

    public boolean isCollision() {
        return collision;
    }

    public Pair<Integer, Integer> getTextureCoords() {
        return textureCoords;
    }

    public int getTextureX() {
        return textureCoords.getLeft();
    }

    public int getTextureY() {
        return textureCoords.getRight();
    }

    // public static TileType getById(int id) {
    //     for (TileType type : values()) {
    //         if (type.getId() == id) {
    //             return type;
    //         }
    //     }
    //     return null;
    // }

    // public static TileType of(String type) {
    //     for (TileType tileType : values()) {
    //         if (tileType.name().equalsIgnoreCase(type)) {
    //             return tileType;
    //         }
    //     }
    //     return null;
    // }
    
    @Override
    public String toString() {
        return "TileType[" 
                + "id=" + id 
                + ", name=" + name 
                + ", collision=" + collision 
                + ", textureCoords=" + textureCoords 
                + "]";
    }

    static final class Special {

        
    }
}