package sk.oop.zadanie3.listeners;

import sk.oop.zadanie3.graphics.Drawable;
import sk.oop.zadanie3.graphics.NetCanvas;
import sk.oop.zadanie3.petrinet.PetriNet;

import java.awt.event.MouseListener;

public abstract class AddArcListener extends NetListener implements MouseListener { //  abstraktna trieda pre listnery na pridavanie hran

    public AddArcListener(NetCanvas myCanvas, PetriNet net) {
        super(myCanvas, net);
    }

    protected Drawable start = null;
    protected Drawable end = null;
}
