package platformer.model.state;

import java.awt.Graphics;

import platformer.PlatformerGame;
import platformer.input.keyboard.KeyAction;
import platformer.input.keyboard.layout.KeyboardLayout;
import platformer.model.Direction;
import platformer.model.entity.implementation.player.Player;
import platformer.model.level.LevelManager;

public class PlayState extends GameState {

    private final LevelManager levelManager;

    private boolean gameEnded;
                                                                                                                                                                                                                                                                                                                                                                                                                              
    public PlayState(PlatformerGame game) {
        super(game);

        this.levelManager = game.getLevelManager();
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public Player getPlayer() {
        return levelManager.getCurrentLevel().getPlayer();
    }

    @Override
    public void tick() {
        if (gameEnded) return;

        levelManager.getCurrentLevel().tick();
    }

    @Override
    public void render(Graphics g) {
        levelManager.getCurrentLevel().render(g);
    }

    @Override
    public void handleKeyAction(KeyAction action, int keyCode) {
        KeyboardLayout keyboardLayout = game.getInputManager().getKeyboardLayout();
        Player p = levelManager.getCurrentLevel().getPlayer();

        int forward, backward, left, right, jump;
        forward = keyboardLayout.getForwardKey().getKeyCode();
        backward = keyboardLayout.getBackwardKey().getKeyCode();
        left = keyboardLayout.getLeftKey().getKeyCode();
        right = keyboardLayout.getRightKey().getKeyCode();
        jump = keyboardLayout.getJumpKey().getKeyCode();

        switch (action) {
        case PRESS:
            if (keyCode == forward) {
                switch (p.getDirection()) {
                case LEFT:
                    p.movingLeft = true;
                    p.setDirection(Direction.LEFT);
                    p.setLastDirection(Direction.LEFT);
                    p.movingRight = false;
                    break;
                case RIGHT:
                    p.movingRight = true;
                    p.setDirection(Direction.RIGHT);
                    p.setLastDirection(Direction.RIGHT);
                    p.movingLeft = false;
                    break;
                default:
                    break;
                }
            } else if (keyCode == backward) {
                if (!p.switchedDirection) { 
                    switch (p.getDirection()) {
                    case LEFT:
                        p.movingRight = true;
                        p.setDirection(Direction.RIGHT);
                        p.setLastDirection(Direction.RIGHT);
                        p.movingLeft = false;
                        break;
                    case RIGHT:
                        p.movingLeft = true;
                        p.setDirection(Direction.LEFT);
                        p.setLastDirection(Direction.LEFT);
                        p.movingRight = false;
                        break;
                    default:
                        break;
                    }
                }
                p.switchedDirection = true;
            } else if (keyCode == left) {
                p.movingLeft = true;
                p.setDirection(Direction.LEFT);
                p.setLastDirection(Direction.LEFT);
                p.movingRight = false;
            } else if (keyCode == right) {
                p.movingRight = true;
                p.setDirection(Direction.RIGHT);
                p.setLastDirection(Direction.RIGHT);
                p.movingLeft = false;
            } else if (keyCode == jump) {
                p.jump();
                p.jumping = true;
            }
            break;
        case RELEASE:
            if (keyCode == forward) {
                switch (p.getLastDirection()) {
                case LEFT:
                    p.movingLeft = false;
                    break;
                case RIGHT:
                    p.movingRight = false;
                    break;
                default:
                    break;
                }
            } else if (keyCode == backward) {
                switch (p.getLastDirection()) {
                case LEFT:
                    p.movingLeft = false;
                    break;
                case RIGHT:
                    p.movingRight = false;
                    break;
                default:
                    break;
                }
                p.switchedDirection = false;
            } else if (keyCode == left) {
                p.movingLeft = false;
                // p.movingRight = false;
                p.setLastDirection(Direction.LEFT);
            } else if (keyCode == right) {
                // p.movingLeft = false;
                p.movingRight = false;
                p.setLastDirection(Direction.RIGHT);
            } else if (keyCode == jump) {
                p.jumping = false;
            }
            break;
        }
    }

    public void setGameEnded(boolean ended) {
        this.gameEnded = ended;
    }
}