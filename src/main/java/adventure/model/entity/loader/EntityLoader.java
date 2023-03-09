package adventure.model.entity.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import adventure.model.entity.Entity;
import adventure.model.entity.implementation.player.Player;
import adventure.model.spritesheet.loader.FileLoader;
import adventure.util.Initializable;
import adventure.util.Location;
import adventure.window.GameWindow;

public class EntityLoader extends FileLoader<Entity> implements Initializable<File> {

    public EntityLoader(Set<Entity> entities, String filePath) {
        super(entities);

        initialize(new File(filePath));
    }

    @Override
    public void initialize(File entityFile) {
        int x, y;
        x = y = 0;

        // Function<String, Entity> tileFunction = (entityID) -> {
        //     int id = Integer.parseInt(entityID);
        //     var loader = AdventureGame.getInstance().getTileManager().getLoader();
            
        //     if (id >= 0 && id <= TileType.values().length) {
        //         return loader.getTileFromID(entityID);
        //     } else {
        //         return loader.getTileFromID("00");
        //     }
        // };

        try {
            BufferedReader reader = new BufferedReader(new FileReader(entityFile));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] currentLine = line.split(" ");
                
                for (String entityID : currentLine) {
                    int id = Integer.parseInt(entityID);

                    if (id < 40) {
                        x++;
                        continue;
                    } else if (id == 44) {
                        // Create a new entity at the current x and y and add it to the loadedAssets
                        // Entity entity = new Entity(x, y, id);

                        System.out.println("Current X: " + x + ", Current Y: " + y + ", ID: " + id);

                        Entity player = new Player(id, "Player", "player");
                        // player.setLocation(new Location(x * 32, y * 32));
                        player.setLocation(new Location(x, y));
                        
                        loadedAssets.add(player);
                    }
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

    @Override
    public Set<Entity> load() {
        return loadedAssets;
    }
    
}