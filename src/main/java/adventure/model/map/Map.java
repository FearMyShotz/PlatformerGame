package adventure.model.map;

import java.awt.Graphics;

import adventure.model.GameAsset;
import adventure.model.map.loader.MapLoader;
import adventure.model.tile.Tile;
import adventure.model.tile.TileManager;
import adventure.tick.Rendering;
import adventure.util.Location;
import adventure.window.GameWindow;

public class Map extends GameAsset implements Rendering {

    private final MapLoader loader;

    private Tile[][] tiles;

    public Map(TileManager manager, String filePath) {
        super(0, "Map", "map", "map");

        this.tiles = new Tile[GameWindow.TILES_Y][GameWindow.TILES_X];

        this.loader = new MapLoader(filePath);

        this.tiles = loader.getTiles();
    }

    @Override
    public void render(Graphics graphics) {
        for (int tileY = 0; tileY < GameWindow.TILES_Y; tileY++) {
            for (int tileX = 0; tileX < tiles[0].length; tileX++) {
                int x = GameWindow.TILE_SIZE * tileX;
                int y = GameWindow.TILE_SIZE * tileY;

                Tile currentTile = tiles[tileY][tileX];
                graphics.drawImage(currentTile.getTileImage().getBufferedImage(), x, y, GameWindow.TILE_SIZE, GameWindow.TILE_SIZE, null);
            }
        }
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
        return tiles[location.getY()][location.getX()];
    }
}