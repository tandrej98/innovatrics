package sk.oop.zadanie3.petrinet;

public class Place extends Node { // trieda reprezentujuca miesto
    private int tokens;
    protected int checkTokens; //sluzi na kontrolu v pripade ak z miesta beru tokeny rozne hrany

    public Place(long id, String name, int tokens) {
        super(id, name);
        this.tokens = tokens;
        checkTokens=tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    @Override
    public int getTokens() {
        return tokens;
    }
}
