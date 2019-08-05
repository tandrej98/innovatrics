package sk.oop.zadanie3;

import sk.oop.zadanie3.graphics.NetFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
       /*try { //vytiahnutie temy pre FileChooser z OS, testnute iba na windows
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        new NetFrame();
    }
}