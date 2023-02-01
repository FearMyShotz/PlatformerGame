package me.fearmyshotz.adventure.model.animation;

import java.awt.image.BufferedImage;

public class AnimationFrame {
    
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

}