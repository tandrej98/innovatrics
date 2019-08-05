package sk.oop.zadanie3.petrinet;

import sk.oop.zadanie3.exceptions.*;


import java.util.*;


public class PetriNet {//vytvorenie Prechodu
    private Map<Integer, Transition> transitions = new HashMap<Integer, Transition>();//miesta a prechody maju vlastne ID - moze existovat prechod 1 a miesto 1, prehladne pri pomenovani t1....
    private Map<Integer, Place> places = new HashMap<Integer, Place>(); //tento sposob sam o sebe osetruje vytvorenie hrany z miesta do miesta a opacne
    private Map<Integer, Arc> arcs =new HashMap<>();
    //pre unikatnost pri exporte a komplikovansti pri uprave na jednu mapu s ID sa pre kazdu mapu pouziva vlastne "predpona"
    //Arc:1, Transition:2, Place:3 - pri dosiahnuti 10 sa pocita odznova napriklad prechody 28,29,210,211

    public void createTransition(int id, String name){ //vytvorenie prechodu,vlozenie do mapy prechodov, osetrene vytvorenie s rovankym id
        try{
            if(transitions.get(id)!=null){
                throw new IdAlreadyExistsException("Prechod sa nevytvoril:Prechod s pozadovanym ID uz existuje.");
            }
            else {transitions.put(id, new Transition(id, name));}
        }
        catch (IdAlreadyExistsException exc1) {
            System.out.println(exc1.getMessage());
        }
    }

    public void createPlace(int id, String name, int tokens){//vytvorenie miesta,vlozenie do mapy miest, osetrene vytvorenie s rovankym id
        try {
            if (places.get(id) != null) {
                throw new IdAlreadyExistsException("Miesto sa nevytvorilo:Miesto s pozadovanym ID uz existuje.");
            }
            if(tokens<0){
                throw new InvalidTokenNumberException("Miesto sa nevytvorilo:Miesto musi mat pocet tokenov aspon nula");
            }
            else {
                places.put(id, new Place(id, name, tokens));
            }
        }
        catch (IdAlreadyExistsException|InvalidTokenNumberException exc1) {
            System.out.println(exc1.getMessage());
        }
    }

    public void createToArc(int transition, int place, int multiplicity, int id){ //vytvori hranu do prechodu, osetrene vsetky pozadovane scenare

        try{
            if (multiplicity <= 0){ throw new InvalidArcMultiplicityException("Hrana sa nevytvorila:Hrana nemoze mat nasobnost mensiu ani rovnu nule.");}
            else if (transitions.get(transition)==null){throw new TransitionDoesNotExistException("Hrana sa nevytvorila:Pozadovany prechod neexistuje, mohli ste si pomylit s ID miesta.");}
            else if (places.get(place)==null){throw new PlaceDoesNotExistException("Hrana sa nevytvorila:Pozadovane miesto neexistuje, mohli ste si pomylit s ID prechodu.");}
            else if (arcs.get(id)!=null){throw new IdAlreadyExistsException("Hrana sa nevytvorila ID uz existuje");}
            else{
                ToArc newArc = new ToArc(places.get(place),transitions.get(transition),id,multiplicity);
                arcs.put(id,newArc);
            }
        }
        catch (InvalidArcMultiplicityException | PlaceDoesNotExistException | TransitionDoesNotExistException | IdAlreadyExistsException  exc1){
            System.out.println(exc1.getMessage());
        }
    }

    public void createFromArc(int transition, int place, int multiplicity, int id){ //vytvori hranu z prechodu, osetrene vsetky pozadovane scenare
        try{
            if (multiplicity <= 0){ throw new InvalidArcMultiplicityException("Hrana sa nevytvorila:Hrana nemoze mat nasobnost mensiu ani rovnu nule.");}
            else if (transitions.get(transition)==null){throw new TransitionDoesNotExistException("Hrana sa nevytvorila:Pozadovany prechod neexistuje, mohli ste si pomylit s ID miesta.");}
            else if (places.get(place)==null){throw new PlaceDoesNotExistException("Hrana sa nevytvorila:Pozadovane miesto neexistuje, mohli ste si pomylit s ID prechodu.");}
            else if (arcs.get(id)!=null){throw new IdAlreadyExistsException("Hrana sa nevytvorila ID uz existuje");}
            else{
                FromArc newArc = new FromArc(places.get(place),transitions.get(transition),id,multiplicity);
                arcs.put(id,newArc);
            }
        }
        catch (InvalidArcMultiplicityException | TransitionDoesNotExistException | PlaceDoesNotExistException | IdAlreadyExistsException  exc1){
            System.out.println(exc1.getMessage());
        }
    }

    public void createResetArc(int transition, int place,int multiplicity, int id){ //vytvori reser hranu, osetrene vsetky pozadovane scenare
        try{
            if (transitions.get(transition)==null){throw new TransitionDoesNotExistException("Hrana sa nevytvorila:Pozadovany prechod neexistuje, mohli ste si pomylit s ID miesta.");}
            else if (places.get(place)==null){throw new PlaceDoesNotExistException("Hrana sa nevytvorila:Pozadovane miesto neexistuje, mohli ste si pomylit s ID prechodu.");}
            else if (arcs.get(id)!=null){throw new IdAlreadyExistsException("Hrana sa nevytvorila ID uz existuje");}
            else {
                ResetArc newArc = new ResetArc(places.get(place),transitions.get(transition),id,multiplicity);
                arcs.put(id,newArc);
            }
        }
        catch (TransitionDoesNotExistException | PlaceDoesNotExistException | IdAlreadyExistsException exc1  ) {
            System.out.println(exc1.getMessage());
        }

    }

    public Map<Integer, Place> getPlaces() {
        return places;
    }

    public Map<Integer, Transition> getTransitions() {
        return transitions;
    }

    public Map<Integer, Arc> getArcs() {
        return arcs;
    }

    private int getTypeID(ObjectType type){ //podla typu objektu vrati predponu pozadovanu do ID
        int typeID = -1;
        switch (type){
            case ARC:
                typeID=1;
                break;
            case TRANSITION:
                typeID=2;
                break;
            case PLACE:
                typeID=3;
                break;
        }
        return typeID;
    }

    public int getNextID(Set<Integer> integers, ObjectType type) { //odluci predponu, najde najvacsie ID a spat prida priponu, vrati dalsie ID v pozadovanej mape
        int maxKey = 0;
        for(Integer key : integers){
            key=(Integer.valueOf(String.valueOf(key).substring(1)));
            if(key>maxKey){maxKey = key;}
        }
        maxKey = Integer.valueOf(String.valueOf(getTypeID(type))+String.valueOf(maxKey+1));
        return  maxKey;
    }

    public int getNextID(int id, ObjectType type){//pouziva sa hlavne pri importe z neznameho zdroja, k ID z XML prida priponu pouzivanu

        return Integer.valueOf(String.valueOf(getTypeID(type))+String.valueOf(id));
    }

    public void removeObject(Node deleted){
        Integer key = null;
        try{
            if(deleted instanceof Place){
                for ( Map.Entry<Integer,Place> element : places.entrySet()){
                    if (element.getValue()==deleted){
                        key=element.getKey();
                    }
                }
                places.remove(key);
            }
            else if(deleted instanceof Transition){
                for ( Map.Entry<Integer,Transition> element : transitions.entrySet()){
                    if (element.getValue()==deleted){
                        key=element.getKey();
                    }
                }
                transitions.remove(key);
            }
        }
        catch (NullPointerException exc){
            System.out.println(exc.getMessage());
        }
    }

    public void removeArc(Integer deletedID){ //odstrani hranu z prechodu
        Arc deletedArc = arcs.get(deletedID);
        deletedArc.getTransition().removeArc(deletedArc);
        arcs.remove(deletedID);
    }

    public void clearPetriNet() {//vymaze vsetky elementy v sieti
        places.clear();
        transitions.clear();
        arcs.clear();
    }

    public void copyPetriNet(PetriNet net){//z jednej pretri siete prekpiruje vsetko do druhej
        transitions.putAll(net.getTransitions());
        places.putAll(net.getPlaces());
        arcs.putAll(net.getArcs());
    }
}
