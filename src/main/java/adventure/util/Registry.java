package adventure.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Registry<Key extends Identifiable, Value extends Identifiable> {

    private final Map<Key, Value> registered;

    public Registry() {
        this.registered = new TreeMap<Key, Value>();
    }

    public void register(Key key, Value value) {
        registered.put(key, value);
    }

    public Set<Entry<Key, Value>> entrySet() {
        return registered.entrySet();
    }
    
}