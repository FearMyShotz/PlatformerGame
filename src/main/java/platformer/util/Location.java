package platformer.util;

/**
 * Eine Klasse, die eine Position darstellt.
 * Kann mit anderen Positionen verglichen werden.
 * 
 * @author Jamil B.
 */
public class Location implements Comparable<Location> {
    
    /**
     * Die X-Koordinate der Position.
     */
    public float x;

    /**
     * Die Y-Koordinate der Position.
     */
    public float y;

    /**
     * Erstellt eine neue Position.
     * 
     * @param x die X-Koordinate der Position
     * @param y die Y-Koordinate der Position
     */
    public Location(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Erstellt eine neue Position.
     * 
     * @param pair ein Pair, das die X- und Y-Koordinate enthält
     */
    public Location(Pair<Integer, Integer> pair) {
        this(pair.getLeft(), pair.getRight());
    }

    /**
     * Klont diese Position.
     * 
     * @return eine neue Position mit den gleichen Attributen
     */
    public Location clone() {
        return new Location(x, y);
    }

    /**
     * Gibt die X-Koordinate der Position als float zurück.
     * 
     * @return die X-Koordinate der Position
     */
    public float getX() {
        return x;
    }

    /**
     * Setzt die X-Koordinate der Position.
     * 
     * @param x die X-Koordinate der Position
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Gibt die Y-Koordinate der Position als float zurück.
     * 
     * @return die Y-Koordinate der Position
     */
    public float getY() {
        return y;
    }

    /**
     * Setzt die Y-Koordinate der Position.
     * 
     * @param y die Y-Koordinate der Position
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Prüft, ob diese Position in der Nähe einer anderen Position ist.
     * 
     * @param location die andere Position
     * @return true, wenn die Positionen in der Nähe sind, sonst false
     */
    public boolean isNear(Location location) {
        return Math.abs(location.getX() - x) <= 1 && Math.abs(location.getY() - y) <= 1;
    }

    /**
     * Prüft, ob diese Position in der Nähe einer anderen Position ist.
     * 
     * @param location die andere Position
     * @param range die maximale Distanz zwischen den Positionen
     * @return true, wenn die Positionen in der Nähe sind, sonst false
     */
    public boolean isNear(Location location, int range) {
        return Math.abs(location.getX() - x) <= range && Math.abs(location.getY() - y) <= range;
    }

    /**
     * Prüft, ob diese Position mit einer anderen Position übereinstimmt.
     * 
     * @param location die andere Position
     * @return true, wenn die Positionen übereinstimmen, sonst false
     */
    public boolean isAt(Location location) {
        return location.getX() == x && location.getY() == y;
    }

    /**
     * Setzt die Position auf die Werte einer anderen Position.
     * 
     * @param location die andere Position
     */
    public void set(Location location) {
        this.x = location.getX();
        this.y = location.getY();
    }

    /**
     * Setzt die Position auf die Werte einer anderen Position.
     * 
     * @param x die X-Koordinate der Position
     * @param y die Y-Koordinate der Position
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Addiert die Werte einer anderen Position zu dieser Position.
     * 
     * @param x die X-Koordinate der Position
     * @param y die Y-Koordinate der Position
     */
    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Erhöht die X-Koordinate der Position um einen bestimmten Wert.
     * 
     * @param f der Wert, um den die X-Koordinate erhöht werden soll
     */
    public void addX(float f) {
        this.x += f;
    }

    /**
     * Erhöht die Y-Koordinate der Position um einen bestimmten Wert.
     * 
     * @param f der Wert, um den die Y-Koordinate erhöht werden soll
     */
    public void addY(float f) {
        this.y += f;
    }

    /**
     * Subtrahiert die Werte einer anderen Position von dieser Position.
     * 
     * @param x die X-Koordinate der Position
     * @param y die Y-Koordinate der Position
     */
    public void subtract(float x, float y) {
        this.x -= x;
        this.y -= y;
    }
    
    /**
     * Multipliziert die Werte dieser Position mit einem bestimmten Wert.
     * 
     * @param i der Wert, mit dem die Position multipliziert werden soll
     * @return eine neue Position mit den multiplizierten Werten
     */
    public Location multiply(float i) {
        return new Location(x * i, y * i);
    }

    /**
     * Gibt eine String-Repräsentation dieser Position zurück.
     * 
     * @return eine String-Repräsentation dieser Position
     */
    @Override
    public String toString() {
        return "Location [x=" + x + ", y=" + y + "]";
    }

    /**
     * Vergleicht diese Position mit einer anderen Position.
     * 
     * @param location die andere Position
     * @return 0, wenn die Positionen übereinstimmen, 1, wenn diese Position größer ist, sonst -1
     */
    @Override
    public int compareTo(Location location) {
        if (location.getX() == x && location.getY() == y) {
            return 0;
        } else if (location.getX() > x && location.getY() > y) {
            return 1;
        } else {
            return -1;
        }
    }
}