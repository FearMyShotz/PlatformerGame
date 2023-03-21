package platformer.model.animation.loader;

import java.util.List;

import platformer.model.animation.Animation;
import platformer.model.animation.loader.implementation.EntityAnimationLoader;
import platformer.model.animation.loader.implementation.ItemAnimationLoader;
import platformer.model.spritesheet.Spritesheet;
import platformer.model.spritesheet.SpritesheetType;
import platformer.model.spritesheet.loader.SpritesheetLoader;
import platformer.util.Initializable;

import java.util.HashSet;

/**
 * Eine spezialisierte {@link SpritesheetLoader}-Implementierung, die für das Laden von Animationen verwendet wird.
 * Übergibt den Parameter des Typs (hier: {@link Animation}) an die {@link SpritesheetLoader}-Oberklasse.
 * 
 * Erstellt einen {@link EntityAnimationLoader} oder einen {@link ItemAnimationLoader}, je nachdem, welchen Typ das Spritesheet hat.
 * 
 * @author Jamil B.
 * @see Animation
 * @see SpritesheetLoader
 */
public class AnimationLoader extends SpritesheetLoader<Animation> implements Initializable<String> {

    /**
     * Der {@link SpritesheetLoader}, der für das Laden der Animationen verwendet wird.
     */
    private SpritesheetLoader<Animation> dedicatedLoader;

    /**
     * Erstellt einen neuen AnimationLoader.
     * 
     * @param sheet Das Spritesheet, die für das Laden der Animationen verwendet werden soll.
     * @param fileName Der Name der Datei, die für das Laden der Animationen verwendet werden soll.
     */
    public AnimationLoader(Spritesheet sheet, String fileName) {
        super(new HashSet<Animation>(), sheet, fileName);

        initialize(fileName);
    }

    /**
     * Initialisiert den AnimationLoader.
     * 
     * Dabei wird zwischen den Typen {@link SpritesheetType#ENTITY} und {@link SpritesheetType#ITEM} unterschieden und
     * {@link SpritesheetType#GUI} und {@link SpritesheetType#TILE} werden ignoriert.
     * 
     * Das Attribut {@link #dedicatedLoader} wird mit einem {@link EntityAnimationLoader} oder einem {@link ItemAnimationLoader} initialisiert.
     * 
     * Zuletzt wird die Methode {@link #load()} aufgerufen, um die Animationen zu laden.
     * 
     * @param fileName Der Name der Datei, die für das Laden der Animationen verwendet werden soll.
     */
    @Override
    public void initialize(String fileName) {
        if (matchesType.test(SpritesheetType.GUI) || matchesType.test(SpritesheetType.TILE)) return;

        this.dedicatedLoader = switch (spritesheet.getType()) {
            case ENTITY -> new EntityAnimationLoader(spritesheet, fileName);
            case ITEM -> new ItemAnimationLoader(spritesheet, fileName);
            default -> null;
        };

        if (dedicatedLoader != null) {
            this.loadedAssets = dedicatedLoader.load();
        }
    }

    /**
     * Gibt die Animationen als {@link List} zurück, die für das Item geladen werden sollen.
     * 
     * @return die Liste der geladenen Animationen.
     */
    public List<Animation> getLoadedAnimations() {
        return List.copyOf(loadedAssets);
    }

    /**
     * Lädt die Animationen, die für das Item geladen werden sollen.
     * 
     * Ruft die Methode {@link #load()} des {@link #dedicatedLoader}s auf.
     * 
     * @return Die Animationen, die für das Item geladen werden sollen.
     */
    @Override
    public HashSet<Animation> load() {
        return new HashSet<Animation>(dedicatedLoader.load());
    }
}