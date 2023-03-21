package platformer.model.animation;

import java.awt.image.BufferedImage;

/**
 * Ein Frame der Animation. Wird in einer {@link HashSet} als Attribut einer {@link Animation} gespeichert.
 * 
 * @author Jamil B.
 * @see Comparable
 */
public final class AnimationFrame implements Comparable<AnimationFrame> {
    
    /**
     * Der Index des Frames.
     */
    private int index;

    /**
     * Das Bild des Frames.
     */
    private BufferedImage frameImage;

    /**
     * Erstellt einen neuen Frame einer Animation.
     * 
     * @param index Der Index des Frames.
     * @param frameImage Das Bild des Frames.
     */
    public AnimationFrame(int index, BufferedImage frameImage) {
        this.index = index;
        this.frameImage = frameImage;
    }

    /**
     * Gibt den Index des Frames zurück.
     * 
     * @return Der Index des Frames.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gibt das Bild des Frames zurück.
     * 
     * @return Das Bild des Frames.
     */
    public BufferedImage getFrameImage() {
        return frameImage;
    }

    /**
     * Setzt den Index des Frames.
     * 
     * @param index Der Index des Frames.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Setzt das Bild des Frames.
     * 
     * @param frameImage Das Bild des Frames.
     */
    public void setImage(BufferedImage frameImage) {
        this.frameImage = frameImage;
    }

    /**
     * Gibt den Frame als String zurück.
     * 
     * @return Der Frame als String.
     */
    @Override
    public String toString() {
        return "AnimationFrame ["
        + "index=" + index
        + "]";
    }

    /**
     * Vergleicht diesen Frame mit einem anderen Frame.
     * 
     * @param f Der andere Frame.
     * @return {@code true}, wenn die Indexe der Frames gleich sind, ansonsten {@code false}.
     */
    @Override
    public int compareTo(AnimationFrame f) {
        return Integer.compare(index, f.index);
    }
}