package platformer.model.spritesheet.loader;

import java.util.AbstractSet;
import java.util.function.Predicate;

import platformer.model.GameAsset;
import platformer.model.spritesheet.Spritesheet;
import platformer.model.spritesheet.SpritesheetType;
import platformer.util.Loader;

/**
 * Ein spezialisierter {@link Loader}, der {@link Spritesheet}s lädt.
 * 
 * @author Jamil B.
 * @see Spritesheet
 * @see Loader
 */
public abstract class SpritesheetLoader<AssetType extends GameAsset> extends Loader<AssetType> {

    /**
     * Ein {@link Predicate}, der überprüft, ob ein {@link SpritesheetType} mit dem
     * Typ des {@link Spritesheet}s übereinstimmt.
     */
    protected final Predicate<SpritesheetType> matchesType;

    /**
     * Ein {@link Predicate}, der überprüft, ob der Dateiname mit dem Dateinamen des
     * {@link Spritesheet}s übereinstimmt.
     */
    protected final Predicate<String> matchesFile;

    /**
     * Das {@link Spritesheet}, das geladen wird.
     */
    protected final Spritesheet spritesheet;

    /**
     * Der Dateiname des {@link Spritesheet}s.
     */
    protected final String fileName;

    /**
     * Erstellt einen neuen SpritesheetLoader.
     * 
     * @param assets die Assets, die geladen werden sollen.
     * @param sheet  das Spritesheet, das geladen werden soll.
     * @param file   der Dateiname des Spritesheets.
     */
    public SpritesheetLoader(AbstractSet<AssetType> assets, Spritesheet sheet, String file) {
        super(assets);

        this.spritesheet = sheet;
        this.fileName = file;

        this.matchesType = (providedType) -> spritesheet.getType() == providedType;
        this.matchesFile = (providedFileName) -> fileName.equals(providedFileName);
    }

    /**
     * Registriert ein Asset.
     */
    protected final void registerAsset(AssetType asset) {
        loadedAssets.add(asset);
    }

    /**
     * Registriert mehrere Assets.
     */
    protected final void registerAssets(Iterable<AssetType> assets) {
        assets.forEach(this::registerAsset);
    }

    /**
     * Gibt das Spritesheet zurück.
     * 
     * @return das Spritesheet.
     */
    public final Spritesheet getSpritesheet() {
        return spritesheet;
    }

    /**
     * Gibt den Dateinamen des Spritesheets zurück.
     * 
     * @return der Dateiname des Spritesheets.
     */
    public final String getFileName() {
        return fileName;
    }
}