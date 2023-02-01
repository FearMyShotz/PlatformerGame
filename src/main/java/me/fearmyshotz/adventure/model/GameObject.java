package me.fearmyshotz.adventure.model;

/**
 * Ein GameObject ist ein GameAsset, welches sich auf der Map befindet und lokalisiert werden kann.
 * Es kann, muss aber nicht, die Klasse {@link Ticking} implementieren.
 * 
 * @see Ticking
 * @author Jamil B.
 */
public abstract class GameObject extends GameAsset {

    protected int x, y;

    public GameObject(int id, String name, String description) {
        super(id, name, description);
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public abstract void update();
}