package sk.oop.zadanie3.petrinet;

public abstract class Node { //trieda z ktorej vznikaju vrcholy-miesto, prechod
    protected long id;
    protected String name;

    public Node(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public int getTokens(){//len Kvoli dedenie
        return 0;
    };

}
