package adventure.model.spritesheet;

import adventure.model.ResourceKey;
import adventure.model.animation.Animation;
import adventure.util.Identifiable;

public enum SpritesheetType implements Identifiable {
    
    ENTITY(0, "Entity"),
    ITEM(1, "Item"),
    TILE(2, "Tile"),
    GUI(3, "GUI"),
    ;
    
    private int id;
    private String name;
    
    SpritesheetType(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public ResourceKey<Animation> getIdentifier() {
        return new ResourceKey.Builder<Animation>(Animation.NAMESPACE, name().toLowerCase()).build();
    }
}