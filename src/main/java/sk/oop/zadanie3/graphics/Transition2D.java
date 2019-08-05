package sk.oop.zadanie3.graphics;

import sk.oop.zadanie3.exceptions.TransitionCannotFireException;
import sk.oop.zadanie3.petrinet.Arc;
import sk.oop.zadanie3.petrinet.Node;
import sk.oop.zadanie3.petrinet.Transition;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Transition2D extends Rectangle2D.Float implements Drawable{ //trieda na vykreslenie miesta

    private  Transition transition;

    public Transition2D(int x, int y, Transition transition){
        super(x,y,40,40);
        this.transition = transition;
    }

    @Override
    public double getCenterX(){

        return super.getCenterX();
    }

    @Override
    public double getCenterY(){

        return super.getCenterY();
    }

    @Override
    public Node getNode() {
        return transition;
    }

    @Override
    public Arc getArc() { //len Kvoli dedenie
        return null;
    }

    @Override
    public void draw(Graphics2D g){ // trieda na vykreslenie prechodu podla momentalneho stavu

        g.setColor(Color.RED);
        if(transition.canFire()){
            g.setColor(Color.GREEN);
            g.fill(this);
            g.setColor(Color.BLACK);
            g.draw(this);
        }
        else{
            g.setColor(Color.WHITE);
            g.fill(this);
            g.setColor(Color.RED);
            g.draw(this);
        }
        g.drawString(transition.getName(),(int)getCenterX()-10,(int)getCenterY()+35);
    }

    @Override
    public void onClick(MouseEvent e){ //trieda na spustenie prechodu ak sa na neho klikne
        try {
            transition.fire();
        }
        catch (TransitionCannotFireException ex){
            System.out.println(ex.getMessage());
        }

    }
}