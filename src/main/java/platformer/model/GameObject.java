package platformer.model;

import platformer.tick.Ticking;
import platformer.util.Location;

/**
 * Ein GameObject ist ein GameAsset, welches sich auf der Map befindet und lokalisiert werden kann.
 * Es muss die Klasse {@link Ticking} implementieren.
 * 
 * Sofern es jedoch keine Spiellogik besitzt, wird eine {@link UnsupportedOperationException} geworfen.
 * 
 * @see Ticking
 * @author Jamil B.
 */
public abstract class GameObject extends GameAsset implements Ticking {

    /**
     * Die Position des GameObjects auf der Map.
     */
    protected Location location;

    /**
     * Erstellt ein neues GameObject.
     * 
     * @param id die ID des GameObjects
     * @param name der Name des GameObjects
     * @param namespace der Namespace des GameObjects
     * @param key der Schlüssel des GameObjects
     */
    public GameObject(int id, String name, String namespace, String key) {
        super(id, name, namespace, key);
    }

    /**
     * Gibt die Position des GameObjects auf der Map zurück.
     * 
     * @return die Position des GameObjects auf der Map
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Setzt die Position des GameObjects auf der Map.
     * 
     * @param location die Position des GameObjects auf der Map
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gibt die Position des GameObjects in Pixeln zurück.
     * 
     * @return die Position des GameObjects in Pixeln
     */
    public Location getPixelLocation() {
        return location.clone().multiply(32);
    }

    /**
     * Setzt die Position des GameObjects in Pixeln.
     * 
     * @param location die Position des GameObjects in Pixeln
     */
    @Override
    public abstract void tick();
}