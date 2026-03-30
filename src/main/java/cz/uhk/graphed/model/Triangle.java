package cz.uhk.graphed.model;

import java.awt.*;

public class Triangle extends AbstractGraphicObject {

    protected int a;
    private int cx, cy;

    public Triangle(Point position, Color color, int a) {
        super(color, position);
        this.a = a;
        computeC();
    }

    private void computeC() {
        cx = position.x + Math.round(a / 2f);
        cy = position.y - (int) Math.round(a * Math.sin(Math.toRadians(60)));
    }

    public Triangle() {}

    @Override
    public void draw(Graphics g) {
        computeC();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.black);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawLine(position.x, position.y, position.x + a, position.y);
        g2d.drawLine(position.x, position.y, cx, cy);
        g2d.drawLine(position.x + a, position.y, cx, cy);
    }

    @Override
    public boolean contains(Point p) {
        int dx = (int) Math.round((position.y - p.y) / Math.tan(Math.PI / 3));
        return p.x >= position.x + dx && p.x <= position.x + a - dx && p.y <= position.y && p.y >= cy;
    }
}
