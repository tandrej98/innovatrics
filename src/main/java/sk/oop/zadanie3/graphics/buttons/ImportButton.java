package sk.oop.zadanie3.graphics.buttons;

import sk.oop.zadanie3.generated.Document;
import sk.oop.zadanie3.generated.DrawableTransformer;
import sk.oop.zadanie3.generated.Importer;
import sk.oop.zadanie3.generated.PetriNetTransformer;
import sk.oop.zadanie3.graphics.Drawable;
import sk.oop.zadanie3.graphics.NetCanvas;
import sk.oop.zadanie3.petrinet.PetriNet;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.Map;

public class ImportButton extends NetButton {

    protected PetriNet net;

    public ImportButton(String label, NetCanvas netCanvas, PetriNet net) throws HeadlessException { //tlacidlo na importovanie siete z XML
        super(label, netCanvas);
        this.net = net;
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            final JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);

            Importer importer = new Importer();
            Document document = importer.importDocument(fc.getSelectedFile().getAbsolutePath());

            PetriNetTransformer petriNetTransformer = new PetriNetTransformer();
            this.net.clearPetriNet();
            PetriNet tempNet = petriNetTransformer.transform(document);
            this.net.copyPetriNet(tempNet);

            DrawableTransformer drawableTransformer = new DrawableTransformer(net);
            Map<Integer, Drawable> drawables = drawableTransformer.transform(document);

            this.myCanvas.clearCanvas();
            this.myCanvas.setElements(drawables);
            this.myCanvas.repaint();
        }
        catch(NullPointerException ex){
            System.out.println("Nebolo mozne spustit import: nezvolili ste si subor");
        }
        catch(FileNotFoundException ex ){
            System.out.println("Nebolo mozne spustit import: zadali ste neplatnu cestu");
        }
        catch (JAXBException ex){
            System.out.println("Nebolo mozne spustit import: zvolili ste nespravny druh suboru");
        }
    }
}

