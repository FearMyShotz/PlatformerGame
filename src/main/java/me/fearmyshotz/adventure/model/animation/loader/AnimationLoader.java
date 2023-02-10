package me.fearmyshotz.adventure.model.animation.loader;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import me.fearmyshotz.adventure.model.animation.Animation;
import me.fearmyshotz.adventure.model.spritesheet.Spritesheet;
import me.fearmyshotz.adventure.model.spritesheet.SpritesheetType;
import me.fearmyshotz.adventure.util.Initializable;
import me.fearmyshotz.adventure.util.SpritesheetLoader;

public class AnimationLoader extends SpritesheetLoader<Animation> implements Initializable<String> {

    public AnimationLoader(Spritesheet sheet, String fileName) {
        super(new TreeSet<Animation>(), sheet, fileName);

        initialize(fileName);
    }

    @Override
    public void initialize(String fileName) {
        if (matchesType.test(SpritesheetType.GUI) || matchesType.test(SpritesheetType.TILE)) return;

        switch (spritesheet.getType()) {
            case ENTITY:
                initializeEntityAnimations(fileName);
                break;
            case ITEM:
            default:
                throw new UnsupportedOperationException(getClass().getSimpleName() + " kann keine Animationen von " + spritesheet.getType() + " laden!");
        }
    }

    public void initializeEntityAnimations(String fileName) {
        if (fileName.equals("player_left")) {
        //     Animation walkLeft = new Animation(0, "player_walk_left", "Nach links laufender Spieler", spritesheet);
        //     walkLeft.setType(AnimationType.WALK_LEFT);
        //     loadedAnimations.add(walkLeft);

        //     Animation idle = new Animation(1, "player_idle", "Stehender Spieler", spritesheet);
        //     idle.setType(AnimationType.IDLE);
        //     loadedAnimations.add(idle);

        //     Animation attackLeft = new Animation(2, "player_attack_left", "Nach links angreifender Spieler", spritesheet);
        //     attackLeft.setType(AnimationType.ATTACK_LEFT);
        //     loadedAnimations.add(attackLeft);

        //     Animation jumpLeft = new Animation(3, "player_jump_left", "Nach links springender Spieler", spritesheet);
        //     jumpLeft.setType(AnimationType.JUMP_LEFT);
        //     loadedAnimations.add(jumpLeft);

        //     Animation hitLeft = new Animation(4, "player_hit_left", "Von rechts getroffener Spieler", spritesheet);
        //     hitLeft.setType(AnimationType.HIT_LEFT);
        //     loadedAnimations.add(hitLeft);
        // }
        // if (fileName.equals("player_right")) {
        //     Animation walkRight = new Animation(0, "player_walk_right", "Nach rechts laufender Spieler", spritesheet);
        //     walkRight.setType(AnimationType.WALK_RIGHT);

        //     loadedAnimations.add(walkRight);

        }
    }

    public List<Animation> getLoadedAnimations() {
        return List.copyOf(loadedAssets);
    }

    @Override
    public Set<Animation> load() {
        return null;
    }
}