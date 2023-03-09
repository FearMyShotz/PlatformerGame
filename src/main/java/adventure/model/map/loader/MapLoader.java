package adventure.model.map.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.function.Function;

import adventure.AdventureGame;
import adventure.model.map.Map;
import adventure.model.spritesheet.loader.FileLoader;
import adventure.model.tile.Tile;
import adventure.model.tile.type.TileType;
import adventure.util.Initializable;
import adventure.window.GameWindow;

public class MapLoader extends FileLoader<Map> implements Initializable<File> {

    private Tile[][] tiles;

    public MapLoader(String filePath) {
        super();

        this.tiles = new Tile[GameWindow.TILES_Y][GameWindow.TILES_X];
        
        initialize(new File(filePath));
    }

    @Override
    public Set<Map> load() {
        return null;
    }

    @Override
    public void initialize(File mapFile) {
        int x, y;
        x = y = 0;

        Function<String, Tile> tileFunction = (tileID) -> {
            int id = Integer.parseInt(tileID);
            var loader = AdventureGame.getInstance().getTileManager().getLoader();
            
            if (id >= 0 && id <= TileType.values().length) {
                return loader.getTileFromID(tileID);
            } else {
                return loader.getTileFromID("00");
            }
        };

        try {
            BufferedReader reader = new BufferedReader(new FileReader(mapFile));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] currentLine = line.split(" ");
                
                for (String tileID : currentLine) {
                    // System.out.println("X: " + x + " Y: " + y + " TileID: " + tileID);

                    tiles[y][x] = tileFunction.apply(tileID);
                    
                    x++;
                }
                x = 0;
                y++;
                if(y >= GameWindow.TILES_Y) break;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }
    
}