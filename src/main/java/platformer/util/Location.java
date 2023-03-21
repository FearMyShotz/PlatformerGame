package platformer.util;

public class Location implements Comparable<Location> {
    
    public float x;
    public float y;

    public Location(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Location(Pair<Integer, Integer> pair) {
        this(pair.getLeft(), pair.getRight());
    }

    public Location clone() {
        return new Location(x, y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isNear(Location location) {
        return Math.abs(location.getX() - x) <= 1 && Math.abs(location.getY() - y) <= 1;
    }

    public boolean isNear(Location location, int range) {
        return Math.abs(location.getX() - x) <= range && Math.abs(location.getY() - y) <= range;
    }

    public boolean isAt(Location location) {
        return location.getX() == x && location.getY() == y;
    }

    public void set(Location location) {
        this.x = location.getX();
        this.y = location.getY();
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void addX(float f) {
        this.x += f;
    }

    public void addY(float f) {
        this.y += f;
    }

    public void subtract(float x, float y) {
        this.x -= x;
        this.y -= y;
    }
    
    public Location multiply(float i) {
        return new Location(x * i, y * i);
    }

    @Override
    public String toString() {
        return "Location [x=" + x + ", y=" + y + "]";
    }

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