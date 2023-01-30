package me.fearmyshotz.adventure.manager.implementation;

import me.fearmyshotz.adventure.AdventureGame;
import me.fearmyshotz.adventure.game.GamePanel;
import me.fearmyshotz.adventure.game.GameWindow;
import me.fearmyshotz.adventure.manager.Manager;

/**
 * @author Jamil B.
 */
public class WindowManager extends Manager {
    
    private GamePanel panel;

    private GameWindow window;

    public WindowManager(AdventureGame game) {
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