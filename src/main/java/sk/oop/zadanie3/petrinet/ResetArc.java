package sk.oop.zadanie3.petrinet;

public class ResetArc extends Arc { // trieda reprezentujuca reset hranu
    public ResetArc(Place newplace,Transition newtransition,  int id, int multiplicity){
        super(newplace,newtransition,id,multiplicity);
        transition.addReset(this);
    }
}
