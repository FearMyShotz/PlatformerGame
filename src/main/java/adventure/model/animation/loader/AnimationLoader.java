package adventure.model.animation.loader;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import adventure.model.animation.Animation;
import adventure.model.animation.loader.implementation.EntityAnimationLoader;
import adventure.model.animation.loader.implementation.ItemAnimationLoader;
import adventure.model.spritesheet.Spritesheet;
import adventure.model.spritesheet.SpritesheetType;
import adventure.model.spritesheet.loader.SpritesheetLoader;
import adventure.util.Initializable;

public class AnimationLoader extends SpritesheetLoader<Animation> implements Initializable<String> {

    private SpritesheetLoader<Animation> dedicatedLoader;

    public AnimationLoader(Spritesheet sheet, String fileName) {
        super(new TreeSet<Animation>(), sheet, fileName);

        // System.out.println("Initializing animation loader for " + fileName + "...");

        initialize(fileName);
    }

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

    public List<Animation> getLoadedAnimations() {
        return List.copyOf(loadedAssets);
    }

    @Override
    public Set<Animation> load() {
        return dedicatedLoader.load();
    }
}