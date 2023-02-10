package me.fearmyshotz.adventure.util;

import java.util.Set;
import java.util.function.Predicate;

import me.fearmyshotz.adventure.model.GameAsset;
import me.fearmyshotz.adventure.model.spritesheet.Spritesheet;
import me.fearmyshotz.adventure.model.spritesheet.SpritesheetType;

public abstract class SpritesheetLoader<R extends GameAsset> extends Loader<R> {

    protected final Predicate<SpritesheetType> matchesType;
    protected final Predicate<String> matchesFile;

    protected final Spritesheet spritesheet;

    protected final String fileName;

    public SpritesheetLoader(Set<R> assets, Spritesheet sheet, String file) {
        super(assets);

        this.spritesheet = sheet;
        this.fileName = file;

        this.matchesType = (providedType) -> spritesheet.getType() == providedType;
        this.matchesFile = (providedFileName) -> fileName.equals(providedFileName);
    }

    protected final void registerAsset(R asset) {
        loadedAssets.add(asset);
    }

    public final Spritesheet getSpritesheet() {
        return spritesheet;
    }

    public final String getFileName() {
        return fileName;
    }
}