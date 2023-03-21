package platformer.model.tile;

import platformer.model.GameObject;
import platformer.model.tile.type.TileType;

public class Tile extends GameObject {

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

    public boolean hasCollision() {
        return collision;
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Tiles besitzen keine Spiellogik");
    }

    @Override
    public String toString() {
        return "Tile ["
        + "key=" + getKey()
        + ", location=" + location
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