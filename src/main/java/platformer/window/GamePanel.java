package platformer.window;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.FocusEvent.Cause;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import platformer.PlatformerGame;
import platformer.input.keyboard.listener.DefaultKeyListener;
import platformer.input.mouse.listener.DefaultMouseListener;

public class GamePanel extends JPanel {

    private final KeyListener keyListener;
    private final DefaultMouseListener mouseListener;

    public GamePanel() {
        this.keyListener = new DefaultKeyListener();
        this.mouseListener = new DefaultMouseListener();

        this.addKeyListener(keyListener);

        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        this.setFocusable(true);
        this.requestFocus(Cause.ACTIVATION);
        
        // setScreenSize(1664, 900);
        setScreenSize(GameWindow.WIDTH, GameWindow.HEIGHT);
        // setScreenSize(1920, 1080);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (PlatformerGame.getInstance().getActiveState() == null) return;

        PlatformerGame.getInstance().getActiveState().render(g);
    }

    private void setScreenSize(int width, int height) {
        Dimension screenSize = new Dimension(width, height);

        this.setPreferredSize(screenSize);

        this.setMinimumSize(screenSize);
        this.setMaximumSize(screenSize);
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }

    public DefaultMouseListener getMouseListener() {
        return mouseListener;
    }
}