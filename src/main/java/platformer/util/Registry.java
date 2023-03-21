package platformer.util;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.HashSet;

/**
 * Eine Registry ist eine Sammlung von Objekten, welche über einen {@link ResourceKey} identifiziert werden können.
 * 
 * @param <Key> der Schlüssel-Typ
 * @param <Value> der Wert-Typ
 * @author Jamil B.
 */
public class Registry<Key extends Identifiable, Value extends Identifiable> {

    /**
     * Die registrierten Objekte.
     */
    private final TreeMap<Key, Value> registered;

    /**
     * Erstellt eine neue Registry.
     */
    public Registry() {
        this.registered = new TreeMap<Key, Value>();
    }

    /**
     * Registriert ein Objekt.
     * 
     * @param key der Schlüssel
     * @param value der Wert
     */
    public void register(Key key, Value value) {
        registered.put(key, value);
    }

    /**
     * Gibt ein {@link HashSet} mit allen registrierten Einträgen, besteht aus {@link Entry}s, zurück.
     */
    public HashSet<Entry<Key, Value>> entrySet() {
        return new HashSet<Entry<Key, Value>>(registered.entrySet());
    }
}