package platformer.model.entity.implementation.type;

import platformer.model.ResourceKey;
import platformer.model.entity.EntityType;
import platformer.model.entity.implementation.player.Player;
import platformer.util.Identifiable;

/**
 * Eine Aufzählung, die die verschiedenen Spielertypen enthält.
 * 
 * @author Jamil B.
 * @see Identifiable
 * @see EntityType
 */
public enum PlayerType implements EntityType, Identifiable {
    
    NOVICE(0, "Anfänger"),
    WARRIOR(1, "Krieger"),
    ARCHER(2, "Bogenschütze"),
    MAGE(3, "Magier"),

    ;

    private int id;
    private String name;

    PlayerType(int id, String name) {
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
    public ResourceKey<Player> getIdentifier() {
        return new ResourceKey.Builder<Player>(Player.NAMESPACE, name().toLowerCase()).build();
    }
 
    @Override
    public GeneralEntityType getType() {
        return GeneralEntityType.PLAYER;
    }

}