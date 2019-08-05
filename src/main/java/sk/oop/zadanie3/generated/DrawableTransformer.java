package sk.oop.zadanie3.generated;

import sk.oop.zadanie3.graphics.*;
import sk.oop.zadanie3.petrinet.ObjectType;
import sk.oop.zadanie3.petrinet.PetriNet;

import java.util.LinkedHashMap;
import java.util.Map;

public class DrawableTransformer extends Transformer<Map<Integer,Drawable>> { //sluzi na transformaciu z objektov z XML na objekty na vykreslenie

    private PetriNet net;

    public DrawableTransformer(PetriNet net){
        if (net == null)
            throw new IllegalArgumentException("Subor sa nepodarilo zpracovat:pozadovana siet neexistuje");
        this.net = net;
    }

    @Override
    public Map<Integer,Drawable> transform (Document document){//vygeneruje objekty na vykreselnie, predtym sa hrany generovali prve, teraz posledne- sposobovalo vazne problemy pri vytvarani siete, pridavani nasobnosti hrany
        Map<Integer,Drawable> drawables = new LinkedHashMap<>();
        int id;
        for (Place place : document.getPlace()) {//vygeneruje miesta na vykreslenie
            id = net.getNextID(place.getId(), ObjectType.PLACE);
            Place2D newPlace = new Place2D( place.getX(),place.getY(),net.getPlaces().get(id));
            drawables.put(place.getId(),newPlace);
        }

        for (Transition transition : document.getTransition()) { //vygeneruje prechody na vykreslenie
            id=net.getNextID(transition.getId(),ObjectType.TRANSITION);
            Transition2D newTransition = new Transition2D(transition.getX(),transition.getY(),net.getTransitions().get(id));
            drawables.put(transition.getId(),newTransition);
        }

        for (Arc arc : document.getArc()) { ////vygeneruje hrany
            id = net.getNextID(arc.getId(),ObjectType.ARC);
            if(arc.getType()==ArcType.REGULAR){
                RegularArc2D newArc = new RegularArc2D(
                        net.getArcs().get(id),
                        (int)drawables.get(arc.getSourceId()).getCenterX(),
                        (int)drawables.get(arc.getSourceId()).getCenterY(),
                        (int)drawables.get(arc.getDestinationId()).getCenterX(),
                        (int)drawables.get(arc.getDestinationId()).getCenterY());
                drawables.put(arc.getId(),newArc);
            }
            else if (arc.getType()==ArcType.RESET){
                ResetArc2D newArc = new ResetArc2D(
                        net.getArcs().get(id),
                        (int)drawables.get(arc.getSourceId()).getCenterX(),
                        (int)drawables.get(arc.getSourceId()).getCenterY(),
                        (int)drawables.get(arc.getDestinationId()).getCenterX(),
                        (int)drawables.get(arc.getDestinationId()).getCenterY());
                drawables.put(arc.getId(),newArc);
            }
        }
        return drawables;
    }
}