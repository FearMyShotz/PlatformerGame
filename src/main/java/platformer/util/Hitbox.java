package platformer.util;

import java.awt.geom.Rectangle2D;

/**
 * Eine Klasse, die eine Rechteckige Hitbox darstellt. Erweitert die Klasse {@link Rectangle2D.Float}.
 * 
 * @author Jamil B.
 */
public final class Hitbox extends Rectangle2D.Float {
    
    /**
     * Erstellt eine neue Hitbox.
     * 
     * @param loc die Position der Hitbox
     * @param width die Breite der Hitbox
     * @param height die Höhe der Hitbox
     */
    public Hitbox(Location loc, int width, int height) {
        this(loc.getX(), loc.getY(), width, height);
    }

    /**
     * Erstellt eine neue Hitbox.
     * 
     * @param x die X-Koordinate der Hitbox
     * @param y die Y-Koordinate der Hitbox
     * @param width die Breite der Hitbox
     * @param height die Höhe der Hitbox
     */
    public Hitbox(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    /**
     * Klont diese Hitbox
     * 
     * @return eine neue Hitbox mit den gleichen Attributen
     */
    public Hitbox clone() {
        return new Hitbox(x, y, width, height);
    }

    /**
     * Gibt die X-Koordinate der Hitbox als float zurück.
     * 
     * @return die X-Koordinate der Hitbox
     */
    public float getXF() {
        return x;
    }

    /**
     * Gibt die Y-Koordinate der Hitbox als float zurück.
     * 
     * @return die Y-Koordinate der Hitbox
     */
    public float getYF() {
        return y;
    }

    /**
     * Gibt die Breite der Hitbox als float zurück.
     * 
     * @return die Breite der Hitbox
     */
    public float getWidthF() {
        return width;
    }

    /**
     * Gibt die Höhe der Hitbox als float zurück.
     * 
     * @return die Höhe der Hitbox
     */
    public float getHeightF() {
        return height;
    }

    /**
     * Prüft, ob sich diese Hitbox mit einer anderen Hitbox überschneidet.
     * 
     * @param hitbox die andere Hitbox
     * @return true, wenn sich die Hitboxen überschneiden, sonst false
     */
    public boolean intersects(Hitbox hitbox) {
        return super.intersects(hitbox);
    }

    /**
     * Prüft, ob sich diese Hitbox mit einer Position überschneidet.
     * 
     * @param location die Position
     * @return true, wenn sich die Hitbox mit der Position überschneidet, sonst false
     */
    public boolean intersects(Location location) {
        return super.contains(location.getX(), location.getY());
    }

    /**
     * Gibt eine String-Repräsentation dieser Hitbox zurück.
     * 
     * @return eine String-Repräsentation dieser Hitbox
     */
    @Override
    public String toString() {
        return "Hitbox [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
    }
}