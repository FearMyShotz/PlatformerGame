package platformer.window;

import platformer.PlatformerGame;
import platformer.manager.Manager;

/**
 * @author Jamil B.
 */
public class WindowManager extends Manager {
    
    private GamePanel panel;

    private GameWindow window;

    public WindowManager(PlatformerGame game) {
        super(game);

        this.panel = new GamePanel();
        this.window = new GameWindow(panel);
    }

    public GamePanel getPanel() {
        return panel;
    }

    public GameWindow getWindow() {
        return window;
    }
}