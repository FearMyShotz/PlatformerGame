package platformer.model;

import platformer.util.Identifiable;

/**
 * Ein AssetType ist ein Typ eines Assets.
 * 
 * @author Jamil B.
 */
public enum AssetType implements Identifiable {

    ENTITY(0),
    ITEM(1),
    TILE(2),
    ANIMATION(3),
    SPRITESHEET(4),
    LEVEL(5),

    ;

    private int id;
    private String name;

    AssetType(int id) {
        this.id = id;
        this.name = name().toLowerCase();
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
    public ResourceKey<?> getIdentifier() {
        return null;
    }
}