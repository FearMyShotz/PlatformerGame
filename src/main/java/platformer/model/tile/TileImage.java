package platformer.model.tile;

import java.awt.image.BufferedImage;

import platformer.util.Pair;

public class TileImage {
    
    private int x;
    private int y;

    private BufferedImage bufferedImage;

    public TileImage(int x, int y, BufferedImage tileImage) {
        this.x = x;
        this.y = y;
        this.bufferedImage = tileImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    @Override
    public String toString() {
        return "TileImage "
        + "[x=" + x
        + ", y=" + y
        + "]";
    }

    static class Builder {
        
        private int x;
        private int y;

        private BufferedImage tileImage;

        public Builder setX(int x) {
            this.x = x;
            return this;
        }

        public Builder setY(int y) {
            this.y = y;
            return this;
        }

        public Builder setCoords(Pair<Integer, Integer> coords) {
            this.x = coords.getLeft();
            this.y = coords.getRight();
            return this;
        }

        public Builder setTileImage(BufferedImage tileImage) {
            this.tileImage = tileImage;
            return this;
        }

        public TileImage build() {
            return new TileImage(x, y, tileImage);
        }
    }
}