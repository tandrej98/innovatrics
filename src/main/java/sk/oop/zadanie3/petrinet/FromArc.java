package sk.oop.zadanie3.petrinet;

public class FromArc extends Arc { //trieda reprezentujuca hranu z prechodu


    public FromArc(Place newplace,Transition newtransition,int id,  int multiplicity) {
        super(newplace, newtransition,id, multiplicity);
        transition.addOutgoing(this);
    }
}
