package sk.oop.zadanie3.graphics;

import sk.oop.zadanie3.petrinet.Arc;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RegularArc2D extends Arc2D implements Drawable{ //trieda pouzita na vykreslenie obycajnej hrany
    private Arc arc;

    public RegularArc2D(Arc arc, int x1, int y1, int x2, int y2){
        super(arc, x1,y1,x2,y2);
        this.arc = arc;
    }

    @Override
    public Polygon createArrow(){ // vytvorenie pozadovanej sipky ktora sa pouzije na vykreslenie
        Polygon arrowHead = new Polygon();
        arrowHead.addPoint(0, 0);
        arrowHead.addPoint(-5, -10);
        arrowHead.addPoint(5, -10);
        return arrowHead;
    }

    @Override
    public void onClick(MouseEvent e){}

    @Override
    public double getCenterX() {
        return  getBounds2D().getCenterX();
    }

    @Override
    public double getCenterY() {
        return getBounds2D().getCenterY();
    }
}