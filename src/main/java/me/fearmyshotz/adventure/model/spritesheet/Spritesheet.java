package me.fearmyshotz.adventure.model.spritesheet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import me.fearmyshotz.adventure.util.Pair;

public class Spritesheet {
    
    protected InputStream stream;

    protected BufferedImage spriteImage;

    protected BufferedImage[][] imageGrid;

    protected int assetWidth;
    protected int assetHeight;

    protected int gridWidth;
    protected int gridHeight;

    public Spritesheet(String path, Pair<Integer, Integer> assetRatio) {
        this(path, assetRatio.getLeft(), assetRatio.getRight());
    }

    public Spritesheet(String path, int assetWidth, int assetHeight) {
        this.stream = getClass().getResourceAsStream(path);
        this.assetWidth = assetWidth;
        this.assetHeight = assetHeight;

        try {
            this.spriteImage = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.gridWidth = spriteImage.getWidth() / assetWidth;
        this.gridHeight = spriteImage.getHeight() / assetHeight;

        fillImageGrid();
    }

    private void fillImageGrid() {
        imageGrid = new BufferedImage[gridWidth][gridHeight];

        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                imageGrid[x][y] = spriteImage.getSubimage(x * assetWidth, y * assetHeight, assetWidth, assetHeight);
            }
        }
    }

    public BufferedImage getSprite(int x, int y) {
        return imageGrid[x][y];
    }

    public BufferedImage[][] getImageGrid() {
        return imageGrid;
    }

    public int getAssetWidth() {
        return assetWidth;
    }

    public int getAssetHeight() {
        return assetHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public InputStream getStream() {
        return stream;
    }

    public BufferedImage getSpriteImage() {
        return spriteImage;
    }
}