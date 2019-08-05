package sk.oop.zadanie3.listeners;

import sk.oop.zadanie3.graphics.NetCanvas;
import sk.oop.zadanie3.graphics.Transition2D;
import sk.oop.zadanie3.petrinet.PetriNet;
import sk.oop.zadanie3.petrinet.ObjectType;

import java.awt.event.MouseEvent;

public class AddTransitionListener extends NetListener { //Listener pre mod vkladania prechodov

    public AddTransitionListener(NetCanvas myCanvas, PetriNet net) {
        super(myCanvas, net);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int id = net.getNextID(net.getTransitions().keySet(), ObjectType.TRANSITION);
        net.createTransition(id," ");
        Transition2D newTransition = new Transition2D(e.getX()-20,e.getY()-20,net.getTransitions().get(id));
        myCanvas.addElement(myCanvas.getMaxDrawable()+1,newTransition);
    }
}
