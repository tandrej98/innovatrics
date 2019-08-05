package sk.oop.zadanie3.graphics.buttons;

import sk.oop.zadanie3.generated.Document;
import sk.oop.zadanie3.generated.Exporter;
import sk.oop.zadanie3.graphics.NetCanvas;
import sk.oop.zadanie3.petrinet.PetriNet;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;


public class ExportButton extends NetButton {
    public ExportButton(String label, NetCanvas netCanvas, PetriNet net) throws HeadlessException { //tlacidlo na export siete do XML
        super(label, netCanvas);
        this.net = net;
        this.addActionListener(this);
    }

    private PetriNet net;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            final JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(null);
            JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
            Exporter exporter = new Exporter();
            Document document = exporter.export(myCanvas.getElements());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(document, new File(fc.getSelectedFile().getAbsolutePath()));
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }
}
