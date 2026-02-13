package com.saberslay.slayerCore;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;

public class ImageLoader {

    private static final String BASE_PATH = "/gfx/";

    public static void setIcon(JFrame frame, String fileName) {
        String path = BASE_PATH + fileName + ".png";

        try (InputStream is = ImageLoader.class.getResourceAsStream(path)) {
            if (is == null) {
                Logger.warn("Icon not found on classpath: " + path);
                return;
            }

            Image icon = ImageIO.read(is);
            frame.setIconImage(icon);

        } catch (IOException e) {
            Logger.warn("Failed to load window icon: " + path);
            e.printStackTrace();
        }
    }

    public static BufferedImage loadImage(String fileName) {
        String path = BASE_PATH + fileName + ".png";

        try (InputStream is = ImageLoader.class.getResourceAsStream(path)) {
            if (is == null) {
                Logger.warn("Image not found on classpath: " + path);
                return null;
            }
            return ImageIO.read(is);
        } catch (IOException e) {
            Logger.warn("Failed to load image: " + path);
            e.printStackTrace();
            return null;
        }
    }
}