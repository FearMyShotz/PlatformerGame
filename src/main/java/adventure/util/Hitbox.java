package adventure.util;

import java.awt.geom.Rectangle2D;

public class Hitbox extends Rectangle2D.Float {
    
    public Hitbox() { super(); }

    public Hitbox(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public boolean intersects(Hitbox hitbox) {
        return super.intersects(hitbox);
    }

    public boolean intersects(Location location) {
        return super.contains(location.getX(), location.getY());
    }

    @Override
    public String toString() {
        return "Hitbox [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
    }
}