package sk.oop.zadanie3.petrinet;

import sk.oop.zadanie3.exceptions.TransitionCannotFireException;

import java.util.ArrayList;
import java.util.List;

public class Transition extends Node { //trieda reprezentujuca prechod
    private List<ToArc> incoming = new ArrayList();
    private List<FromArc> outgoing = new ArrayList();
    private List<ResetArc> reset = new ArrayList();
    private Boolean canFire = false;

    public Transition(long id, String name) {
        super(id, name);
    }

    public void addIncoming(ToArc arc){
        incoming.add(arc);
    }

    public void addOutgoing(FromArc arc){
        outgoing.add(arc);
    }

    public void addReset(ResetArc arc) {reset.add(arc);}

    public void removeArc(Arc arc){ //vymaze pozadovanu hranu z prechodu
        incoming.remove(arc);
        outgoing.remove(arc);
        reset.remove(arc);
    }

    public Boolean canFire() {
        for (ToArc element:incoming){//nastavenie kontrolnych tokenov

            element.getPlace().checkTokens = element.getPlace().getTokens();
        }
        for (ToArc element:incoming){//prejde vsetky hrany do prechodu a porovna ich nasobnost s tokenmi na miestach na ich zaciatku
                if( element.getMultiplicity()>element.getPlace().checkTokens){//skontroluje sa kontrolna premenna checkTokens, ak ma miesto menej tokenov ako je potrebne vrati sa vynimka so spravou, ze prechod sa nedal spustit
                    this.canFire = false;
                    return false;
                }
                element.getPlace().checkTokens -= element.getMultiplicity();
        }
        this.canFire = true;
        return true;
    }

    public void fire()throws TransitionCannotFireException{ //naozaj vykona spustenie
        canFire();
        if (canFire){
            for(ToArc element:incoming){//prejdu sa vsetky hrany vchadzajuce do prechodu a odcita sa potrebny pocet tokenov
                int newTokens= element.getPlace().getTokens() - ((ToArc) element).getMultiplicity();//zistia sa tokeny miesta a odcita sa od nich nasobnost hrany
                element.getPlace().setTokens(newTokens);
            }
            for (FromArc element:outgoing){ //prejde vsetky hrany smerujuce z prechodu a do miest na konci prida potrebny pocet tokenov
                int newTokens= element.getPlace().getTokens() + ((FromArc) element).getMultiplicity(); // zistia sa tokeny miesta a pripocita sa nasobnost hrany
                element.getPlace().setTokens(newTokens);
            }
            for (ResetArc element:reset){ //prejdu sa vsetky reset hrany a vynuluju sa v nich tokeny
                element.getPlace().setTokens(0);
            }
        }
        else {
            throw new TransitionCannotFireException("Prechod sa nespustil:Prechod nesplna podmienky na spustenie.");
        }

    }
}
