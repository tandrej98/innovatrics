package sk.oop.zadanie3.graphics;

import sk.oop.zadanie3.petrinet.Arc;
import sk.oop.zadanie3.petrinet.Node;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public abstract class Arc2D extends Line2D.Float implements Drawable{ //abstraktna trieda z ktorej dedia hrany
    private Arc arc;

    public Arc2D(Arc arc, int x1,  int y1,int x2, int y2){
        super(x1,y1,x2,y2);
        this.arc = arc;
    }

    public abstract Polygon createArrow();//metoda na vytvorenie pozadovanej sipky-kazda trieda si vytvori vlastnu

    @Override
    public void draw(Graphics2D g){//metoda obsahujuca matematiku na vytvorenie a vykreslenie hrany
        g.setColor(Color.BLACK);
        g.draw(this);

        Graphics2D g2 = (Graphics2D) g.create();
        double angle = Math.atan2(y2-y1, x2 - x1);
        AffineTransform tx = g.getTransform();
        tx.translate(getCenterX(), getCenterY());
        tx.rotate(angle - Math.PI / 2d);
        g2.setTransform(tx);

        Polygon arrow = createArrow();

        g2.fill(arrow);
        g2.dispose();
        if (arc.getMultiplicity()>1){
            g.drawString("" + arc.getMultiplicity(), (int) getBounds2D().getCenterX(),(int)getBounds2D().getCenterY()+20);
        }
    }

    @Override
    public void onClick(MouseEvent e){}//len pre dedenie z Drawables

    @Override
    public double getCenterX() {
        return  getBounds2D().getCenterX();
    }

    @Override
    public double getCenterY() {
        return getBounds2D().getCenterY();
    }

    @Override
    public Node getNode() { //len Kvoli dedenie
        return null;
    }

    @Override
    public Arc getArc() {
        return arc;
    }
}
