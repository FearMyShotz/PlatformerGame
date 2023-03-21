package platformer.util;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.HashSet;

public class Registry<Key extends Identifiable, Value extends Identifiable> {

    private final TreeMap<Key, Value> registered;

    public Registry() {
        this.registered = new TreeMap<Key, Value>();
    }

    public void register(Key key, Value value) {
        registered.put(key, value);
    }

    public HashSet<Entry<Key, Value>> entrySet() {
        return new HashSet<Entry<Key, Value>>(registered.entrySet());
    }
    
}