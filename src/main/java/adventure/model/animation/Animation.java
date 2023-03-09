package adventure.model.animation;

import java.util.Set;
import java.util.TreeSet;

import adventure.model.GameAsset;
import adventure.model.ResourceKey;
import adventure.model.animation.implementation.ItemAnimation;
import adventure.model.animation.implementation.NPCAnimation;
import adventure.model.animation.implementation.PlayerAnimation;
import adventure.model.animation.implementation.TileAnimation;
import adventure.model.animation.type.AnimationType;
import adventure.model.entity.implementation.type.GeneralEntityType;
import adventure.model.spritesheet.Spritesheet;
import adventure.util.Identifiable;

public abstract class Animation extends GameAsset {

    public static transient final String NAMESPACE = ResourceKey.DEFAULT_NAMESPACE + "animation";

    private AnimationType type;  
    private GeneralEntityType entityType;

    private int frameAmount;

    private Set<AnimationFrame> frames;

    private Spritesheet spritesheet;

    public Animation(int id, String key, Spritesheet spritesheet, AnimationType type, /*GeneralEntityType entityType,*/ int frames) {
        super(id, key, NAMESPACE, key);

        this.spritesheet = spritesheet;
        this.frames = new TreeSet<AnimationFrame>();
        this.type = type;
        this.entityType = null;
        this.frameAmount = frames;

        loadFrames();
    }

    public void loadFrames() {
        if (spritesheet == null) return;

        int y = id;

        int until = frameAmount == spritesheet.getGridWidth() ? spritesheet.getGridWidth() : frameAmount;

        for (int x = 0; x < until; x++) {
            frames.add(new AnimationFrame(x, spritesheet.getSprite(x, y)));
        }
    }

    public void setType(Identifiable i) {
        if (i instanceof AnimationType type) this.type = type;
    }

    public AnimationType getType() {
        return type;
    }

    public GeneralEntityType getEntityType() {
        return entityType;
    }

    public int getFrameAmount() {
        return frameAmount;
    }

    public void setFrameAmount(int frameAmount) {
        this.frameAmount = frameAmount;
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

    @Override
    public String toString() {
        return "Animation ["
            + "key=" + getKey()
            + "id=" + id
            + ", type=" + type
            + ", entityType=" + entityType
            + ", frameAmount=" + frameAmount 
            + ", frames=" + frames 
            + ", spritesheet=" + spritesheet
            + ", key=" + key + "]";
    }

    public static class Builder {

        private int id;

        private String key;

        private Spritesheet spritesheet;

        private AnimationType type;

        private int frameAmount;

        private GeneralEntityType entityType;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setKey(String name) {
            this.key = name;
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

        public Builder setEntityType(GeneralEntityType entityType) {
            this.entityType = entityType;
            return this;
        }

        public Builder setFrameAmount(int frameAmount) {
            this.frameAmount = frameAmount;
            return this;
        }

        public Animation build() {
            return switch (entityType) {
                case PLAYER -> new PlayerAnimation(id, key, spritesheet, type, frameAmount);
                case NPC -> new NPCAnimation(id, key, spritesheet, type, frameAmount);
                case ITEM -> new ItemAnimation(id, key, spritesheet, type, frameAmount);
                case TILE -> new TileAnimation(id, key, spritesheet, type, frameAmount);
            };
        }
    }
}