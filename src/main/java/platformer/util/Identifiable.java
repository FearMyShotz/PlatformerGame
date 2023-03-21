package platformer.util;

import platformer.model.ResourceKey;

/**
 * Eine Schnittstelle für alle Objekte, die einen Namen, eine ID und einen @link ResourceKey} besitzen, also identifiziert werden können.
 * 
 * @author Jamil B.
 */
public interface Identifiable {
    
    /**
     * Gibt die ID zurück.
     * 
     * @return die ID
     */
    int getId();

    /**
     * Gibt den Namen zurück.
     * 
     * @return der Name
     */
    String getName();

    /**
     * Gibt den Schlüssel zurück.
     * 
     * @return der Schlüssel
     */
    ResourceKey<?> getIdentifier();
}