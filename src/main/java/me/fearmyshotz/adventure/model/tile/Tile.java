package me.fearmyshotz.adventure.model.tile;

import me.fearmyshotz.adventure.model.GameAsset;
import me.fearmyshotz.adventure.model.ResourceKey;
import me.fearmyshotz.adventure.model.tile.type.TileType;

public class Tile extends GameAsset {

    public static transient final String NAMESPACE = "tile";

    protected TileImage tileImage;

    protected TileType tileType;

    protected boolean collision;

    public Tile(TileType type, TileImage image) {
        super(type.getId(), type.getName(), null);

        this.tileType = type;
        this.tileImage = image;
        this.collision = type.isCollision();

        setKey(new ResourceKey.Builder<Tile>(NAMESPACE, type.getName()).build());
    }

    public Tile(int id, String name, String description) {
        super(id, name, description);
    }

    static class Builder {

        private TileType type;

        private TileImage image;

        public Builder setType(TileType type) {
            this.type = type;
            return this;
        }

        public Builder setImage(TileImage image) {
            this.image = image;
            return this;
        }

        public Tile build() {
            return new Tile(type, image);
        }
    }
}