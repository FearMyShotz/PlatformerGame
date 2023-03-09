package adventure.util;

public class Location implements Comparable<Location> {
    
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location(Pair<Integer, Integer> pair) {
        this(pair.getLeft(), pair.getRight());
    }

    public Location clone() {
        return new Location(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isNear(Location location) {
        return Math.abs(location.getX() - x) <= 1 && Math.abs(location.getY() - y) <= 1;
    }

    public boolean isAt(Location location) {
        return location.getX() == x && location.getY() == y;
    }

    public void set(Location location) {
        this.x = location.getX();
        this.y = location.getY();
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void subtract(int x, int y) {
        this.x -= x;
        this.y -= y;
    }
    
    public Location multiply(int i) {
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