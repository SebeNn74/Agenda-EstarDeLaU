package com.uptc.is.view;

import java.awt.event.ActionListener;

public class View {

    private final AppFrame frame;

    public View(ActionListener listener) {
        frame = new AppFrame(listener);
    }

    //GETTERS Y SETTERS DE VISTA
    //....

    public AppFrame getFrame(){
        return frame;
    }

}
