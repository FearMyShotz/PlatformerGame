package platformer.model.map;

import static platformer.window.GameWindow.TILES_X;
import static platformer.window.GameWindow.TILES_Y;
import static platformer.window.GameWindow.TILE_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import platformer.model.GameAsset;
import platformer.model.map.loader.MapLoader;
import platformer.model.tile.Tile;
import platformer.model.tile.TileManager;
import platformer.tick.Rendering;
import platformer.util.Location;
import platformer.window.GameWindow;

/**
 * Ein {@link GameAsset}, das eine Map darstellt.
 * 
 * @author Jamil B.
 */
public class Map extends GameAsset implements Rendering {

    /**
     * Der MapLoader, der die Map lädt.
     */
    private final MapLoader loader;

    /**
     * Die Tiles in einem zweidimensionalen Array.
     */
    private Tile[][] tiles;

    /**
     * Die Liste der Wolken.
     */
    private final ArrayList<Cloud> clouds;

    /**
     * Erstellt eine neue Map.
     * @param manager der TileManager, der die Tiles verwaltet.
     * @param filePath der Pfad zur Datei, die geladen werden soll.
     */
    public Map(TileManager manager, String filePath) {
        super(0, "Map", "map", "map");

        this.tiles = new Tile[TILES_Y][TILES_X];

        this.loader = new MapLoader(filePath);

        this.tiles = loader.getTiles();

        this.clouds = new ArrayList<Cloud>();

        loadClouds();
    }

    /**
     * Lädt die Wolken mit zufälligen Positionen.
     */
    public void loadClouds() {
        for (int i = 1; i < 7; i++) {
            int x = (int) (Math.random() * (GameWindow.WIDTH - 100));
            int y = (int) (Math.random() * (GameWindow.HEIGHT - 600));

            Location loc = new Location(x, y);

            clouds.stream().forEach(cloud -> {
                if (cloud.getLocation().isNear(loc, 200)) {
                    loc.setX((int) (Math.random() * (GameWindow.WIDTH - 100)));
                    loc.setY((int) (Math.random() * (GameWindow.HEIGHT - 600)));
                }
            });

            BufferedImage cloudImage = null;

            try {
                cloudImage = ImageIO.read(getClass().getResourceAsStream("/cloud" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Cloud cloud = new Cloud(i, loc, cloudImage);
            clouds.add(cloud);

            if (i == 6) i = 0;

            if (clouds.size() == 10) break;
        }
    }

    /**
     * Rendert den Hintergrund.
     * 
     * @param g das Graphics-Objekt, mit dem gerendert wird.
     */
    public void renderBackground(Graphics g) {
        g.setColor(new Color(15, 135, 255));
        g.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);
    }

    /**
     * Rendert die Wolken.
     * 
     * @param g das Graphics-Objekt, mit dem gerendert wird.
     */
    public void renderClouds(Graphics g) {
        clouds.stream().forEach(cloud -> cloud.render(g));
    }

    /**
     * Rendert die Map an sich.
     * 
     * @param g das Graphics-Objekt, mit dem gerendert wird.
     */
    @Override
    public void render(Graphics g) {
        renderBackground(g);
        renderClouds(g);

        for (int tileY = 0; tileY < TILES_Y; tileY++) {
            for (int tileX = 0; tileX < TILES_X; tileX++) {
                int x = TILE_SIZE * tileX;
                int y = TILE_SIZE * tileY;

                Tile currentTile = tiles[tileY][tileX];
                g.drawImage(currentTile.getTileImage().getBufferedImage(), x, y, TILE_SIZE, TILE_SIZE, null);
                
                // g.drawRect(x, y, GameWindow.TILE_SIZE, GameWindow.TILE_SIZE);
            }
        }
    }

    /**
     * Gibt die Breite der Map zurück.
     * 
     * @return die Breite der Map.
     */
    public int getWidth() {
        return tiles[0].length;
    }

    /**
     * Gibt die Höhe der Map zurück.
     * 
     * @return die Höhe der Map.
     */
    public int getHeight() {
        return tiles.length;
    }

    /**
     * Gibt die Tiles der Map in einem zweidimensionalen Array zurück.
     * 
     * @return die Tiles der Map.
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Gibt die Tile an der angegebenen Position zurück.
     * 
     * @param x die X-Koordinate der Position.
     * @param y die Y-Koordinate der Position.
     * @return die Tile an der angegebenen Position.
     */
    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    /**
     * Gibt die Tile an der angegebenen Position zurück.
     * 
     * @param x die X-Koordinate der Position.
     * @param y die Y-Koordinate der Position.
     * @return die Tile an der angegebenen Position.
     */
    public Tile getTile(double x, double y) {
        return tiles[(int) y][(int) x];
    }

    /**
     * Gibt die Tile an der angegebenen Position zurück.
     * 
     * @param location die Position.
     * @return die Tile an der angegebenen Position.
     */
    public Tile getTile(Location location) {
        return tiles[(int) location.getY()][(int) location.getX()];
    }
}