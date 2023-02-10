package me.fearmyshotz.adventure.manager.implementation;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

import me.fearmyshotz.adventure.AdventureGame;
import me.fearmyshotz.adventure.manager.Manager;

public class LogManager extends Manager {

    public LogManager(AdventureGame game) {
        super(game);
    }

    @Override
    public void initialize(AdventureGame game) {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomFormatter());

        // game.getLogger().addHandler(handler);
    }

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