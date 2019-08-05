package sk.oop.zadanie3.graphics;

import sk.oop.zadanie3.listeners.NetListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class NetCanvas extends Canvas implements MouseListener{//trieda canvasu pouziteho na vykreslenie petriho siete

    private Map<Integer,Drawable> elements =  new HashMap<>();

    public NetCanvas() {
        addMouseListener(this);
    }

    public void setElements(Map<Integer,Drawable> elements) { //nastavenie elemento-hlavne pri importe
        this.elements = elements;
    }

    public void addElement(int id,Drawable newElement){ //pridanie noveho elementu
        elements.put(id,newElement);
        this.repaint();
    }

    public void removeElement(Drawable deleted){ //odstraneni elementu
        int key = 0;
        for(Integer element : elements.keySet()){
            if(elements.get(element)==deleted){key = element;}
        }
        elements.remove(key);
        this.repaint();
    }

    public Map<Integer,Drawable> getElements(){return elements;}

    public int getMaxDrawable(){ //metoda vrati najvyssie ID
        int maxKey = 0;
        for(Integer key : elements.keySet()){
            if(key>maxKey){maxKey = key;}
        }
        return  maxKey;
    }

    public void clearCanvas(){ //Vycistenie Canvasu
        elements.clear();
    }

    @Override
    public void paint( Graphics g){
        for (Map.Entry<Integer,Drawable> element : elements.entrySet()){
            element.getValue().draw((Graphics2D)g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){//metoda na vykonanie pozadovanej akcii v objekte na ktory je kliknute, podorbne v prislusnych triedach
        for (Map.Entry<Integer,Drawable> element : elements.entrySet()){
            if (element.getValue().contains(e.getX(),e.getY())){
                element.getValue().onClick(e);
                this.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}

