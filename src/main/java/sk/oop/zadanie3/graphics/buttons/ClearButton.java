package sk.oop.zadanie3.graphics.buttons;

import sk.oop.zadanie3.graphics.NetCanvas;
import sk.oop.zadanie3.petrinet.PetriNet;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ClearButton extends NetButton {
    public ClearButton(String label, NetCanvas netCanvas, PetriNet net) throws HeadlessException {
        super(label, netCanvas);
        this.net = net;
        this.addActionListener(this);
    }

    private PetriNet net;

    @Override
    public void actionPerformed(ActionEvent e) {
        net.clearPetriNet();
        myCanvas.clearCanvas();
        myCanvas.repaint();
    }
}
