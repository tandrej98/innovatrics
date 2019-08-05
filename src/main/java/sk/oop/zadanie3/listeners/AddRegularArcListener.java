package sk.oop.zadanie3.listeners;

import sk.oop.zadanie3.exceptions.InvalidArcMultiplicityException;
import sk.oop.zadanie3.exceptions.SameTypeException;
import sk.oop.zadanie3.graphics.NetCanvas;
import sk.oop.zadanie3.graphics.RegularArc2D;
import sk.oop.zadanie3.petrinet.*;

import java.awt.event.MouseEvent;

public class AddRegularArcListener extends AddArcListener { //Listener na pre mod vkladania standardnych hran
    public AddRegularArcListener(NetCanvas myCanvas, PetriNet net) {
        super(myCanvas, net);
    }

    private void addRegularArc(){
        int id = net.getNextID(net.getArcs().keySet(), ObjectType.ARC);
        try{
            if(start.getNode().getClass() == end.getNode().getClass()){
                throw new SameTypeException("Hrana sa nevytvorila: Aj zaciatok aj koniec je objekt rovnakeho typu");
            }
            if (start.getNode() instanceof Place){
                net.createToArc((int)end.getNode().getId(), (int)start.getNode().getId(),1,id);
            }
            else if(start.getNode() instanceof Transition){
                net.createFromArc((int)start.getNode().getId(),(int)end.getNode().getId(),1,id);
            }
            myCanvas.addElement(myCanvas.getMaxDrawable()+1,new RegularArc2D(net.getArcs().get(id),(int)start.getCenterX(),(int)start.getCenterY(),(int)end.getCenterX(),(int)end.getCenterY()));
        }
        catch (SameTypeException exc){
            System.out.println(exc.getMessage());
        }
        catch (NullPointerException exc){
            System.out.println("Hrana sa nevytvorila: Klikli ste mimo objektu");
        }
    }

    private void editMultiplicity(MouseEvent e){
        Arc clickedArc = start.getArc();
        try {
            if (e.getButton() == MouseEvent.BUTTON1) {
                clickedArc.setMultiplicity(start.getArc().getMultiplicity() + 1);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                if (clickedArc.getMultiplicity() - 1 >= 1) {
                    clickedArc.setMultiplicity(clickedArc.getMultiplicity() - 1);
                } else {
                    throw new InvalidArcMultiplicityException("Operacia sa nevykonala: Nasobnost hrany nemoze byt mensia ako 1");
                }
            }
        }
        catch (InvalidArcMultiplicityException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(start==null){
            start = getClickedObject(e);
        }
        else if(start !=null && !(start instanceof RegularArc2D)) {
            end = getClickedObject(e);
            addRegularArc();
            start = null;
            end = null;
        }
        else if(start != null && start instanceof RegularArc2D){
                editMultiplicity(e);
                start=null;
                end=null;
                myCanvas.repaint();
            }
        }
    }

