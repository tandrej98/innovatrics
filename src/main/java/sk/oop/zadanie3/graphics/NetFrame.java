package sk.oop.zadanie3.graphics;

import sk.oop.zadanie3.graphics.buttons.*;
import sk.oop.zadanie3.listeners.*;
import sk.oop.zadanie3.petrinet.PetriNet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NetFrame extends Frame{ //trieda s canvasom

    private final NetCanvas myCanvas;
    private final PetriNet net;

    public NetFrame() throws HeadlessException { // konstruktor ktory vytvori okno programu
        super("Petrinet Editor");

        net = new PetriNet();
        myCanvas = new NetCanvas();
        myCanvas.setBackground(Color.LIGHT_GRAY);
        myCanvas.setFocusable(false);



        setSize(960,720);

        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e) {
                                  dispose();
                              }
                          } );

        ImportButton importXML = new ImportButton("Import",myCanvas,net);

        ExportButton exportXML = new ExportButton("Export",myCanvas,net);

        ModeButton placeButton = new ModeButton("Place", myCanvas,new AddPlaceListener(myCanvas,net));

        ModeButton transitionButton = new ModeButton("Transition",myCanvas,new AddTransitionListener(myCanvas,net));

        ModeButton regularArcButton = new ModeButton("Regular Arc",myCanvas,new AddRegularArcListener(myCanvas,net));

        ModeButton resetArcButton = new ModeButton("Reset Arc",myCanvas,new AddResetArcListener(myCanvas,net));

        ModeButton fireButton = new ModeButton("Fire",myCanvas,myCanvas);

        ModeButton deleteButton = new ModeButton("Delete",myCanvas,new DeleteListener(myCanvas,net));

        ClearButton clearButton = new ClearButton("Clear",myCanvas,net);

        TestButton printObjects = new TestButton("Test",myCanvas,net);


        Panel menu = new Panel();
        menu.setBackground(Color.DARK_GRAY);
        menu.setLayout(new FlowLayout(FlowLayout.LEFT));


        this.setLayout(new BorderLayout());
        menu.add(importXML);
        menu.add(exportXML);
        menu.add(Box.createHorizontalStrut(10));

        menu.add(fireButton);
        menu.add(deleteButton);
        menu.add(Box.createHorizontalStrut(10));


        menu.add(placeButton);
        menu.add(transitionButton);
        menu.add(regularArcButton);
        menu.add(resetArcButton);
        menu.add(clearButton);
        menu.add(Box.createHorizontalStrut(50));

        //  menu.add(printObjects); //print vsetky elementy do konzoly


        this.add(menu,BorderLayout.NORTH);
        this.add(myCanvas,BorderLayout.CENTER);
        this.requestFocus();
        this.setVisible(true);
    }
}