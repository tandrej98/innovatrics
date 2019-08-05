package sk.oop.zadanie3.graphics;
import sk.oop.zadanie3.petrinet.Arc;
import sk.oop.zadanie3.petrinet.Node;
import sk.oop.zadanie3.petrinet.Place;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class Place2D extends Ellipse2D.Float implements Drawable{ //trieda obsahujuca objekt na vykreslenie miesta

    private Place place; //odkaz na objekt petri zo siete

    public Place2D(int x, int y, Place place){
        super(x,y,40,40);
        this.place = place;
    }

    @Override
    public Node getNode() {
        return place;
    }

    @Override
    public Arc getArc() {//len Kvoli dedenie
        return null;
    }

    @Override
    public void draw(Graphics2D g){ // trieda na vykreslenie miesta podla momentalneho stavu
        g.setColor(Color.WHITE);
        g.fill(this);
        g.setColor(Color.BLACK);
        g.draw(this);
        if(place.getTokens()>0){g.drawString(String.valueOf(place.getTokens()),(int)getCenterX(),(int)getCenterY());} //vykreslenie tokenov do stredu miesta ak je viac ako 0
    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public double getCenterX(){

        return super.getCenterX();
    }

    @Override
    public double getCenterY(){

        return super.getCenterY();
    }
}






