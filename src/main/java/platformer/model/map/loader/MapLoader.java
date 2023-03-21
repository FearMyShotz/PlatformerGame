package platformer.model.map.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.function.Function;

import platformer.PlatformerGame;
import platformer.model.map.Map;
import platformer.model.tile.Tile;
import platformer.model.tile.TileLoader;
import platformer.model.tile.type.TileType;
import platformer.util.FileLoader;
import platformer.util.Initializable;
import platformer.util.Location;
import platformer.window.GameWindow;

/**
 * Ein spezialisierter {@link FileLoader}, der {@link Map}s lädt.
 * 
 * @author Jamil B.
 * @see FileLoader
 */
public class MapLoader extends FileLoader<Map> implements Initializable<String> {

    /**
     * Die Tiles in einem zweidimensionalen Array.
     */
    private final Tile[][] tiles;

    /**
     * Erstellt und initialisiert einen neuen MapLoader.
     * 
     * @param filePath der Pfad zur Datei, die geladen werden soll.
     */
    public MapLoader(final String filePath) {
        super(new HashSet<Map>(), filePath);

        this.tiles = new Tile[GameWindow.TILES_Y][GameWindow.TILES_X];
        
        initialize(filePath);
    }

    /**
     * Initialisiert diesen MapLoader.
     * 
     * @param mapFilePath der Pfad zur Datei, die geladen werden soll.
     */
    @Override
    public void initialize(final String mapFilePath) {
        load();
    }

    /**
     * Lädt die Map aus der Datei.
     * 
     * @return die geladene Map.
     */
    @Override
    public HashSet<Map> load() {
        int x, y;
        x = y = 0;

        BufferedReader reader = read(filePath, "/default.map");

        final Function<String, Tile> getTileFunction = (tileID) -> {
            final int id = Integer.parseInt(tileID);
            final TileLoader loader = PlatformerGame.getInstance().getTileManager().getLoader();
            
            if (id >= 0 && id <= TileType.values().length) {
                return loader.getTileFromID(tileID);
            } else {
                return loader.getTileFromID("00");
            }
        };

        try {
            String line;

            while ((line = reader.readLine()) != null) {
                final String[] currentLine = line.split(" ");
                
                for (final String tileID : currentLine) {
                    // System.out.println("X: " + x + " Y: " + y + " TileID: " + tileID);
                    final Tile foundTile = getTileFunction.apply(tileID);
                    foundTile.setLocation(new Location(x, y));

                    tiles[y][x] = foundTile;
                    
                    x++;
                }
                x = 0;
                y++;
                if (y >= GameWindow.TILES_Y) break;
            }
            reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gibt die Tiles in einem zweidimensionalen Array zurück.
     * 
     * @return die Tiles in einem zweidimensionalen Array.
     */
    public Tile[][] getTiles() {
        return tiles;
    }
}