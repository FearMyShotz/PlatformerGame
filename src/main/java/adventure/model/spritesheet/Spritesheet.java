package adventure.model.spritesheet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import adventure.util.Pair;

public class Spritesheet {
    
    private SpritesheetType type;

    private InputStream stream;

    private BufferedImage spriteImage;

    private BufferedImage[][] imageGrid;

    private int assetWidth;
    private int assetHeight;

    private int gridWidth;
    private int gridHeight;

    public Spritesheet(String path, Pair<Integer, Integer> assetRatio) {
        this(null, path, assetRatio.getLeft(), assetRatio.getRight());
    }

    public Spritesheet(SpritesheetType type, String path, Pair<Integer, Integer> assetRatio) {
        this(type, path, assetRatio.getLeft(), assetRatio.getRight());
    }

    public Spritesheet(SpritesheetType type, String path, int assetWidth, int assetHeight) {
        this.type = type;
        this.stream = getClass().getResourceAsStream("/" + path);
        this.assetWidth = assetWidth;
        this.assetHeight = assetHeight;

        try {
            this.spriteImage = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public SpritesheetType getType() {
        return type;
    }

    public BufferedImage getSprite(int x, int y) {
        return imageGrid[x][y];
    }

    public BufferedImage getSprite(Pair<Integer, Integer> pair) {
        return getSprite(pair.getLeft(), pair.getRight());
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

    @Override
    public String toString() {
        return "Spritesheet ["
            + "type=" + type
            // + ", stream=" + stream
            // + ", spriteImage=" + spriteImage
            // + ", imageGrid=" + imageGrid
            + ", assetWidth=" + assetWidth
            + ", assetHeight=" + assetHeight
            + ", gridWidth=" + gridWidth
            + ", gridHeight=" + gridHeight + "]";
    }
}