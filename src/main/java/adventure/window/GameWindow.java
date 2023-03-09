package adventure.window;

import javax.swing.JFrame;

public class GameWindow {
    
    public static final float SCALE = 1.5f;

    public static final int DEFAULT_TILE_SIZE = 32;
    public static final int DEFAULT_ENTITY_SIZE = 32;

    public static final int ENTITY_SIZE_INCREMENT = 20;

    public static final int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
    // public static final int ENTITY_SIZE = (int) (DEFAULT_ENTITY_SIZE * (SCALE));
    public static final int ENTITY_SIZE = (int) (DEFAULT_ENTITY_SIZE * SCALE);

    public static final int TILES_X = 42;
    public static final int TILES_Y = 24;

    public static final int HEIGHT = TILES_Y * TILE_SIZE;
    public static final int WIDTH = TILES_X * TILE_SIZE;

    private JFrame frame;

    public GameWindow(GamePanel panel) {
        frame = new JFrame("Adventure Game");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(panel);
        frame.setUndecorated(true);
        // frame.getContentPane().setBackground(Color.decode("#0599fc"));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}