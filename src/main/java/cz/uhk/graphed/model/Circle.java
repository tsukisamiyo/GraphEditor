package cz.uhk.graphed.model;

import java.awt.*;

public class Circle extends AbstractGraphicObject {

    protected int r;
    protected int height;

    public Circle(Point position, Color color, int r) {
        super(color, position);
        this.r = r;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(color);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawOval(position.x, position.y, r * 2, r * 2);
    }

    @Override
    public boolean contains(Point p) {
        return Math.pow(p.x - position.x - r, 2) +
                Math.pow(p.y - position.y - r, 2) <= Math.pow(r, 2);
    }
}
