package sk.oop.zadanie3.listeners;

import sk.oop.zadanie3.exceptions.InvalidTokenNumberException;
import sk.oop.zadanie3.graphics.Drawable;
import sk.oop.zadanie3.graphics.NetCanvas;
import sk.oop.zadanie3.graphics.Place2D;
import sk.oop.zadanie3.petrinet.PetriNet;
import sk.oop.zadanie3.petrinet.ObjectType;
import sk.oop.zadanie3.petrinet.Place;

import java.awt.event.MouseEvent;

public class AddPlaceListener extends NetListener {

    public AddPlaceListener(NetCanvas myCanvas, PetriNet net) {
        super(myCanvas, net);
    }

    public void addPlace(MouseEvent e){ //metoda na pridanie miesta na pozadovanu poziciu na canvas
        int id = net.getNextID(net.getPlaces().keySet(), ObjectType.PLACE);
        net.createPlace(id," ",1);
        Place2D newPlace = new Place2D(e.getX()-20,e.getY()-20,net.getPlaces().get(id));
        myCanvas.addElement(myCanvas.getMaxDrawable()+1,newPlace);
    }

    public void editTokens(Drawable clicked, MouseEvent e){ //metoda na pridanie/ubratie tokenov po kliknuti
        try{
            Place place =(Place) clicked.getNode();
            if (e.getButton() == MouseEvent.BUTTON1){
                place.setTokens(place.getTokens()+1);
            }
            else if(e.getButton() == MouseEvent.BUTTON3){
                if (place.getTokens()-1>=0){
                    place.setTokens(place.getTokens()-1);
                }
                else{
                    throw new InvalidTokenNumberException("Pocet tokenov nemoze byt mensi ako 0");
                }
            }
        }
        catch (InvalidTokenNumberException ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Drawable clicked=getClickedObject(e);
        if(clicked==null){
            addPlace(e);
        }
        else if(clicked!=null){
            editTokens(clicked,e);
        }
        myCanvas.repaint();
    }
}
