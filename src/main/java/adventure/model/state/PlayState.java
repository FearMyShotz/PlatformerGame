package adventure.model.state;

import java.awt.Graphics;

import adventure.AdventureGame;
import adventure.input.keyboard.KeyAction;
import adventure.input.keyboard.layout.KeyboardLayout;
import adventure.model.Direction;
import adventure.model.entity.implementation.player.Player;
import adventure.model.level.LevelManager;

public class PlayState extends GameState {

    private final LevelManager levelManager;
                                                                                                                                                                                                                                                                                                                                                                                                                              
    public PlayState(AdventureGame game) {
        super(game);

        this.levelManager = game.getLevelManager();
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    @Override
    public void tick() {
        levelManager.getCurrentLevel().tick();
    }

    @Override
    public void render(Graphics graphics) {
        levelManager.getCurrentLevel().render(graphics);
    }

    @Override
    public void handleKeyAction(KeyAction action, int keyCode) {
        KeyboardLayout keyboardLayout = game.getInputManager().getKeyboardLayout();
        Player p = levelManager.getCurrentLevel().getPlayer();

        boolean moving = action == KeyAction.PRESS;

        switch (action) {
        case PRESS:
        case RELEASE:
            if (keyCode == keyboardLayout.getForwardKey().getKeyCode()) {
                p.setMoving(null, moving);
            } else if (keyCode == keyboardLayout.getLeftKey().getKeyCode()) {
                p.setMoving(Direction.LEFT, moving);
            } else if (keyCode == keyboardLayout.getRightKey().getKeyCode()) {
                p.setMoving(Direction.RIGHT, moving);
            }
            break;
        }
    }
}