package com.uptc.is.view.custom_components;

import java.awt.*;

public class ModernButtonFactory {

    public static ModernButton primary(String text) {
        ModernButton btn = new ModernButton(text);
        btn.setBackgroundColors(
                new Color(14, 121, 182),
                new Color(64, 161, 212),
                new Color(10, 55, 95)
        );
        btn.setForegroundColors(Color.WHITE, Color.WHITE);
        btn.setBorderColor(new Color(14, 121, 182));
        return btn;
    }

    public static ModernButton success(String text) {
        ModernButton btn = new ModernButton(text);
        btn.setBackgroundColors(
                new Color(46, 204, 113),
                new Color(88, 214, 141),
                new Color(30, 132, 73)
        );
        btn.setForegroundColors(Color.WHITE, Color.WHITE);
        btn.setBorderColor(new Color(46, 204, 113));
        return btn;
    }

    public static ModernButton danger(String text) {
        ModernButton btn = new ModernButton(text);
        btn.setBackgroundColors(
                new Color(220, 53, 69),
                new Color(227, 78, 91),
                new Color(194, 47, 60)
        );
        btn.setForegroundColors(Color.WHITE, Color.WHITE);
        btn.setBorderColor(new Color(220, 53, 69));
        return btn;
    }

    public static ModernButton variant(String text) {
        ModernButton btn = new ModernButton(text);
        btn.setBackgroundColors(
                new Color(227, 93, 48),
                new Color(247, 133, 88),
                new Color(150, 50, 20)
        );
        btn.setForegroundColors(Color.WHITE, Color.WHITE);
        btn.setBorderColor(new Color(227, 93, 48));
        return btn;
    }

    public static ModernButton transparent(String text) {
        ModernButton btn = new ModernButton(text);
        btn.setBackgroundColors(
                new Color(0, 0, 0,0),
                new Color(50, 50, 50, 80),
                new Color(46, 204, 113)
        );
        btn.setForegroundColors(new Color(50, 50, 50), new Color(50, 50, 50));
        btn.setBorderColor(new Color(50, 50, 50));
        return btn;
    }

}
