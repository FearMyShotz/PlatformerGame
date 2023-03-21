package platformer.model.tile;

import java.awt.image.BufferedImage;

import platformer.util.Pair;

/**
 * Ein TileImage ist ein Bild, welches ein Tile darstellt.
 * 
 * @author Jamil B.
 */
public class TileImage {
    
    /**
     * Die X-Koordinate des Tiles.
     */
    private int x;

    /**
     * Die Y-Koordinate des Tiles.
     */
    private int y;

    /**
     * Das BufferedImage, welches das Tile darstellt.
     */
    private BufferedImage bufferedImage;

    /**
     * Erstellt ein neues TileImage.
     * 
     * @param x die X-Koordinate des Tiles.
     * @param y die Y-Koordinate des Tiles.
     * @param tileImage das BufferedImage, welches das Tile darstellt.
     */
    public TileImage(int x, int y, BufferedImage tileImage) {
        this.x = x;
        this.y = y;
        this.bufferedImage = tileImage;
    }

    /**
     * Gibt die X-Koordinate des Tiles zurück.
     * 
     * @return die X-Koordinate des Tiles.
     */
    public int getX() {
        return x;
    }

    /**
     * Gibt die Y-Koordinate des Tiles zurück.
     * 
     * @return die Y-Koordinate des Tiles.
     */
    public int getY() {
        return y;
    }

    /**
     * Gibt das BufferedImage, welches das Tile darstellt, zurück.
     * 
     * @return das BufferedImage, welches das Tile darstellt.
     */
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    /**
     * Gibt eine String-Repräsentation dieses TileImages zurück.
     */
    @Override
    public String toString() {
        return "TileImage "
        + "[x=" + x
        + ", y=" + y
        + "]";
    }

    /**
     * Builder-Klasse für TileImages.
     * 
     * @author Jamil B.
     */
    static class Builder {
        
        /**
         * Die X-Koordinate des Tiles.
         */
        private int x;

        /**
         * Die Y-Koordinate des Tiles.
         */
        private int y;

        /**
         * Das BufferedImage, welches das Tile darstellt.
         */
        private BufferedImage tileImage;

        /**
         * Setzt die X-Koordinate des Tiles.
         * 
         * @param x die X-Koordinate des Tiles.
         * @return diese Builder-Instanz.
         */
        public Builder setX(int x) {
            this.x = x;
            return this;
        }

        /**
         * Setzt die Y-Koordinate des Tiles.
         * 
         * @param y die Y-Koordinate des Tiles.
         * @return diese Builder-Instanz.
         */
        public Builder setY(int y) {
            this.y = y;
            return this;
        }

        /**
         * Setzt die Koordinaten des Tiles.
         * 
         * @param coords die Koordinaten des Tiles.
         * @return diese Builder-Instanz.
         */
        public Builder setCoords(Pair<Integer, Integer> coords) {
            this.x = coords.getLeft();
            this.y = coords.getRight();
            return this;
        }

        /**
         * Setzt das BufferedImage, welches das Tile darstellt.
         * 
         * @param tileImage das BufferedImage, welches das Tile darstellt.
         * @return diese Builder-Instanz.
         */
        public Builder setTileImage(BufferedImage tileImage) {
            this.tileImage = tileImage;
            return this;
        }

        /**
         * Erstellt ein neues TileImage aus den gesetzten Werten.
         * 
         * @return ein neues TileImage aus den gesetzten Werten.
         */
        public TileImage build() {
            return new TileImage(x, y, tileImage);
        }
    }
}