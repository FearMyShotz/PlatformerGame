package platformer.model;

import platformer.PlatformerGame;
import platformer.util.Identifiable;

/**
 * Ein GameAsset ist ein Asset, das im Spiel verwendet wird.
 * Es besitzt Attribute, mit welchem es identifiziert werden kann.
 * Dazu zählen die ID, der Name und der {@link ResourceKey}.
 * 
 * @author Jamil B.
 */
public abstract class GameAsset implements Identifiable, Comparable<GameAsset> {
    
    /**
     * Die Instanz der Hauptklasse des Spiels.
     */
    protected PlatformerGame game;

    /**
     * Der {@link ResourceKey}, unter dem das Asset registriert ist.
     */
    protected ResourceKey<? extends GameAsset> key;

    /**
     * Die ID des Assets.
     */
    protected int id;
    
    /**
     * Der Name des Assets.
     */
    protected String name;
    
    /**
     * Erstellt ein neues GameAsset.
     * 
     * @param id die ID des Assets
     * @param name der Name des Assets
     * @param namespace der Namespace des Assets
     * @param key der Schlüssel des Assets
     */
    public GameAsset(int id, String name, String namespace, String key) {
        this.game = PlatformerGame.getInstance();
        this.id = id;
        this.name = name;
        this.key = new ResourceKey.Builder<GameAsset>(namespace, key).build();

        PlatformerGame.getInstance().getGameRegistry().register(this.key, this);
    }

    /**
     * Gibt die ID des Assets zurück.
     * 
     * @return die ID des Assets
     */
    @Override
    public int getId() {
        return id;
    }
    
    /**
     * Gibt den Namen des Assets zurück.
     * 
     * @return der Name des Assets
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gibt den {@link ResourceKey} des Assets zurück.
     * 
     * @return der {@link ResourceKey} des Assets
     */
    @Override
    public ResourceKey<? extends GameAsset> getIdentifier() {
        return key;
    }

    /**
     * Setzt den {@link ResourceKey} des Assets.
     * 
     * @param key der {@link ResourceKey}, der gesetzt werden soll
     */
    public void setKey(ResourceKey<? extends GameAsset> key) {
        this.key = key;
    }
    
    /**
     * Gibt den {@link ResourceKey} des Assets zurück.
     * 
     * @return der {@link ResourceKey} des Assets
     */
    public ResourceKey<? extends GameAsset> getKey() {
        return key;
    }

    /**
     * Vergleicht dieses GameAsset mit einem anderen.
     * 
     * @param o das andere GameAsset
     * @return {@code 0}, wenn die IDs gleich sind, {@code -1}, wenn die ID dieses GameAssets kleiner ist als die ID des anderen GameAssets, {@code 1} wenn die ID dieses GameAssets größer ist als die ID des anderen GameAssets
     */
    @Override
    public int compareTo(GameAsset o) {
        return Integer.compare(id, o.id);
    }
}