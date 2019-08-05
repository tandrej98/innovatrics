package sk.oop.zadanie3.graphics;

import sk.oop.zadanie3.petrinet.Arc;
import sk.oop.zadanie3.petrinet.Node;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface Drawable extends Shape{

    Node getNode(); //nie idealne riesenie

    Arc getArc();

    void draw(Graphics2D g);

    void onClick(MouseEvent e);

    double getCenterX();

    double getCenterY();
}
