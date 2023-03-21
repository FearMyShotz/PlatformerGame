package platformer.model.entity.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;

import platformer.model.entity.Entity;
import platformer.model.entity.implementation.player.Player;
import platformer.util.FileLoader;
import platformer.util.Hitbox;
import platformer.util.Location;
import platformer.window.GameWindow;

/**
 * Ein spezialisierter {@link FileLoader}, der {@link Entity} aus einer Datei lädt.
 * 
 * @author Jamil B.
 * @see FileLoader
 */
public class EntityLoader extends FileLoader<Entity> {

    /**
     * Erstellt einen neuen EntityLoader.
     * 
     * @param entities die {@link HashSet}, in der die geladenen {@link Entity} gespeichert werden.
     * @param filePath der Pfad zur Datei, die geladen werden soll.
     */
    public EntityLoader(HashSet<Entity> entities, String filePath) {
        super(entities, filePath);
    }

    /**
     * Lädt die {@link Entity}s aus der Datei, indem es sie liest und über die einzel Zeilen iteriert.
     * 
     * @return ein {@link HashSet} mit geladenen {@link Entity}s.
     */
    @Override
    public HashSet<Entity> load() {
        int x, y;
        x = y = 0;

        BufferedReader reader = read(filePath, "/default.map");

        try {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] currentLine = line.split(" ");
                
                for (String entityID : currentLine) {
                    int id = Integer.parseInt(entityID);

                    if (id < 40) {
                        x++;
                        continue;
                    } else if (id == 44) {
                        Location loc = new Location(x * GameWindow.TILE_SIZE, y * GameWindow.TILE_SIZE);

                        Player player = new Player(id, "Player", "player");
                        player.setLocation(loc);
                        player.setHitbox(new Hitbox(loc, (int) (32 * GameWindow.SCALE), (int) (32 * GameWindow.SCALE) - 1));

                        loadedAssets.add(player);
                    }
                    x++;
                }
                x = 0;
                y++;
                if (y >= GameWindow.TILES_Y) break;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new HashSet<Entity>(loadedAssets);
    }
}