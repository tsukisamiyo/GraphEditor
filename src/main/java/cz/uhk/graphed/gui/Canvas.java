package cz.uhk.graphed.gui;

import cz.uhk.graphed.model.AbstractGraphicObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private List<AbstractGraphicObject> graphicObjects = new ArrayList<>();
    private AbstractGraphicObject abstractGraphicObject;
    private int dx, dy;

    public Canvas() {
        setPreferredSize(new Dimension(800, 600));


        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                abstractGraphicObject = findObjectContaining(e.getPoint());
                if (abstractGraphicObject != null) {
                    dx = e.getX() - abstractGraphicObject.getPosition().x;
                    dy = e.getY() - abstractGraphicObject.getPosition().y;
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (abstractGraphicObject != null) {
                    abstractGraphicObject.setPosition(e.getX() - dx, e.getY() - dy);
                    repaint();
                }
            }
        });
    }

    private AbstractGraphicObject findObjectContaining(Point point) {
        AbstractGraphicObject o = null;
        for (AbstractGraphicObject g : graphicObjects) {
            if (g.contains(point)) {
                o = g;
            }
        }
        return o;
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
