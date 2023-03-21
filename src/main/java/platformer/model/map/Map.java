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

public class Map extends GameAsset implements Rendering {

    private final MapLoader loader;

    private Tile[][] tiles;

    private final ArrayList<Cloud> clouds;

    // private BufferedImage backgroundImage;

    public Map(TileManager manager, String filePath) {
        super(0, "Map", "map", "map");

        this.tiles = new Tile[TILES_Y][TILES_X];

        this.loader = new MapLoader(filePath);

        this.tiles = loader.getTiles();

        this.clouds = new ArrayList<Cloud>();

        // setBackgroundImage();
        loadClouds();
    }

    // private void setBackgroundImage() {
    //     try {
    //         // this.backgroundImage = ImageIO.read(getClass().getResourceAsStream("/background" + number + ".png"));
    //         this.backgroundImage = ImageIO.read(getClass().getResourceAsStream("/clouds" + ".png"));
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public void loadClouds() {
        // Spawn clouds at random positions between 0 and GameWindow.WIDTH and 0 and GameWindow.HEIGHT - 600
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

    public void renderBackground(Graphics g) {
        g.setColor(new Color(15, 135, 255));
        g.fillRect(0, 0, GameWindow.WIDTH, GameWindow.HEIGHT);

        // g.drawImage(backgroundImage, 0, 0, GameWindow.WIDTH, GameWindow.HEIGHT, null);
    }

    public void renderClouds(Graphics g) {
        clouds.stream().forEach(cloud -> cloud.render(g));
    }

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

    public int getWidth() {
        return tiles[0].length;
    }

    public int getHeight() {
        return tiles.length;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    public Tile getTile(double x, double y) {
        return tiles[(int) y][(int) x];
    }

    public Tile getTile(Location location) {
        return tiles[(int) location.getY()][(int) location.getX()];
    }
}