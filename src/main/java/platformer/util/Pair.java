package platformer.util;

/**
 * Eine generische Klasse, die zwei Objekte als ein Wertepaar speichert.
 * 
 * @param <Left> der Typ des linken Objekts
 * @param <Right> der Typ des rechten Objekts
 * @author Jamil B.
 */
public class Pair<Left, Right> {

    /**
     * Das linke Objekt.
     */
    private Left left;

    /**
     * Das rechte Objekt.
     */
    private Right right;

    /**
     * Erstellt ein neues Wertepaar.
     * 
     * @param left das linke Objekt
     * @param right das rechte Objekt
     */
    public Pair(Left left, Right right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Gibt das linke Objekt zurück.
     * 
     * @return das linke Objekt
     */
    public Left getLeft() {
        return left;
    }

    /**
     * Gibt das rechte Objekt zurück.
     * 
     * @return das rechte Objekt
     */
    public Right getRight() {
        return right;
    }

    /**
     * Setzt das linke Objekt.
     * 
     * @param left das linke Objekt
     */
    public void setLeft(Left left) {
        this.left = left;
    }

    /**
     * Setzt das rechte Objekt.
     * 
     * @param right das rechte Objekt
     */
    public void setRight(Right right) {
        this.right = right;
    }

    /**
     * Gibt eine String-Repräsentation dieses Wertepaares zurück.
     * 
     * @return eine String-Repräsentation dieses Wertepaares
     */
    @Override
    public String toString() {
        return "Pair [left=" + left + ", right=" + right + "]";
    }
}