package platformer.window;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.FocusEvent.Cause;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import platformer.PlatformerGame;
import platformer.input.keyboard.listener.DefaultKeyListener;
import platformer.input.mouse.listener.DefaultMouseListener;

/**
 * Das Panel, auf dem das Spiel gezeichnet wird. Erweitert {@link JPanel}.
 * 
 * @author Jamil B.
 */
public class GamePanel extends JPanel {

    /**
     * Der Listener für Tastatureingaben.
     */
    private final KeyListener keyListener;

    /**
     * Der Listener für Mausereignisse.
     */
    private final DefaultMouseListener mouseListener;

    /**
     * Erstellt ein neues GamePanel.
     */
    public GamePanel() {
        this.keyListener = new DefaultKeyListener();
        this.mouseListener = new DefaultMouseListener();

        this.addKeyListener(keyListener);

        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        this.setFocusable(true);
        this.requestFocus(Cause.ACTIVATION);
        
        setScreenSize(GameWindow.WIDTH, GameWindow.HEIGHT);
    }

    /**
     * Zeichnet das Spiel.
     * 
     * @param g das Graphics-Objekt, auf dem gezeichnet wird
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (PlatformerGame.getInstance().getActiveState() == null) return;

        PlatformerGame.getInstance().getActiveState().render(g);
    }

    /**
     * Setzt die Größe des Panels.
     * 
     * @param width die Breite des Panels
     * @param height die Höhe des Panels
     */
    private void setScreenSize(int width, int height) {
        Dimension screenSize = new Dimension(width, height);

        this.setPreferredSize(screenSize);

        this.setMinimumSize(screenSize);
        this.setMaximumSize(screenSize);
    }

    /**
     * Gibt den KeyListener zurück.
     * 
     * @return der KeyListener
     */
    public KeyListener getKeyListener() {
        return keyListener;
    }

    /**
     * Gibt den MouseListener zurück.
     * 
     * @return der MouseListener
     */
    public DefaultMouseListener getMouseListener() {
        return mouseListener;
    }
}