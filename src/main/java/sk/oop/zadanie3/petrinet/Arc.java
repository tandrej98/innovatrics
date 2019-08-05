package sk.oop.zadanie3.petrinet;

public abstract class Arc { //trieda z ktorej vznikaju obyvcajne a reset hrany
    protected Place place;
    protected Transition transition;
    protected int id;

    public int getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
    }

    protected int multiplicity;

    public Arc(Place place, Transition transition, int id, int multiplicity) {
        this.place = place;
        this.transition = transition;
        this.id = id;
        this.multiplicity = multiplicity;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) { this.place = place; }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public long getId() {
        return id;
    }
}
