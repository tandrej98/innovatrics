package sk.oop.zadanie3.graphics.buttons;

import sk.oop.zadanie3.graphics.NetCanvas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class NetButton extends Button implements ActionListener { //abstraktna trieda pre tlacidla

    public NetButton(String label, NetCanvas netCanvas) throws HeadlessException {
        super(label);
        this.myCanvas = netCanvas;
    }

    protected NetCanvas myCanvas;

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
