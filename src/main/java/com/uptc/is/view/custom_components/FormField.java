package com.uptc.is.view.custom_components;

import com.uptc.is.util.FontLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormField extends JPanel{

    private final CustomTextField textField;

    public FormField(String labelText, int labelWidth) {
        setLayout(new BorderLayout(10, 5));
        setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(FontLoader.loadFont("/fonts/Montserrat-Bold.ttf", 14.5f));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setPreferredSize(new Dimension(labelWidth, 0));

        textField = new CustomTextField();
        textField.setBackground(Color.WHITE);

        this.add(label, BorderLayout.WEST);
        this.add(textField, BorderLayout.CENTER);
    }

    public void addActionListener(ActionListener action){
        this.textField.addActionListener(action);
    }

    public boolean requestFocusInWindow(){
        this.textField.requestFocusInWindow();
        return false;
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public void clearField(){
        textField.setText("");
    }

    public void setEditable(boolean editable){
        textField.setEditable(editable);
        if (!editable){
            textField.setBackground(new Color(230, 230, 230));
        }else{
            textField.setBackground(Color.WHITE);
        }
    }

}
