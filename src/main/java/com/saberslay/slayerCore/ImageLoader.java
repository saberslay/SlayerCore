package com.saberslay.slayerCore;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class ImageLoader {
    public static void setIcon(JFrame frame, String fileName) {
        try {
            File file = new File("resources/gfx/" + fileName + ".png");
            Image icon = ImageIO.read(file);
            frame.setIconImage(icon);
        } catch (IOException e) {
            Logger.warn("Failed to load window icon: " + fileName);
            e.printStackTrace();
        }
    }

    public static BufferedImage loadImage(String fileName) {
        try {
            File file = new File("resources/gfx/" + fileName + ".png");
            return ImageIO.read(file);
        } catch (IOException e) {
            Logger.warn("Failed to load image: " + fileName);
            e.printStackTrace();
            return null;
        }
    }
}