package sk.oop.zadanie3.listeners;

import sk.oop.zadanie3.graphics.Arc2D;
import sk.oop.zadanie3.graphics.Drawable;
import sk.oop.zadanie3.graphics.NetCanvas;
import sk.oop.zadanie3.petrinet.Arc;
import sk.oop.zadanie3.petrinet.PetriNet;

import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class DeleteListener extends NetListener {

    public DeleteListener(NetCanvas myCanvas, PetriNet net) {
        super(myCanvas, net);
    }

    public void deleteGraphics(Drawable deleted){
        List<Arc2D> arc2DToDelete= new LinkedList<>();
        for (Map.Entry<Integer,Drawable> element : myCanvas.getElements().entrySet()){
            if(element.getValue() instanceof Arc2D){
                if (deleted.contains(((Arc2D) element.getValue()).x1,((Arc2D) element.getValue()).y1)||
                        deleted.contains(((Arc2D) element.getValue()).x2,((Arc2D) element.getValue()).y2)){
                    arc2DToDelete.add((Arc2D)element.getValue());
                }
            }
        }
        for(Drawable element : arc2DToDelete ){
            myCanvas.removeElement(element);
        }
    }

    private void deleteLogic(Drawable deleted){
        List<Integer> arcToDelete=new LinkedList<>();
        for(Map.Entry<Integer,Arc> element : net.getArcs().entrySet()){
            if(element.getValue().getPlace() == deleted.getNode()||element.getValue().getTransition()==deleted.getNode()){
                arcToDelete.add(element.getKey());
            }
        }
        for(Integer element : arcToDelete ) {
            net.removeArc(element);
        }
        if(deleted instanceof Arc2D){net.removeArc((int)deleted.getArc().getId());}
        else{net.removeObject(deleted.getNode());}
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        try{
            Drawable deleted = getClickedObject(e);
            myCanvas.removeElement(deleted);
            deleteGraphics(deleted);
            deleteLogic(deleted);
            myCanvas.repaint();
        }
        catch (NullPointerException exc){
            System.out.println("Akcia sa nesplnila: Klikli ste mimo objektu");
        }
    }
}
