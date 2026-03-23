package cz.uhk.graphed.gui;

import cz.uhk.graphed.model.AbstractGraphicObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private List<AbstractGraphicObject> graphicObjects = new ArrayList<>();

    public Canvas() {
        setPreferredSize(new Dimension(800, 600));
    }

    public void add(AbstractGraphicObject graphicObject) {
        graphicObjects.add(graphicObject);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (var o :  graphicObjects) {
            o.draw(g);
        }
    }
}
