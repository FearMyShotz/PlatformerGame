package platformer.util;

import java.awt.geom.Rectangle2D;

public class Hitbox extends Rectangle2D.Float {
    
    public Hitbox(Location loc, int width, int height) {
        this(loc.getX(), loc.getY(), width, height);
    }

    public Hitbox(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Hitbox clone() {
        return new Hitbox(x, y, width, height);
    }

    public float getXF() {
        return x;
    }

    public float getYF() {
        return y;
    }

    public float getWidthF() {
        return width;
    }

    public float getHeightF() {
        return height;
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