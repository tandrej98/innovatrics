package sk.oop.zadanie3.listeners;

import sk.oop.zadanie3.graphics.Drawable;
import sk.oop.zadanie3.graphics.NetCanvas;
import sk.oop.zadanie3.petrinet.PetriNet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public abstract class NetListener implements MouseListener { //Abstraktna trieda pre listenery
    public NetListener(NetCanvas myCanvas, PetriNet net) {
        this.myCanvas = myCanvas;
        this.net = net;
    }

    protected NetCanvas myCanvas;
    protected PetriNet net;

    public Drawable getClickedObject(MouseEvent e){ //vrati graficky objekt na ktory bolo kliknute alebo objekt pretinajuci rect o velkosti 20x20 od miesta kliknutia - koli hranam
        Drawable clicked = null;
        for (Map.Entry<Integer, Drawable> element : myCanvas.getElements().entrySet()){
            if (element.getValue().contains(e.getX(),e.getY())){
                clicked=element.getValue();
                break;
            }
            else{
                Rectangle2D aproxRectangle = new Rectangle2D.Double(e.getX()-10,e.getY()-10,20,20);
                if(element.getValue().intersects(aproxRectangle)){
                    clicked=element.getValue();
                    break;
                }
            }
        }
        //System.out.println("Klikli ste na:"+clicked); //lahsie zistenie miesta kliknutia
        return clicked;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
