package cz.uhk.graphed.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicGroup extends AbstractGraphicObject {

    List<AbstractGraphicObject> items = new ArrayList<>();

    public GraphicGroup() {
        position = new Point(0, 0);
    }
    @Override
    public void draw(Graphics g) {
        items.forEach(item -> item.draw(g));
    }

    @Override
    public boolean contains(Point p) {
        for (var it : items) {
            if (it.contains(p)) {
                return true;
            }
        }
        return false;
    }
/*
    @Override
    public void setPosition(Point position) {
        int dx = position.x - this.position.x;
        int dy = position.y - this.position.y;
        for (var item : items) {
            Point p = item.getPosition();
            item.setPosition(new Point(p.x + dx, p.y + dy));
        }
        super.setPosition(position);
    }
*/
    public void add(AbstractGraphicObject ago) {
        items.add(ago);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        items.forEach(ago -> ago.move(dx, dy));

    }
}
