package platformer.tick;

import java.awt.Graphics;

/**
 * Eine Schnittstelle für alle Spielobjekte, die grafisch dargestellt werden
 * 
 * @author Jamil B.
 */
@FunctionalInterface
public interface Rendering {

    /**
     * Zeichnet das Spielobjekt
     * 
     * @param g das Graphics-Objekt, mit dem gezeichnet werden soll
     */
    void render(Graphics g);

}