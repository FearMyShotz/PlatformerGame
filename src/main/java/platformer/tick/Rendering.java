package platformer.tick;

import java.awt.Graphics;

@FunctionalInterface
public interface Rendering {

    void render(Graphics g);

}