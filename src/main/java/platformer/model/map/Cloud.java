package platformer.model.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import platformer.model.GameObject;
import platformer.tick.Rendering;
import platformer.util.Location;
import platformer.window.GameWindow;

/**
 * Ein Objekt, das eine Wolke darstellt.
 * 
 * @author Jamil B.
 */
public class Cloud extends GameObject implements Rendering {

    /**
     * Das Bild, das die Wolke darstellt.
     */
    private final BufferedImage image;

    /**
     * Erstellt eine neue Wolke.
     * 
     * @param id die ID der Wolke.
     * @param loc die Position der Wolke.
     * @param image das Bild, das die Wolke darstellt.
     */
    public Cloud(int id, Location loc, BufferedImage image) {
        super(id, "Wolke", "map", "cloud");

        this.location = loc;

        this.image = image;
    }

    /**
     * Rendert die Wolke.
     * 
     * @param g das Graphics-Objekt, mit dem gerendert wird.
     */
    @Override
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

    /**
     * Wird nicht verwendet, da Wolken keine Spiellogik durchführen.
     */
    @Override
    public void tick() {
        throw new UnsupportedOperationException("Unimplemented method 'tick'");
    }
}