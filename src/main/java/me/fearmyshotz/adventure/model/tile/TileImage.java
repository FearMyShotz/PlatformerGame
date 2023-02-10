package me.fearmyshotz.adventure.model.tile;

import java.awt.image.BufferedImage;

public class TileImage {
    
    private int x;
    private int y;

    private BufferedImage tileImage;

    public TileImage(int x, int y, BufferedImage tileImage) {
        this.x = x;
        this.y = y;
        this.tileImage = tileImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getTileImage() {
        return tileImage;
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

        public Builder setTileImage(BufferedImage tileImage) {
            this.tileImage = tileImage;
            return this;
        }

        public TileImage build() {
            return new TileImage(x, y, tileImage);
        }
    }
}