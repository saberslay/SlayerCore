package com.saberslay.slayerCore.gui;

import javax.swing.*;
import java.awt.*;

public abstract class SlayerWindow extends JFrame {

    private static final String DEFAULT_TITLE = "Slayer Window";
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private final int width;
    private final int height;

    public SlayerWindow(String title, int width, int height) {
        super(title == null ? DEFAULT_TITLE : title);
        this.width = (width <= 0) ? DEFAULT_WIDTH : width;
        this.height = (height <= 0) ? DEFAULT_HEIGHT : height;

        configureWindow();
        createCanvas();
        init(); // your custom setup
    }

    private void configureWindow() {
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
    }

    private void createCanvas() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                render((Graphics2D) g);
            }
        };

        panel.setDoubleBuffered(true);
        add(panel, BorderLayout.CENTER);
    }

    protected abstract void init(); // setup components
    protected abstract void render(Graphics2D g2); // draw custom graphics

    public void launch() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }
}