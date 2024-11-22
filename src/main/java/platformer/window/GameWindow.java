package platformer.window;

import javax.swing.JFrame;

/**
 * Die Klasse, die das Fenster des Spiels verwaltet.
 * 
 * @author Jamil B.
 */
public class GameWindow {
    
    /**
     * Die Skalierungsfaktor des Spiels.
     */
    public static final float SCALE = 1f;

    /**
     * Die Standardgröße eines Tiles.
     */
    public static final int DEFAULT_TILE_SIZE = 32;

    /**
     * Die Standardgröße eines Entities.
     */
    public static final int DEFAULT_ENTITY_SIZE = 32;

    /**
     * Die Größe eines Tiles, multipliziert mit dem Skalierungsfaktor {@link #SCALE}.
     */
    public static final int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);

    /**
     * Die Größe eines Entities, multipliziert mit dem Skalierungsfaktor {@link #SCALE}.
     */
    public static final int ENTITY_SIZE = (int) (DEFAULT_ENTITY_SIZE * SCALE);

    /**
     * Die Anzahl der Tiles in X-Richtung.
     */
    public static final int TILES_X = 26;

    /**
     * Die Anzahl der Tiles in Y-Richtung.
     */
    public static final int TILES_Y = 18;

    /**
     * Die Breite des Fensters, multipliziert mit der Anzahl an Tiles {@link #TILES_X}.
     */
    public static final int WIDTH = TILES_X * TILE_SIZE; // 1664

    /**
     * Die Höhe des Fensters, multipliziert mit der Anzahl an Tiles {@link #TILES_Y}.
     */
    public static final int HEIGHT = TILES_Y * TILE_SIZE; // 1152

    /**
     * Das Fenster, in dem das Spiel läuft.
     */
    private final JFrame frame;

    /**
     * Erstellt ein neues GameWindow.
     * 
     * @param panel das GamePanel, das in das Fenster gezeichnet wird
     */
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
