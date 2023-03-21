package platformer.model.tile;

import platformer.model.GameObject;
import platformer.model.tile.type.TileType;

/**
 * Ein Tile ist ein {@link GameObject}, welches eine Spielkachel darstellt und in der Spielwelt platziert werden kann.
 * 
 * @author Jamil B.
 */
public class Tile extends GameObject {

    /**
     * Der Namespace, unter dem die Tiles registriert werden.
     */
    public static transient final String NAMESPACE = "tile";

    /**
     * Die TileImage, die dieses Tile darstellt.
     */
    protected TileImage tileImage;

    /**
     * Der TileType, der dieses Tile darstellt.
     */
    protected TileType tileType;

    /**
     * Wahrheitswert, ob dieses Tile eine Kollision besitzt.
     */
    protected boolean collision;

    /**
     * Erstellt ein neues Tile.
     * 
     * @param type der TileType, der dieses Tile darstellt.
     * @param image die TileImage, die dieses Tile darstellt.
     */
    public Tile(TileType type, TileImage image) {
        super(type.getId(), type.getName(), NAMESPACE, type.name().toLowerCase());

        this.tileType = type;
        this.tileImage = image;
        this.collision = type.isCollision();
    }

    /**
     * Gibt den TileType dieses Tiles zurück.
     * 
     * @return der TileType dieses Tiles.
     */
    public TileType getTileType() {
        return tileType;
    }

    /**
     * Gibt das TileImage dieses Tiles zurück.
     * 
     * @return das TileImage dieses Tiles.
     */
    public TileImage getTileImage() {
        return tileImage;
    }

    /**
     * Gibt zurück, ob dieses Tile eine Kollision besitzt.
     * 
     * @return {@code true}, wenn dieses Tile eine Kollision besitzt, ansonsten {@code false}.
     */
    public boolean hasCollision() {
        return collision;
    }

    /**
     * Tiles sind zwar lokalisierbar, sie besitzen aber keine Spiellogik.
     * 
     * @throws UnsupportedOperationException wenn diese Methode aufgerufen wird.
     * @see GameObject#location
     * @see Ticking#tick()
     */
    @Override
    public void tick() {
        throw new UnsupportedOperationException("Tiles besitzen keine Spiellogik");
    }

    /**
     * Gibt eine String-Repräsentation dieses Tiles zurück.
     * 
     * @return eine String-Repräsentation dieses Tiles.
     */
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

    /**
     * Builder-Klasse für Tiles.
     * 
     * @author Jamil B.
     */
    static class Builder {

        /**
         * Die TileType, die dieses Tile darstellt.
         */
        private TileType type;

        /**
         * Die TileImage, die dieses Tile darstellt.
         */
        private TileImage image;

        /**
         * Setzt den TileType dieses Tiles.
         * 
         * @param type TileType dieses Tiles
         * @return diese Builder-Instanz
         */
        public Builder setType(TileType type) {
            this.type = type;
            return this;
        }

        /**
         * Setzt das TileImage dieses Tiles.
         * 
         * @param image TileImage dieses Tiles
         * @return diese Builder-Instanz
         */
        public Builder setImage(TileImage image) {
            this.image = image;
            return this;
        }

        /**
         * Erstellt ein neues Tile aus den gesetzten Werten.
         * 
         * @return ein neues Tile
         */
        public Tile build() {
            return new Tile(type, image);
        }
    }
}