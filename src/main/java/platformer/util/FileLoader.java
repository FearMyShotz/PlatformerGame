package platformer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashSet;

import platformer.model.GameAsset;

public abstract class FileLoader<AssetType extends GameAsset> extends Loader<AssetType> {
    
    protected String filePath;

    public FileLoader(HashSet<AssetType> assets, String path) {
        super(assets);

        this.filePath = path;
    }

    public String getFilePath() {
        return filePath;
    }

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