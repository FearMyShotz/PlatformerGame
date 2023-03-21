package platformer.model.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import platformer.model.GameObject;
import platformer.tick.Rendering;
import platformer.util.Location;
import platformer.window.GameWindow;

public class Cloud extends GameObject implements Rendering {

    private final BufferedImage image;

    public Cloud(int id, Location loc, BufferedImage image) {
        super(id, "Wolke", "map", "cloud");

        this.location = loc;

        this.image = image;
    }

    public void render(Graphics g) {
        g.drawImage(
            image, 
            (int) location.getX(), 
            (int) location.getY(), 
            (int) (image.getWidth() * GameWindow.SCALE), 
            (int) (image.getHeight() * GameWindow.SCALE), 
            null
        );
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }
}