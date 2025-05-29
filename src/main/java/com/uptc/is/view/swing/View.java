package com.uptc.is.view.swing;

import java.awt.event.ActionListener;

public class View {

    private final MainFrame frame;

    public View(ActionListener listener) {
        frame = new MainFrame(listener);
    }

    //GETTERS Y SETTERS DE VISTA
    //....

    public MainFrame getFrame(){
        return frame;
    }

}
