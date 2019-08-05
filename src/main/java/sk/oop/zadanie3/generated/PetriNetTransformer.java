package sk.oop.zadanie3.generated;

import sk.oop.zadanie3.petrinet.ObjectType;
import sk.oop.zadanie3.petrinet.PetriNet;

import static sk.oop.zadanie3.generated.ArcType.REGULAR;
import static sk.oop.zadanie3.generated.ArcType.RESET;

public class PetriNetTransformer extends Transformer<PetriNet> { //z objektov z XML sa vytvoria objekty PetriNet sluziace v back-ende na vytvorenie logiky
    @Override
    public PetriNet transform(Document document) {//na importovane objektyy maju upravene ID aby boli kompatibilne s vytvaranymi objektmi. viac v triede PetriNet
        PetriNet net = new PetriNet();

        for (Place place : document.getPlace()) { //vytvori miesta
            net.createPlace(net.getNextID(place.getId(), ObjectType.PLACE),place.getLabel(),place.getTokens());
        }

        for (Transition transition : document.getTransition()) { //vytvori prechody
            net.createTransition(net.getNextID(transition.getId(),ObjectType.TRANSITION),transition.getLabel());
        }

        for (Arc arc : document.getArc()) { // podla druhu a smeru vytvori hrany
            if(arc.getType()==REGULAR && net.getTransitions().containsKey(net.getNextID(arc.getSourceId(),ObjectType.TRANSITION))){
                net.createFromArc(net.getNextID(arc.getSourceId(),ObjectType.TRANSITION),net.getNextID(arc.getDestinationId(),ObjectType.PLACE),arc.getMultiplicity(),net.getNextID(arc.getId(),ObjectType.ARC));
            }
            else if(arc.getType()==REGULAR && net.getPlaces().containsKey(net.getNextID(arc.getSourceId(),ObjectType.PLACE))){
                net.createToArc(net.getNextID(arc.getDestinationId(),ObjectType.TRANSITION),net.getNextID(arc.getSourceId(),ObjectType.PLACE),arc.getMultiplicity(),net.getNextID(arc.getId(),ObjectType.ARC));
            }
            else if(arc.getType()==RESET){
                net.createResetArc(net.getNextID(arc.getDestinationId(),ObjectType.TRANSITION),net.getNextID(arc.getSourceId(),ObjectType.PLACE),arc.getMultiplicity(),net.getNextID(arc.getId(),ObjectType.ARC));
            }
        }
        return net;
    }
}
