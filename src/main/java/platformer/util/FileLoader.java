package platformer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashSet;

import platformer.model.GameAsset;

/**
 * Ein abstrakter {@link Loader}, der {@link GameAsset}s aus einer Datei lädt.
 * 
 * @author Jamil B.
 */
public abstract class FileLoader<AssetType extends GameAsset> extends Loader<AssetType> {
    
    /**
     * Der Pfad zur Datei.
     */
    protected String filePath;

    /**
     * Erstellt einen neuen FileLoader.
     * 
     * @param assets die Assets, die geladen werden sollen
     * @param path der Pfad zur Datei
     */
    public FileLoader(HashSet<AssetType> assets, String path) {
        super(assets);

        this.filePath = path;
    }

    /**
     * Gibt den Pfad zur Datei zurück.
     * 
     * @return der Pfad zur Datei
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Gibt einen {@link BufferedReader} zurück, der aus der Datei oder aus einer JAR lesen kann.
     * 
     * @param path der Pfad zur Datei
     * @param jarPath der Pfad zur Datei in der JAR
     * 
     * @return der {@link BufferedReader}
     */
    protected BufferedReader read(String path, String jarPath) {
        BufferedReader reader = null;

        try { // Aus Datei lesen
            reader = new BufferedReader(new FileReader(new File(path)));
        } catch (final FileNotFoundException e) { // In Jar lesen
            reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(jarPath)));
        }

        return reader;
    }
}