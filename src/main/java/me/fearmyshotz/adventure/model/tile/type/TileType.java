package me.fearmyshotz.adventure.model.tile.type;

import me.fearmyshotz.adventure.util.Identifiable;

public enum TileType implements Identifiable {
    
    GRASS_TOP(0, "Gras", true),
    GRASS_TOP_LEFT(1, "Gras (Links)", true),
    GRASS_TOP_RIGHT(2, "Gras (Rechts)", true),

    GRASS_SINGLE(3, "Gras (Einzeln)", true),

    GRASS_BOTTOM_LEFT(4, "Gras (Einzeln, Links)", true),
    GRASS_BOTTOM_RIGHT(5, "Gras (Einzeln, Rechts)", true),

    DIRT(6, "Erde", true),
    DIRT_SINGLE(7, "Erde (Einzeln)", true),
    DIRT_SINGLE_LEFT(8, "Erde (Einzeln, Links)", true),
    DIRT_SINGLE_RIGHT(9, "Erde (Einzeln, Rechts)", true),

    DIRT_BOTTOM(10, "Erde (Unten)", true),
    DIRT_BOTTOM_SINGLE(11, "Erde (Unten, Einzeln)", true),
    DIRT_BOTTOM_LEFT(12, "Erde (Unten, Links)", true),
    DIRT_BOTTOM_RIGHT(13, "Erde (Unten, Rechts)", true),

    STONE(14, "Stein", true),

    LOG(15, "Baumstamm", true),
    LOG_MULTI(16, "Baumstamm (Mehrfach)", true),

    LADDER(17, "Leiter", false),
    LADDER_MULTI(18, "Leiter (Mehrfach)", false),

    SPIKES(19, "Stacheln", true),

    CHEST_SILVER(20, "Silberne Truhe", false),
    CHEST_SILVER_OPEN(21, "Silberne Truhe (Geöffnet)", false),

    CHEST_GOLD(22, "Goldene Truhe", false),
    CHEST_GOLD_OPEN(23, "Goldene Truhe (Geöffnet)", false),

    KEY_SILVER(24, "Silberner Schlüssel", false),

    KEY_GOLD(25, "Goldener Schlüssel", false),

    COIN(26, "Goldmünze", false),

    STAR(27, "Stern", false),

    HEART(28, "Lebenspunkt", false),

    ;

    private int id;
    private String name;

    private boolean collision;

    TileType(int id, String name, boolean collision) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isCollision() {
        return collision;
    }

    public static TileType getById(int id) {
        for (TileType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public static TileType of(String type) {
        for (TileType tileType : values()) {
            if (tileType.name().equalsIgnoreCase(type)) {
                return tileType;
            }
        }
        return null;
    }


}