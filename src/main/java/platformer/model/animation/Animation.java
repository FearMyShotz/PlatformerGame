package platformer.model.animation;

import java.util.HashSet;

import platformer.model.GameAsset;
import platformer.model.ResourceKey;
import platformer.model.animation.implementation.ItemAnimation;
import platformer.model.animation.implementation.NPCAnimation;
import platformer.model.animation.implementation.PlayerAnimation;
import platformer.model.animation.implementation.TileAnimation;
import platformer.model.animation.type.AnimationType;
import platformer.model.entity.implementation.type.GeneralEntityType;
import platformer.model.spritesheet.Spritesheet;
import platformer.util.Identifiable;

/**
 * Eine Animation, die die Klasse {@link GameAsset} erweitert und für die Darstellung von Entities, Items und Tiles verwendet wird.
 * Eine abstrakte Klasse, die von {@link ItemAnimation}, {@link NPCAnimation}, {@link PlayerAnimation} und {@link TileAnimation} erweitert wird.
 * 
 * @author Jamil B.
 * @see Identifiable
 * @see GameAsset
 */
public abstract class Animation extends GameAsset {

    /**
     * Der Namespace, der bei {@link ResourceKey}s für die Animationen verwendet wird.
     */
    public static transient final String NAMESPACE = ResourceKey.DEFAULT_NAMESPACE + "animation";

    /**
     * Der Animationstyp der Animation.
     */
    private AnimationType type;

    /**
     * Der Entitytyp der Animation.
     */
    private GeneralEntityType entityType;

    /**
     * Die Anzahl der Frames der Animation.
     */
    private int frameAmount;

    /**
     * Die Frames der Animation in einer {@link HashSet}.
     */
    private HashSet<AnimationFrame> frames;

    /**
     * Die Spritesheet, die die Frames der Animation enthält.
     */
    private Spritesheet spritesheet;

    /**
     * Erstellt eine neue Animation und ruft die Methode {@link #loadFrames()} auf, um die Frames der Animation zu laden.
     * 
     * @param id Die ID der Animation.
     * @param key Der Schlüssel der Animation.
     * @param spritesheet Die Spritesheet, die die Frames der Animation enthält.
     * @param type Der Animationstyp der Animation.
     * @param entityType Der Entitytyp der Animation.
     * @param frames Die Anzahl der Frames der Animation.
     */
    public Animation(int id, String key, Spritesheet spritesheet, AnimationType type, /*GeneralEntityType entityType,*/ int frames) {
        super(id, key, NAMESPACE, key);

        this.spritesheet = spritesheet;
        this.frames = new HashSet<AnimationFrame>();
        this.type = type;
        this.entityType = null;
        this.frameAmount = frames;

        loadFrames();
    }

    /**
     * Lädt die Frames der Animation aus der Spritesheet.
     * 
     * Verwendet einen {@link for}-Loop, um die Frames der Animation zu laden.
     */
    public void loadFrames() {
        if (spritesheet == null) return;

        int y = id, until = frameAmount == spritesheet.getGridWidth() ? spritesheet.getGridWidth() : frameAmount;

        for (int x = 0; x < until; x++) frames.add(new AnimationFrame(x, spritesheet.getSprite(x, y)));
    }

    /**
     * Setzt den Animationstyp der Animation.
     * 
     * @param type Der Animationstyp der Animation.
     */
    public void setType(Identifiable i) {
        if (i instanceof AnimationType type) this.type = type;
    }

    /**
     * Gibt den Animationstyp der Animation zurück.
     * 
     * @return Animationstyp der Animation.
     */
    public AnimationType getType() {
        return type;
    }

    /**
     * Gibt den Entitytyp der Animation zurück.
     * @return Entitytyp der Animation.
     */
    public GeneralEntityType getEntityType() {
        return entityType;
    }

    /**
     * Gibt die Anzahl der Frames der Animation zurück.
     * @return Anzahl der Frames der Animation.
     */
    public int getFrameAmount() {
        return frameAmount;
    }

    /**
     * Setzt die Anzahl der Frames der Animation.
     * 
     * @param frameAmount Die Anzahl der Frames der Animation.
     */
    public void setFrameAmount(int frameAmount) {
        this.frameAmount = frameAmount;
    }

    /**
     * Fügt der Animation einen Frame hinzu.
     * Dazu wird ein neuer {@link AnimationFrame} der {@link HashSet} der Frames hinzugefügt.
     * 
     * @param frame Der Frame, der hinzugefügt werden soll.
     */
    public void addFrame(AnimationFrame frame) {
        frames.add(frame);
    }

    /**
     * Gibt den Frame mit dem angegebenen Index zurück, sofern dieser existiert. Ansonsten wird {@code null} zurückgegeben.
     * Iteriert über das Attribut {@link #frames} mit einem {@link Stream} und gibt den Frame mit dem angegebenen Index zurück.
     * 
     * @param index Der Index des Frames.
     */
    public AnimationFrame getFrame(int index) {
        return frames.stream()
            .filter(frame -> frame.getIndex() == index)
            .findFirst()
            .orElse(null);
    }

    /**
     * Gibt die Frames der Animation als {@link HashSet} zurück.
     * @return Frames der Animation.
     */
    public HashSet<AnimationFrame> getFrames() {
        return frames;
    }

    /**
     * Gibt diese Animation als {@link String} zurück.
     * 
     * @return Diese Animation als {@link String}.
     */
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

    /**
     * Eine Builder-Klasse, die verwendet wird, um eine neue Animation zu erstellen.
     */
    public static class Builder {

        /**
         * Die ID der Animation.
         */
        private int id;

        /**
         * Der Schlüssel der Animation.
         */
        private String key;

        /**
         * Die Spritesheet, die die Frames der Animation enthält.
         */
        private Spritesheet spritesheet;

        /**
         * Der Animationstyp der Animation.
         */
        private AnimationType type;

        /**
         * Die Anzahl der Frames der Animation.
         */
        private int frameAmount;

        /**
         * Der Entitytyp der Animation.
         */
        private GeneralEntityType entityType;

        /**
         * Setzt die ID der Animation.
         * 
         * @param id Die ID der Animation.
         * @return Diese Builder-Instanz.
         */
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        /**
         * Setzt den Schlüssel der Animation.
         * 
         * @param name Der Schlüssel der Animation.
         * @return Diese Builder-Instanz.
         */
        public Builder setKey(String name) {
            this.key = name;
            return this;
        }

        /**
         * Setzt die Spritesheet, die die Frames der Animation enthält.
         * 
         * @param spritesheet Die Spritesheet, die die Frames der Animation enthält.
         * @return Diese Builder-Instanz.
         */
        public Builder setSpritesheet(Spritesheet spritesheet) {
            this.spritesheet = spritesheet;
            return this;
        }

        /**
         * Setzt den Animationstyp der Animation.
         * 
         * @param type Der Animationstyp der Animation.
         * @return Diese Builder-Instanz.
         */
        public Builder setType(AnimationType type) {
            this.type = type;
            return this;
        }

        /**
         * Setzt den Entitytyp der Animation.
         * 
         * @param entityType Der Entitytyp der Animation.
         * @return Diese Builder-Instanz.
         */
        public Builder setEntityType(GeneralEntityType entityType) {
            this.entityType = entityType;
            return this;
        }

        /**
         * Setzt die Anzahl der Frames der Animation.
         * 
         * @param frameAmount Die Anzahl der Frames der Animation.
         * @return Diese Builder-Instanz.
         */
        public Builder setFrameAmount(int frameAmount) {
            this.frameAmount = frameAmount;
            return this;
        }

        /**
         * Erstellt eine neue Animation aus diesem Builder. Verwendet die entsprechende Animation-Spezialisierung, je nach Entitytyp.
         * 
         * @return Eine neue Animation.
         */
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