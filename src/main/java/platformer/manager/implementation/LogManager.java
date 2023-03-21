package platformer.manager.implementation;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import platformer.PlatformerGame;
import platformer.manager.Manager;

/**
 * Der LogManager ist der Manager, der für die Verwaltung des Loggers zuständig ist.
 * 
 * Wird derzeit nicht verwendet. (Stand: 21.03.2023)
 * 
 * @author Jamil B.
 * @see Manager
 */
public class LogManager extends Manager {

    /**
     * Erstellt einen neuen LogManager.
     * 
     * @param game Instanz der Hauptklasse des Spiels
     */
    public LogManager(PlatformerGame game) {
        super(game);
    }

    /**
     * Initialisiert den LogManager.
     * 
     * @param game Instanz der Hauptklasse des Spiels
     */
    @Override
    public void initialize(PlatformerGame game) {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomFormatter());

        // game.getLogger().addHandler(handler);
    }

    /**
     * Die CustomFormatter-Klasse ist eine Unterklasse der Formatter-Klasse.
     * Sie formatiert die Log-Ausgaben.
     * 
     * @author Jamil B.
     * @see Formatter
     */
    class CustomFormatter extends Formatter {

        @Override
        public String format(LogRecord record) {
            return new StringBuilder()
                    .append("[" + record.getLevel().getName() + "]")
                    .append("[" + record.getLoggerName() + "]")
                    .append(record.getMessage())
                    .append(System.lineSeparator())
                    .toString();
        }
    }
}