package platformer.model.state;

import java.awt.Graphics;

import platformer.PlatformerGame;
import platformer.input.keyboard.KeyAction;
import platformer.input.keyboard.layout.KeyboardLayout;
import platformer.model.Direction;
import platformer.model.entity.implementation.player.Player;
import platformer.model.level.LevelManager;

/**
 * Ein Spielzustand, welcher Spiellogik für das eigentliche Spiel enthält und dargestellt werden kann.
 * 
 * @author Jamil B.
 * @see Ticking
 * @see Rendering
 */
public class PlayState extends GameState {

    /**
     * Der LevelManager, welcher die Level verwaltet.
     */
    private final LevelManager levelManager;

    /**
     * Ein Wahrheitswert, welcher angibt, ob das Spiel beendet wurde.
     */
    private boolean gameEnded;
                                 
    /**
     * Erstellt einen neuen Spiel-Spielzustand.
     * 
     * @param game die Instanz des Spiels, in welchem sich der Spielzustand befindet
     */
    public PlayState(PlatformerGame game) {
        super(game);

        this.levelManager = game.getLevelManager();
    }

    /**
     * Gibt den LevelManager zurück.
     * 
     * @return der LevelManager
     */
    public LevelManager getLevelManager() {
        return levelManager;
    }

    /**
     * Gibt den Spieler zurück.
     * 
     * @return der Spieler
     */
    public Player getPlayer() {
        return levelManager.getCurrentLevel().getPlayer();
    }

    /**
     * Führt die Spiellogik für einen Tick aus.
     * 
     * @see Ticking#tick()
     */
    @Override
    public void tick() {
        if (gameEnded) return;

        levelManager.getCurrentLevel().tick();
    }

    /**
     * Zeichnet den Spielzustand.
     * 
     * @see Rendering#render(Graphics)
     * @param g das Graphics-Objekt, mit welchem gezeichnet wird
     */
    @Override
    public void render(Graphics g) {
        levelManager.getCurrentLevel().render(g);
    }

    /**
     * Verarbeitet eine Tastenaktion und aktualisiert die Attribute des Spielers entsprechend.
     * 
     * @param action die Tastenaktion
     * @param keyCode der Tastencode
     */
    @Override
    public void handleKeyAction(KeyAction action, int keyCode) {
        KeyboardLayout keyboardLayout = game.getInputManager().getKeyboardLayout();
        Player p = levelManager.getCurrentLevel().getPlayer();

        int forward, backward, left, right, jump;
        forward = keyboardLayout.getForwardKey().getKeyCode();
        backward = keyboardLayout.getSwitchKey().getKeyCode();
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
                p.setLastDirection(Direction.LEFT);
            } else if (keyCode == right) {
                p.movingRight = false;
                p.setLastDirection(Direction.RIGHT);
            } else if (keyCode == jump) {
                p.jumping = false;
            }
            break;
        }
    }

    /**
     * Setzt den Wahrheitswert, ob das Spiel beendet wurde.
     * 
     * @param ended der Wahrheitswert
     */
    public void setGameEnded(boolean ended) {
        this.gameEnded = ended;
    }
}