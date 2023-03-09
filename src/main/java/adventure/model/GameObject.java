package adventure.model;

import org.jetbrains.annotations.Nullable;

import adventure.tick.Ticking;
import adventure.util.Location;

/**
 * Ein GameObject ist ein GameAsset, welches sich auf der Map befindet und lokalisiert werden kann.
 * Es kann, muss aber nicht, die Klasse {@link Ticking} implementieren.
 * 
 * @see Ticking
 * @author Jamil B.
 */
public abstract class GameObject extends GameAsset implements Ticking {

    protected Location location;

    public GameObject(int id, String name, @Nullable String namespace, @Nullable String key) {
        super(id, name, namespace, key);
    }

    public Location getLocation() {
        return location;
    }

    public Location getPixelLocation() {
        return location.clone().multiply(32);
    }

    @Override
    public abstract void tick();
}