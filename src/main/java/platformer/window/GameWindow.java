package platformer.window;

import javax.swing.JFrame;

public class GameWindow {
    
    public static final float SCALE = 2f;

    public static final int DEFAULT_TILE_SIZE = 32;
    public static final int DEFAULT_ENTITY_SIZE = 32;

    // public static final int ENTITY_SIZE_INCREMENT = 10;

    public static final int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
    // public static final int ENTITY_SIZE = (int) (DEFAULT_ENTITY_SIZE * (SCALE));
    public static final int ENTITY_SIZE = (int) (DEFAULT_ENTITY_SIZE * SCALE);

    public static final int TILES_X = 26;
    public static final int TILES_Y = 18;

    public static final int HEIGHT = TILES_Y * TILE_SIZE; // 1152
    public static final int WIDTH = TILES_X * TILE_SIZE; // 1664

    private final JFrame frame;

    public GameWindow(GamePanel panel) {
        frame = new JFrame("PlatformerGame");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}