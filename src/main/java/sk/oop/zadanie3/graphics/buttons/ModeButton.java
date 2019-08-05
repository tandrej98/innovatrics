package sk.oop.zadanie3.graphics.buttons;

import sk.oop.zadanie3.graphics.NetCanvas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

public class ModeButton extends NetButton {

    public ModeButton(String label, NetCanvas netCanvas, MouseListener mouseListener) throws HeadlessException {
        super(label, netCanvas);
        this.mouseListener = mouseListener;
        this.addActionListener(this);
    }

    private MouseListener mouseListener;

    @Override
    public void actionPerformed(ActionEvent e) {
        MouseListener[] mouseListeners = myCanvas.getMouseListeners();
        for(MouseListener mouseListener : mouseListeners){
            myCanvas.removeMouseListener(mouseListener);
        }
        myCanvas.addMouseListener(mouseListener);
    }
}
