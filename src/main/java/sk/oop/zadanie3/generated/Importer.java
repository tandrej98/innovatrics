package sk.oop.zadanie3.generated;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Importer { //sluzi na import suborov

    public sk.oop.zadanie3.generated.Document importDocument(String path) throws JAXBException, FileNotFoundException{
        FileInputStream inputStream = new FileInputStream(path);
        JAXBContext context = JAXBContext.newInstance(Document.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Document)unmarshaller.unmarshal(inputStream);
    }
}
