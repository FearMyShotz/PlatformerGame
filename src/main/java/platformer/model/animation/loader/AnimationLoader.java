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

public class AnimationLoader extends SpritesheetLoader<Animation> implements Initializable<String> {

    private SpritesheetLoader<Animation> dedicatedLoader;

    public AnimationLoader(Spritesheet sheet, String fileName) {
        super(new HashSet<Animation>(), sheet, fileName);

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
    public HashSet<Animation> load() {
        return new HashSet<Animation>(dedicatedLoader.load());
    }
}