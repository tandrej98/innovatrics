package sk.oop.zadanie3.listeners;

import sk.oop.zadanie3.exceptions.InvalidStartException;
import sk.oop.zadanie3.exceptions.SameTypeException;
import sk.oop.zadanie3.graphics.NetCanvas;
import sk.oop.zadanie3.graphics.ResetArc2D;
import sk.oop.zadanie3.petrinet.PetriNet;
import sk.oop.zadanie3.petrinet.Place;
import sk.oop.zadanie3.petrinet.ObjectType;

import java.awt.event.MouseEvent;

public class AddResetArcListener extends AddArcListener { //listener na vzstupenie do modu tvorenia reset hran


    public AddResetArcListener(NetCanvas myCanvas, PetriNet net) {
        super(myCanvas, net);
    }

    private void addResetArc(MouseEvent e) throws InvalidStartException,SameTypeException,NullPointerException{
        int id = net.getNextID(net.getArcs().keySet(), ObjectType.ARC);
        if(start==null) {
            start = getClickedObject(e);
            if(!(start.getNode() instanceof Place)){
                start=null;
                end=null;
                throw new InvalidStartException("Reset Hrana sa nevytvorila: pozadovany zaciatocny objekt nie je miesto");
            }
        }
        else if(start != null){
            end= getClickedObject(e);

            if(start.getNode().getClass() == end.getNode().getClass()){
                start=null;
                end=null;
                throw new SameTypeException("Reset hrana sa nevytvorila: musi byt vytvorena z miesta do prechodu");
            }
            else if (start.getNode() instanceof Place){
                net.createResetArc((int)end.getNode().getId(), (int)start.getNode().getId(),1,id);
            }
            myCanvas.addElement(myCanvas.getMaxDrawable()+1,new ResetArc2D(net.getArcs().get(id),(int)start.getCenterX(),(int)start.getCenterY(),(int)end.getCenterX(),(int)end.getCenterY()));
            start = null;
            end=null;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        try{
            addResetArc(e);
        }
        catch (NullPointerException exc){
            System.out.println("Hrana sa nevytvorila: klikli ste mimo objektu");
        }
        catch (InvalidStartException exc1){
            System.out.println(exc1.getMessage());
        }
        catch (SameTypeException exc2){
            System.out.println(exc2.getMessage());
        }

    }
}
