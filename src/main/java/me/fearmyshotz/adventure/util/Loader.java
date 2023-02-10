package me.fearmyshotz.adventure.util;

import java.util.Set;

import me.fearmyshotz.adventure.model.GameAsset;

public abstract class Loader<R extends GameAsset> {

    protected final Set<R> loadedAssets;

    public Loader(Set<R> assets) {
        this.loadedAssets = assets;
    }

    public abstract Set<R> load();

    public Set<R> getLoadedAssets() {
        return loadedAssets;
    }
}