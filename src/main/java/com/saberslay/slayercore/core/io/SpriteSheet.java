package com.saberslay.slayercore.core.io;

import com.saberslay.slayercore.core.logging.Level;
import com.saberslay.slayercore.core.logging.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    private String path;
    public final int SIZE;
    public int[] pixels;

    public SpriteSheet(String path, int size) {
        this.path = path;
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    private  void load() {
        try {
            Logger.log(Level.INFO,"Trying to load: " + path + "...");
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0,0, w, h, pixels,0, w);
            Logger.log(Level.INFO,"Successfully loaded: " + path + "...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }  catch (Exception e) {
            Logger.log(Level.ERROR,"Failed to load: " + path + "!");
        }
    }
}