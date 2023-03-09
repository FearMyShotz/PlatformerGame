package adventure.model;

import org.jetbrains.annotations.Nullable;

import adventure.AdventureGame;
import adventure.util.Identifiable;

public abstract class GameAsset implements Identifiable, Comparable<GameAsset> {
    
    protected @Nullable ResourceKey<? extends GameAsset> key;

    protected int id;
    
    protected String name;
    
    
    public GameAsset(int id, String name, @Nullable String namespace, @Nullable String key) {
        this.id = id;
        this.name = name;
        this.key = new ResourceKey.Builder<GameAsset>(namespace, key).build();

        AdventureGame.getInstance().getGameRegistry().register(this.key, this);
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
    public ResourceKey<? extends GameAsset> getIdentifier() {
        return key;
    }

    public void setKey(ResourceKey<? extends GameAsset> key) {
        this.key = key;
    }
    
    public ResourceKey<? extends GameAsset> getKey() {
        return key;
    }

    @Override
    public int compareTo(GameAsset o) {
        return Integer.compare(id, o.id);
    }
}