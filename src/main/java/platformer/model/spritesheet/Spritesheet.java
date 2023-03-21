package platformer.model.spritesheet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import platformer.PlatformerGame;
import platformer.util.Pair;

/**
 * Ein Spritesheet fasst mehrere Bilder in einem einzigen Bild zusammen. Dies wird auch als Texturatlas oder texture batch bezeichnet.
 * 
 * @author Jamil B.
 * @see <a href="https://en.wikipedia.org/wiki/Texture_atlas">Wikipedia: Texture atlas</a>
 */
public final class Spritesheet {
    
    /**
     * Der Typ des Spritesheets.
     */
    private SpritesheetType type;

    /**
     * Der Stream, der das Bild des Spritesheets enthält.
     */
    private InputStream stream;

    /**
     * Das Bild des Spritesheets.
     */
    private BufferedImage spriteImage;

    /**
     * Ein zweidimensionales Array, das alle Bilder des Spritesheets enthält.
     */
    private BufferedImage[][] imageGrid;

    /**
     * Die Breite eines einzelnen Bildes.
     */
    private int assetWidth;

    /**
     * Die Höhe eines einzelnen Bildes.
     */
    private int assetHeight;

    /**
     * Die Breite des Spritesheets in Bildern.
     */
    private int gridWidth;

    /**
     * Die Höhe des Spritesheets in Bildern.
     */
    private int gridHeight;

    /**
     * Erstellt ein neues Spritesheet.
     * @param path der Pfad zum Bild des Spritesheets.
     * @param assetRatio die Breite und Höhe eines einzelnen Bildes.
     */
    public Spritesheet(String path, Pair<Integer, Integer> assetRatio) {
        this(null, path, assetRatio.getLeft(), assetRatio.getRight());
    }

    /**
     * Erstellt ein neues Spritesheet.
     * @param path der Pfad zum Bild des Spritesheets.
     * @param assetWidth die Breite eines einzelnen Bildes.
     * @param assetHeight die Höhe eines einzelnen Bildes.
     */
    public Spritesheet(SpritesheetType type, String path, Pair<Integer, Integer> assetRatio) {
        this(type, path, assetRatio.getLeft(), assetRatio.getRight());
    }

    /**
     * Erstellt ein neues Spritesheet.
     * @param path der Pfad zum Bild des Spritesheets.
     * @param assetWidth die Breite eines einzelnen Bildes.
     * @param assetHeight die Höhe eines einzelnen Bildes.
     */
    public Spritesheet(SpritesheetType type, String path, int assetWidth, int assetHeight) {
        this.type = type;
        this.stream = Spritesheet.class.getResourceAsStream("/" + path);

        PlatformerGame.getInstance().debug("Loading spritesheet from " + path);

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

    /**
     * Füllt das zweidimensionale Array mit den Bildern des Spritesheets.
     */
    private void fillImageGrid() {
        imageGrid = new BufferedImage[gridWidth][gridHeight];

        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                imageGrid[x][y] = spriteImage.getSubimage(x * assetWidth, y * assetHeight, assetWidth, assetHeight);
            }
        }
    }

    /**
     * Gibt den Typ des Spritesheets zurück.
     * 
     * @return der Typ des Spritesheets.
     */
    public SpritesheetType getType() {
        return type;
    }

    /**
     * Gibt ein Bild des Spritesheets an einer bestimmten Position zurück.
     * 
     * @param x die X-Koordinate des Bildes.
     * @param y die Y-Koordinate des Bildes.
     * @return das Bild an der Position.
     */
    public BufferedImage getSprite(int x, int y) {
        return imageGrid[x][y];
    }

    /**
     * Gibt ein Bild des Spritesheets an einer bestimmten Position zurück.
     * 
     * @param pair die Koordinaten des Bildes.
     * @return das Bild an der Position.
     */
    public BufferedImage getSprite(Pair<Integer, Integer> pair) {
        return getSprite(pair.getLeft(), pair.getRight());
    }

    /**
     * Gibt das zweidimensionale Array mit allen Bildern des Spritesheets zurück.
     * 
     * @return das zweidimensionale Array mit allen Bildern des Spritesheets.
     */
    public BufferedImage[][] getImageGrid() {
        return imageGrid;
    }

    /**
     * Gibt die Breite eines einzelnen Bildes zurück.
     * 
     * @return die Breite eines einzelnen Bildes.
     */
    public int getAssetWidth() {
        return assetWidth;
    }

    /**
     * Gibt die Höhe eines einzelnen Bildes zurück.
     * 
     * @return die Höhe eines einzelnen Bildes.
     */
    public int getAssetHeight() {
        return assetHeight;
    }

    /**
     * Gibt die Breite des Spritesheets in Bildern zurück.
     * 
     * @return die Breite des Spritesheets in Bildern.
     */
    public int getGridWidth() {
        return gridWidth;
    }

    /**
     * Gibt die Höhe des Spritesheets in Bildern zurück.
     * 
     * @return die Höhe des Spritesheets in Bildern.
     */
    public int getGridHeight() {
        return gridHeight;
    }

    /**
     * Gibt den Stream zurück, der das Bild des Spritesheets enthält.
     * 
     * @return der Stream, der das Bild des Spritesheets enthält.
     */
    public InputStream getStream() {
        return stream;
    }

    /**
     * Gibt das Bild des Spritesheets zurück.
     * 
     * @return das Bild des Spritesheets.
     */
    public BufferedImage getSpriteImage() {
        return spriteImage;
    }

    /**
     * Gibt eine String-Repräsentation des Spritesheets zurück.
     * 
     * @return eine String-Repräsentation des Spritesheets.
     */
    @Override
    public String toString() {
        return "Spritesheet ["
            + "type=" + type
            + ", assetWidth=" + assetWidth
            + ", assetHeight=" + assetHeight
            + ", gridWidth=" + gridWidth
            + ", gridHeight=" + gridHeight + "]";
    }
}