package com.saberslay.slayerCore;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.GlyphVector;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

public class Gui {
    public void DrawButton(Graphics2D g2, String text, int x, int y, int w, int h) {
        g2.setColor(new Color(30, 30, 25));
        g2.fillRect(x, y, w, h);
        Font font = g2.getFont();
        GlyphVector gv = font.createGlyphVector(g2.getFontRenderContext(), text);
        int textX = x + (w - (int)gv.getVisualBounds().getWidth()) / 2;
        int textY = y + (h + (int)gv.getVisualBounds().getHeight()) / 2;
        Shape shape = gv.getOutline(textX, textY);
        g2.setColor(new Color(205, 59, 19));
        g2.setStroke(new BasicStroke(4.0F));
        g2.draw(shape);
        g2.setColor(new Color(238, 182, 9));
        g2.fill(shape);
    }

    public void DrawOutlinedText(Graphics2D g2, String text, int x, int y, int w, int h, Font font) {
        g2.setFont(font);
        GlyphVector gv = font.createGlyphVector(
                g2.getFontRenderContext(), text);
        int textX = x + (w - (int)gv.getVisualBounds().getWidth()) / 2;
        int textY = y + (h + (int)gv.getVisualBounds().getHeight()) / 2;
        Shape shape = gv.getOutline(textX, textY);
        g2.setStroke(new BasicStroke(4.0F));
        g2.setColor(new Color(205, 59, 19));
        g2.draw(shape);
        g2.setColor(new Color(238, 182, 9));
        g2.fill(shape);
    }
}