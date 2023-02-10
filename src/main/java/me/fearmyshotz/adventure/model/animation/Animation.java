package me.fearmyshotz.adventure.model.animation;

import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import me.fearmyshotz.adventure.model.AssetType;
import me.fearmyshotz.adventure.model.GameAsset;
import me.fearmyshotz.adventure.model.ResourceKey;
import me.fearmyshotz.adventure.model.animation.implementation.ItemAnimation;
import me.fearmyshotz.adventure.model.animation.implementation.NPCAnimation;
import me.fearmyshotz.adventure.model.animation.implementation.PlayerAnimation;
import me.fearmyshotz.adventure.model.animation.implementation.TileAnimation;
import me.fearmyshotz.adventure.model.animation.type.AnimationType;
import me.fearmyshotz.adventure.model.entity.implementation.type.GeneralEntityType;
import me.fearmyshotz.adventure.model.spritesheet.Spritesheet;
import me.fearmyshotz.adventure.util.Identifiable;

public abstract class Animation extends GameAsset {

    public static transient final String NAMESPACE = ResourceKey.DEFAULT_NAMESPACE + "animation";

    private @Nullable AnimationType type;
    private @Nullable AssetType assetType;    

    private Set<AnimationFrame> frames;

    private Spritesheet spritesheet;

    public Animation(int id, String key, String description, Spritesheet spritesheet, AnimationType type) {
        super(id, key, description);

        this.spritesheet = spritesheet;
        this.frames = new HashSet<AnimationFrame>();
        this.type = type;

        setKey(new ResourceKey.Builder<Animation>(NAMESPACE, key).build());

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

    public static class Builder {

        private int id;

        private String key;

        private String description;

        private Spritesheet spritesheet;

        private AnimationType type;

        private GeneralEntityType entityType;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setKey(String name) {
            this.key = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setSpritesheet(Spritesheet spritesheet) {
            this.spritesheet = spritesheet;
            return this;
        }

        public Builder setType(AnimationType type) {
            this.type = type;
            return this;
        }

        public Animation build() {
            return switch (entityType) {
                case PLAYER -> new PlayerAnimation(id, key, description, spritesheet, type);
                case NPC -> new NPCAnimation(id, key, description, spritesheet, type);
                case ITEM -> new ItemAnimation(id, key, description, spritesheet, type);
                case TILE -> new TileAnimation(id, key, description, spritesheet, type);
                default -> new Animation(id, key, description, spritesheet, type) {};
            };
        }
    }
}