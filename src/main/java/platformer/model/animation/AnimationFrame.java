package platformer.model.animation;

import java.awt.image.BufferedImage;

public class AnimationFrame implements Comparable<AnimationFrame> {
    
    private int index;

    private BufferedImage frameImage;

    public AnimationFrame(int index, BufferedImage frameImage) {
        this.index = index;
        this.frameImage = frameImage;
    }

    public int getIndex() {
        return index;
    }

    public BufferedImage getFrameImage() {
        return frameImage;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setImage(BufferedImage frameImage) {
        this.frameImage = frameImage;
    }

    @Override
    public String toString() {
        return "AnimationFrame ["
        + "index=" + index
        + "]";
    }

    @Override
    public int compareTo(AnimationFrame f) {
        return Integer.compare(index, f.index);
    }
}