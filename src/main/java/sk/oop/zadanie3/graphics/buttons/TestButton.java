package sk.oop.zadanie3.graphics.buttons;

import sk.oop.zadanie3.graphics.NetCanvas;
import sk.oop.zadanie3.petrinet.PetriNet;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TestButton extends NetButton { //Button na vytlacenie obsahu PetriNet aj Drawables do konzoly - nebude vo final pouzity
    public TestButton(String label, NetCanvas netCanvas, PetriNet net) throws HeadlessException {
        super(label, netCanvas);
        this.net = net;
        this.addActionListener(this);
    }

    private PetriNet net;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("\nPlaces:"+net.getPlaces());
        System.out.println("Trans:"+net.getTransitions());
        System.out.println("Arcs:"+net.getArcs());
        System.out.println("Drawables"+myCanvas.getElements()+"\n");
    }
}
