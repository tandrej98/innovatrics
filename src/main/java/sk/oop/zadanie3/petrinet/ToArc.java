package sk.oop.zadanie3.petrinet;

public class ToArc extends Arc { //trieda reprezentujuca hranu do prechodu

    public ToArc(Place newplace,Transition newtransition,int id,  int multiplicity) {
        super(newplace, newtransition,id,multiplicity);
        transition.addIncoming(this);
    }


}
