package sk.oop.zadanie3.generated;

import sk.oop.zadanie3.graphics.*;
import sk.oop.zadanie3.petrinet.FromArc;
import sk.oop.zadanie3.petrinet.ResetArc;
import sk.oop.zadanie3.petrinet.ToArc;

import java.util.Map;

public class Exporter {//sluzi na vytvorenie dokumentu na export

    private Place exportPlace(Drawable element){ //spracuje sa miesto
        Place newPlace = new Place();
        newPlace.setId((int)element.getNode().getId());
        newPlace.setLabel( element.getNode().getName());
        newPlace.setX((int)( element.getCenterX()));
        newPlace.setY((int)(element.getCenterY()));
        newPlace.setTokens(element.getNode().getTokens());
        return newPlace;
    }

    private Transition exportTransition(Drawable element){ //spracuje sa prechod
        Transition newTransition = new Transition();
        newTransition.setId((int)element.getNode().getId());
        newTransition.setLabel(element.getNode().getName());
        newTransition.setX((int)element.getCenterX());
        newTransition.setY((int)element.getCenterY());
        return newTransition;
    }

    private Arc exportArc(Drawable element){ //spracuje sa hrana
        Arc newArc = new Arc();
        newArc.setId((int)element.getArc().getId());
        if(element.getArc() instanceof ToArc){
            newArc.setDestinationId((int)element.getArc().getTransition().getId());
            newArc.setSourceId((int)element.getArc().getPlace().getId());
            newArc.setMultiplicity(element.getArc().getMultiplicity());
            newArc.setType(ArcType.REGULAR);
        }
        else if(element.getArc() instanceof FromArc){
            newArc.setDestinationId((int)element.getArc().getPlace().getId());
            newArc.setSourceId((int)element.getArc().getTransition().getId());
            newArc.setMultiplicity(element.getArc().getMultiplicity());
            newArc.setType(ArcType.REGULAR);
        }
        else if(element.getArc() instanceof ResetArc){
            newArc.setDestinationId((int)element.getArc().getTransition().getId());
            newArc.setSourceId((int)element.getArc().getPlace().getId());
            newArc.setType(ArcType.RESET);
        }
        return newArc;
    }

    public Document export(Map<Integer,Drawable> elements){// prejdu a spracuju sa vsetky vykreslene elementy
        Document document = new Document();
        for (Map.Entry<Integer,Drawable> element : elements.entrySet()){
            if(element.getValue() instanceof Place2D){
                document.getPlace().add(exportPlace(element.getValue()));
            }
            else if(element.getValue() instanceof Transition2D){
                document.getTransition().add(exportTransition(element.getValue()));
            }
            else if(element.getValue() instanceof Arc2D){
                document.getArc().add(exportArc(element.getValue()));
            }
        }
        return document;
    }
}
