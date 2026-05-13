package com.saberslay.slayercore.core.io;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import com.saberslay.slayercore.core.logging.Level;
import com.saberslay.slayercore.core.logging.Logger;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ImageLoader {

    private static final String BASE_PATH = "/";

    public static void setIcon(JFrame frame, String fileName) {
        BufferedImage icon = loadImage(fileName);

        if (icon != null) {
            frame.setIconImage(icon);
        }
    }

    public static BufferedImage loadImage(String fileName) {
        String path = BASE_PATH + fileName;

        try (InputStream is = ImageLoader.class.getResourceAsStream(path)) {
            if (is == null) {
                Logger.log(Level.WARNING,"Image not found on classpath: " + path);
                return null;
            }
            return ImageIO.read(is);
        } catch (IOException e) {
            Logger.log(Level.WARNING,"Failed to load image: " + path);
            e.printStackTrace();
            return null;
        }
    }
}