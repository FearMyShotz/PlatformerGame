package platformer.model;

import platformer.tick.Ticking;
import platformer.util.Location;

/**
 * Ein GameObject ist ein GameAsset, welches sich auf der Map befindet und lokalisiert werden kann.
 * Es kann, muss aber nicht, die Klasse {@link Ticking} implementieren.
 * 
 * @see Ticking
 * @author Jamil B.
 */
public abstract class GameObject extends GameAsset implements Ticking {

    protected Location location;

    public GameObject(int id, String name, String namespace, String key) {
        super(id, name, namespace, key);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getPixelLocation() {
        return location.clone().multiply(32);
    }

    @Override
    public abstract void tick();
}