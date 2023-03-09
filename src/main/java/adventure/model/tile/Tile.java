package adventure.model.tile;

import adventure.model.GameAsset;
import adventure.model.tile.type.TileType;

public class Tile extends GameAsset {

    public static transient final String NAMESPACE = "tile";

    protected TileImage tileImage;

    protected TileType tileType;

    protected boolean collision;

    public Tile(TileType type, TileImage image) {
        super(type.getId(), type.getName(), NAMESPACE, type.name().toLowerCase());

        this.tileType = type;
        this.tileImage = image;
        this.collision = type.isCollision();
    }

    public TileType getTileType() {
        return tileType;
    }

    public TileImage getTileImage() {
        return tileImage;
    }

    @Override
    public String toString() {
        return "Tile ["
        + "key=" + getKey()
        + ", tileImage=" + tileImage
        + ", tileType=" + tileType
        + ", collision=" + collision
        + "]";
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