package com.saberslay.slayercore.core.system;

/*
 * SlayerCore
 * Copyright (c) 2026 saberslay
 * Licensed under the MIT License.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UIButtons {

    public static JButton action(
            String title,
            String desc,
            Color foreground,
            Color background,
            ActionListener listener
    ) {
        JButton btn = new JButton("<html><b>" + title + "</b><br>" + desc + "</html>");
        btn.setForeground(foreground);
        btn.setBackground(background);
        btn.setFont(new Font("Consolas", Font.PLAIN, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(foreground, 1));
        btn.setHorizontalAlignment(SwingConstants.LEFT);

        if (listener != null) {
            btn.addActionListener(listener);
        }

        return btn;
    }
}