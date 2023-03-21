package platformer.util;

/**
 * Eine Schnittstelle für alle Objekte, die initialisiert werden können.
 * 
 * @author Jamil B.
 * @param <I> der Typ des zu initialisierenden Objekts
 */
@FunctionalInterface
public interface Initializable<I> {

    /**
     * Initialisiert das Objekt.
     * 
     * @param i das zu initialisierende Objekt
     */
    void initialize(I i);
}