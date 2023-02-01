package me.fearmyshotz.adventure.model.animation;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import me.fearmyshotz.adventure.model.GameAsset;
import me.fearmyshotz.adventure.model.ResourceKey;
import me.fearmyshotz.adventure.model.animation.type.AnimationType;
import me.fearmyshotz.adventure.model.spritesheet.Spritesheet;
import me.fearmyshotz.adventure.util.Identifiable;

public class Animation extends GameAsset {

    public static final String NAMESPACE = "game:animation";

    private @Nullable AnimationType type;

    private Set<AnimationFrame> frames;

    private Spritesheet spritesheet;

    public Animation(int id, String name, String description, Spritesheet spritesheet) {
        super(id, name, description);

        this.spritesheet = spritesheet;
        this.frames = new HashSet<AnimationFrame>();

        setKey(new ResourceKey.Builder<Animation>(NAMESPACE, name).build());

        loadFrames();
    }

    public void loadFrames() {
        if (spritesheet == null) return;

        int row = id;

        for (int x = 0; x < spritesheet.getGridWidth(); x++) {
            frames.add(new AnimationFrame(x, spritesheet.getSprite(x, row)));
        }

    }

    public void setType(Identifiable i) {
        if (i instanceof AnimationType type) this.type = type;
    }

    public AnimationType getType() {
        return type;
    }

    public void addFrame(AnimationFrame frame) {
        frames.add(frame);
    }

    public AnimationFrame getFrame(int index) {
        return frames.stream()
            .filter(frame -> frame.getIndex() == index)
            .findFirst()
            .orElse(null);
    }

    public Set<AnimationFrame> getFrames() {
        return frames;
    }
}